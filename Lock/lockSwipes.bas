Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
		#Extends: com.datasteam.b4a.system.lockscreen.LockScreenActivity
#End Region

Sub Process_Globals
	Dim TmClock As Timer
End Sub

Sub Globals
	Dim SlidingSidebar As ClsSlidingSidebar
	Private LbClock As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Sidebar initialization
	SlidingSidebar.Initialize(Activity, 100%x, 0, 0, 250, 250)
	SlidingSidebar.ContentPanel.LoadLayout("lockscreen2")
	SlidingSidebar.SetOnChangeListeners(Me, "Sidebar_FullyOpen", "", "")
	'Gesture Area
	SlidingSidebar.EnableSwipeGesture(True, 100%x, 1)

	'http://www.basic4ppc.com/android/forum/threads/help-To-make-a-simple-digital-clock.32338/#post-188507
		LbClock.Typeface = Typeface.LoadFromAssets("digi.ttf") : LbClock.TextSize = 120
	TmClock.Initialize("Clock", 1000) : TmClock.Enabled = True : Clock_Tick
End Sub

Sub Activity_Resume
End Sub

Sub Activity_Pause (UserClosed As Boolean)
End Sub

Sub Sidebar_FullyOpen
	Dim Vibrator As PhoneVibrate : Vibrator.Vibrate(100)
	lockSwiping.Controller.Unlock
End Sub
Sub Clock_Tick
	Try 
		DateTime.TimeFormat = "hh:mm"
		LbClock.Text = DateTime.Time(DateTime.Now)
	Catch
		Log(LastException.Message)
	End Try
End Sub
