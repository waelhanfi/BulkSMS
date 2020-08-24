/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package daoGetSilver;


import businessGetSilver.Campagne;
import businessGetSilver.Diffusion;
import businessGetSilver.GsmListe;
import businessGetSilver.GsmParam;
import businessGetSilver.HistoriqueEnvoi;
import businessGetSilver.Liste;
import businessGetSilver.Utile;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DaoCampagne {



    public void saveCampaign(Sql sql,Campagne campagne,Utile util)
    {
       
        sql.Update("insert into  campagne values (SQ_CAMPAGNE.nextval,'" + campagne.getNom().replaceAll("'", "''") + "',sysdate," + campagne.getListe() + ",'" + campagne.getMessage().replaceAll("'", "''") + "','enregistree',to_date('"+
        campagne.getDateEnvoi()+"','dd-mm-yyyy hh24:mi'))");
        sql.Commit();
    
        
       
    }

     public void sendCampaign_enregistrement(Sql sql,Campagne campagne,Utile util,String user,String dd )
    {
        
        DaoGsmListe daogsmliste =new DaoGsmListe();
        Liste L = new Liste();
        Diffusion diffusion = new Diffusion();
       
       // sql.Update("insert into  campagne values (SQ_CAMPAGNE.nextval,'" + campagne.getNom().replaceAll("'", "''") + "',sysdate," + campagne.getListe() + ",'" + campagne.getMessage().replaceAll("'", "''") + "','envoyee',sysdate)");
        L.setCodeListe(campagne.getListe());
      //  System.out.println("Code de al liste : " + L.getCodeListe());
          GsmListe[] listeGsm =  daogsmliste.getDistinctGsmBylist(L);
      //  System.out.println(listeGsm.length);
                diffusion.DiffuserGsmListe_enregistrement(listeGsm, campagne.getMessage(), user.toUpperCase(),dd);
      //  System.out.println("Fin Bulk");
        //Statistiques
		 int total = listeGsm.length;        
        if(campagne.getMessage().length() > 160){ total = total * 2;}
        String nombre=String.valueOf(total);
		
        HistoriqueEnvoi historiqueEnvoi = new HistoriqueEnvoi(campagne.getMessage(),String.valueOf(listeGsm.length),user);
        DaoHistoriqueEnvoi daoHistoriqueEnvoi  = new DaoHistoriqueEnvoi();
      //   System.out.println("ici1");
        daoHistoriqueEnvoi.addHistory(sql, historiqueEnvoi);
      //   System.out.println("ici2");
        // mise à jour CREDIT
       util.debitAccount(sql, nombre,user); 
        sql.Commit();
        
    }
        public void sendCampaign_enregistrementArab(Sql sql,Campagne campagne,Utile util,String user,String dd )
    {
        
        DaoGsmListe daogsmliste =new DaoGsmListe();
        Liste L = new Liste();
        Diffusion diffusion = new Diffusion();
       
       // sql.Update("insert into  campagne values (SQ_CAMPAGNE.nextval,'" + campagne.getNom().replaceAll("'", "''") + "',sysdate," + campagne.getListe() + ",'" + campagne.getMessage().replaceAll("'", "''") + "','envoyee',sysdate)");
        L.setCodeListe(campagne.getListe());
      //  System.out.println("Code de al liste : " + L.getCodeListe());
          GsmListe[] listeGsm =  daogsmliste.getDistinctGsmBylist(L);
      //  System.out.println(listeGsm.length);
                diffusion.DiffuserGsmListe_enregistrement(listeGsm, campagne.getMessage(), user.toUpperCase(),dd);
      //  System.out.println("Fin Bulk");
        //Statistiques
		 int total = listeGsm.length;        
        if(campagne.getMessage().length() > 70){ total = total * 2;}
        String nombre=String.valueOf(total);
		
        HistoriqueEnvoi historiqueEnvoi = new HistoriqueEnvoi(campagne.getMessage(),String.valueOf(listeGsm.length),user);
        DaoHistoriqueEnvoi daoHistoriqueEnvoi  = new DaoHistoriqueEnvoi();
      //   System.out.println("ici1");
        daoHistoriqueEnvoi.addHistory(sql, historiqueEnvoi);
      //   System.out.println("ici2");
        // mise à jour CREDIT
       util.debitAccount(sql, nombre,user); 
        sql.Commit();
        
    }

public void sendCampaign_enregistrement_param(Sql sql,Campagne campagne,Utile util,String user,String dd )
    {
        
        DaoGsmListe daogsmliste =new DaoGsmListe();
        Liste L = new Liste();
        Diffusion diffusion = new Diffusion();
        //sql.Update("insert into  campagne values (SQ_CAMPAGNE.nextval,'" + campagne.getNom().replaceAll("'", "''") + "',to_char("+dd+",'dd-mm-yyyy')," + campagne.getListe() + ",'" + campagne.getMessage().replaceAll("'", "''") + "','Enregistree',sysdate)");

       // sql.Update("insert into  campagne values (SQ_CAMPAGNE.nextval,'" + campagne.getNom().replaceAll("'", "''") + "',sysdate," + campagne.getListe() + ",'" + campagne.getMessage().replaceAll("'", "''") + "','envoyee',sysdate)");
        L.setCodeListe(campagne.getListe());
         GsmParam[] listeGsm =  daogsmliste.getDistinctGsmBylistpm(L);
      //  System.out.println("Code de al liste : " + L.getCodeListe());
         diffusion.DiffuserGsmListe_enregistrementparam(listeGsm, campagne.getMessage(), user.toUpperCase(),dd);
      //  System.out.println(listeGsm.length);
             //   diffusion.DiffuserGsmListe_enregistrement(listeGsm, campagne.getMessage(), user.toUpperCase(),dd);
      //  System.out.println("Fin Bulk");
        //Statistiques
         int total = listeGsm.length;        
        if(campagne.getMessage().length() > 160){ total = total * 2;}
        String nombre=String.valueOf(total);
        HistoriqueEnvoi historiqueEnvoi = new HistoriqueEnvoi(campagne.getMessage(),String.valueOf(listeGsm.length),user);
        DaoHistoriqueEnvoi daoHistoriqueEnvoi  = new DaoHistoriqueEnvoi();
      //   System.out.println("ici1");
        daoHistoriqueEnvoi.addHistory(sql, historiqueEnvoi);
      //   System.out.println("ici2");
        // mise à jour CREDIT
        util.debitAccount(sql,nombre,user);        
        sql.Commit();
        
    }
 public void sendCampaign(Sql sql,Campagne campagne,Utile util,String user)
    {
        
        DaoGsmListe daogsmliste =new DaoGsmListe();
        Liste L = new Liste();
        Diffusion diffusion = new Diffusion();
        
        sql.Update("insert into  campagne values (SQ_CAMPAGNE.nextval,'" + campagne.getNom().replaceAll("'", "''") + "',sysdate," + campagne.getListe() + ",'" + campagne.getMessage().replaceAll("'", "''") + "','envoyee',sysdate)");
        L.setCodeListe(campagne.getListe());
       // System.out.println("Code de al liste : " + L.getCodeListe());
        GsmListe[] listeGsm =  daogsmliste.getDistinctGsmBylist(L);
        //System.out.println(listeGsm.length);
        diffusion.DiffuserGsmListe(listeGsm, campagne.getMessage(), user.toUpperCase());
        //System.out.println("Fin Bulk");
        //Statistiques
		int total = listeGsm.length;        
        if(campagne.getMessage().length() > 160){ total = total * 2;}
        String nombre=String.valueOf(total);
		
        HistoriqueEnvoi historiqueEnvoi = new HistoriqueEnvoi(campagne.getMessage(),String.valueOf(listeGsm.length),user);
        DaoHistoriqueEnvoi daoHistoriqueEnvoi  = new DaoHistoriqueEnvoi();
        daoHistoriqueEnvoi.addHistory(sql, historiqueEnvoi);
        // mise à jour CREDIT
         util.debitAccount(sql, nombre,user);       
        sql.Commit();
        
    }

    public void sendCampaignArab(Sql sql,Campagne campagne,Utile util,String user)
    {
        
        DaoGsmListe daogsmliste =new DaoGsmListe();
        Liste L = new Liste();
        Diffusion diffusion = new Diffusion();
        
        sql.Update("insert into  campagne values (SQ_CAMPAGNE.nextval,'" + campagne.getNom().replaceAll("'", "''") + "',sysdate," + campagne.getListe() + ",'" + campagne.getMessage().replaceAll("'", "''") + "','envoyee',sysdate)");
        L.setCodeListe(campagne.getListe());
       // System.out.println("Code de al liste : " + L.getCodeListe());
        GsmListe[] listeGsm =  daogsmliste.getDistinctGsmBylist(L);
        //System.out.println(listeGsm.length);
        diffusion.DiffuserGsmListe(listeGsm, campagne.getMessage(), user.toUpperCase());
        //System.out.println("Fin Bulk");
        //Statistiques
		int total = listeGsm.length;        
        if(campagne.getMessage().length() > 70){ total = total * 2;}
        String nombre=String.valueOf(total);
		
        HistoriqueEnvoi historiqueEnvoi = new HistoriqueEnvoi(campagne.getMessage(),String.valueOf(listeGsm.length),user);
        DaoHistoriqueEnvoi daoHistoriqueEnvoi  = new DaoHistoriqueEnvoi();
        daoHistoriqueEnvoi.addHistory(sql, historiqueEnvoi);
        // mise à jour CREDIT
         util.debitAccount(sql, nombre,user);       
        sql.Commit();
        
    }
public void sendCampaignparam(Sql sql,Campagne campagne,Utile util,String user)
    {
        
        DaoGsmListe daogsmliste =new DaoGsmListe();
        Liste L = new Liste();
        Diffusion diffusion = new Diffusion();
        
        sql.Update("insert into  campagne values (SQ_CAMPAGNE.nextval,'" + campagne.getNom().replaceAll("'", "''") + "',sysdate," + campagne.getListe() + ",'" + campagne.getMessage().replaceAll("'", "''") + "','envoyee',sysdate)");
        L.setCodeListe(campagne.getListe());
       // System.out.println("Code de al liste : " + L.getCodeListe());
        GsmParam[] listeGsm =  daogsmliste.getDistinctGsmBylistpm(L);
       // System.out.println(" cic "+listeGsm.length);
        diffusion.DiffuserGsmListeParam(listeGsm, campagne.getMessage(), user.toUpperCase());
        //System.out.println("Fin Bulk");
        //Statistiques
        int total = listeGsm.length;        
        if(campagne.getMessage().length() > 160){ total = total * 2;}
        String nombre=String.valueOf(total);
        HistoriqueEnvoi historiqueEnvoi = new HistoriqueEnvoi(campagne.getMessage(),String.valueOf(listeGsm.length),user);
        DaoHistoriqueEnvoi daoHistoriqueEnvoi  = new DaoHistoriqueEnvoi();
        daoHistoriqueEnvoi.addHistory(sql, historiqueEnvoi);
        // mise à jour CREDIT
        util.debitAccount(sql, nombre,user);        
        sql.Commit();
        
    }

    public  List<Campagne> getAllRegistred(Sql sql,String utilisateur)
 {
        List<Campagne> campagnes = new ArrayList<Campagne>();
        try {
            Statement st = sql.Get_Connection().createStatement();
            DaoListe daoListe =new DaoListe();
            ResultSet rs = st.executeQuery("select NOM, to_char(date_lancement,'dd/mm/yyyy'), LISTE, MESSAGE,to_char(DATE_ENVOI,'dd/mm/yy hh24:mi'),etat from  campagne ");//where liste in (select code_liste from liste where utilisateur like '"+utilisateur+"')"
            while (rs.next()) {
            Campagne campagneliste = new Campagne(rs.getString(1),rs.getString(2),daoListe.getListebyId(sql,rs.getString(3)).getNom(),rs.getString(4).replaceAll("\n", " "),rs.getString(6), rs.getString(5));

            campagnes.add(campagneliste);
            }
        } catch (Exception e) {            
            e.printStackTrace();
        }
        return campagnes;
    }



    public static void main(String[] args)
    {

        DaoCampagne dao = new DaoCampagne();
    }}

