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

public class Period {
	private String period;
	private int date;
	
	public static boolean isBetween(int x, int lower, int upper) {
		  return lower <= x && x <= upper;
		}

	public Period() {
		// TODO Auto-generated constructor stub
	}
	
	public void setDate(int date) { 
		this.date = date;
		if(isBetween(date,20140101,20140431)) period = "2014P1";
		if(isBetween(date,20140501,20140630)) period = "2014P2";
		if(isBetween(date,20140701,20140831)) period = "2014P3";
	}
	
	public String getPeriod() {
		return period;
	}

	public Integer getDate() {
		return date;
	}

}