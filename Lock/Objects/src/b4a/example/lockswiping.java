package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import myk.b4a.BA;
import myk.b4a.BALayout;
import myk.b4a.B4AActivity;
import myk.b4a.ObjectWrapper;
import myk.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import myk.b4a.B4AUncaughtException;
import myk.b4a.debug.*;
import java.lang.ref.WeakReference;

public class lockswiping extends Activity implements B4AActivity{
	public static lockswiping mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.lockswiping");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (lockswiping).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.lockswiping");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        myk.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.lockswiping", processBA, activityBA, _activity, myk.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (lockswiping) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (lockswiping) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return lockswiping.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		myk.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (lockswiping) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        myk.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (lockswiping) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static com.datasteam.b4a.system.lockscreen.LockScreenController _controller = null;
public static String _applabel = "";
public myk.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _btnenableservice = null;
public myk.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _btnenablehomeblocker = null;
public myk.b4a.objects.LabelWrapper _lbdim = null;
public myk.b4a.objects.SeekBarWrapper _sbdim = null;
public myk.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbhidestatusbar = null;
public myk.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper _cbimmersive = null;
public myk.b4a.objects.ButtonWrapper _btnuninstall = null;
public b4a.example.main _main = null;
public b4a.example.book _book = null;
public b4a.example.intro _intro = null;
public b4a.example.signaturecapture _signaturecapture = null;
public b4a.example.siglock _siglock = null;
public b4a.example.lockscreen _lockscreen = null;
public b4a.example.locksliding _locksliding = null;
public b4a.example.lockswipes _lockswipes = null;
public b4a.example.viewmap _viewmap = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 23;BA.debugLine="Activity.LoadLayout(\"swipeLock\")";
mostCurrent._activity.LoadLayout("swipeLock",mostCurrent.activityBA);
 //BA.debugLineNum = 24;BA.debugLine="Activity.Title = AppLabel & \": \" & Activity.Title";
mostCurrent._activity.setTitle((Object)(mostCurrent._applabel+": "+BA.ObjectToString(mostCurrent._activity.getTitle())));
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 28;BA.debugLine="UpdateViews";
_updateviews();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _btnenablehomeblocker_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 47;BA.debugLine="Sub BtnEnableHomeBlocker_CheckedChange(Checked As";
 //BA.debugLineNum = 48;BA.debugLine="If Controller.HomeBlockerEnabled <> Checked Then";
if (_controller.getHomeBlockerEnabled()!=_checked) { 
 //BA.debugLineNum = 49;BA.debugLine="If Checked Then";
if (_checked) { 
 //BA.debugLineNum = 50;BA.debugLine="Msgbox(\"Please set:\" & CRLF & CRLF & \"\"\"\" & App";
anywheresoftware.b4a.keywords.Common.Msgbox("Please set:"+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"\""+mostCurrent._applabel+" Launcher\""+anywheresoftware.b4a.keywords.Common.CRLF+myk.b4a.keywords.Common.CRLF+"as your preferred launcher in the next dialog.",mostCurrent._applabel,mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 52;BA.debugLine="Msgbox(\"Please set your preferred launcher.\", A";
myk.b4a.keywords.Common.Msgbox("Please set your preferred launcher.",mostCurrent._applabel,mostCurrent.activityBA);
 };
 //BA.debugLineNum = 55;BA.debugLine="Controller.HomeBlockerEnabled = Checked";
_controller.setHomeBlockerEnabled(_checked);
 //BA.debugLineNum = 56;BA.debugLine="UpdateViews";
_updateviews();
 };
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _btnenableservice_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub BtnEnableService_CheckedChange(Checked As Bool";
 //BA.debugLineNum = 44;BA.debugLine="Controller.Enabled = Checked";
_controller.Enabled = _checked;
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _btnpreview_click() throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Sub BtnPreview_Click";
 //BA.debugLineNum = 82;BA.debugLine="StartActivity(lockSwipes)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._lockswipes.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _btnuninstall_click() throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub BtnUninstall_Click";
 //BA.debugLineNum = 61;BA.debugLine="If Msgbox2(\"This action will remove \"& AppLabel &";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("This action will remove "+mostCurrent._applabel+" from device administrators and will initiate the uninstall procedure. Are you sure?",mostCurrent._applabel,"yes","no","",(android.graphics.Bitmap)(myk.b4a.keywords.Common.Null),mostCurrent.activityBA)==myk.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 62;BA.debugLine="Controller.Uninstall(Activity)";
_controller.Uninstall(mostCurrent._activity);
 };
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _cbhidestatusbar_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 77;BA.debugLine="Sub CbHideStatusBar_CheckedChange(Checked As Boole";
 //BA.debugLineNum = 78;BA.debugLine="Controller.HideStatusBar = Checked";
_controller.HideStatusBar = _checked;
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static String  _cbimmersive_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Sub CbImmersive_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 74;BA.debugLine="Controller.ImmersiveMode = Checked";
_controller.ImmersiveMode = _checked;
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private AppLabel As String = Activity.Title";
mostCurrent._applabel = BA.ObjectToString(mostCurrent._activity.getTitle());
 //BA.debugLineNum = 13;BA.debugLine="Private BtnEnableService As ToggleButton";
mostCurrent._btnenableservice = new myk.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private BtnEnableHomeBlocker As ToggleButton";
mostCurrent._btnenablehomeblocker = new myk.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private LbDim As Label";
mostCurrent._lbdim = new myk.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private SbDim As SeekBar";
mostCurrent._sbdim = new myk.b4a.objects.SeekBarWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private CbHideStatusBar As CheckBox";
mostCurrent._cbhidestatusbar = new anywheresoftware.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private CbImmersive As CheckBox";
mostCurrent._cbimmersive = new myk.b4a.objects.CompoundButtonWrapper.CheckBoxWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private BtnUninstall As Button";
mostCurrent._btnuninstall = new myk.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim Controller As LockScreenController";
_controller = new com.datasteam.b4a.system.lockscreen.LockScreenController();
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _sbdim_valuechanged(int _value,boolean _userchanged) throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub SbDim_ValueChanged (Value As Int, UserChanged";
 //BA.debugLineNum = 67;BA.debugLine="LbDim.Text = \"dim lockscreen at \" & Value & \"%\"";
mostCurrent._lbdim.setText((Object)("dim lockscreen at "+BA.NumberToString(_value)+"%"));
 //BA.debugLineNum = 68;BA.debugLine="If UserChanged Then";
if (_userchanged) { 
 //BA.debugLineNum = 69;BA.debugLine="Controller.DimValue = Value / 100";
_controller.DimValue = _value/(double)100;
 };
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _updateviews() throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub UpdateViews";
 //BA.debugLineNum = 35;BA.debugLine="BtnEnableService.Checked = Controller.Enabled";
mostCurrent._btnenableservice.setChecked(_controller.Enabled);
 //BA.debugLineNum = 36;BA.debugLine="BtnEnableHomeBlocker.Checked = Controller.HomeBlo";
mostCurrent._btnenablehomeblocker.setChecked(_controller.getHomeBlockerEnabled());
 //BA.debugLineNum = 37;BA.debugLine="SbDim_ValueChanged (Controller.DimValue * 100, Tr";
_sbdim_valuechanged((int) (_controller.DimValue*100),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 38;BA.debugLine="CbHideStatusBar.Checked = Controller.HideStatusBa";
mostCurrent._cbhidestatusbar.setChecked(_controller.HideStatusBar);
 //BA.debugLineNum = 39;BA.debugLine="CbImmersive.Checked = Controller.ImmersiveMode";
mostCurrent._cbimmersive.setChecked(_controller.ImmersiveMode);
 //BA.debugLineNum = 40;BA.debugLine="BtnUninstall.Enabled = Controller.DeviceAdminActi";
mostCurrent._btnuninstall.setEnabled(_controller.DeviceAdminActivated());
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
}
