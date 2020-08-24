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
import businessGetSilver.Liste;
import daoGetSilver.Sql;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yosr
 */
public class GetListe extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("content-type", "text/html; charset=gb2312");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null)
        {
        DaoListe daoListe= new DaoListe();
        String user =(String) session.getAttribute("username");
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();        
        List<Liste> maList = daoListe.getAllInList(sql,user);
        sql.Fermer_Cnn();
        int ListCount = maList.size();
        Iterator<Liste> it = maList.iterator();
        Liste info = null;
        out.print("{");
        out.print("results:" + ListCount + ",rows:[");
        for (int i = 0; it.hasNext(); i++) {
            info = it.next();
            out.print("{code:'" + info.getCodeListe() + "',nom:'" + info.getNom() + "',date:'" + info.getDateCreation());
            if (i == ListCount - 1) {
                out.print("'}");
            } else {
                out.print("'},");
            }
        }
        out.print("]}");

        out.flush();
        out.close();
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
