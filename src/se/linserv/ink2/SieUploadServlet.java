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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class SieUploadServlet
 */
@WebServlet("/sieupload")
public class SieUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Importsie importsie = new Importsie();
		if(ServletFileUpload.isMultipartContent(request)){
			InputStreamReader input = new InputStreamReader(request.getInputStream(),Charset.forName("Cp865"));
			BufferedReader bReader = new BufferedReader(input);
			try {
				importsie.load(bReader);
				HttpSession session = request.getSession();
				session.setAttribute("importsie", importsie);
				System.out.println(session.getAttribute("importsie").toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("message", "Uppload file upload request"); 

		 } else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request"); 
		 }
		if(importsie.getSruinfo().getOrgnr()!=null) {
			request.getRequestDispatcher("/header.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
