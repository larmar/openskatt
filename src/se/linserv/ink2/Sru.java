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

public class Sru {
	
	private int srukod;
	private String line;
	private String description;
	private int multiplier;
	private String value;

	public Sru() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSrukod(String srukod) {
		this.srukod=Integer.parseInt(srukod);
	}
	
	public int getSrukod() {
		return srukod;
	}

	public void setLine(String line) {
		this.line=line;
	}
	
	public String getLine() {
		return line;
	}

	public void setDescription(String description) {
		this.description=description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setMultiplier(String multiplier) {
		this.multiplier=Integer.parseInt(multiplier);
	}
	
	public int getMultiplier(){
		return multiplier;
	}
	
	public void setValue(String value) {
		this.value=value;
	}
	
	public String getValue(){
		return value;
	}
	
}
