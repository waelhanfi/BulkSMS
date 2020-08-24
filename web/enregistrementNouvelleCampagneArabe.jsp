<%--
    Document   : succe_blackliste
    Created on : 2 sept. 2010, 11:40:31
    Author     : yosr
--%>
<%@ page import="businessGetSilver.*,daoGetSilver.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=windows-1256"%>
<html>

	<head>  <meta HTTP-EQUIV="Refresh" content="1;URL=NouvelleCampagne.jsp">
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
	String Nom = request.getParameter("nom_liste");
        String Liste = request.getParameter("listenvoi");
        String Message = request.getParameter("msg");
       
        String idliste = request.getParameter("idListe");
        String date_envoi = request.getParameter("date_envoi");
        String etat = "enregistree";
       
	    byte[] utf8Bytes = Message.getBytes("iso-8859-1");
        
      //  System.out.println("msg "+message);
         Message = Diffusion.printBytes(utf8Bytes);
       // Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        Sql sql = new Sql();
        sql.Open_Connexion();  
       
        Liste liste = new Liste();
        liste.setCodeListe(idliste);       
       DaoCampagne dao = new DaoCampagne();
       DaoGsmListe test= new DaoGsmListe();             
       Utile util = new Utile();       
        if(util.checkCampaign(sql,liste,user,Message )==true) {
            Campagne campagne = new Campagne(Nom,idliste, Message,etat,date_envoi);
         
         
                    
                       dao.sendCampaign_enregistrementArab(sql,campagne, util,user,campagne.getDateEnvoi());
                       
        } else {
            response.sendRedirect("erreur_envoi_campagne.jsp");
               }
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


    	<div align="center"><span> <span class="Style3">Votre Campagne en cours d'enregistrement<br><img src="images/ajax-loader.gif" width="32" height="32"> </span></span>





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