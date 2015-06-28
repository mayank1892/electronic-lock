Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	Dim Controller As LockScreenController
End Sub

Sub Globals
	Private AppLabel As String = Activity.Title

	Private BtnEnableService As ToggleButton
	Private BtnEnableHomeBlocker As ToggleButton
	Private LbDim As Label
	Private SbDim As SeekBar
	Private CbHideStatusBar As CheckBox
	Private CbImmersive As CheckBox
	Private BtnUninstall As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("swipeLock")
	Activity.Title = AppLabel & ": " & Activity.Title
End Sub

Sub Activity_Resume
	UpdateViews
End Sub

Sub Activity_Pause (UserClosed As Boolean)
End Sub

Sub UpdateViews
	BtnEnableService.Checked = Controller.Enabled
	BtnEnableHomeBlocker.Checked = Controller.HomeBlockerEnabled
	SbDim_ValueChanged (Controller.DimValue * 100, True)
	CbHideStatusBar.Checked = Controller.HideStatusBar
	CbImmersive.Checked = Controller.ImmersiveMode
	BtnUninstall.Enabled = Controller.DeviceAdminActivated
End Sub

Sub BtnEnableService_CheckedChange(Checked As Boolean)
	Controller.Enabled = Checked	
End Sub

Sub BtnEnableHomeBlocker_CheckedChange(Checked As Boolean)
	If Controller.HomeBlockerEnabled <> Checked Then
		If Checked Then
			Msgbox("Please set:" & CRLF & CRLF & """" & AppLabel & " Launcher""" & CRLF & CRLF & "as your preferred launcher in the next dialog.", AppLabel)
		Else
			Msgbox("Please set your preferred launcher.", AppLabel)
		End If

		Controller.HomeBlockerEnabled = Checked
		UpdateViews
	End If
End Sub

Sub BtnUninstall_Click
	If Msgbox2("This action will remove "& AppLabel &" from device administrators and will initiate the uninstall procedure. Are you sure?", AppLabel, "yes", "no", "", Null) = DialogResponse.POSITIVE Then
		Controller.Uninstall(Activity)
	End If 
End Sub

Sub SbDim_ValueChanged (Value As Int, UserChanged As Boolean)
	LbDim.Text = "dim lockscreen at " & Value & "%"
	If UserChanged Then
		Controller.DimValue = Value / 100
	End If 
End Sub

Sub CbImmersive_CheckedChange(Checked As Boolean)
	Controller.ImmersiveMode = Checked	
End Sub

Sub CbHideStatusBar_CheckedChange(Checked As Boolean)
	Controller.HideStatusBar = Checked	
End Sub

Sub BtnPreview_Click
	StartActivity(lockSwipes)
End Sub

