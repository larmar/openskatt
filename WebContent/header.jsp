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

<%@page import="se.linserv.ink2.Importsie"%>
<%@page import="se.linserv.ink2.Sruinfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> <%
Importsie importsie = (Importsie) session.getAttribute("importsie");
Sruinfo sruinf = importsie.getSruinfo();
String saved = new String(request.getAttribute("saved")!=null?"disabled":"");
String next = new String(request.getAttribute("saved")==null?"disabled":"");
%>
<form name="header" action="HeaderServlet" method="post" >
<!-- 
Organisationsnummer:<input type=text name="ORGNR" value="<%= sruinf.getOrgnr() %>" ><br>
Organisationsnummer:<input type=text name="PRODUKT" value="<%= sruinf.getProduct() %>" ><br>
Organisationsnummer:<input type=text name="SKAPAD" value="<%= sruinf.getDatum() + sruinf.getTid() %>" ><br>
Organisationsnummer:<input type=text name="PROGRAM" value="<%= sruinf.getProgram() %>" ><br>
 -->
Får användas av medieleverantören för ex.vis. numrering av disketter/filer.
Lagras ej i skattedatabasen. Ej obligatorisk.:<input type=text name="MEDIAID" value="<%= sruinf.getMediaid() %>" ><br>
Medieleverantörens namn:<input type=text name="NAMN" value="<%= sruinf.getNamn() %>" ><br>
Medieleverantörens
utdelningsadress.
Ej obligatorisk:<input type=text name="ADRESS" value="<%= sruinf.getAdress() %>" ><br>
Medieleverantörens postnummer:<input type=text name="POSTNR" value="<%= sruinf.getPostnr() %>" ><br>
Medieleveran
törens postort:<input type=text name="POSTORT" value="<%= sruinf.getPostort() %>" ><br>
Kontaktpersonens avdelning
eller liknande. Ej obligatorisk:<input type=text name="AVDELNING" value="<%= sruinf.getAvdelning() %>" ><br>
Kontaktperson. Ej obligatorisk:<input type=text name="KONTAKT" value="<%= sruinf.getKontakt() %>" ><br>
Kontaktpersonens emailadress.
Ej obligatorisk:<input type=text name="EMAIL" value="<%= sruinf.getEmail() %>" ><br>
Kontaktp
ersonens telefonnummer.
Ej obligatorisk:<input type=text name="TELEFON" value="<%= sruinf.getTelefon() %>" ><br>
Kontaktpersonens telefaxnummer.
Ej obligatorisk:<input type=text name="FAX" value="<%= sruinf.getFax() %>" ><br>
<input type="submit" name="submit" value="Spara" <%=saved%>><input type="submit" name="submit" value="Nästa" <%=next%>>
</form>
</body>
</html>