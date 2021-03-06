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
import businessGetSilver.MessagePredefini;
import daoGetSilver.Sql;
import javax.servlet.http.HttpSession;



public class GetMessagePredefini extends HttpServlet
{

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setHeader("content-type", "text/html; charset=gb2312") ;
		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("username") != null) 
        {

        String user =(String) session.getAttribute("username");
        DaoMessagePredefini daoMessagePredefini=new DaoMessagePredefini();
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        
		List<MessagePredefini> messageList  = daoMessagePredefini.getAll(sql,user);
        sql.Fermer_Cnn();
		int messageListCount = messageList.size() ;
               
		Iterator<MessagePredefini> it = messageList.iterator() ;
               
		MessagePredefini info = null ;
		out.print("{") ;
		out.print("results:" + messageListCount + ",rows:[") ;
		for(int i = 0 ; it.hasNext()  ; i++)
		{
			info = it.next() ;
			out.print("{nom:'" + info.getNom() + "',message:'" + info.getMessage().replaceAll("'", "\\\\'") + "',date:'" +
					info.getDateAjout() ) ;
			if(i == messageListCount - 1)
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
