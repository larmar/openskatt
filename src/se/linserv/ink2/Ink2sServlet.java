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
package se.linserv.ink2;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Ink2sServlet
 */
@WebServlet("/Ink2sServlet")
public class Ink2sServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ink2sServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Importsie importsie = (Importsie) session.getAttribute("importsie");
		request.setCharacterEncoding("UTF-8");

		Enumeration<String> srus = request.getParameterNames();
		while(srus.hasMoreElements()) {
			String sru = (String) srus.nextElement();
			try {
				int srui=Integer.parseInt(sru);
				if(srui!=0) {
					importsie.putSruink2(srui, request.getParameter(sru));
				}
			}catch (NumberFormatException e){
				System.out.println("Not a number '"+sru+"' :"+e);
			}
		}
        
        if(request.getParameter("submit").equals("Spara")) {
            request.setAttribute("readonly", "true");
            request.setAttribute("saved", "true");
    		request.getRequestDispatcher("/ink2s.jsp").forward(request, response);	
        } else {
    		request.getRequestDispatcher("/result.jsp").forward(request, response);
        }	
	}

}
