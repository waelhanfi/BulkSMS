/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servletGetSilver;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daoGetSilver.*;
import businessGetSilver.GsmListe;
import businessGetSilver.GsmParam;
import businessGetSilver.Liste;
import daoGetSilver.Sql;


public class GetGsmListe extends HttpServlet {
   
   
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {              
        response.setHeader("content-type", "text/html; charset=gb2312") ;
		PrintWriter out = response.getWriter();
        String p = request.getParameter("idlist");
        Liste L = new Liste();
        L.setCodeListe(p);

List<GsmListe> gsms = null;


         Sql sql =  new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
         sql.Open_Connexion();
        
         DaoGsmListe daoGsmListe= new DaoGsmListe();
         String nom=daoGsmListe.getNomliste(sql,L);
         if(nom.endsWith("$pm")){
            gsms  = daoGsmListe.getGsmByIdparam(sql,L) ;
         //System.out.println("OKKKKKKk");
                 
         }
         else{ gsms  = daoGsmListe.getGsmById(sql,L) ;}
            sql.Fermer_Cnn();


         
        int gsmListCount= gsmListCount = gsms.size() ;
         System.out.println("number"+gsmListCount);
		Iterator<GsmListe> it = gsms.iterator() ;

		GsmListe info = null ;
		out.print("{") ;
		out.print("results:" + gsmListCount + ",rows:[") ;
		for(int i = 0 ; it.hasNext()  ; i++)
		{
			info = it.next() ;
			out.print("{idgsm:'"+info.getCodeGsm()+"',nom:'" + info.getNom() + "',gsm:'" + info.getNumGsm() + "',date:'" +
					info.getDateAjout() ) ;
			if(i == gsmListCount - 1)
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

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request,response) ;
      
    }

   

}
