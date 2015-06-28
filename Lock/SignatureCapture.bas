Type=StaticCode
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Code module
Sub Process_Globals
	Dim px, py As Int
	Type SignatureData (Canvas As Canvas, Panel As Panel, SignatureColor As Int, SignatureWidth As Int)
End Sub

Sub Panel_Touch(SD As SignatureData, x As Int,y As Int, Action As Int)
	If Action = 0 Then 'mouse down constant
		px = x
		py = y
	Else
		SD.Canvas.DrawLine(px, py, x, y, SD.SignatureColor, SD.SignatureWidth)
		SD.Panel.Invalidate
		px = x
		py = y
	End If
End Sub
Sub Clear(SD As SignatureData)
	SD.Canvas.DrawColor(Colors.White)
	SD.Panel.Invalidate
End Sub
Sub Save(SD As SignatureData, Dir As String, Name As String)
	Dim out As OutputStream
	out = File.OpenOutput(Dir, Name, False)
	SD.Canvas.Bitmap.WriteToStream(out, 100, "PNG")
	out.Close
End Sub