/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;

/**
 *
 * @author yosr
 */
public class GsmListe {
    private String codeGsm;
    private String numGsm;
    private Liste liste;
    private String nom;
    private String prenom;
    private String dateAjout;



    public GsmListe(String codeGsm,String numGsm, Liste liste, String nom, String prenom, String dateAjout) {
        this.codeGsm = codeGsm;
        this.numGsm = numGsm;
        this.liste = liste;
        this.nom = nom;
        this.prenom = prenom;
        this.dateAjout = dateAjout;
    }

   
    
    public GsmListe(String numGsm, Liste liste, String nom, String prenom, String dateAjout) {
        this.numGsm = numGsm;
        this.liste = liste;
        this.nom = nom;
        this.prenom = prenom;
        this.dateAjout = dateAjout;
    }

    public GsmListe(String codeGsm, String numGsm, Liste liste, String nom, String dateAjout) {
        this.codeGsm = codeGsm;
        this.numGsm = numGsm;
        this.liste = liste;
        this.nom = nom;
        this.dateAjout = dateAjout;
    }



    


    public GsmListe(String numGsm, Liste liste, String nom, String prenom) {

        
        this.numGsm = numGsm;
        this.liste = liste;
        this.nom = nom;
        this.prenom = prenom;
    }

    public GsmListe() {
       
    }

    public GsmListe(String numGsm, Liste liste, String nom) {
        this.numGsm = numGsm;
        this.liste = liste;
        this.nom = nom;
    }

    public GsmListe(String numGsm) {
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumGsm() {
        return numGsm;
    }

    public void setNumGsm(String numGsm) {
        this.numGsm = numGsm;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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
