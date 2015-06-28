Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
   
	Private WebView1 As WebView
	Private btnSearch As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	Activity.Title = "GIS of Tandag"
	Activity.LoadLayout("1")
	WebView1.Left = 0%x
	WebView1.Width = 100%x
	WebView1.Top = 10%y
	WebView1.Height = 90%y
	WebView1.Zoom(False)
     WebView1.ZoomEnabled = True
	
	btnSearch.Left = 0%x
	btnSearch.Width = 100%x
	btnSearch.Top = 0%y
	btnSearch.Height = 10%y
	
	 WebView1.LoadHtml("<html><body><img src='file:///android_asset/normal.jpg' ' width='600' height='700'  alt='Tandag Map'/></body></html>")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub



Sub btnSearch_Click
	StartActivity("searchb")
End Sub