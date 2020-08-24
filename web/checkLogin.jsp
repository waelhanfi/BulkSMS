<%--
    Document   : checklogin
    Created on : 19 avr. 2010, 16:06:52
    Author     : yosr
--%>
<%@ page import="businessGetSilver.*,daoGetSilver.*" %>



<%
  
	try{
            String login = request.getParameter("username");
            String psw = request.getParameter("pwd");
            DaoAdministrateur daoAdmin = new DaoAdministrateur();
            Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
            sql.Open_Connexion();
            Administrateur admin = new Administrateur(login,psw);
            String role = daoAdmin.getUserRole(sql,admin);


            if(daoAdmin.checkUser(sql, admin))
            {
         
                    session.setAttribute("username",login);
                    session.setAttribute("pwd",psw);                    
                    response.sendRedirect("menuAdmin.jsp");
	     }
               else
               {
                   response.sendRedirect("index.jsp");
               }

           sql.Fermer_Cnn();


         }
    	catch(Exception e){
		response.sendRedirect("index.jsp");
	}

%>

