/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daoGetSilver;
import businessGetSilver.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DaoMessagePredefini {


    public void addMessage(Sql sql,MessagePredefini message)
    {   
        String mssage=message.getMessage().replaceAll("'", "''");
        mssage=mssage.replaceAll("\n", ";");
        mssage=mssage.replaceAll("\r", "");        
        sql.Update("insert into message_predefini values (sq_message_predefini.nextval,'"+mssage+"',sysdate,'"+message.getNom().replaceAll("'","''")+"','"+message.getUtilisateur()+"')");
       
        sql.Commit();
    }
public void addMessageArabe(Sql sql,MessagePredefini message)
    {   
        String mssage=message.getMessage().replaceAll("'", "''");
        mssage=mssage.replaceAll("\n", ";");
        mssage=mssage.replaceAll("\r", "");        
        sql.Update("insert into message_predefini_ar values (sq_message_predefin_ar.nextval,'"+mssage+"',sysdate,'"+message.getNom().replaceAll("'","''")+"','"+message.getUtilisateur()+"')");
       
        sql.Commit();
    }


   public List<MessagePredefini> getAll(Sql sql, String utilisateur) {

        List<MessagePredefini> messageList = new ArrayList<MessagePredefini>();
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select NOM, CONTENU,to_char(DATE_MESSAGE,'dd/mm/yyyy'),utilisateur  from MESSAGE_PREDEFINI where utilisateur like '"+utilisateur+"'");
            while (rs.next()) {
                 MessagePredefini messagepredefini = new MessagePredefini(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                messageList.add(messagepredefini);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return messageList;
    }
public List<MessagePredefini> getAllarabe(Sql sql, String utilisateur) {

        List<MessagePredefini> messageList = new ArrayList<MessagePredefini>();
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select NOM, CONTENU,to_char(DATE_MESSAGE,'dd/mm/yyyy'),utilisateur  from MESSAGE_PREDEFINI_AR where utilisateur like '"+utilisateur+"'");
            while (rs.next()) {
                 MessagePredefini messagepredefini = new MessagePredefini(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                messageList.add(messagepredefini);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return messageList;
    }
 public String getmsg(Sql sql,String nom) {

      String messagepredefini="";
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select CONTENU from MESSAGE_PREDEFINI where nom = '"+nom+"'");
            while (rs.next()) {
                  messagepredefini = rs.getString(1);
             
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return messagepredefini;
    }

    public static void main(String []args)
    {
        System.out.println("bonjouir");
        }


}
