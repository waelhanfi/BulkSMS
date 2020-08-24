<%-- 
    Document   : deconnexion
    Created on : 20 oct. 2010, 10:04:30
    Author     : hakim
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

    if (session != null) {
            session.setAttribute("username", "");
            session.setAttribute("pwd", "");
            session.invalidate();
            session= null;
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);


%>

