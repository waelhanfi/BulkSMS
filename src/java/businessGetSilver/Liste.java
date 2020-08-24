/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;

/**
 *
 * @author yosr
 */
public class Liste {

    private String codeListe;
    private String nom;
    private String dateCreation;
    private String utilisateur;

    public Liste() {
    }

    public Liste(String codeListe, String nom, String dateCreation,String utilisateur) {
        this.codeListe = codeListe;
        this.nom = nom;
        this.dateCreation = dateCreation;
        this.utilisateur=utilisateur;
    }

    public Liste(String nom) {
        this.nom = nom;
    }

    public Liste(String nom, String utilisateur) {
        this.nom = nom;
        this.utilisateur=utilisateur;
    }

    public String getUtlisateur() {
        return utilisateur;
    }

    public void setUtlisateur(String utlisateur) {
        this.utilisateur = utlisateur;
    }





    public String getCodeListe() {
        return codeListe;
    }

    public void setCodeListe(String codeListe) {
        this.codeListe = codeListe;
    }




    public Liste(String nom, String dateCreation,String utilisateur) {
        this.nom = nom;
        this.dateCreation = dateCreation;
        this.utilisateur=utilisateur;
    }



    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public static void main(String []args)
    {
        Liste list = new Liste();
        System.out.println("bonjour");

    }

}
