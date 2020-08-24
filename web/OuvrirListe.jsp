<%@ page import="businessGetSilver.*, daoGetSilver.*,java.util.List.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
		<link rel="stylesheet" href="css/stylemenu.css" type="text/css" media="screen, projection"/>
		<link type="text/css" href="css/themename/jquery-ui-1.8.custom.css" rel="Stylesheet" />
		<link type="text/css" href="css/pos_form.css" rel="Stylesheet" />
		<link type="text/css" href="css/jquery.ui.dialog.js" rel="Stylesheet" />
		<link type="text/css" href="css/style_mv.css" rel="Stylesheet" />
<link type="text/css" rel="stylesheet" href="css/extjs/css/combos.css" />
                <link type="text/css" rel="stylesheet" href="css/extjs/css/ext-all.css" />
                <link type="text/css" rel="stylesheet" href="css/styleutil.css" />

		<script type="text/javascript" src="js/jquery.js"></script>
	    <script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
	    <script type="text/javascript" language="javascript" src="js/jquery.dropdownPlain.js"></script>
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>

		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
	    <script type="text/javascript" src="js/jquery.ui.core.js"></script>
	    <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
		<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/jquery.ui.button.js"></script>
		<script src="js/jquery.validate.js" type="text/javascript"></script>



   <script>
    function listegsm()
     {

        document.nom_liste.action ="GsmListe.jsp";

      }
   </script>


<!--[if IE]>
	<style type="text/css">
	#grid_rows{border:1px solid;}
	</style>
	<![endif]-->

	<script type="text/javascript" src="js/ext-base.js"></script>
	<script type="text/javascript" src="js/ext-all.js"></script>


<script type="text/javascript">

$(document).ready(function () {
/*Menu G*/

$("#sliding-navigation3 ul li a").mouseover(function(){
		$(this).stop().animate({paddingLeft: '20px'},50);
			$(this).css({'color' : '#FF8830'});

		$(this).css({'background-color' : ' #181A1F'});
});

$("#sliding-navigation3 ul li a").mouseout(function(){
	$(this).stop().animate({paddingLeft: '40px'},50);
	$(this).css({'color' : '#FFFFFF'});
	$(this).css({'background-color' : '#2E3339'});
});

});
</script>



<script type="text/javascript">
	$(function() {
		$("button, input:submit, a", ".demo").button();

		$("a", ".demo").click(function() { return false; });
	});
</script>

<%

	//charger les attribus de l'abonné
	 Sql sql = new Sql(Sql.ADR_IP, Sql.NOM_BASE, Sql.LOGIN, Sql.PWD);
        sql.Open_Connexion();
        DaoListe dao = new DaoListe();
        Liste [] listes = dao.getAllInTab(sql, user);
	sql.Fermer_Cnn();
%>


<script language="javascript" type="text/javascript">
 jQuery.validator.setDefaults({
		success: "valid"
});

 $.validator.setDefaults({


	highlight: function(input) {
		$(input).addClass("ui-state-highlight");
	},
	unhighlight: function(input) {
		$(input).removeClass("ui-state-highlight");
	}
});

$().ready(function() {
	$.fn.themeswitcher && $('<div/>').css({
		position: "absolute",
		right: 10,
		top: 10
	}).appendTo(document.body).themeswitcher();


	// validate the comment form when it is submitted
	$("#commentForm").validate();


	// validate signup form on keyup and submit
	$("#nom_liste").validate({


		rules: {


			listes: {
				required: true



			},

			agree: "required"
		},
		messages: {

		listes:{
				required: "Slectionnez une liste"

		}

	}});

	$("#nom_liste input:not(:submit)").addClass("ui-widget-content");


	$(":submit").button();


       }
);



</script>





<style type="text/css">


#nom_liste label.error, #nom_liste button.submit { margin-left: 253px; }
#nom_liste{ width: 570px; }
#nom_liste label.error {
	margin-left: 54px;
	width: auto;
	display:block;
	position:absolute;
	margin-top:10px;


}



label.error {
		background: url(images/unchecked.gif) no-repeat;
		padding-left: 16px;
		margin-left: .3em;
                color: red;
	}
label.valid {
		background: url(images/checked.gif) no-repeat;
		display: block;
		width: 16px;
		height: 16px;

}
</style>
<!--[if IE ]>
<style type="text/css">
.box {
  display: inline;
  vertical-align: top;
}
</style>
<![endif]-->

</head>

<body class="blog">
<script type="text/javascript" src="js/validationStatus.js"> </script>

<!-- Fichier contenant quelques fonctions javascripts utiles un peu partout
Contient en outres la combobox permettant de choisir le theme extJS
Contient le template utilisé pour les combobox recherchant dans la base de données (template = apparance + champs a selectionner)
Contient le template de la fonction utilisée lors du click sur submit d'un formulaire -->
<script type="text/javascript" src="js/global.js"></script>

<!-- Contient le formulaire de contact en bas de la page -->
<script type="text/javascript" src="js/contact.js"></script>

<!-- Cela concerne les icones du menu de la datagrid -->
<script type="text/javascript" src="js/grid/menu/EditableItem.js"></script>
<script type="text/javascript" src="js/grid/menu/RangeMenu.js"></script>

<!-- Filtres utilisés dans la datagrid pour trier les données par genre-->
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
		<li><a href="menuAdmin.jsp">Accueil</a></li>	<li><a href="MessageRapide.jsp">Lancement rapide</a></li>
		<li><a href="NouvelleCampagne.jsp">Nouvelle campagne</a>	</li><li><a href="NouvelleCampagneArabe.jsp">Nouvelle campagne Arabe</a></li></li>
		<li><a href="OuvrirCampagne.jsp">Ouvrir campagne</a></li>
		<li><a href="CreditRestant.jsp">Cr&eacute;dit restant</a></li>
		


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
   <h1>MES LISTES </h1>

   </div><!--PAGE TITLE ENDS-->
   <!--DataGrid-->
   <h4  style="margin-left:22px;">Veuillez choisir une liste:</h4>
   <form class="styl_form2" id="nom_liste" name="nom_liste" >

 <div class="demo"  style="height:3px; margin-top:50px;">
  <label for="nom_liste" style="margin-right:5px;">Liste</label>


          <select id="listes" name="listes" style=" width:200px"  onchange="maliste()">
            <option value="">Selectionnez une liste</option>
         <% if (listes.length>0)
              {
		for (int i=0; i<listes.length;i++)
                  { %>
   <option value="<%=listes[i].getCodeListe()%>"><%= listes[i].getNom()%></option>
                          <% }}%>
          </select>


		    <button type="submit" style="margin-left:10px; position:static;" onclick="listegsm()">OK</button>


 </div><!-- End demo -->

</form>
<br /><br /><br /><br /><br />
<div id="EditorPanel" style="margin-left:170px"></div>

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