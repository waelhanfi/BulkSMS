package daoGetSilver;



import businessGetSilver.GsmListe;
import businessGetSilver.GsmParam;
import businessGetSilver.Liste;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



public class DaoGsmListe
{


    public void addGsm(Sql sql,GsmListe gsmList)
    {sql.Update("insert into gsm_liste values (SQ_GSM.nextval,'"+gsmList.getNumGsm()+"','"+gsmList.getListe().getCodeListe()+"','"+gsmList.getNom()+"','"+gsmList.getPrenom()+"',sysdate)");

    }
 public void addGsmparam(Sql sql,GsmParam gsmList)
    {
                      
        sql.Update("insert into gsm_param values (SQ_GSM.nextval,'"+gsmList.getNumGsm()+"','"+gsmList.getListe().getCodeListe()+"',sysdate,'"+gsmList.getKW1()+"','"+gsmList.getKW2()+"','"+gsmList.getKW3()+"','"+gsmList.getKW4()+"','"+gsmList.getKW5()+"','"+gsmList.getKW6()+"')");

    }

      public GsmListe[] getGsmBylist(Liste L) {
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        Vector <GsmListe> gsms = new Vector();

        sql.Open_Connexion();
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select NUM_GSM,LISTE,NOM,PRENOM, to_char( DATE_AJOUT,'dd/mm/yyyy') from gsm_liste where LISTE="+L.getCodeListe());
            while (rs.next()) {
                gsms.addElement(new GsmListe(rs.getString(1),L,rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            sql.Fermer_Cnn();
            return gsms.toArray(new GsmListe[0]);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
     }


      public  List<GsmListe> getGsmById(Sql sql,Liste L)
 {    
        List<GsmListe> gsms = new ArrayList<GsmListe>();
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select CODE,NUM_GSM,LISTE,NOM,to_char( DATE_AJOUT,'dd/mm/yyyy') from gsm_liste where LISTE="+L.getCodeListe());
            while (rs.next()) {
                 GsmListe gsmliste = new GsmListe(rs.getString(1),rs.getString(2),L,rs.getString(4),rs.getString(5));
                 if(gsmliste.getNom()==null)
                 {gsmliste.setNom("Champ non renseigné");
                 }


                gsms.add(gsmliste);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return gsms;
 }
     public  List<GsmListe> getGsmByIdparam(Sql sql,Liste L)
 {    
        List<GsmListe> gsms = new ArrayList<GsmListe>();
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select CODE,NUM_GSM,LISTE,KW1,to_char( DATE_AJOUT,'dd/mm/yyyy') from gsm_param where LISTE="+L.getCodeListe());
            while (rs.next()) {
                 GsmListe gsmliste = new GsmListe(rs.getString(1),rs.getString(2),L,rs.getString(4),rs.getString(5));
                 if(gsmliste.getNom()=="vide")
                 {gsmliste.setNom("Champ non renseigné");
                 }


                gsms.add(gsmliste);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return gsms;
 }

public  String getNomliste(Sql sql,Liste L)
 {    
        String nom=null;
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select NOM from liste where CODE_LISTE="+L.getCodeListe());
            while (rs.next()) {
                 nom = rs.getString(1);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nom;
 }
public  int getLongParam(String L)
 {    
        int a = 0;
       Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
     Liste l=new Liste(L);
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select KW1,KW2,KW3,KW4 from GSM_PARAM where CODE_LISTE="+l.getCodeListe());
            while (rs.next()) {
                
               int aa = (rs.getString(1).length())+(rs.getString(2).length())+(rs.getString(3).length())+(rs.getString(4).length());
            if(aa>a)
            {
            a=aa;
            }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (156-a);
 }

      public void delGsm(Sql sql,String gsm,Liste L) {
        sql.Update(" delete from gsm_liste where NUM_GSM = '"+gsm+"' and LISTE= "+L.getCodeListe());
        sql.Commit();        
    }

public boolean itisParam(Liste L) {
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        String nom="";
        sql.Open_Connexion();
        try {
            Statement st = sql.Get_Connection().createStatement();
            //System.out.println("select distinct(NUM_GSM) from gsm_liste where LISTE="+L.getCodeListe());
            ResultSet rs = st.executeQuery("select nom from liste where code_LISTE="+L.getCodeListe());
            while (rs.next()) {
                 nom=rs.getString(1);
            }
            sql.Fermer_Cnn();
            return nom.endsWith("$pm");
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
     }

      public GsmListe[] getDistinctGsmBylist(Liste L) {
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        Vector <GsmListe> gsms = new Vector();

        sql.Open_Connexion();
        try {
            Statement st = sql.Get_Connection().createStatement();
            //System.out.println("select distinct(NUM_GSM) from gsm_liste where LISTE="+L.getCodeListe());
            ResultSet rs = st.executeQuery("select distinct(NUM_GSM) from gsm_liste where LISTE="+L.getCodeListe());
            while (rs.next()) {
                gsms.addElement(new GsmListe(rs.getString(1)));
            }
            sql.Fermer_Cnn();
            return gsms.toArray(new GsmListe[0]);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
     }

      public GsmParam[] getDistinctGsmBylistpm(Liste L) {
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        Vector <GsmParam> gsms = new Vector();

        sql.Open_Connexion();
        try {
            Statement st = sql.Get_Connection().createStatement();
            //System.out.println("select distinct(NUM_GSM) from gsm_liste where LISTE="+L.getCodeListe());
            ResultSet rs = st.executeQuery("select distinct(NUM_GSM),KW1,KW2,KW3,KW4,KW5,KW6 from gsm_param where LISTE="+L.getCodeListe());
            while (rs.next()) {
                gsms.addElement(new GsmParam(rs.getString(1),L,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
          // System.out.println(""+rs.getString(1)+" "+rs.getString(5));
            }
            sql.Fermer_Cnn();
            return gsms.toArray(new GsmParam[0]);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
     }
      public void updateGsm (Sql sql,GsmListe gsmliste) {

        sql.Update("update gsm_liste set NOM='"+gsmliste.getNom().replaceAll("'", "''")+"',NUM_GSM='"+gsmliste.getNumGsm()+"'where code="+gsmliste.getCodeGsm());
        sql.Commit();
    }



    public static void main(String [] args)
    {
        DaoGsmListe daogsmliste =new DaoGsmListe();
        DaoListe daoListe = new DaoListe();
        Liste L = new Liste ("test2");        
         Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
         daoListe.addListe(sql,L);
        String utilisateur="";
        GsmListe gsmListe = new GsmListe("97559680",daoListe.getLastListe(sql,utilisateur),"turki","dali");

   }

}
