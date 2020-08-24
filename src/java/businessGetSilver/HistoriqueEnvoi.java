/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;

/**
 *
 * @author pb
 */
public class HistoriqueEnvoi {

    private String message;
    private String number;
    private String heure;
    private String nombre;
    private String dateEnvoi;
    private String utilisateur;
    private String status;

    public HistoriqueEnvoi() {
    }



    public HistoriqueEnvoi(String message, String nombre, String dateEnvoi,String utilisateur) {
        this.message = message;
        this.nombre = nombre;
        this.dateEnvoi = dateEnvoi;
        this.utilisateur=utilisateur;
    }

    public HistoriqueEnvoi(String message, String nombre,String utilisateur) {
        this.message = message;
        this.nombre = nombre;
        this.utilisateur=utilisateur;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

 public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    } public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

   



    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    





    public static void main(String []args)
    {
        System.out.println("Bonjour");
    }

}
