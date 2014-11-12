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

import se.linserv.ink2.Sruinfo;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Importsie {

	private Map<Integer, Integer> konto_sru = new HashMap<Integer, Integer>();
   	private Map<Integer, Double> konto_rr = new HashMap<Integer, Double>();
   	private Map<Integer, Double> sruink2 = new HashMap<Integer, Double>();

   	private Ink2s ink2s = new Ink2s();
	private Ink2r ink2r = new Ink2r();
   	private Sruinfo sruinf = new Sruinfo();
   	private Period period = new Period(); 

	public Importsie()  {
   		
	}
	
	public void load(BufferedReader bReader) throws Exception {
	        
		ink2r.load();
   		    ink2s.load();
	        String line;

	       	
	        /**
	         * Looping the read block until all lines in the file are read.
	         */
	        while ((line = bReader.readLine()) != null) {
	        	
	        	if(line.startsWith("#")) {
	        		
	        	
	            /**
	             * Splitting the content of space separated line
	             */
	            System.out.println(line);

	        	String datavalue[] = line.split(" ",2);
	            switch (datavalue[0]) {
	            	case "#ORGNR": sruinf.setOrgnr(datavalue[1]); break;
	            	case "#FNAMN": sruinf.setNamn(datavalue[1]); break;
	            	case "#ADRESS": sruinf.setSieAdress(datavalue[1]); break;
	            	case "#KTYP":
	            		break;
	            	case "#SRU": 
	            		String dsru[] = datavalue[1].split(" ");
	            		konto_sru.put(Integer.parseInt(dsru[0]), Integer.parseInt(dsru[1]));
	            		break; 
	            	case "#UB":
	            		String dbal[] = datavalue[1].split(" ");
	            		if (dbal[0].equals("0")) {
		            		konto_rr.put(Integer.parseInt(dbal[1]),Double.parseDouble(dbal[2]));	            			
	            		}
	            		break;
	            	case "#RES":
	            		String dres[] = datavalue[1].split(" ");
	            		if (dres[0].equals("0")) {
		            		konto_rr.put(Integer.parseInt(dres[1]),Double.parseDouble(dres[2]));	            			
	            		}
	            		break;
	            	case "#RAR":
	            		// SRU code 7011 och 7012
	            		String drar[] = datavalue[1].split(" ");
	            		if (drar[0].equals("0")) {
		            		sruink2.put(7011, Double.parseDouble(drar[1]));
		            		period.setDate(Integer.parseInt(drar[2]));
		            		sruink2.put(7012, Double.parseDouble(drar[2]));	            			
	            		}
	            		break;
	            }
		        
	        	}
	           }
	        
	        bReader.close();
	        
            System.out.println(konto_sru.size());
            System.out.println(konto_rr.size());
            
            for( int key: konto_rr.keySet()) {
            	System.out.println(konto_sru.get(key)+"::"+konto_rr.get(key));
            	if(sruink2.containsKey(konto_sru.get(key))) {
            		Double old=sruink2.get(konto_sru.get(key));
            		sruink2.put(konto_sru.get(key),konto_rr.get(key)+old);           		
            	} else sruink2.put(konto_sru.get(key), konto_rr.get(key));
            }
           	
            final int konto[][] = {
            		{ 7411, 7510 },
           			{ 7510, 7411 },
           			{ 7414, 7518 },
          			{ 7518, 7414 },
          			{ 7415, 7519 },
          			{ 7519, 7415 },
          			{ 7416, 7520 },
          			{ 7520, 7416 },
            		{ 7526, 7421 },
            		{ 7421, 7526 },
            		{ 7422, 7527 },
            		{ 7527, 7422 }
            };
            	
            for(int line1[]: konto) {
                if((sruink2.get(line1[0])==null ? 0:sruink2.get(line1[0]))*ink2r.multiplier(line1[0])<0) {
                	System.out.println("Byt sru kod från "+line1[0]+" till "+line1[1]);
                	sruink2.put(line1[1], sruink2.get(line1[0]));
                	sruink2.remove(line1[0]);
                }            	
            }
            
            /* 
             * Överför årets resultat till ink2s
             */
            
            sruink2.put(7650,sruink2.get(7450));
            sruink2.put(7750,sruink2.get(7550));
            
            /*
             * Skatt på årets resultat
             */
            
            sruink2.put(7651, sruink2.get(7528));
       }

	public Ink2r getInk2r() {
        for( int k2: ink2r.keySet()) {
        	if(sruink2.get(k2) != null ) {
        		ink2r.setValue(k2, ink2r.multiplier(k2)*Math.round(sruink2.get(k2)));
        	} else ink2r.setValue(k2, 0);
        }
		return ink2r;
	}

	public Ink2s getInk2s() {
        for( int k2: ink2s.keySet()) {
        	if(sruink2.get(k2) != null ) {
        		if(sruink2.get(k2) !=  (double) 0){
        			ink2s.setValue(k2, String.valueOf(Math.round(sruink2.get(k2))));
        		}
        	} else if(ink2s.getValue(k2) == null) {
        		ink2s.setValue(k2, "0");
        	}
        }
		return ink2s;
	}

	public void putSruink2(Integer sru,String value) {
		try {
			Double v = Double.valueOf(value);
			if(v != 0) sruink2.put(sru, v);
		} catch (NumberFormatException e){
			System.out.println("Not a number in '"+sru+"' code :"+e);
			sruink2.put(sru,(double) 0);
		}
		ink2s.setValue(sru, value);
	}

	public LinkedList<String> sruinfo() {
		LinkedList<String> list = new LinkedList<String>();
	    list.add("#DATABESKRIVNING_START");
        list.add("#PRODUKT "+sruinf.getProduct());
        if(sruinf.getMediaid() != "" ) list.add("#MEDIAID "+sruinf.getMediaid());
        list.add("#SKAPAD "+sruinf.getDatum()+" "+sruinf.getTid());
        list.add("#PROGRAM "+sruinf.getProgram());
        if(sruinf.getFilnamn() != "" ) list.add("#FILNAMN "+sruinf.getFilnamn());
        list.add("#DATABESKRIVNING_SLUT");
        list.add("#MEDIELEV_START");
        list.add("#ORGNR "+sruinf.getOrgnr());
        list.add("#NAMN "+sruinf.getNamn());
        if(sruinf.getAdress() != null) list.add("#ADRESS "+sruinf.getAdress());
        list.add("#POSTNR "+sruinf.getPostnr());
        list.add("#POSTORT "+sruinf.getPostort());
        if(sruinf.getAvdelning() != "") list.add("#AVDELING "+sruinf.getAvdelning());
        if(sruinf.getKontakt() != "" ) list.add("#KONTAKT "+sruinf.getKontakt());
        if(sruinf.getEmail() != "" )  list.add("#EMAIL "+sruinf.getEmail());
        if(sruinf.getTelefon() != "" ) list.add("#TELEFON "+sruinf.getTelefon());
        if(sruinf.getFax() != "" ) list.add("#FAX "+sruinf.getFax());
        list.add("#MEDIELEV_SLUT");
		return list;
	}

	public LinkedList<String> ink2r() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("#BLANKETT INK2R-"+period.getPeriod());
		list.add("#IDENTITET "+sruinf.getOrgnr()+" "+sruinf.getDatum()+" "+sruinf.getTid());
		list.add("#NAMN "+sruinf.getNamn());
		list.add("#UPPGIFT 7011 "+Math.round(sruink2.get(7011)));
		list.add("#UPPGIFT 7012 "+Math.round(sruink2.get(7012)));
        for( int k2: ink2r.keySet()) {
        	if(sruink2.get(k2) != null ) {
        		list.add("#UPPGIFT "+k2+" "+ink2r.getValue(k2));
        	}        	
        }
		// list.add("#SYSTEMINFO");
		// #UPPGIFT
		list.add("#BLANKETTSLUT");
		return list;
	}

	public LinkedList<String> ink2s() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("#BLANKETT INK2S-"+period.getPeriod());
		list.add("#IDENTITET "+sruinf.getOrgnr()+" "+sruinf.getDatum()+" "+sruinf.getTid());
		list.add("#NAMN "+sruinf.getNamn());
		list.add("#UPPGIFT 7011 "+Math.round(sruink2.get(7011)));
		list.add("#UPPGIFT 7012 "+Math.round(sruink2.get(7012)));
        for( int k2: ink2s.keySet()) {
        	if(sruink2.get(k2) != null ) {
        		list.add("#UPPGIFT "+k2+" "+ink2s.getValue(k2));
        	}        	
        }
		// list.add("#SYSTEMINFO");
		// #UPPGIFT
		list.add("#BLANKETTSLUT");
		return list;
	}

	
	public Sruinfo getSruinfo() {
		return sruinf;
	}
	
}
