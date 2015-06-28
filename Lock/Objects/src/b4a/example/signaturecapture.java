package b4a.example;


import myk.b4a.BA;
import myk.b4a.BALayout;
import myk.b4a.debug.*;

public class signaturecapture {
private static signaturecapture mostCurrent = new signaturecapture();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static int _px = 0;
public static int _py = 0;
public b4a.example.main _main = null;
public b4a.example.book _book = null;
public b4a.example.intro _intro = null;
public b4a.example.siglock _siglock = null;
public b4a.example.lockscreen _lockscreen = null;
public b4a.example.locksliding _locksliding = null;
public b4a.example.lockswiping _lockswiping = null;
public b4a.example.lockswipes _lockswipes = null;
public b4a.example.viewmap _viewmap = null;
public static class _signaturedata{
public boolean IsInitialized;
public myk.b4a.objects.drawable.CanvasWrapper Canvas;
public myk.b4a.objects.PanelWrapper Panel;
public int SignatureColor;
public int SignatureWidth;
public void Initialize() {
IsInitialized = true;
Canvas = new myk.b4a.objects.drawable.CanvasWrapper();
Panel = new myk.b4a.objects.PanelWrapper();
SignatureColor = 0;
SignatureWidth = 0;
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _clear(anywheresoftware.b4a.BA _ba,b4a.example.signaturecapture._signaturedata _sd) throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Clear(SD As SignatureData)";
 //BA.debugLineNum = 19;BA.debugLine="SD.Canvas.DrawColor(Colors.White)";
_sd.Canvas.DrawColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 20;BA.debugLine="SD.Panel.Invalidate";
_sd.Panel.Invalidate();
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public static String  _panel_touch(myk.b4a.BA _ba,b4a.example.signaturecapture._signaturedata _sd,int _x,int _y,int _action) throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Panel_Touch(SD As SignatureData, x As Int,y As";
 //BA.debugLineNum = 8;BA.debugLine="If Action = 0 Then 'mouse down constant";
if (_action==0) { 
 //BA.debugLineNum = 9;BA.debugLine="px = x";
_px = _x;
 //BA.debugLineNum = 10;BA.debugLine="py = y";
_py = _y;
 }else {
 //BA.debugLineNum = 12;BA.debugLine="SD.Canvas.DrawLine(px, py, x, y, SD.SignatureCol";
_sd.Canvas.DrawLine((float) (_px),(float) (_py),(float) (_x),(float) (_y),_sd.SignatureColor,(float) (_sd.SignatureWidth));
 //BA.debugLineNum = 13;BA.debugLine="SD.Panel.Invalidate";
_sd.Panel.Invalidate();
 //BA.debugLineNum = 14;BA.debugLine="px = x";
_px = _x;
 //BA.debugLineNum = 15;BA.debugLine="py = y";
_py = _y;
 };
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Dim px, py As Int";
_px = 0;
_py = 0;
 //BA.debugLineNum = 4;BA.debugLine="Type SignatureData (Canvas As Canvas, Panel As Pa";
;
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return "";
}
public static String  _save(anywheresoftware.b4a.BA _ba,b4a.example.signaturecapture._signaturedata _sd,String _dir,String _name) throws Exception{
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 22;BA.debugLine="Sub Save(SD As SignatureData, Dir As String, Name";
 //BA.debugLineNum = 23;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 24;BA.debugLine="out = File.OpenOutput(Dir, Name, False)";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(_dir,_name,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 25;BA.debugLine="SD.Canvas.Bitmap.WriteToStream(out, 100, \"PNG\")";
_sd.Canvas.getBitmap().WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
 //BA.debugLineNum = 26;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
}
