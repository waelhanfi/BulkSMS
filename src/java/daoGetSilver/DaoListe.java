/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package daoGetSilver;

import java.sql.Statement;
import java.sql.ResultSet;
import businessGetSilver.Liste;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class DaoListe {


       public List<Liste> getAllInList(Sql sql,String utilisateur) {

        List<Liste> maList = new ArrayList<Liste>();
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select CODE_LISTE,NOM,to_char(DATE_CREATION,'dd/mm/yyyy'),utilisateur from liste where utilisateur like '"+utilisateur+"' order by DATE_CREATION desc");
            while (rs.next()) {
                 Liste liste = new Liste(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                maList.add(liste);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return maList;
    }

 public String getNomList (Sql sql,int ListID)
        {        
        String nom =sql.Get_String_For_This_Query("select nom from LISTE where CODE_LISTE="+ListID);        
        return nom ;
        } 


        public Liste[] getAllInTab(Sql sql,String utilisateur) {
        Vector <Liste> litses = new Vector();

        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select CODE_LISTE,NOM,to_char(DATE_CREATION,'dd/mm/yyyy'),utilisateur from liste where utilisateur like '"+utilisateur+"' order by DATE_CREATION desc");
            while (rs.next()) {
                litses.addElement(new Liste(rs.getString(1),rs.getString(2).replaceAll(" ", "_"),rs.getString(3),rs.getString(4)));
            }

            return litses.toArray(new Liste[0]);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
     }



        public void addListe(Sql sql,Liste liste)
        {
        sql.Update("insert into liste values (sq_liste.nextval,'"+liste.getNom().replaceAll("'", "''")+"',sysdate,'"+liste.getUtlisateur()+"')");
        sql.Commit();        
        }




        public String getListSize (Sql sql,Liste liste)
        {        
        String size =sql.Get_String_For_This_Query("select count(*) from GSM_LISTE where LISTE="+liste.getCodeListe());        
        return(size);
        }



        public Liste getLastListe (Sql sql,String utilisateur)
        {        
        Liste L = new Liste();
         try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select CODE_LISTE,NOM,to_char(DATE_CREATION,'dd/mm/yyyy'),utilisateur from liste where CODE_LISTE " +
                    " in (select max(CODE_LISTE) from liste where utilisateur like '"+utilisateur+"') and utilisateur like '"+utilisateur+"' ");
            while (rs.next()) {

                L = new Liste(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }         
            return L;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;}
        }



        public Liste getListebyId(Sql sql,String codeListe)
        {
            
            Liste L = new Liste();
         try{
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select NOM,to_char(DATE_CREATION,'dd/mm/yyyy'),utilisateur from liste where CODE_LISTE="+codeListe);
            while (rs.next()) {

                L = new Liste(rs.getString(1),rs.getString(2),rs.getString(3));
            }            
            return L;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;}
        }


    public static void main (String [] args)
    {
     
    }
}
