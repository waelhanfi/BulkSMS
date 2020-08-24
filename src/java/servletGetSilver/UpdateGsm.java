/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servletGetSilver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daoGetSilver.*;
import businessGetSilver.GsmListe;
import daoGetSilver.Sql;


public class UpdateGsm extends HttpServlet
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
                
        String newIdgsm = request.getParameter("idgsms");
        String newNom = request.getParameter("noms") ;
		String newGsm = request.getParameter("gsms") ;
         String[] serIds = newIdgsm.split("zbanda9louch") ;
         String[] serNewnom = newNom.split("zbanda9louch") ;
		 String[] serNewgsm = newGsm.split("zbanda9louch") ;
		GsmListe gsmliste = new GsmListe() ;
        DaoGsmListe daoGsmListe=new DaoGsmListe();
        Sql sql =  new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();

		for(int i = 0 ; i < serNewgsm.length ; i++)
		{       gsmliste.setCodeGsm(serIds[i]);
			gsmliste.setNom(serNewnom[i]) ;
			gsmliste.setNumGsm(serNewgsm[i]) ;

            daoGsmListe.updateGsm(sql, gsmliste);
			//GsmManager.getInstance().updateGsm(gsmliste) ;
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request,response) ;
	}

}
