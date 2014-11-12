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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sruinfo {

	private final String PRODUCT="SRU" ; // Constant allways SRU
	private String Mediaid=""; // Not mandatory String max 250
	private String Datum; // Date Not mandatory
	private String Tid;  // Time Not mandatory
	private final String PROGRAM="Open Skatt v0.1"; // Program not mandatory
	private String Filnamn; // Filname BLANKETTER.SRU
	private String Orgnr; // Orgid 12 char 
	private String Namn=""; // Name max 250 hot blank
	private String Adress=""; // Address for post not Mandatory
	private String Postnr=""; // Zipcode number
	private String Postort=""; // City 250 char not blank
	private String Avdelning=""; // Department 250 char not blank
	private String Kontakt=""; // Not man. string 250
	private String Email=""; // Not mandatory string 250
	private String Telefon=""; // Not man string 15
	private String Fax=""; // Not man string 15

	public Sruinfo() {
		// TODO Auto-generated constructor stub
		Date date = new Date();
		SimpleDateFormat fdate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat ftime = new SimpleDateFormat("hhmmss");
		this.Datum=fdate.format(date);
		this.Tid=ftime.format(date);
		this.Filnamn="BLANKETTER.SRU";
		
	}
	
	public String getProduct() {
		return PRODUCT;
	}
	public void setMediaid(String Mediaid ) {
		// Not mandatory String max 250
		this.Mediaid = Mediaid.length() > 251 ? Mediaid.substring(0, 250) : Mediaid;
	}

	public String getMediaid() {
		return Mediaid;
	}
	
	public String getDatum() {
		return Datum;
	}
	
	public String getTid() {
		return Tid;
	}

	public String getProgram() {
		return PROGRAM;
	}

	public void setFilnamn (String Filnamn) {
		// Max 32 char own restriction 
		this.Filnamn = Filnamn.length() > 33 ? Filnamn.substring(0, 32) : Filnamn;
	}
	
	public String getFilnamn () {
		return Filnamn;
	}
	
	public void setOrgnr (String Orgnr) {
		this.Orgnr = "16" + Orgnr.replaceAll("[^\\d]", "");
	}
	
	public String getOrgnr() {
		return Orgnr;
	}

	public void setNamn (String Namn) {
	    // Name max 250 not blank
		this.Namn = Namn.replaceAll("\\\"", "");
	}

	public String getNamn() {
		return Namn;
	}
	
	public void setSieAdress (String SieAdress) {
		String datavalue[]=SieAdress.split("\" \"");
		this.Kontakt=datavalue[0].replace("\"", "");
		this.Adress=datavalue[1];
		this.Postnr=datavalue[2].replaceAll("[^\\d]", "");
		this.Postort=datavalue[2].replaceAll("[\\d ]", "");
		this.Telefon=datavalue[3].replace("\"", "");
	}

	public void setAdress (String Adress) {
		this.Adress=Adress;
	}
	
	public void setKontakt (String Kontakt) {
		this.Kontakt=Kontakt.replace("\"", "");
	}
	
	public void setPostnr (String Postnr) {
		this.Postnr=Postnr.replaceAll("[^\\d]", "");	
	}
	
	public void setPostort ( String Postort){
		this.Postort=Postort.replaceAll("[\\d ]", "");	
	}
	
	public void setTelefon ( String Telefon ) {
		this.Telefon=Telefon.replace("\"", "");
	}
	
	public String getKontakt() {
		return Kontakt;
	}
	
	public String getAdress(){
		return Adress;
	}
	
	public String getPostnr(){
		return Postnr;
	}
	
	public String getPostort(){
		return Postort;
	}
	
	public String getTelefon(){
		return Telefon;
	}
	
	public void setAvdelning(String Avdelning){
		this.Avdelning=Avdelning;
	}
	
	public String getAvdelning() {
		return Avdelning;
	}

	
	public void setEmail(String Email){
		this.Email=Email;
	}
	
	public String getEmail() {
		return Email;
	}

	
	public void setFax(String Fax){
		this.Fax=Fax;
	}
	
	public String getFax() {
		return Fax;
	}
}
