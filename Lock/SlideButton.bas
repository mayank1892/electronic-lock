Type=Class
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region Class Attributes 
	#Event: CheckPoint,Unlock
#End Region 

Sub Class_Globals
	Private mWidget As View
	Private mOwner As Object
	Private mEventName As String
End Sub

Public Sub Initialize (Owner As Object, EventName As String)
	mOwner = Owner
	mEventName = EventName
	mWidget = CreateSlideButton
End Sub

Private Sub CreateSlideButton As View
	Dim Context, WrapperObject, Wrapper As JavaObject
	
	Context = Me : Context = Context.RunMethodJO("getBA", Null)
	
	WrapperObject.InitializeNewInstance("com.datasteam.b4a.system.lockscreen.ui.LockScreenSlideButton", Array As Object(Context.GetField("activity")))

	Wrapper.InitializeNewInstance("anywheresoftware.b4a.objects.CompoundButtonWrapper", Null)
	Wrapper.RunMethod("setObject", Array As Object(WrapperObject))
	Wrapper.RunMethod("innerInitialize", Array As Object(Context, "SlideButton", True))
	
	Dim Event As Object = WrapperObject.CreateEvent("com.datasteam.b4a.system.lockscreen.ui.LockScreenSlideButton.SlideButtonListener", "HandleSlide", Null)
	WrapperObject.RunMethod("setSlideButtonListener",  Array As Object(Event))
	
	Return WrapperObject
End Sub

Public Sub DesignerCreateView(Base As Panel, Label As Label, Props As Map)
	Dim jBase As JavaObject = Base
	Dim Parent As Panel = jBase.RunMethod("getParent", Null)
	
	Parent.AddView(mWidget, Base.Left, Base.Top, Base.Width, Base.Height) : Base.RemoveView
	
	mWidget.Tag = Base.Tag
End Sub

Private Sub HandleSlide_Event (MethodName As String, Args() As Object)
	CallSubDelayed(mOwner, mEventName & "_" & MethodName)
End Sub

Public Sub JO As JavaObject
	Dim Obj = mWidget As JavaObject : Return Obj
End Sub

Public Sub GetThreshold As Int
	Return JO.GetField("threshold")
End Sub

Public Sub SetThreshold(Value As Int)
	JO.SetField("threshold", Value)
End Sub
