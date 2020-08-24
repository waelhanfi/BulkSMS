/*************************************************************************************************************
*************************************************** log.js *****************************************************

Fichier concernant le formulaire de connexion u 1er tutoriel. (l�gerement adapt� pour correspondre avec le 2nd tuto)

auteur : p.hoffmann - mai 2009
*************************************************************************************************************/

function fct_submit_finit(btn){
	//Fonction pour rediriger l'utilisateur sur la m�me page o� il se trouve.
	win_login.hide();
}

//Fonction appel�e en cliquant sur submit du formulaire
function fct_submit() {
	var sb = Ext.getCmp('form-statusbar');		//on instancie la status bar en bas a gauche de la fenetre
	
	if (login.form.isValid()) {					//si le formulaire dans login est valide
	
		sb.showBusy('Traitement en cours');				//on y affiche un message de chargement
		login.getEl().mask();						//on applique un masque gris sur la fenetre nomm�e login (contenant le formulaire)
		
		login.form.submit({						//similaire � la m�thode post
			url: 'extjs/log_post.php',				//URL de la page sur laquelle on effectue la m�thode post
			method: 'POST',
			
			reset: false,						//pour ne pas reseter les formulaires en cas d'echec
				//Si une erreure est survenue lors du POST
			failure: function(result, action) {
				obj = Ext.util.JSON.decode(action.response.responseText);		//L'objet JSON vas rechercher dans lire le r�sultat du POST et le d�coder
				var message = obj.errors.reason;	//S'il s'agit d'une phrase g�n�rale on l'affiche		(errors = type d'action ; reason : nom de l'action, on peut nommer cela comme on veut dans le formulaire post)
					//Sinon on affiche le message d'erreur
				if (message == undefined) message = obj.errors[0].msg;		//si le message n'inclue pas un ID pr�cis d'un champ de notre formulaire, on affiche simplement un message d'erreur g�n�ral dans notre status bar, et non a droite du champ concern�
				
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
					text:"Authentification r�ussie", 
					iconCls:'',
					clear: true
				});
				
				Ext.MessageBox.alert("Authentification r�ussie", message,fct_submit_finit);		//msgbox affichant le message de connexion, puis redirige l'utilisateur s'il clique sur ok
				
			} 
		});
		login.getEl().unmask();			//on enl�ve le masque de notre formulaire
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
	id: 'login',				//id de la fen�tre
	frame: true, 				//pour que tous les items soient dans la m�me frame
	autoWidth:true,
	autoHeight:true,
	labelWidth:  135, 			//largeur des labels des champs
	defaults: {width: 230}, 	//largeur des champs
	labelAlign: 'right',			//les labels s'aligneront a droite		
	bodyCfg: {tag:'center', cls:'x-panel-body'},		//on aligne tous les champs au milieu de la fen�tre
	bodyStyle: 'padding:5p;margin:0px;', 

	items: [{		//Ici, on affiche � la suite tous les champs que l'on veut mettre
	xtype: 'textfield',			// = champ de texte
		fieldLabel: 'Adresse Email', 		// = label de description du champ
		id: 'email', 
		name: 'email', 
		vtype: 'email', 	//V�rification type : met un masque d'adresse email sur ce champ
		vtypeText: 'Votre adresse email doit �tre de la forme de "user@domain.com"',
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

//Ce panel contiendra le panel pr�c�dent qui est le formulaire, sauf qu'en bas de celui ci figure la status bar, permettant d'afficher le status de la connexion (chargement ....)
var login_total = new Ext.Panel({
	autoWidth:true,
	autoHeight:true,
	layout: 'fit',
	items: login,		//On met dans ce panel le panel de login
	bbar: new Ext.StatusBar({
		id: 'form-statusbar',
		defaultText: 'Pr�t',
		plugins: new Ext.ux.ValidationStatus({form:'login'})
	})
});


var win_login;
if(!win_login){			//Si la fen�tre de connexion n'existe pas, on la cr�e
	win_login = new Ext.Window({
		title: 'Authentification',		//titre de la fen�tre
		el:'popup_log_window',		
//********* el = id du div dans le code html de la page qui contiendra la popup ! ************//
		width:450,
		closable: true,
		autoHeight:true,
		modal: true, 			//Grise automatiquement le fond de la page
		closeAction:'hide',
		items: login_total		//On met dans cette fen�tre le panel pr�c�dent
	});
}

function show_win_login(){
	win_login.show();
}