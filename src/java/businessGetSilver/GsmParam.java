/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;

/**
 *
 * @author yosr
 */
public class GsmParam {
    private String codeGsm;
    private String numGsm;
    private Liste liste;
    private String KW1="vide";
    private String KW2="vide";
    private String KW3="vide";
    private String KW4="vide";
    private String KW5="vide";
    private String KW6="vide";
    private String dateAjout;



    public GsmParam(String numGsm, Liste liste, String KW1, String KW2, String KW3, String KW4, String dateAjout) {
        
        this.numGsm = numGsm;
        this.liste = liste;
        this.KW1 = KW1;
        this.KW2 = KW2;
	this.KW3 = KW3;
	this.KW4 = KW4;
        this.dateAjout = dateAjout;
    }

    public GsmParam(String numGsm, Liste liste, String KW1, String KW2, String dateAjout) {
        this.numGsm = numGsm;
        this.liste = liste;
        this.KW1 = KW1;
        this.KW2 = KW2;
        this.dateAjout = dateAjout;
    }

    public GsmParam(String codeGsm, String numGsm, Liste liste, String KW1, String dateAjout) {
        this.codeGsm = codeGsm;
        this.numGsm = numGsm;
        this.liste = liste;
        this.KW1 = KW1;
        this.dateAjout = dateAjout;
    }


    


    public GsmParam(String numGsm, Liste liste, String KW1, String KW2) {

        
        this.numGsm = numGsm;
        this.liste = liste;
        this.KW1 = KW1;
        this.KW2 = KW2;
    }
 public GsmParam(String numGsm, Liste liste, String KW1, String KW2, String KW3, String KW4, String KW5, String KW6) 
 {        
        this.numGsm = numGsm;
        this.liste = liste;
        this.KW1 = KW1;
        this.KW2 = KW2;
        this.KW3 = KW3;
        this.KW4 = KW4;
        this.KW5 = KW5;
        this.KW6 = KW6;
   
 }
    public GsmParam() {
       
    }

    public GsmParam(String numGsm, Liste liste, String KW1) {
        this.numGsm = numGsm;
        this.liste = liste;
        this.KW1 = KW1;
    }

    public GsmParam(String numGsm) {
        this.numGsm=numGsm;
       
    }

   

    


    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Liste getListe() {
        return liste;
    }

    public void setListe(Liste liste) {
        this.liste = liste;
    }

    public String getKW1() {
        return KW1;
    }

    public void setKW1(String KW1) {
        this.KW1 = KW1;
    }

    public String getNumGsm() {
        return numGsm;
    }

    public void setNumGsm(String numGsm) {
        this.numGsm = numGsm;
    }

    public String getKW2() {
        return KW2;
    }

    public void setKW2(String KW2) {
        this.KW2 = KW2;
    }

    public String getKW3() {
        return KW3;
    }

    public void setKW3(String KW3) {
        this.KW3 = KW3;
    }
	public String getKW4() {
        return KW4;
    }

    public void setKW4(String KW4) {
        this.KW4 = KW4;
    }
    	public String getKW5() {
        return KW5;
    }

    public void setKW5(String KW4) {
        this.KW5 = KW5;
    }
    	public String getKW6() {
        return KW6;
    }

    public void setKW6(String KW4) {
        this.KW6 = KW6;
    }
    public String getCodeGsm() {
        return codeGsm;
    }

    public void setCodeGsm(String codeGsm) {
        this.codeGsm = codeGsm;
    }




    public static void main( String[]args)
    {
        System.out.println("bonjour");
    }

}
