/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package daoGetSilver;

import businessGetSilver.User;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DaoUser {
    
    
    public void delUser(Sql sql,int userId) {        
        sql.Update(" delete from utilisateur where CODE_UTILISATEUR = " + userId);
        sql.Commit();
    }
    
    

    public List<User> getAllUser(Sql sql) {



        List<User> userList = new ArrayList<User>();
        try {
            Statement st = sql.Get_Connection().createStatement();
            ResultSet rs = st.executeQuery("select * from utilisateur");
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("CODE_UTILISATEUR"));
                user.setUserName(rs.getString("login"));
                user.setUserPassword(rs.getString("pwd"));
                user.setUserRole(rs.getString("role"));
                user.setUserCredit(rs.getInt("credit"));
                userList.add(user);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }      

        return userList;
    }

    public void saveUser(Sql sql,User user) {
        
        sql.Update("insert into utilisateur values (SQ_UTILISATEUR.nextval,'" + user.getUserName().replaceAll("'", "''") +  "','"+user.getUserPassword().replaceAll("'", "''")+"','"  + user.getUserRole() + "',"+user.getUserCredit()+")");
        sql.Commit();

    }
    

    public void updateUser(Sql sql,User user) {        
        sql.Update("update utilisateur set login ='"+user.getUserName().replaceAll("'", "''")+"',pwd='"+user.getUserPassword().replaceAll("'", "''")+"',role='"+user.getUserRole()+"',credit="+user.getUserCredit()+" where CODE_UTILISATEUR = "+user.getUserId());
        sql.Commit();        
    }


}
