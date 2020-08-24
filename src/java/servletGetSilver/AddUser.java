package servletGetSilver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoGetSilver.*;
import daoGetSilver.Sql;
import businessGetSilver.User;


/** 
 * @author thtwin http://thtwin.javaeye.com 
 * @version 
 * 
 */
public class AddUser extends HttpServlet
{

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();


		String newNames = request.getParameter("utilisateur_ajout_login") ;
		String newPasswords = request.getParameter("utilisateur_ajout_pass") ;
		String newRoles = request.getParameter("utilisateur_ajout_role_value") ;
        String newCredits=request.getParameter("utilisateur_ajout_credit");

            

			User user = new User() ;

			user.setUserName(newNames) ;
			user.setUserPassword(newPasswords) ;
			user.setUserRole(newRoles) ;
            user.setUserCredit(Integer.parseInt(newCredits));
            
          Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
             sql.Open_Connexion();		
             DaoUser daoUser= new DaoUser();
                daoUser.saveUser(sql, user);
			//UserManager.getInstance().saveUser(user) ;
                sql.Fermer_Cnn();


		out.println("{success: true, msg:{reason:'ok'}}") ;
		out.flush();
		out.close();
	}

}
