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
import businessGetSilver.Liste;
import daoGetSilver.*;
import daoGetSilver.Sql;


public class DelGsm extends HttpServlet
{

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

        String listeselec = request.getParameter("idlist");
        //System.out.println(listeselec);
		String gsm = request.getParameter("gsm") ;
                //System.out.println(gsm);
                 Liste L = new Liste();                  
                 L.setCodeListe(listeselec);

                DaoGsmListe daoGsmListe=new DaoGsmListe();
                Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
                sql.Open_Connexion();
                daoGsmListe.delGsm(sql, gsm, L);
                sql.Fermer_Cnn();

		//GsmManager.getInstance().delGsm(gsm,L) ;
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request,response) ;
	}

}
