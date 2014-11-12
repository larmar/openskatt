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
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OutputStream out = response.getOutputStream();
		PrintStream ps  = new PrintStream(out);
		
		HttpSession session = request.getSession();
		Importsie importsie = (Importsie) session.getAttribute("importsie");

//		LinkedList<String> listinfo = new LinkedList<String>(importsie.sruinfo());
//		LinkedList<String> listink2r = new LinkedList<String>(importsie.ink2r());
//		LinkedList<String> listink2s = new LinkedList<String>(importsie.ink2s());
		
		Iterator<String> i;
		
		response.setContentType("text/plain");

		if(request.getParameter("submit").equals("INFO.SRU")) { 
			response.setHeader("Content-Disposition","attachment;filename=INFOSRU");
		
			LinkedList<String> listinfo = new LinkedList<String>(importsie.sruinfo());
			
			i = listinfo.iterator();

			while(i.hasNext()) {
				ps.println(i.next());
			}
	 } else {
			response.setHeader("Content-Disposition","attachment;filename=BLANKETTER.SRU");

			LinkedList<String> listink2r = new LinkedList<String>(importsie.ink2r());
			LinkedList<String> listink2s = new LinkedList<String>(importsie.ink2s());

			
			i = listink2r.iterator();
			while(i.hasNext()) {
				ps.println(i.next());
			}
			
			i = listink2s.iterator();
			while(i.hasNext()) {
				ps.println(i.next());
			}
			ps.println("#FIL_SLUT");
		}	
		
		
		out.close();
		
	}

}
