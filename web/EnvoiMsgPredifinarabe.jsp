<%--
    Document   : succe_blackliste
    Created on : 2 sept. 2010, 11:40:31
    Author     : yosr
--%>
<%@ page import="businessGetSilver.*,daoGetSilver.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=windows-1256"%>

<html>

	<head>  <meta HTTP-EQUIV="Refresh" content="1;URL=menuAdmin.jsp">
               <meta http-equiv="Content-Type" content="text/html; charset=windows-1256" />
		<title>Getsilver</title>
<%
	try{
        if (session != null)
            {
               String userName=(String)session.getAttribute("username");
               String userPass=(String)session.getAttribute("pwd");

		if(session == null || userName == null || userName.equals(""))
		{
                  request.getRequestDispatcher("index.jsp").forward(request, response);
        }}
            else {request.getRequestDispatcher("index.jsp").forward(request, response);}
    }
	catch(Exception e){
		response.sendRedirect("index.jsp");
        
	}
        String user=(String)session.getAttribute("username");
%>
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />




        <style type="text/css">
<!--
.Style3 {
	font-size: 18px;
	font-weight: bold;
}
-->
        </style>
</head>
<%
	//RECEPTION DE LA LISTE DES INFORMATIONS
	String Nom = request.getParameter("nom_msg");
        String message = request.getParameter("message");
		  byte[] utf8Bytes = message.getBytes("iso-8859-1");
    
           
        message = Diffusion.printBytes(utf8Bytes);
		
        Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();

        DaoMessagePredefini dao = new DaoMessagePredefini();
        MessagePredefini msg = new MessagePredefini(Nom, message,user);
        dao.addMessageArabe(sql,msg);
        sql.Fermer_Cnn();

   %>
<body class="blog">


<div id="bg-container">

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<div id="container">

<div id="page-top">
<img src="images/page_top.png" alt=""/>
</div><!--PAGE TOP ENDS-->

<div id="page-middle">
  <div id="page-content">


    	<div align="center"><span> <span class="Style3">Votre message en cours de traitement<br><img src="images/ajax-loader.gif" width="32" height="32"> </span></span>





      </div>
  </div>
  <!--PAGE CONTENT ENDS-->
    <!--SIDEBAR ENDS-->
</div><!--PAGE MIDDLE ENDS-->

<div id="page-bottom">
<img src="images/page_bottom.png" alt=""/>
</div><!--PAGE BOTTOM ENDS-->

<div id="footer"><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> </div>
<!--FOOTER ENDS-->

</div>
<!--CONTAINER ENDS-->
</div>


</body>

</html>