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
<%@page import="se.linserv.ink2.Ink2r"%>
<%@page import="se.linserv.ink2.Importsie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div id="result">
            <h3>${requestScope["message"]}</h3>
        </div>
        <form  action="Ink2rServlet" method="post" >
        <h4>Tillgångar / Anläggningstillgångar</h4>
        <table>
        <%  Importsie importsie = (Importsie) session.getAttribute("importsie");
        Ink2r ink2r = importsie.getInk2r();
        for( int sru: new int[] { 7201,7202,7214,7215,7216,7217,7230,7231,7232,7233,7234,7235 } ) { %>
        <tr>
        <td><%= ink2r.line(sru)+" "+ink2r.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2r.getValue(sru)+" readonly />" %></td>
        </tr>
        <% } %>
        </table>
        <h4>Omsättningstillgångar</h4>
        <table>
        <%
        for( int sru: new int[] { 7241,7242,7243,7244,7245,7246,7251,7252,7261,7262,7263,7270,7271,7281 } ) { %>
        <tr>
        <td><%= ink2r.line(sru)+" "+ink2r.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2r.getValue(sru)+" readonly />" %></td>
        </tr>
        <% } %>
        </table>
        <h4>Eget kapital</h4>
        <table>
        <%
        for( int sru: new int[] { 7301,7302 } ) { %>
        <tr>
        <td><%= ink2r.line(sru)+" "+ink2r.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2r.getValue(sru)+" readonly />" %></td>
        </tr>
        <% } %>
        </table>
        <h4>Obeskattade reserver och avsättningar</h4>
        <table>
        <%
        for( int sru: new int[] { 7321,7322,7323,7331,7332,7333 } ) { %>
        <tr>
        <td><%= ink2r.line(sru)+" "+ink2r.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2r.getValue(sru)+" readonly />" %></td>
        </tr>
        <% } %>
        </table>
        <h4>Skulder</h4>
        <table>
        <%
        for( int sru: new int[] { 7350,7351,7352,7353,7354,7360,7361,7362,7363,7364,7365,7366,7367,7368,7369,7370 } ) { %>
        <tr>
        <td><%= ink2r.line(sru)+" "+ink2r.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2r.getValue(sru)+" readonly />" %></td>
        </tr>
        <% } %>
        </table>
        
        <h4>Resultaträkning</h4>
        <table>
        <%
        for( int sru: new int[] { 7410,7411,7510,7412,7413,7511,7512,7513,7514,7515,7516,7517,7414,7518,7415,7519,7416,7520 } ) { %>
        <tr>
        <td><%= ink2r.line(sru)+" "+ink2r.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2r.getValue(sru)+" readonly />" %></td>
        </tr>
        <% } %>
        </table>
        <h4>Resultaträkning</h4>
        <table>
        <%
        
        for( int sru: new int[] { 7417,7521,7522,7418,7523,7524,7419,7420,7525,7421,7526,7422,7527,7528,7450,7550 } ) { %>
      <tr>
        <td><%= ink2r.line(sru)+" "+ink2r.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2r.getValue(sru)+" readonly"+">" %></td>
        </tr>
        <% } %>
        </table>
        <input type="submit" name="submit" value="Spara" disabled><input type="submit" name="submit" value="Nästa">
        </form>
    </body>
</html>