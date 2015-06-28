Type=Activity
Version=4.3
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals

End Sub

Sub Globals
	Dim Panel1 As Panel
	Dim Canvas1 As Canvas
	Dim SD As SignatureData 'This object holds the data required for SignatureCapture
	Private Label1 As Label

	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("sigLock")
	Canvas1.Initialize(Panel1)
	
	Panel1.Left = 0%x
	Panel1.Width = 100%x
	
	Label1.Left = 0%x
	Label1.Width = 100%x
	
	SD.Initialize
	SD.Canvas = Canvas1
	SD.Panel = Panel1
	SD.SignatureColor = Colors.Black
	SD.SignatureWidth = 5dip 'Stroke width
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub Panel1_Touch (Action As Int, X As Float, Y As Float)

	SignatureCapture.Panel_Touch(SD, X, Y, Action)

   
		
	

	'Return True
End Sub
Sub btnSave_Click
	SignatureCapture.Save(SD, File.DirRootExternal, "sign.png")
	ToastMessageShow("Signature saved to: " & File.Combine(File.DirRootExternal, "sign.png"), True)
	'The next line will load the image and set it as the activity background.
	
End Sub
Sub btnClear_Click
	SignatureCapture.Clear(SD)
End Sub
'Sub GesturesTouch(View As Object, PointerID As Int, Action As Int, X As Float, Y As Float) As Boolean
'	Dim p As Point
'	Select Action
'		Case g.ACTION_DOWN, g.ACTION_POINTER_DOWN
'			'New Point is assigned to the new touch
'			p.Id = PointerID
'			p.Color = Colors.Blue
'			TouchMap.Put(PointerID, p)
'		Case g.ACTION_POINTER_UP
'			TouchMap.Remove(PointerID)
'		Case g.ACTION_UP
'			'This is the end of this gesture
'			TouchMap.Clear
'	End Select
'	'Clear the previous text
'	Canvas.DrawRect(TextRect, Colors.Transparent, True, 0)
'	Dim px, py As Int
'	For i = 0 To TouchMap.Size - 1
'		p = TouchMap.GetValueAt(i)
'		px = g.GetX(p.id)
'		py = g.GetY(p.id)
'		Dim s As String
'		s = p.Id & ": " & px & " x " & py
'		Canvas.DrawText(s, 10dip, 20dip + i * RowHeight, Typeface.DEFAULT, TextSize, p.Color, "LEFT")
'		If p.prevX > 0 AND p.prevY > 0 Then
'			Canvas.DrawLine(p.prevX, p.prevY, px, py, p.Color, 5dip)
'		End If
'		p.prevX = px
'		p.prevY = py
'	Next
'	bgd.Invalidate
'	Return True
'End Sub

