package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_slidelock{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[slidelock/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="BtnPreview.Left = 0%x"[slidelock/General script]
views.get("btnpreview").vw.setLeft((int)((0d / 100 * width)));
//BA.debugLineNum = 5;BA.debugLine="BtnPreview.width = 100%x"[slidelock/General script]
views.get("btnpreview").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 7;BA.debugLine="Panel1.Left = 0%x"[slidelock/General script]
views.get("panel1").vw.setLeft((int)((0d / 100 * width)));
//BA.debugLineNum = 8;BA.debugLine="Panel1.width = 100%x"[slidelock/General script]
views.get("panel1").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 10;BA.debugLine="BtnEnableService.Left = 0%x"[slidelock/General script]
views.get("btnenableservice").vw.setLeft((int)((0d / 100 * width)));
//BA.debugLineNum = 11;BA.debugLine="BtnEnableService.width = 100%x"[slidelock/General script]
views.get("btnenableservice").vw.setWidth((int)((100d / 100 * width)));
//BA.debugLineNum = 13;BA.debugLine="BtnEnableHomeBlocker.Left = 0%x"[slidelock/General script]
views.get("btnenablehomeblocker").vw.setLeft((int)((0d / 100 * width)));
//BA.debugLineNum = 14;BA.debugLine="BtnEnableHomeBlocker.width = 100%x"[slidelock/General script]
views.get("btnenablehomeblocker").vw.setWidth((int)((100d / 100 * width)));

}
}