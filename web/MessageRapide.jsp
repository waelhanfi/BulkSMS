<%@ page import="businessGetSilver.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<head>

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
        <link rel="stylesheet" href="styles.css" type="text/css"/>
		<link type="text/css" href="css/themename/jquery-ui-1.8.custom.css" rel="Stylesheet"/>
		<link type="text/css" href="css/jquery.ui.dialog.js" rel="Stylesheet" />
		<link type="text/css" href="css/style_mv.css" rel="Stylesheet" />

<link type="text/css" rel="stylesheet" href="css/extjs/css/combos.css" />
                <link type="text/css" rel="stylesheet" href="css/extjs/css/ext-all.css" />
                <link type="text/css" rel="stylesheet" href="css/styleutil.css" />

		<script type="text/javascript" src="js/jquery.js"></script>
	    <script type="text/javascript" src="js/jquery-1.3.1.min.js"></script>
	    <script type="text/javascript" src="js/jquery.dropdownPlain.js"></script>
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>


		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
	    <script type="text/javascript" src="js/jquery.ui.core.js"></script>
	    <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
	    <script type="text/javascript" src="js/jquery.ui.resizable.js"></script>
		<script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/jquery.ui.button.js"></script>
		<script type="text/javascript" src="js/jquery.ui.button.js"></script>
		<script type="text/javascript" src="js/jquery.keyfilter-1.7.js"></script>
 	    <script type="text/javascript" src="js/query.easing.1.3.js"></script>



             <!Controle & form-->

           <script src="js/jquery.validationEngine-fr.js" type="text/javascript"></script>
  	   <script src="js/jquery.validationEngine.js" type="text/javascript"></script>
           <link rel="stylesheet" href="css/validationEngine.jquery.css" type="text/css" media="screen" title="no title" charset="utf-8" />
	   <link rel="stylesheet" href="css/template.css" type="text/css" media="screen" title="no title" charset="utf-8" />


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







<script language="Javascript" type="text/javascript">
// args : string moncontroletexte, int nbcar, string moncontroledecompte
// return : aucun
// Affecte à certains évènements d'un textarea, le contrôle de la longueur de son contenu
function LimiterTextArea(nom_controletexte, nbcar, nom_controledecompte)
{
	var moncontroletexte = document.getElementById(nom_controletexte);

	var moncontroledecompte = document.getElementById(nom_controledecompte);

	if (moncontroletexte && moncontroledecompte)
	{

		moncontroletexte.onclick = function(){TextAreaEstRempli(moncontroletexte, nbcar, moncontroledecompte)};
		moncontroletexte.onblur = function(){TextAreaEstRempli(moncontroletexte, nbcar, moncontroledecompte)};
		moncontroletexte.onkeyup = function(){TextAreaEstRempli(moncontroletexte, nbcar, moncontroledecompte)};
		moncontroletexte.onkeypress = function(){TextAreaEstRempli(moncontroletexte, nbcar, moncontroledecompte)};

		// *** Affichage du nombre de caractères restant
		if(moncontroledecompte.type)
			moncontroledecompte.value = NbCarRestant(moncontroletexte, nbcar);			// Pour un input de formulaire
		else
			moncontroledecompte.innerHTML = NbCarRestant(moncontroletexte, nbcar);	// Pour un élément HTML

	}
}

// TextAreaEstRempli
// args : textarea moncontroletexte, int nbcar, element_HTML moncontroledecompte
// return : bool
// Renvoie vrai si le nombre de caractères maximum du textarea n'est pas atteint
function TextAreaEstRempli(moncontroletexte, nbcar, moncontroledecompte)
{
	if (moncontroletexte)
	{
		if (moncontroletexte.value.length <= nbcar)
		{
			//alert("pas rempli");
			// mes actions ...

			// *** Affichage du nombre de caractères restant
			if(moncontroledecompte.type)
				moncontroledecompte.value = NbCarRestant(moncontroletexte, nbcar);
			else
				moncontroledecompte.innerHTML = NbCarRestant(moncontroletexte, nbcar);

			return true;
		}
		else
		{
			//alert("rempli");
			// mes actions ...

			// Affichage du nombre de caractères restant
			moncontroletexte.value = moncontroletexte.value.substr(0, nbcar);

			// *** Affichage du nombre de caractères restant
			if(moncontroledecompte.type)
				moncontroledecompte.value = NbCarRestant(moncontroletexte, nbcar);
			else
				moncontroledecompte.innerHTML = NbCarRestant(moncontroletexte, nbcar);

			return false;
		}
	}
}

// NbCarRestant
// args : textarea moncontroletexte, int nbcar
// return : int
// Renvoie le nombre de caractère à saisir
function NbCarRestant(moncontroletexte, nbcar)
{
	if (moncontroletexte.value.length)
		return new Number(nbcar - moncontroletexte.value.length);
	else
		return new Number(nbcar);
}
</script>







<script type="text/javascript">
	$(function() {
		$("button, input:submit, a", ".demo").button();

		$("a", ".demo").click(function() { return false; });
	});
</script>




	<style type="text/css">
		.ui-resizable-se {
			bottom: 13px;

		}
	</style>
	<script type="text/javascript">
	$(function() {
		$("#message").resizable({
			handles: "se"
		});
	});
	</script>


<style type="text/css">
#gris {
  position : absolute;
  top : 0;
  left : 0;
  width : 100%;
  height : 100%;
  background-color : #000;
  filter:alpha(opacity=50);   /* IE */
  -moz-opacity:0.5;           /* Firefox */
  opacity: 0.5;               /* standard CSS3 */
  -khtml-opacity: 0.5;        /* Konqueror */
   }
</style>




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
                <li><a href="menuAdmin.jsp">Accueil</a></li>
		<li><a href="MessageRapide.jsp">Lancement rapide</a></li>
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
        			<li><a href="NouvelleListe.jsp">Nouvelle liste</a></li>
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
          <li><a href="#">Mes messages Arabe</a>

        <ul class="sub_menu">
        			<li><a href="MessageRapidearabe.jsp">Message rapide</a></li>
        			<li><a href="MessagePredefiniarabe.jsp">Message pr&eacute;d&eacute;fini</a></li>
        </ul>
        </li>
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
    	<h1>MESSAGE RAPIDE</h1>
        </div><!--PAGE TITLE ENDS-->



<form class="formular" action="SucceEnvoi.jsp" >

     <label>
         <span>Num&eacute;ro : </span>
         <input class="validate[required,custom[onlyNumber]] ] text-input"  maxlength="8"   value="" name="num_gsm" id="num_gsm"  />
   </label>



     <label>
         <span>Message : </span>
         <textarea id="message" name="message"  onDrop="return false" onPaste="return false" class="validate[required,length[3,320]] " rows="5" cols="30"  name="msg"  ></textarea>
     </label>

<div id="controle_decompte" name="controle_decompte" class="decompte" style="padding:0px 180px; "></div>
			<script language="Javascript" type="text/javascript">
			LimiterTextArea('message', 320, 'controle_decompte');
		    </script>


 <div class="demo">

     <div style="float:right;">
     <button type="submit">Envoyer</button>
</div>
</div>
<br/><br/>
</form>


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
</div><!-- End demo -->



</body>

</html>