/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;


import daoGetSilver.Sql;
import java.util.*;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author yosr
 */
public class DaoAdministrateur {




    public void addUser(Sql sql, Administrateur admin )
    {
            sql.Update("insert into utilisateur values(sq_utilisateur.nextval,'"+admin.getLogin()+"', '"+admin.getPsw()+"','"+admin.getRole()+"')");
            sql.Commit();
    }




    public boolean checkUser(Sql sql,Administrateur admin)
    {
         int nbr =0;
                 nbr=sql.Get_Int_For_This_Query(
                 "select count(*) from utilisateur where lower(LOGIN)='"+admin.getLogin().toLowerCase() +"' and lower(pwd) ='"+admin.getPsw().toLowerCase()+"'");
        if (nbr == 0 || nbr == -1) return false;
        else return true;
    
    }

    public String getUserRole(Sql sql,Administrateur admin)
    {
        String role ="";
        role = sql.Get_String_For_This_Query("select role from utilisateur where lower(login) = '"+admin.getLogin().toLowerCase()+"' and lower(pwd) ='"+admin.getPsw().toLowerCase()+"'");
        admin.setRole(role);

    return (role);
    }





    public void deleteUser (Sql sql,Administrateur admin)
    {
            int codeUtilisateur=getCodeUser(sql,admin);
            sql.Update("delete from utilisateur where lower(LOGIN)='"+admin.getLogin() +"' and pwd ='"+admin.getPsw()+"'");
            sql.Commit();
    }





    public int getCodeUser(Sql sql,Administrateur admin)
    {
         int nbr = sql.Get_Int_For_This_Query(
                 "select code_utilisateur from utilisateur where lower(LOGIN)='"+admin.getLogin() +"' and pwd ='"+admin.getPsw()+"'");        
        return (nbr);

    }



    public void updateUser(Sql sql, Administrateur user1,Administrateur user2)
    {
            int codeUtilisateur=getCodeUser(sql,user1);
            sql.Update("update utilisateur set login='"+user2.getLogin()+"', pwd='"+user2.getPsw()+"', role='"+user2.getRole()+"' where code_utilisateur="+codeUtilisateur);
            sql.Commit();
    }




//    public Vector<Administrateur> getUsers(Sql sql)
//    {
//        Vector<Administrateur> users = new Vector<Administrateur>();
//
//    try {
//      Statement st = sql.Get_Connection().createStatement();
//      ResultSet rs = st.executeQuery(
//          "select login,pwd,role  from utilisateur");
//
//      while (rs.next()) {
//        users.add(new Administrateur(rs.getString(1),rs.getString(2),rs.getString(3)));
//      }
//      rs.close();
//      st.close();
//    }
//
//    catch (Exception e) {
//      System.out.println(e.toString());
//      return null;
//    }
//    return users;
//    }

public ArrayList getUtilisateurs (Sql sql)
{

     ArrayList list =new ArrayList();
     try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery(
             "select login,pwd,role  from utilisateur");

      while (rs.next()) {
        list.add(new Administrateur(rs.getString(1),rs.getString(2),rs.getString(3)));
      }
    }
    catch (Exception e) {
      System.out.println(e.toString());
      return null;
    }
    return list;
}


    public static void main(String [] arg)
    {
         DaoAdministrateur daoAdmin = new DaoAdministrateur();
        Sql sql = new Sql("172.16.50.5","DBV9","get_silver","get_silver");
        sql.Open_Connexion();
        Administrateur user = new Administrateur("haythem","P@ssw0rd","user");

        if (!daoAdmin.checkUser(sql,user))
        {
            daoAdmin.addUser(sql, user);
            

        }
        else {
        System.out.println("deja existant");
        }


        sql.Fermer_Cnn();
    }

}
