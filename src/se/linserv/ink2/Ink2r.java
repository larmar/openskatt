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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Ink2r {

    private Map<Integer,Sru> ink2r = new HashMap<Integer, Sru>();

	public Ink2r() {
		
	}
	
	public void load() throws IOException { 
		// TODO Auto-generated constructor stub

		/**
         * Source file to read data from.
         */
        String dataFileName = "INK2R";
        
        File f = new File(dataFileName);  
      System.out.println(f.getAbsolutePath()); 

                
        /**
         * Creating a buffered reader to read the file with character encoding "UTF8" 
         */
        BufferedReader bReader = new BufferedReader( 
        		new InputStreamReader(new FileInputStream(dataFileName),Charset.forName("UTF8")));

        String line;

        /**
         * Looping the read block until all lines in the file are read.
         */
        while ((line = bReader.readLine()) != null) {
	        
            /**
             * Splitting the content of tab separated line
             */
            String datavalue[] = line.split("\t");
            Sru sru = new Sru();
            sru.setSrukod(datavalue[0]);
            sru.setLine(datavalue[1]);
            sru.setDescription(datavalue[2]);
            sru.setMultiplier(datavalue[3]);
            ink2r.put(sru.getSrukod(),sru);

        }
        
        bReader.close();
   }
	
	public int multiplier(Integer sru) {
		return ink2r.get(sru).getMultiplier();
	}
	
	public String line(Integer sru) {
		return ink2r.get(sru).getLine();
	}
	
	public String description(Integer sru) {
		return ink2r.get(sru).getDescription();
	}
	
	public void setValue(Integer sru, long value) {
		ink2r.get(sru).setValue(Integer.toString((int)value));
	}
	
	public String getValue(Integer sru){
		return ink2r.get(sru).getValue();
	}
	
	public Set<Integer> keySet(){
		SortedSet<Integer> values = new TreeSet<Integer>(ink2r.keySet());
		return values;
	}

}
