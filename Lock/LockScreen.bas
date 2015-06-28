Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Activity Attributes 
	#Extends: com.datasteam.b4a.system.lockscreen.LockScreenActivity
#End Region

Sub Process_Globals
	Dim TmClock As Timer
End Sub

Sub Globals
	Dim LbClock As Label
	Private BtnSlide As SlideButton
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("LockScreen1")

	'http://www.basic4ppc.com/android/forum/threads/help-To-make-a-simple-digital-clock.32338/#post-188507
	LbClock.Typeface = Typeface.LoadFromAssets("digi.ttf") : LbClock.TextSize = 120
	TmClock.Initialize("Clock", 1000) : TmClock.Enabled = True : Clock_Tick
	
	BtnSlide.SetThreshold(80)
End Sub

Sub Activity_Resume
End Sub

Sub Activity_Pause (UserClosed As Boolean)
End Sub

Sub Vibrate 
	Dim Vibrator As PhoneVibrate : Vibrator.Vibrate(100)
End Sub

Sub BtnSlide_CheckPoint
	Vibrate
End Sub

Sub BtnSlide_Unlock
	Vibrate
	lockSliding.Controller.Unlock
End Sub

Sub BtnLock_Click
	lockSliding.Controller.Lock
End Sub

Sub Clock_Tick
	Try 
		DateTime.TimeFormat = "hh:mm"
		LbClock.Text = DateTime.Time(DateTime.Now)
	Catch
		Log(LastException.Message)
	End Try
End Sub

