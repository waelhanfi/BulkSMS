/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import businessGetSilver.Campagne;
import daoGetSilver.Sql;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yosr
 */
public class GetCampagne extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setHeader("content-type", "text/html; charset=gb2312") ;
		PrintWriter out = response.getWriter();
          HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null)
        {
        String user =(String) session.getAttribute("username");
        DaoCampagne daoCampagne=new DaoCampagne();
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
		List<Campagne> campagneList  = daoCampagne.getAllRegistred(sql,user);
        sql.Fermer_Cnn();
                
		int campagneListCount = campagneList.size() ;
		Iterator<Campagne> it = campagneList.iterator() ;
		Campagne info = null ;
		out.print("{") ;
		out.print("results:" + campagneListCount + ",rows:[") ;
		for(int i = 0 ; it.hasNext()  ; i++)
		{
			info = it.next() ;
			out.print("{nom_campagne:'" + info.getNom() + "',date:'" + info.getDateLancement() + "',liste:'" +info.getListe()+"',message:'"+info.getMessage().replaceAll("'", "\\\\'")+"',date_envoi:'"+info.getDateEnvoi()+"',etat:'"+info.getEtat() ) ;
			if(i == campagneListCount - 1)
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
	}}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request,response) ;
	}

}
