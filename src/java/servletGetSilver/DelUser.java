package servletGetSilver;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daoGetSilver.*;
import daoGetSilver.Sql;


public class DelUser extends HttpServlet
{

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        DaoUser daoUser= new DaoUser();
		String userId = request.getParameter("userId") ;
		//UserManager.getInstance().delUser(Integer.parseInt(userId)) ;
        daoUser.delUser(sql, Integer.parseInt(userId));

        sql.Fermer_Cnn();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request,response) ;
	}

}
