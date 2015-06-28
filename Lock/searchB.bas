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


	Private txtSearch As AutoCompleteEditText
	Private ListView1 As ListView
	Private txtname As EditText
	Private txtPop As EditText
	Private txtDengue As EditText
	Private txtMalaria As EditText
	Private txtMeasles As EditText
	Private btnUpdate As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("searchMap")
   DBload
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub txtSearch_TextChanged (Old As String, New As String)
	viewdb(txtSearch.Text)
End Sub
Sub txtSearch_ItemClick (Value As String)
  'Main.cursor1 = Main.sql1.ExecQuery("SELECT * FROM tblMed")
   'For i = 0 To Main.cursor1.RowCount - 1
   ' Main.cursor1.Position = i
   
   
   
   txtname.Text = Main.cursor1.GetString("bName")
	txtMalaria.Text = Main.cursor1.GetString("bMalaria")
	txtPop.Text = Main.cursor1.GetString("bPopulation")
	txtDengue.Text = Main.cursor1.GetString("bDengue")
	txtMeasles.Text = Main.cursor1.GetString("bMeasles")
	
	'Next
End Sub

Sub viewdb( psearch As String)
	'txtSearchGuest.Initialize("txtSearchGuest")
	Main.cursor1 = Main.sql1.ExecQuery("Select * from tblBarangay WHERE bName like '" & psearch & "%'")
	For i = 0 To Main.cursor1.RowCount - 1
	Main.cursor1.Position = i
	Dim nomi() As String
	
	nomi = Array As String(Main.cursor1.GetString("bName"))
	txtSearch.SetItems(nomi)
	Next
End Sub

Sub btnDisplay_Click
 Main.clicksound.play

	DBload 
End Sub


Sub ListView1_ItemClick (Position As Int, Value As Object)
	Dim idvalue As String
Dim countIt As Int
Dim ID As String
idvalue = Value
countIt = idvalue.IndexOf("-") 'find location of sperator
idvalue = idvalue.SubString2(0,countIt) 'find first part of label text
ID = idvalue
Main.cursor1 = Main.sql1.ExecQuery("SELECT * FROM tblBarangay where ID = '" & ID & "' ")
For i = 0 To Main.cursor1.RowCount - 1
Main.cursor1.Position = i
	txtname.text=Main.cursor1.getString("bName")
	txtPop.text=Main.cursor1.getString("bPopulation")
	txtDengue.text=Main.cursor1.getString("bDengue")
	txtMalaria.text=Main.cursor1.getString("bMalaria")
	txtMeasles.text=Main.cursor1.getString("bMeasles")
	'unlock
'	txtAddress.text=cursor1.getString("Address")
Next
End Sub

Sub DBload

'ListView1.Initialize("ListView1")
ListView1.Clear'need to clear the list
Main.cursor1 = Main.sql1.ExecQuery("SELECT * FROM tblBarangay")
For i = 0 To Main.cursor1.RowCount - 1
Main.cursor1.Position = i
ListView1.AddTwoLines(Main.cursor1.getString("ID") & "-" & "Brgy. Name: " & Main.cursor1.getString("bName") & " " &  "Population: " & Main.cursor1.getString("bPopulation"),  "Dengue: " & Main.cursor1.getString("bDengue") &  " " & "Malaria: " & Main.cursor1.getString("bMalaria") & " " & "Measles: " & Main.cursor1.getString("bMeasles"))
'ListView1.AddSingleLine(cursor1.GetString("tEnglish")& " - " & cursor1.GetString("tTagalog") & " | " & cursor1.GetString("tVisayan") & " | " & cursor1.GetString("tIlonggo"))
ListView1.TwoLinesLayout.Label.TextColor = Colors.red
		ListView1.TwoLinesLayout.Label.TextSize = 16
		ListView1.TwoLinesLayout.SecondLabel.TextColor = Colors.blue
	'	ListView1.TwoLinesAndBitmap.ImageView.Height = 10%x
	'	ListView1.TwoLinesAndBitmap.ImageView.Width = 10%x
		ListView1.TwoLinesLayout.SecondLabel.TextSize = 14
	Next

End Sub

Sub btnUpdate_Click
		 Main.clicksound.play
		
	If txtSearch.Text = "" OR txtname.Text = "" OR txtPop.Text = "" OR txtMalaria.Text = "" OR txtMeasles.text = "" OR txtDengue.Text = "" Then
	  Main.TTS.Speak("You have to enter all fields!", True)
		Msgbox("You have to enter all fields","Missed data field")
	Else
	Dim NewID As Int
	Main.sql1.BeginTransaction
	Try
	NewID = Main.cursor1.GetInt("ID")
	'Main.sql1.ExecNonQuery("INSERT INTO tblDate VALUES('" & NewID & "','" & users.u1 & "','" & txtDate.Text & "')")
	Main.sql1.ExecNonQuery("UPDATE tblBarangay set bName = '" & txtname.text & "', bPopulation ='" & txtPop.text & "', bDengue ='" & txtDengue.text & "', bMalaria ='" & txtMalaria.text & "', bMeasles ='" & txtMeasles.text & "' WHERE ID = " & NewID)
	'tts.Speaking("Successfully updated!")
	Main.TTS.speak("Successfully updated!", True)
	Msgbox ("Successfully updated!", "Updated Record")
	Main.sql1.TransactionSuccessful
	Catch
	Msgbox (LastException.Message,"")
	End Try
	Main.sql1.EndTransaction
	DBload
	 txtname.Text = ""
	txtMalaria.Text = ""
	txtPop.Text = ""
	txtDengue.Text = ""
	txtMeasles.Text = ""


	End If
End Sub