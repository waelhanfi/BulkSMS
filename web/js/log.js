/*************************************************************************************************************
*************************************************** log.js *****************************************************

Fichier concernant le formulaire de connexion u 1er tutoriel. (légerement adapté pour correspondre avec le 2nd tuto)

auteur : p.hoffmann - mai 2009
*************************************************************************************************************/

function fct_submit_finit(btn){
	//Fonction pour rediriger l'utilisateur sur la même page où il se trouve.
	win_login.hide();
}

//Fonction appelée en cliquant sur submit du formulaire
function fct_submit() {
	var sb = Ext.getCmp('form-statusbar');		//on instancie la status bar en bas a gauche de la fenetre
	
	if (login.form.isValid()) {					//si le formulaire dans login est valide
	
		sb.showBusy('Traitement en cours');				//on y affiche un message de chargement
		login.getEl().mask();						//on applique un masque gris sur la fenetre nommée login (contenant le formulaire)
		
		login.form.submit({						//similaire à la méthode post
			url: 'extjs/log_post.php',				//URL de la page sur laquelle on effectue la méthode post
			method: 'POST',
			
			reset: false,						//pour ne pas reseter les formulaires en cas d'echec
				//Si une erreure est survenue lors du POST
			failure: function(result, action) {
				obj = Ext.util.JSON.decode(action.response.responseText);		//L'objet JSON vas rechercher dans lire le résultat du POST et le décoder
				var message = obj.errors.reason;	//S'il s'agit d'une phrase générale on l'affiche		(errors = type d'action ; reason : nom de l'action, on peut nommer cela comme on veut dans le formulaire post)
					//Sinon on affiche le message d'erreur
				if (message == undefined) message = obj.errors[0].msg;		//si le message n'inclue pas un ID précis d'un champ de notre formulaire, on affiche simplement un message d'erreur général dans notre status bar, et non a droite du champ concerné
				
				sb.setStatus({					//On modifie le texte de notre status bar
					text:'Erreur : '+ message, 
					iconCls:'',
					clear: true
				});
			}, 
				//S'il n'y a pas eu d'erreur dans notre POST...
			success: function(result, action) {
				//Message de bienvenue
				obj = Ext.util.JSON.decode(action.response.responseText);
				var message = obj.msg.reason;		//on recherche le message que l'on souhaite afficher
				
				sb.setStatus({
					text:"Authentification réussie", 
					iconCls:'',
					clear: true
				});
				
				Ext.MessageBox.alert("Authentification réussie", message,fct_submit_finit);		//msgbox affichant le message de connexion, puis redirige l'utilisateur s'il clique sur ok
				
			} 
		});
		login.getEl().unmask();			//on enlève le masque de notre formulaire
	} else {		//Si le formulaire n'est pas valide, on change la phrase dans notre status bar
		sb.setStatus({
			text:'Erreur : Formulaire non valide.', 
			iconCls:'',
			clear: true
		});
	}
	
}

//Login, il s'agit d'un panel contenant le formulaire avec les champs login, password...
var login = new Ext.FormPanel({
	id: 'login',				//id de la fenêtre
	frame: true, 				//pour que tous les items soient dans la même frame
	autoWidth:true,
	autoHeight:true,
	labelWidth:  135, 			//largeur des labels des champs
	defaults: {width: 230}, 	//largeur des champs
	labelAlign: 'right',			//les labels s'aligneront a droite		
	bodyCfg: {tag:'center', cls:'x-panel-body'},		//on aligne tous les champs au milieu de la fenêtre
	bodyStyle: 'padding:5p;margin:0px;', 

	items: [{		//Ici, on affiche à la suite tous les champs que l'on veut mettre
	xtype: 'textfield',			// = champ de texte
		fieldLabel: 'Adresse Email', 		// = label de description du champ
		id: 'email', 
		name: 'email', 
		vtype: 'email', 	//Vérification type : met un masque d'adresse email sur ce champ
		vtypeText: 'Votre adresse email doit être de la forme de "user@domain.com"',
		//message si email non valide
		allowBlank: false,				//champ obligatoire pour poster le formulaire
		blankText:"Veuillez saisir votre adresse email."	//message si le champ n'est pas rempli
	},{ 
		xtype: 'textfield',
		fieldLabel: 'Mot de passe', 
		id: 'pass', 
		name: 'pass', 
		allowBlank: false, 
		inputType: 'password',
		blankText:"Veuillez saisir votre mot de passe."
	},{
		xtype:'checkbox',  
		boxLabel : ' ',
		fieldLabel: 'Se souvenir de moi',
		checked: true,
		name: 'save'
	},{ 
		xtype: 'hidden',
		id: 'connexion_hidden', 
		name: 'connexion_hidden'
	}],
	buttons: [{
		xtype: 'button',
		text: 'Se connecter', 
		handler: fct_submit
	}]
}); 

//Ce panel contiendra le panel précédent qui est le formulaire, sauf qu'en bas de celui ci figure la status bar, permettant d'afficher le status de la connexion (chargement ....)
var login_total = new Ext.Panel({
	autoWidth:true,
	autoHeight:true,
	layout: 'fit',
	items: login,		//On met dans ce panel le panel de login
	bbar: new Ext.StatusBar({
		id: 'form-statusbar',
		defaultText: 'Prêt',
		plugins: new Ext.ux.ValidationStatus({form:'login'})
	})
});


var win_login;
if(!win_login){			//Si la fenêtre de connexion n'existe pas, on la crée
	win_login = new Ext.Window({
		title: 'Authentification',		//titre de la fenêtre
		el:'popup_log_window',		
//********* el = id du div dans le code html de la page qui contiendra la popup ! ************//
		width:450,
		closable: true,
		autoHeight:true,
		modal: true, 			//Grise automatiquement le fond de la page
		closeAction:'hide',
		items: login_total		//On met dans cette fenêtre le panel précédent
	});
}

function show_win_login(){
	win_login.show();
}