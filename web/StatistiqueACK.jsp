<%@ page import="businessGetSilver.*,java.util.Vector,java.util.Enumeration,daoGetSilver.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
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
<!--[if IE]><script language="javascript" type="text/javascript" src="excanvas.js"></script><![endif]-->
<!--[if IE]>
	<style type="text/css">
	#grid_rows{border:1px solid;}
	</style>
	<![endif]-->

	<script type="text/javascript" src="js/ext-base.js"></script>
        
	<script type="text/javascript" src="js/ext-all.js"></script>
        <link rel="stylesheet" href="css/Style_tab.css" type="text/css" media="screen, projection"/>
		<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="css/stylemenu.css" type="text/css" media="screen, projection"/>
		<link type="text/css" href="css/themename/jquery-ui-1.8.custom.css" rel="Stylesheet" />
           	<link type="text/css" href="css/pos_form.css" rel="Stylesheet" />
                <link type="text/css" href="css/style_mv.css" rel="Stylesheet" />
                <link type="text/css" href="css/jquery.jqplot.css" rel="Stylesheet" />
<link type="text/css" rel="stylesheet" href="css/extjs/css/combos.css" />
                <link type="text/css" rel="stylesheet" href="css/extjs/css/ext-all.css" />
                <link type="text/css" rel="stylesheet" href="css/styleutil.css" />
       <!--<script type="text/javascript" src="js/testrec.js"></script>-->
		<script type="text/javascript" src="js/jquery.js"></script>
	        <script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
	        <script type="text/javascript" language="javascript" src="js/jquery.dropdownPlain.js"></script>
                <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
                <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
                <script type="text/javascript" src="js/query.easing.1.3.js"></script>
                <script type="text/javascript" src="js/jquery.ui.datepicker.js"></script>
        <!--calandrier-->
        <!Controle & form-->

       <script src="js/jquery.validationEngine-fr.js" type="text/javascript"></script>
  	   <script src="js/jquery.validationEngine.js" type="text/javascript"></script>
       <link rel="stylesheet" href="css/validationEngine.jquery.css" type="text/css" media="screen" title="no title" charset="utf-8" />
	   <link rel="stylesheet" href="css/template.css" type="text/css" media="screen" title="no title" charset="utf-8" />

        <!-- BEGIN: load jqplot -->


                  <script language="javascript" type="text/javascript" src="js/jquery.jqplot.js"></script>
                  <script language="javascript" type="text/javascript" src="js/jqplot.logAxisRenderer.js"></script>
                  <script language="javascript" type="text/javascript" src="js/jqplot.dateAxisRenderer.js"></script>
                  <script language="javascript" type="text/javascript" src="js/jqplot.highlighter.js"></script>
                  <script language="javascript" type="text/javascript" src="js/jqplot.cursor.js"></script>
                  <style type="text/css" media="screen">
                        .jqplot-axis {
                          font-size: 0.85em;
                        }
                        .jqplot-axis{
                          font-size: 0.75em;
                        }
                </style>


        <script type="text/javascript">

	$(function() {

		var dates = $('#from, #to').datepicker({
			dateFormat: "dd-mm-yy",

			changeMonth: true,
			numberOfMonths: 1,
			showOn: 'button',
			buttonImage: 'images/ew_calendar.gif',
			buttonImageOnly: true,
			onSelect: function(selectedDate) {
				var option = this.id == "from" ? "minDate" : "maxDate";
				var instance = $(this).data("datepicker");
				var date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, selectedDate,instance.setting);
				dates.not(this).datepicker("option", option, date);


            }





		});

	});

</script>

<script type="text/javascript">
	$(function() {
		$("button, input:submit, a", ".demo").button();

		$("a", ".demo").click(function() { return false; });
	});
</script>
 
</head>

<body class="blog">
<script type="text/javascript" src="js/validationStatus.js"> </script>

<!-- Fichier contenant quelques fonctions javascripts utiles un peu partout
Contient en outres la combobox permettant de choisir le theme extJS
Contient le template utilis� pour les combobox recherchant dans la base de donn�es (template = apparance + champs a selectionner)
Contient le template de la fonction utilis�e lors du click sur submit d'un formulaire -->
<script type="text/javascript" src="js/global.js"></script>

<!-- Contient le formulaire de contact en bas de la page -->
<script type="text/javascript" src="js/contact.js"></script>

<!-- Cela concerne les icones du menu de la datagrid -->
<script type="text/javascript" src="js/grid/menu/EditableItem.js"></script>
<script type="text/javascript" src="js/grid/menu/RangeMenu.js"></script>

<!-- Filtres utilis�s dans la datagrid pour trier les donn�es par genre-->
<script type="text/javascript" src="js/grid/filter/GridFilters.js"></script>
<script type="text/javascript" src="js/grid/filter/Filter.js"></script>
<script type="text/javascript" src="js/grid/filter/StringFilter.js"></script>
<script type="text/javascript" src="js/grid/filter/DateFilter.js"></script>
<script type="text/javascript" src="js/grid/filter/ListFilter.js"></script>
<script type="text/javascript" src="js/grid/filter/NumericFilter.js"></script>
<script type="text/javascript" src="js/grid/filter/BooleanFilter.js"></script>


<!-- Fichiers d'administration-->
<script type="text/javascript" src="js/ajouter_utilisateur.js"></script>
<script type="text/javascript" src="js/modifier_utilisateur.js"></script>

<!--Datagrid-->
<script type="text/javascript" src="js/grid/datagrid.js"></script>

<script>
    function globale()
    {
    win_datagriduser.show();
    }
</script>

<div id="div_datagrid"></div>
<div id="div_datagriduser"></div>
<div id="div_ajout"></div>
<div id="div_modif"></div>

<div id="popup_contact"></div>

<div id="bg-container">
    <div id="sliding-navigation3">
	<ul>
		<li><a href="MessageRapide.jsp">Lancement rapide</a></li>
		<li><a href="NouvelleCampagne.jsp">Nouvelle campagne</a></li>
		<li><a href="OuvrirCampagne.jsp">Ouvrir campagne</a></li>
		<li><a href="CreditRestant.jsp">Cr&eacute;dit restant</a></li>
		<li><a href="#">Alimenter mon compte</a></li>


	</ul>
</div>

<div id="container">

	<div id="logo"><img src="images/silver.png" alt="" /></div><!--LOGO ENDS-->
    


<div id="navigation">
	 <ul class="nav-links">
        <%
        if (user!=null)
          {
        if(user.equals("admin"))
        {%>
             <li><a href="javascript:void(0);" onclick="globale()">Admin</a></li>
             <%}}
        %>
	   <li><a href="Aide.jsp" title="">Aide</a></li>
       <li><a href="deconnexion.jsp" title="">D&eacute;connexion</a></li>
    </ul>
</div><!--NAVIGATION ENDS-->


<div id="page-wrap">
         <ul class="dropdown">

        	        
        	        <li><a href="#">Destinataires</a>

        <ul class="sub_menu">
					
        			
        			<li><a href="listeparam.jsp">liste param.</a></li> <li><a href="NouvelleListe.jsp">Nouvelle liste</a></li>
        			<li><a href="OuvrirListe.jsp">Ouvrir une liste</a></li>
        			<li><a href="BlackListes.jsp">Blacklist</a></li>
      	</ul>
        </li>
        	        <li><a href="#">Mes messages </a>

        <ul class="sub_menu">
        			<li><a href="MessageRapide.jsp">Message rapide</a></li>
        			<li><a href="MessagePredefini.jsp">Message pr&eacute;d&eacute;fini</a></li>
        </ul>
        </li>
        	         <li><a href="#">Mes messages Arabe </a>       <ul class="sub_menu">       			<li><a href="MessageRapidearabe.jsp">Message rapide Arabe</a></li>	<li><a href="MessagePredefiniarabe.jsp">Message pr&eacute;d&eacute;fini Arabe</a></li>      </ul>  </li> 
                          <li><a href="#">Statistiques</a>

        <ul class="sub_menu">
        			<li><a href="StatistiquesEnvoi.jsp">Statistiques Envoi</a></li>
        			<li><a href="StatistiqueEnvoiACK.jsp">Statistiques ACK</a></li>
        </ul>
        </li>
      	</ul>
	</div>



<div id="page-top">
  <img src="images/page_top.png" alt=""/>
</div><!--PAGE TOP ENDS-->

<div id="page-middle">
	<div id="page-content">

   <div id="page-title">
   <h1>Statistiques </h1>

   </div><!--PAGE TITLE ENDS-->
   <!--calandrier-->

<br /><br /><br /><br /><br />
<div style="padding:13px 55px; ">
    <form id="stat" name="stat" action="StatistiqueACK.jsp">
        <fieldset>
<label for="from">Date de d�but</label>
<input type="text" id="from" name="date_min" value="" class="validate[required]"/>

<span style="padding:10px 20px; ">
<label for="to">Date de fin</label>
<input type="text" id="to" name="date_max" value="" class="validate[required]"/>
</span>

<input name="Submit" type="submit" value="stat"/>
</fieldset>
</form>

</div>



  <%

             Gerer_Date gerer = new Gerer_Date();

	String date_debut = gerer.getJour2();
	String date_fin = gerer.getJour2();

        try
	{
		Enumeration e = request.getParameterNames();
 		while( e.hasMoreElements() ) {

    		String un_parametre = (String) e.nextElement();

			if(un_parametre.equals("date_min"))
				date_debut = request.getParameter("date_min");
			if(un_parametre.equals("date_max"))
				date_fin = request.getParameter("date_max");



 		}
	}
	catch(Exception e)	{	out.print(e.toString()); }

              String[] date_min_c = date_debut.split("-");
              String[] date_max_c = date_fin.split("-");

              String date_min_converti = date_min_c[2]+"-"+date_min_c[1]+"-"+date_min_c[0];
              String date_max_converti = date_max_c[2]+"-"+date_max_c[1]+"-"+date_max_c[0];

             Diffusion diffusion = new Diffusion();

         Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        Vector table = new Vector();
        table = diffusion.data(sql, date_min_converti, date_max_converti,user);
        int delevrer = diffusion.getTotalSMSByStatus("DELIVRD",date_min_converti, date_max_converti);
        int rejectd = diffusion.getTotalSMSByStatus("REJECTD",date_min_converti, date_max_converti);
        int enroute = diffusion.getTotalSMSByStatus("ENROUTE",date_min_converti, date_max_converti);
		
		
        sql.Fermer_Cnn();
        

              %>
  
<center>
    <%
    String total=""+table.size();
%>
<br/>
<center>Nombre des SMS  : <%= total%></center>
<center><small>Nombre des SMS DELIVRER  : <%= delevrer%></small></center><br/>
		<center><small>Nombre des SMS  REJETER  : <%= rejectd%></small></center><br/>
		<center><small>Nombre des SMS EN ROUTE  : <%= enroute%></small></center>   
    <br/><br/><br/>
<table border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center" valign="top"><center><strong> Num�ro </strong></center></td>
    <td align="center" valign="top"><center><strong> Message </strong></center></td>
	<td align="center" valign="top"><center><strong> Date </strong></center></td>
	<td align="center" valign="top"><center><strong> Heure </strong></center></td>
        <td align="center" valign="top"><center><strong> Status </strong></center></td>
</tr>

<% for (int i = 0; i < table.size(); i++) {
	String gsm = ( (HistoriqueEnvoi) table.elementAt(i)).getNumber();
        String msg = ( (HistoriqueEnvoi) table.elementAt(i)).getMessage();
        String date = ( (HistoriqueEnvoi) table.elementAt(i)).getDateEnvoi().substring(0, 10);
        String heure = ( (HistoriqueEnvoi) table.elementAt(i)).getHeure();
        String status = ( (HistoriqueEnvoi) table.elementAt(i)).getStatus();
        if (status==null||status.equals("vide"))
            status="En cours";
        
	if (gsm.startsWith("216")){
		gsm = gsm.substring(3);
		}
		if (gsm.startsWith("+216")){
		gsm = gsm.substring(4);
		}
		%>
  <tr>
   <td align="center" valign="top"><%= gsm%></td>
   <td align="center" valign="top"><%= msg%></td>
   <td align="center" valign="top"><%= date%></td>
   <td align="center" valign="top"><%= heure%></td>
   <td align="center" valign="top"><%= status%></td>
  </tr>
  
  <%} %>
      
</table>  
</center>
  
 
<br />

<div id="content">
    <p class="welcome-text">&nbsp;</p>
    <p class="welcome-text">&nbsp;</p>
</div><!--CONTENT ENDS-->

</div><!--PAGE CONTENT ENDS-->
    <!--SIDEBAR ENDS-->
</div><!--PAGE MIDDLE ENDS-->

<div id="page-bottom">
  <img src="images/page_bottom.png" alt=""/>
</div><!--PAGE BOTTOM ENDS-->

<div id="footer">
  <p class="footer">Designed by <a href="http://www.getwireless.com.tn/">GET Wireless</a> 2010 V2.1</p>
</div><!--FOOTER ENDS-->

</div><!--CONTAINER ENDS-->
</div>


</body>

</html>