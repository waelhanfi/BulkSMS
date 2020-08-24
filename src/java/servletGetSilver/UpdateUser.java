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

public class UpdateUser extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String logins = request.getParameter("utilisateur_modif_login");
        String passwords = request.getParameter("utilisateur_modif_pass");
        String roles = request.getParameter("utilisateur_modif_rang_value");
        String credits = request.getParameter("utilisateur_modif_credit");

        String id = request.getParameter("utilisateur_modif_id");
        User user = new User();
        user.setUserId(Integer.parseInt(id));
        user.setUserName(logins);
        user.setUserPassword(passwords);
        user.setUserRole(roles);
        user.setUserCredit(Integer.parseInt(credits));

        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        DaoUser daoUser= new DaoUser();
        daoUser.updateUser(sql, user);
        
        //UserManager.getInstance().updateUser(user);


        out.println("{success: true, msg:{reason:'ok'}}");

        out.flush();
        out.close();

    }
}
