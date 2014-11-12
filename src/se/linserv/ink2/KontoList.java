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

import java.util.ArrayList;

public class KontoList {

	ArrayList<Sru> kl = new ArrayList<Sru>();

	public KontoList(){
		
	}

	
	class Sru {

		private int Konto;
		private int Srukod;

		Sru(String Data) {
			String datavalue[]=Data.split(" ");
			Konto=Integer.parseInt(datavalue[0]);
			Srukod=Integer.parseInt(datavalue[1]);
		}
		
		int getSru() {
			return Srukod;
		}
		
		int getKonto() {
			return Konto;
		}
	}

	public void add(String Data) {
		kl.add(new Sru(Data));
	}
	
	public int size() {
		return kl.size();
	}
	
	public Object getSru(int konto){
		for (Sru sru : kl) {
			if (sru.getKonto() == konto){
				return sru.getSru();
			}
		}
		return null;
	}

}
/*
Sru findSrubyKonto(int id){    
    for (Sru sru : srus) {
        if (sru.getId() == id) {
            return sru;
        }
    }
    return null; 
}
*/

