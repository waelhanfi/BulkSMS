<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Get Silver</title>

      
        <link rel="StyleSheet" type="text/css" href="css/design.css">
        <link rel="StyleSheet" type="text/css" href="css/stylevibration.css">
        <link type="text/css" href="css/themename/jquery-ui-1.8.custom.css" rel="Stylesheet" />
      
		
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="js/vibrate.js"></script>
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
		
<script type="text/javascript">
	$(function() {
		$("button, input:submit, a", ".demo").button();

		$("a", ".demo").click(function() { return false; });
	});
	</script> 
	
</head>
<body>
       
	
           
         <h1> Authentification </h1>
		 
   <div class="content-box">
              
  <div id="login">
               
<form action="checkLogin.jsp" method="post" id="loginform">
	<label for="username">Nom d'utilisateur:</label>
	<input type="text" name="username" id="username" />
	<label for="pwd">Mot de passe :</label>
	<input type="password" name="pwd" id="pwd" />
                
 <div class="demo" align="right">
		<input type="submit" name="Submit" id="submit_butt" value="connexion" />
 </div>

</form>

</div>
</div>
           

<script type="text/javascript">

$(document).ready(function() {

	$("#submit_butt").submit( function() {

		// configurations for the buzzing effect. Be careful not to make it too annoying!
		var conf = {
				frequency: 5000,
				spread: 5,
				duration: 600
			};

		/* do your AJAX call and processing here...
			....
			....
		*/

		// this is the call we make when the AJAX callback function indicates a login failure
		$("#login").vibrate(conf);

		// let's also display a notification
		if($("#errormsg").text() == "")
			$("#loginform").append('<p id="errormsg">Invalid username or password!</p>');

		// clear the fields to discourage brute forcing :)

		$("#username").val("");
		$("#pwd").val("");
	});

});


</script>

</body>
</html>
