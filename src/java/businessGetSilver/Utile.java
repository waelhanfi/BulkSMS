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
        //à partir d'ici
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
        //à partir d'ici
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
   
sms = sms.replaceAll("�","A");
sms = sms.replaceAll("�","A");
sms = sms.replaceAll("�","A");
sms = sms.replaceAll("�","A");
sms = sms.replaceAll("�","A");
sms = sms.replaceAll("�","A");
sms = sms.replaceAll("�","A");
sms = sms.replaceAll("�","C");
sms = sms.replaceAll("�","E");
sms = sms.replaceAll("�","E");
sms = sms.replaceAll("�","E");
sms = sms.replaceAll("�","E");
sms = sms.replaceAll("�","I");
sms = sms.replaceAll("�","I");
sms = sms.replaceAll("�","I");
sms = sms.replaceAll("�","I");
sms = sms.replaceAll("�","D");
sms = sms.replaceAll("�","N");
sms = sms.replaceAll("�","O");
sms = sms.replaceAll("�","O");
sms = sms.replaceAll("�","O");
sms = sms.replaceAll("�","O");
sms = sms.replaceAll("�","O");
sms = sms.replaceAll("�","O");
sms = sms.replaceAll("�","p");
sms = sms.replaceAll("�","U");
sms = sms.replaceAll("�","U");
sms = sms.replaceAll("�","U");
sms = sms.replaceAll("�","U");
sms = sms.replaceAll("�","Y");


sms = sms.replaceAll("�","a");
sms = sms.replaceAll("�","a");
sms = sms.replaceAll("�","a");
sms = sms.replaceAll("�","a");
sms = sms.replaceAll("�","a");
sms = sms.replaceAll("�","a");
sms = sms.replaceAll("�","c");
sms = sms.replaceAll("�","e");
sms = sms.replaceAll("�","e");
sms = sms.replaceAll("�","i");
sms = sms.replaceAll("�","i");
sms = sms.replaceAll("�","i");
sms = sms.replaceAll("�","i");
sms = sms.replaceAll("�","o");
sms = sms.replaceAll("�","n");
sms = sms.replaceAll("�","o");
sms = sms.replaceAll("�","o");
sms = sms.replaceAll("�","o");
sms = sms.replaceAll("�","o");
sms = sms.replaceAll("�","o");
sms = sms.replaceAll("�","o");
sms = sms.replaceAll("�","p");
sms = sms.replaceAll("�","u");
sms = sms.replaceAll("�","u");
sms = sms.replaceAll("�","u");
sms = sms.replaceAll("�","u");
sms = sms.replaceAll("�","y");


    return sms;
    }

    public static void main(String[] args) {
        Utile util = new Utile();

        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        Liste liste = new Liste();
        liste.setCodeListe("83");
        String utilisateur = "";
        System.out.println(util.Traitement_Message("�tre �'est"));
        sql.Fermer_Cnn();

    }
}
