/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package daoGetSilver;




import businessGetSilver.HistoriqueEnvoi;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.Statement;


public class DaoHistoriqueEnvoi {


    public void addHistory(Sql sql,HistoriqueEnvoi historiqueEnvoi)
    {
        sql.Update("insert into HISTORIQUE_ENVOI values (SQ_HISTORIQUE_ENVOI.nextval,'"+historiqueEnvoi.getMessage().replaceAll("'", "''")+"','"+historiqueEnvoi.getNombre()+"',sysdate,'"+historiqueEnvoi.getUtilisateur()+"')");
        sql.Commit();
    }



    public Vector  Nbr_Sms (Sql sql, String date_min, String date_max,String utilisateur)
    {
        Vector history = new Vector();
        try {
      Statement st = sql.Get_Connection().createStatement();
          ResultSet rs = st.executeQuery("select distinct(to_char(DATE_ENVOI,'mm/dd/yyyy')) ,SUM(nombre) from HISTORIQUE_ENVOI where to_char(DATE_ENVOI,'mm/dd/yyyy') between '" +
                                     date_min + "' and '" + date_max + "' and utilisateur like '"+utilisateur+"'  group by to_char(DATE_ENVOI,'mm/dd/yyyy')");
      while (rs.next()) {

         HistoriqueEnvoi histo =new HistoriqueEnvoi();
         histo.setDateEnvoi(rs.getString(1));
         histo.setNombre(rs.getString(2));
         history.addElement( histo );
      }
          rs.close();
          st.close();
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }
    return history;





    }



    public String Max (Vector v)
 {
     int maxi;
     if(v != null && v.size() > 0){
         maxi = Integer.parseInt(((HistoriqueEnvoi)v.elementAt(0)).getNombre());
         for(int i=0;i<=v.size()-1;i++){
             if(maxi < Integer.parseInt(((HistoriqueEnvoi)v.elementAt(i)).getNombre())){
                 maxi = Integer.parseInt(((HistoriqueEnvoi)v.elementAt(i)).getNombre());
             }
         }
         return Integer.toString(maxi + 100);
     }
     return "";
 }


    public static void main(String [] args)
    {
        System.out.println("Bonjour");


        DaoHistoriqueEnvoi daohistoriquenvoi = new DaoHistoriqueEnvoi();

         Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        Vector courbe = new Vector();
        courbe = daohistoriquenvoi.Nbr_Sms(sql, "09/05/2010", "09/15/2010","user");

        sql.Fermer_Cnn();
        String max = daohistoriquenvoi.Max(courbe);
        for (int i=0; i < courbe.size(); i++)
        {

                         String date =  ((HistoriqueEnvoi) courbe.elementAt(i)).getDateEnvoi();
                          String total = ((HistoriqueEnvoi)courbe.elementAt(i)).getNombre();
                          System.out.println("date-----"+date+"------totale---"+total );
                          

        }

    }

}
