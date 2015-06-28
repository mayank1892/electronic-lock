package b4a.example;


import myk.b4a.BA;
import myk.b4a.B4AClass;
import myk.b4a.BALayout;
import myk.b4a.debug.*;

public class slidebutton extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.slidebutton");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "b4a.example.slidebutton",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public myk.b4a.keywords.Common __c = null;
public myk.b4a.objects.ConcreteViewWrapper _mwidget = null;
public Object _mowner = null;
public String _meventname = "";
public b4a.example.main _main = null;
public b4a.example.book _book = null;
public b4a.example.intro _intro = null;
public b4a.example.signaturecapture _signaturecapture = null;
public b4a.example.siglock _siglock = null;
public b4a.example.lockscreen _lockscreen = null;
public b4a.example.locksliding _locksliding = null;
public b4a.example.lockswiping _lockswiping = null;
public b4a.example.lockswipes _lockswipes = null;
public b4a.example.viewmap _viewmap = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private mWidget As View";
_mwidget = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private mOwner As Object";
_mowner = new Object();
 //BA.debugLineNum = 8;BA.debugLine="Private mEventName As String";
_meventname = "";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public myk.b4a.objects.ConcreteViewWrapper  _createslidebutton() throws Exception{
myk.b4j.object.JavaObject _context = null;
myk.b4j.object.JavaObject _wrapperobject = null;
myk.b4j.object.JavaObject _wrapper = null;
Object _event = null;
 //BA.debugLineNum = 17;BA.debugLine="Private Sub CreateSlideButton As View";
 //BA.debugLineNum = 18;BA.debugLine="Dim Context, WrapperObject, Wrapper As JavaObject";
_context = new myk.b4j.object.JavaObject();
_wrapperobject = new myk.b4j.object.JavaObject();
_wrapper = new myk.b4j.object.JavaObject();
 //BA.debugLineNum = 20;BA.debugLine="Context = Me : Context = Context.RunMethodJO(\"get";
_context.setObject((java.lang.Object)(this));
 //BA.debugLineNum = 20;BA.debugLine="Context = Me : Context = Context.RunMethodJO(\"get";
_context = _context.RunMethodJO("getBA",(Object[])(__c.Null));
 //BA.debugLineNum = 22;BA.debugLine="WrapperObject.InitializeNewInstance(\"com.datastea";
_wrapperobject.InitializeNewInstance("com.datasteam.b4a.system.lockscreen.ui.LockScreenSlideButton",new Object[]{_context.GetField("activity")});
 //BA.debugLineNum = 24;BA.debugLine="Wrapper.InitializeNewInstance(\"anywheresoftware.b";
_wrapper.InitializeNewInstance("myk.b4a.objects.CompoundButtonWrapper",(Object[])(__c.Null));
 //BA.debugLineNum = 25;BA.debugLine="Wrapper.RunMethod(\"setObject\", Array As Object(Wr";
_wrapper.RunMethod("setObject",new Object[]{(Object)(_wrapperobject.getObject())});
 //BA.debugLineNum = 26;BA.debugLine="Wrapper.RunMethod(\"innerInitialize\", Array As Obj";
_wrapper.RunMethod("innerInitialize",new Object[]{(Object)(_context.getObject()),(Object)("SlideButton"),(Object)(__c.True)});
 //BA.debugLineNum = 28;BA.debugLine="Dim Event As Object = WrapperObject.CreateEvent(\"";
_event = _wrapperobject.CreateEvent(ba,"com.datasteam.b4a.system.lockscreen.ui.LockScreenSlideButton.SlideButtonListener","HandleSlide",__c.Null);
 //BA.debugLineNum = 29;BA.debugLine="WrapperObject.RunMethod(\"setSlideButtonListener\",";
_wrapperobject.RunMethod("setSlideButtonListener",new Object[]{_event});
 //BA.debugLineNum = 31;BA.debugLine="Return WrapperObject";
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_wrapperobject.getObject()));
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return null;
}
public String  _designercreateview(anywheresoftware.b4a.objects.PanelWrapper _base,myk.b4a.objects.LabelWrapper _label,myk.b4a.objects.collections.Map _props) throws Exception{
myk.b4j.object.JavaObject _jbase = null;
myk.b4a.objects.PanelWrapper _parent = null;
 //BA.debugLineNum = 34;BA.debugLine="Public Sub DesignerCreateView(Base As Panel, Label";
 //BA.debugLineNum = 35;BA.debugLine="Dim jBase As JavaObject = Base";
_jbase = new anywheresoftware.b4j.object.JavaObject();
_jbase.setObject((java.lang.Object)(_base.getObject()));
 //BA.debugLineNum = 36;BA.debugLine="Dim Parent As Panel = jBase.RunMethod(\"getParent\"";
_parent = new anywheresoftware.b4a.objects.PanelWrapper();
_parent.setObject((android.view.ViewGroup)(_jbase.RunMethod("getParent",(Object[])(__c.Null))));
 //BA.debugLineNum = 38;BA.debugLine="Parent.AddView(mWidget, Base.Left, Base.Top, Base";
_parent.AddView((android.view.View)(_mwidget.getObject()),_base.getLeft(),_base.getTop(),_base.getWidth(),_base.getHeight());
 //BA.debugLineNum = 38;BA.debugLine="Parent.AddView(mWidget, Base.Left, Base.Top, Base";
_base.RemoveView();
 //BA.debugLineNum = 40;BA.debugLine="mWidget.Tag = Base.Tag";
_mwidget.setTag(_base.getTag());
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public int  _getthreshold() throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Public Sub GetThreshold As Int";
 //BA.debugLineNum = 52;BA.debugLine="Return JO.GetField(\"threshold\")";
if (true) return (int)(BA.ObjectToNumber(_jo().GetField("threshold")));
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return 0;
}
public String  _handleslide_event(String _methodname,Object[] _args) throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Private Sub HandleSlide_Event (MethodName As Strin";
 //BA.debugLineNum = 44;BA.debugLine="CallSubDelayed(mOwner, mEventName & \"_\" & MethodN";
__c.CallSubDelayed(ba,_mowner,_meventname+"_"+_methodname);
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _owner,String _eventname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 11;BA.debugLine="Public Sub Initialize (Owner As Object, EventName";
 //BA.debugLineNum = 12;BA.debugLine="mOwner = Owner";
_mowner = _owner;
 //BA.debugLineNum = 13;BA.debugLine="mEventName = EventName";
_meventname = _eventname;
 //BA.debugLineNum = 14;BA.debugLine="mWidget = CreateSlideButton";
_mwidget = _createslidebutton();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4j.object.JavaObject  _jo() throws Exception{
myk.b4j.object.JavaObject _obj = null;
 //BA.debugLineNum = 47;BA.debugLine="Public Sub JO As JavaObject";
 //BA.debugLineNum = 48;BA.debugLine="Dim Obj = mWidget As JavaObject : Return Obj";
_obj = new anywheresoftware.b4j.object.JavaObject();
_obj.setObject((java.lang.Object)(_mwidget.getObject()));
 //BA.debugLineNum = 48;BA.debugLine="Dim Obj = mWidget As JavaObject : Return Obj";
if (true) return _obj;
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return null;
}
public String  _setthreshold(int _value) throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Public Sub SetThreshold(Value As Int)";
 //BA.debugLineNum = 56;BA.debugLine="JO.SetField(\"threshold\", Value)";
_jo().SetField("threshold",(Object)(_value));
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
