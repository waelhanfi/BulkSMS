package servletGetSilver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import businessGetSilver.GsmListe;
import daoGetSilver.*;
import businessGetSilver.Liste;
import daoGetSilver.Sql;

public class AddGsmListe extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String p = request.getParameter("idlist");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Liste L = new Liste();
        L.setCodeListe(p);

        String newNom = request.getParameter("newNom");
        String newGsm = request.getParameter("newGsm");


        String returnSerNewIds = "";

        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();

        String[] serNewnom = newNom.split("zbanda9louch");
        String[] serNewgsm = newGsm.split("zbanda9louch");

        DaoGsmListe daoGsmListe = new DaoGsmListe();
        GsmListe gsmliste = new GsmListe();

        for (int i = 0; i < serNewgsm.length; i++) {

            gsmliste.setNom(serNewnom[i]);
            gsmliste.setNumGsm(serNewgsm[i]);
            gsmliste.setListe(L);
            daoGsmListe.addGsm(sql, gsmliste);

        }
        sql.Fermer_Cnn();
        out.println(returnSerNewIds);
        out.flush();
        out.close();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
