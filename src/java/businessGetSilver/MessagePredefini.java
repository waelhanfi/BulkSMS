/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;

/**
 *
 * @author pb
 */
public class MessagePredefini
{
    private String nom;
    private String message;
    private String dateAjout;
    private String utilisateur;

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public MessagePredefini(String nom, String message, String dateAjout, String utilisateur) {
        this.nom = nom;
        this.message = message;
        this.dateAjout = dateAjout;
        this.utilisateur = utilisateur;
    }


  

    public MessagePredefini(String nom, String message, String utilisateur) {
        this.nom = nom;
        this.message = message;
        this.utilisateur = utilisateur;
    }





    public MessagePredefini(String nom , String message) {
        this.message = message;
        this.nom = nom;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



      public MessagePredefini(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }



    public static void main(String[] args)
    {

    }


}