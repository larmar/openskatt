<%
/*  OpenSkatt is used to create electronic forms that could be sent
 *  to swedish tax autorities.
 *  
 *  Copyright (C) Martin Wilderoth  <martin.wilderoth@pomali.se>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body><p>OpenSkatt v0.1 Copyright (C) 2014  Martin Wilderoth</p>
<p>OpenSkatt är öppen källkod och licensierad under GPLv3.</p>
<p>Programmet skapar filer till Inkomstdeklaration 2 som kan skickas in via deras filöverförings tjänst</p>
<p>Som input används en SIE file med korekta SRU koder ifyllda. Filen måste vara av typ 1 eller 2. Rekomenderat format är Typ-2</p>
<p>Blankett INK2R och INK2S genreras och data presenteras. Du kan nu updatera och fylla i data som saknas eller blivit fel.</p>
<p>Filen INFSSRU och BLANKETTER.SRU skapas och kan laddas upp till skattemyndigheten</p>
<p>Deklarationen dvs huvudblanketten fylls direkt i skattemyndigheten</p>

<p><h3>Fil Uppladdning:</h3>
Välj en SIE fil att ladda upp: <br />
<form action="sieupload" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Ladda upp" />
</form>
<p>
    This program comes with ABSOLUTELY NO WARRANTY, <a href="gpl.html" name="GPLv3">for details follow this link</a>.
    This is free software, and you are welcome to redistribute it
    under certain conditions; <a href="gpl.html" name="GPLv3">for details follow this link</a>.
</p>
<p>Openskatt is an opensource project to report tax to swedish tax authorities. I have developed this program as
I didn't find any opensource alternatives to create the files needed to do filetransfers. This product is developed for swedish market and standards,
 so the GUI's are developed in swedish. Most of the comments in code are in english.
</p>
<p>The program is in Java to be running on a tomcat server. The development is done in eclipse on Ubuntu client.</p>
<p>Source code is availble for download here</p>
</body>
</html>