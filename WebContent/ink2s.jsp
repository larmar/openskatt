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

<%@page import="se.linserv.ink2.Ink2s"%>
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
        <form  action="Ink2sServlet" method="post" >
        <table>
        <%  Importsie importsie = (Importsie) session.getAttribute("importsie");
        Ink2s ink2s = importsie.getInk2s();
        int summa = 0;
        String readonly = new String(request.getAttribute("readonly")!=null?"readonly":"");
        String saved = new String(request.getAttribute("saved")!=null?"disabled":"");
        String next = new String(request.getAttribute("saved")==null?"disabled":"");

        for( int sru: new int[] { 7650,7750,7651,7652,7653,7751,7764,7752,7753,7754,7654,7668,7655,7667,7665,7755,7656,7756,7657,7658,7757,7758,7659,7660,7759,7666,7765,7661,7760,7761,7662,7663,7762,7763,7664 } ) { 
        summa+=Integer.parseInt(ink2s.getValue(sru)) * ink2s.multiplier(sru);
        %>
        <tr>
        <td><%= ink2s.line(sru)+" "+ink2s.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2s.getValue(sru)+" "+readonly+" />" %></td>
        </tr>
        <% } 
        ink2s.setValue( summa > 0 ? 7670 : 7770 ,String.valueOf(summa));
        %>
        <tr>
        <td><%= ink2s.line(7670)+" "+ink2s.description(7670) %></td>
        <td><%= "<input type=\"text\" name="+7670+" value="+ink2s.getValue(7670)+" readonly />" %></td>
        </tr>
        <tr>
        <td><%= ink2s.line(7770)+" "+ink2s.description(7770) %></td>
        <td><%= "<input type=\"text\" name="+7770+" value="+ink2s.getValue(7770)+" readonly />" %></td>
        </tr>
        </table>
        <h4>Övriga Uppgigter</h4>
        <table>
        <%
        for( int sru: new int[] { 8020,8021,8023,8026,8022 } ) { %>
        <tr>
        <td><%= ink2s.line(sru)+" "+ink2s.description(sru) %></td>
        <td><%= "<input type=\"text\" name="+sru+" value="+ink2s.getValue(sru)+" "+readonly+" />" %></td>
        </tr>
        <% } %>
        </table>
        <h4>Upplysningar om årsredovisningen</h4>
        <table>
        <%
        for( int sru: new int[] { 8040,8041,8044,8045 } ) { 
                String checked=(ink2s.getValue(sru).equals("X") ? "checked":"");
        %>
        <tr>
        <td><%= ink2s.description(sru) %></td>
        
        <td><%= "<input type=\"checkbox\" name="+sru+" "+checked+" value=X "+readonly+" />" %></td>
        </tr>
        <% } %>
        </table>
        <input type="submit" name="submit" value="Spara" <%=saved%>/><input type="submit" name="submit" value="Nästa" <%=next%>/>
        </form>

</body>
</html>