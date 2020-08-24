package servletGetSilver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daoGetSilver.*;
import daoGetSilver.Sql;
import businessGetSilver.User;



public class GetUserInfo extends HttpServlet
{

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
        
        String p = request.getParameter("idlist");                
		response.setHeader("content-type", "text/html; charset=gb2312") ;
		PrintWriter out = response.getWriter();
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();		
        DaoUser daoUser= new DaoUser();
        List<User> userList = daoUser.getAllUser(sql);
        sql.Fermer_Cnn();

		int userListCount = userList.size() ;
		Iterator<User> it = userList.iterator() ;
               
		User info = null ;
		out.print("{") ;
		out.print("results:" + userListCount + ",rows:[") ;
		for(int i = 0 ; it.hasNext() ; i++)
		{
			info = it.next() ;
			out.print("{userId:'" + info.getUserId() + "',userName:'" + info.getUserName() + "',userPassword:'" +
					info.getUserPassword() + "',userRole:'" + info.getUserRole()+"',userCredit:'"+info.getUserCredit()) ;
			if(i == userListCount - 1)
			{
				out.print("'}") ;
			}
			else
			{
				out.print("'},") ;
			}
		}
		out.print("]}") ;

		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request,response) ;
	}

}
