/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;


public class Campagne
{
    private String nom;
    private String dateLancement;
    private String liste;
    private String message;
    private String etat;
    private String dateEnvoi;

    private Campagne(){
    }



    public Campagne(String nom, String dateLancement, String liste, String message, String etat, String dateEnvoi) {
        this.nom = nom;
        this.dateLancement = dateLancement;
        this.liste = liste;
        this.message = message;
        this.etat = etat;
        this.dateEnvoi = dateEnvoi;
    }

    public Campagne(String nom, String liste, String message, String etat, String dateEnvoi) {
        this.nom = nom;
        this.liste = liste;
        this.message = message;
        this.etat = etat;
        this.dateEnvoi = dateEnvoi;
    }


    public Campagne(String nom, String liste, String message, String etat) {
        this.nom = nom;
        this.liste = liste;
        this.message = message;
        this.etat = etat;
    }

    public String getDateLancement() {
        return dateLancement;
    }

    public void setDateLancement(String dateLancement) {
        this.dateLancement = dateLancement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getListe() {
        return liste;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public void setListe(String liste) {
        this.liste = liste;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }







    public static void main(String []args)
    {
        Campagne camp= new Campagne();
        System.out.println("bonjour");
    }



}
