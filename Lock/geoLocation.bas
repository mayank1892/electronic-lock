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
Dim MyWebViewExtras As WebViewExtras
	Dim WebView1 As WebView
	Dim WebView2 As WebView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("layoutWeb")
  WebView1.Height=100%y
	WebView1.Width=100%x
	
	'	add the WebChromeClient to WebView1
	'	with version 1.30 of WebViewExtras an EventName is now required when adding the WebChromeClient
	MyWebViewExtras.addWebChromeClient(WebView1, "MyEventName")
	
	WebView1.LoadUrl("http://html5demos.com/geo")
	
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Sub MyEventName_GeolocationPermissionsRequest As Int
	Dim Response As Int
	Response=Msgbox2("Allow the webpage to use device geolocation features?", "Permission required:", "Allow", "", "Disallow", Null)
	If Response=DialogResponse.POSITIVE Then
		ToastMessageShow("Permission granted", True)
		Return MyWebViewExtras.GEOLOCATION_PERMISSION_ALLOW
	Else
		ToastMessageShow("Permission denied", True)
		Return MyWebViewExtras.GEOLOCATION_PERMISSION_DISALLOW
	End If
End Sub
