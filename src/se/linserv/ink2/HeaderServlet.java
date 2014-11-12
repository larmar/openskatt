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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HeaderServlet
 */
@WebServlet("/HeaderServlet")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeaderServlet() {
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
		Sruinfo sruinf = importsie.getSruinfo();
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("submit").equals("Spara")) {
	
		// sruinf.setOrgnr(request.getParameter("ORGNR"));
		sruinf.setMediaid(request.getParameter("MEDIAID"));
		sruinf.setNamn(request.getParameter("NAMN"));
		sruinf.setAdress(request.getParameter("ADRESS"));
		sruinf.setPostnr(request.getParameter("POSTNR"));
		sruinf.setPostort(request.getParameter("POSTORT" ));
		sruinf.setAvdelning(request.getParameter("AVDELNING"));
		sruinf.setEmail(request.getParameter("EMAIL"));
		sruinf.setKontakt(request.getParameter("KONTAKT" ));
		sruinf.setTelefon(request.getParameter("TELEFON" ));
		sruinf.setFax(request.getParameter("FAX"));
 		request.setAttribute("saved", "true");

		request.getRequestDispatcher("/header.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("/ink2r.jsp").forward(request, response);
			
		}
	}

}
