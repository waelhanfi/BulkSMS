/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businessGetSilver;

import daoGetSilver.DaoListe;
import daoGetSilver.Sql;



/**
 *
 * @author pb
 */
public class Utile {

    public void addBlacklist(Sql sql, String num_gsm, String utilisateur) {
        sql.Update("delete from GSM_LISTE where num_gsm like '" + num_gsm + "' and GSM_LISTE.liste in (select code_liste from liste where utilisateur like '" + utilisateur + "')");
        sql.Update("insert into blacklist values (SQ_BLACKLIST.nextval,'" + num_gsm + "','" + utilisateur + "')");
        sql.Commit();
    }

    public void creditAccount(Sql sql, String amount) {
        sql.Update("update credit set solde=solde+" + amount);
        sql.Commit();
    }

    public void debitAccount(Sql sql, String amount, String utilisateur) {
        sql.Update("update utilisateur set credit=credit-" + amount + " where LOGIN  like '" + utilisateur + "'");
        sql.Commit();
    }

    public String getCredit(Sql sql, String utilisateur) {
        return (sql.Get_String_For_This_Query("select credit from utilisateur where LOGIN like '" + utilisateur + "'"));
    }

  public boolean checkCampaign(Sql sql, Liste liste, String utilisateur,String message) {
        DaoListe daoListe = new DaoListe();
        
        String listSize = daoListe.getListSize(sql,liste);
        //Ã  partir d'ici
        int total = Integer.parseInt(listSize);
        if(message.length()>160){ total = total * 2;}
        String credit = getCredit(sql, utilisateur);
        if (Integer.parseInt(credit) >= total) {
        //STOP
            return true;
        } else {
            return false;
        }
    }

  public boolean checkCampaignArab(Sql sql, Liste liste, String utilisateur,String message) {
        DaoListe daoListe = new DaoListe();
        
        String listSize = daoListe.getListSize(sql,liste);
        //Ã  partir d'ici
        int total = Integer.parseInt(listSize);
        if(message.length()>70){ total = total * 2;}
        String credit = getCredit(sql, utilisateur);
        if (Integer.parseInt(credit) >= total) {
        //STOP
            return true;
        } else {
            return false;
        }
    }

public String Traitement_Message(String smsBrut)
    {
    String sms = smsBrut;
   // int i = 0;
   
sms = sms.replaceAll("À","A");
sms = sms.replaceAll("Á","A");
sms = sms.replaceAll("Â","A");
sms = sms.replaceAll("Ã","A");
sms = sms.replaceAll("Ä","A");
sms = sms.replaceAll("Å","A");
sms = sms.replaceAll("Æ","A");
sms = sms.replaceAll("Ç","C");
sms = sms.replaceAll("È","E");
sms = sms.replaceAll("É","E");
sms = sms.replaceAll("Ê","E");
sms = sms.replaceAll("Ë","E");
sms = sms.replaceAll("Ì","I");
sms = sms.replaceAll("Í","I");
sms = sms.replaceAll("Î","I");
sms = sms.replaceAll("Ï","I");
sms = sms.replaceAll("Ð","D");
sms = sms.replaceAll("Ñ","N");
sms = sms.replaceAll("Ò","O");
sms = sms.replaceAll("Ó","O");
sms = sms.replaceAll("Ô","O");
sms = sms.replaceAll("Õ","O");
sms = sms.replaceAll("Ö","O");
sms = sms.replaceAll("Ø","O");
sms = sms.replaceAll("þ","p");
sms = sms.replaceAll("Ù","U");
sms = sms.replaceAll("Ú","U");
sms = sms.replaceAll("Û","U");
sms = sms.replaceAll("Ü","U");
sms = sms.replaceAll("Ý","Y");


sms = sms.replaceAll("á","a");
sms = sms.replaceAll("â","a");
sms = sms.replaceAll("ã","a");
sms = sms.replaceAll("ä","a");
sms = sms.replaceAll("å","a");
sms = sms.replaceAll("æ","a");
sms = sms.replaceAll("ç","c");
sms = sms.replaceAll("ê","e");
sms = sms.replaceAll("ë","e");
sms = sms.replaceAll("ì","i");
sms = sms.replaceAll("í","i");
sms = sms.replaceAll("î","i");
sms = sms.replaceAll("ï","i");
sms = sms.replaceAll("ð","o");
sms = sms.replaceAll("ñ","n");
sms = sms.replaceAll("ò","o");
sms = sms.replaceAll("ó","o");
sms = sms.replaceAll("ô","o");
sms = sms.replaceAll("õ","o");
sms = sms.replaceAll("ö","o");
sms = sms.replaceAll("ø","o");
sms = sms.replaceAll("Þ","p");
sms = sms.replaceAll("ù","u");
sms = sms.replaceAll("ú","u");
sms = sms.replaceAll("û","u");
sms = sms.replaceAll("ü","u");
sms = sms.replaceAll("ý","y");


    return sms;
    }

    public static void main(String[] args) {
        Utile util = new Utile();

        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        Liste liste = new Liste();
        liste.setCodeListe("83");
        String utilisateur = "";
        System.out.println(util.Traitement_Message("être ç'est"));
        sql.Fermer_Cnn();

    }
}
