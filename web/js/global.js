


Ext.QuickTips.init();							//nécessaire pour initialiser les messages d'erreur et les infobulles
Ext.form.Field.prototype.msgTarget = 'side';	//nécessaire pour initialiser les messages d'erreur et les infobulles

var numeric = /^[0-9]+$/;								//regex pour laissee que des chiffres
var alphanum2 = /^[a-zA-Z0-9_Ã Ã¢Ã¤Ã©Ã¨ÃªÃ«Ã®Ã¯Ã´Ã¶Ã¹Ã»Ã¼Ã§\s\-]+$/;		//regex pour laissee des lettre chiffre + accents

Ext.apply(Ext.form.VTypes, {

   numeric: function(val) {
    return numeric.test(val);
  },
  
  alphanum2: function(val) {
    return alphanum2.test(val);
  },
  
  password: function(val, field) {
    if (field.initialPassField) {
      var pwd = Ext.getCmp(field.initialPassField);
      return (val == pwd.getValue());
    }
    return true;
  },
  
  passwordText: 'Les mots de passe ne correspondent pas'
});


	

//Fonction template utilisé pour valider un formulaire.
function templateSubmit(sb, formulaire, fonctionOK, messageAffiche){

	if (formulaire.form.isValid()) {					//si le formulaire dans login est valide
	
		sb.showBusy('Traitement en cours');				//on y affiche un message de chargement
		formulaire.getEl().mask();						//on applique un masque gris sur la fenetre nommée login (contenant le formulaire)
		
		formulaire.form.submit({						//similaire à la méthode post
			url: './AddUser',				//URL de la page sur laquelle on effectue la méthode post
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
					text:messageAffiche, 
					iconCls:'',
					clear: true
				});
				
				Ext.MessageBox.alert(messageAffiche, message,fonctionOK);		//msgbox affichant le message de connexion, puis redirige l'utilisateur s'il clique sur ok
				
			} 
		});
		formulaire.getEl().unmask();			//on enlève le masque de notre formulaire
	} else {		//Si le formulaire n'est pas valide, on change la phrase dans notre status bar
		sb.setStatus({
			text:'Erreur : Formulaire non valide.', 
			iconCls:'',
			clear: true
		});
	}
	
}
///cas mise a jour utilisateur ///////////////
function templateSubmitUpdate(sb, formulaire, fonctionOK, messageAffiche){

	if (formulaire.form.isValid()) {					//si le formulaire dans login est valide

		sb.showBusy('Traitement en cours');				//on y affiche un message de chargement
		formulaire.getEl().mask();						//on applique un masque gris sur la fenetre nommée login (contenant le formulaire)

		formulaire.form.submit({						//similaire à la méthode post
			url: './UpdateUser',				//URL de la page sur laquelle on effectue la méthode post
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
					text:messageAffiche,
					iconCls:'',
					clear: true
				});

				Ext.MessageBox.alert(messageAffiche, message,fonctionOK);		//msgbox affichant le message de connexion, puis redirige l'utilisateur s'il clique sur ok

			}
		});
		formulaire.getEl().unmask();			//on enlève le masque de notre formulaire
	} else {		//Si le formulaire n'est pas valide, on change la phrase dans notre status bar
		sb.setStatus({
			text:'Erreur : modification non valide.',
			iconCls:'',
			clear: true
		});
	}

}
//fin mise a jour


//Store de la combobox dynamique permettant d'afficher dynamiquement les utilisateurs.
//Ceci va rechercher dans ka bdd les utilisateurs en postant dans le fichier form_search_utilisateur
var utilisateurStore= new Ext.data.Store({
    
	proxy: new Ext.data.HttpProxy({ url: './GetUserInfo'}),
	reader: new Ext.data.JsonReader({ root: 'rows',totalProperty: 'results',id: 'userId'}, [
		                                                {name:"userId",type:"int"},
								{name:"userName"},
								{name:"userPassword"},
								{name:"userRole"}
	])
});

//Template qui sert à définir le rendu qu'aura la combobox
var utilisateurTemplate = new Ext.XTemplate(
'<tpl for="."><div class="search-item">',
	'<span style="font-size:13px; font-weight:bold;"><span class="selectTitle">{userName}</span> - {userPassword} </span>',
	'<span style="font-size:10px;">{userRole}</span>',
'</div></tpl>'
);

Ext.ux.ThemeCombo = Ext.extend(Ext.form.ComboBox, {
	// configurables
	themeBlueText: 'Ext Blue Theme'
	,themeGrayText: 'Gray Theme'
	,themeDarkGrayText: 'Dark Gray Theme'
	,themeSlateText: 'Slate Theme'
	,themeGreenText: 'Green Theme'
	,themeMidnightText: 'Midnight Theme'
	,themeIndigoText: 'Indigo Theme'
	,themeSilverCherryText: 'Silver Cherry Theme'
	,themeBlackText: 'Black Theme'
	,themeVar:'theme'
	,selectThemeText: 'Select Theme'
	,lazyRender:true
	,lazyInit:true
	,cssPath:'css/extjs/css/'
	,initComponent:function(){
		Ext.apply(this,{
			store: new Ext.data.SimpleStore({
				fields: ['themeFile', {name:'themeName', type:'string'}]
				,data: [
				['xtheme-default.css', this.themeBlueText]
				,['xtheme-slate.css', this.themeSlateText]
				,['xtheme-green.css', this.themeGreenText]
				,['xtheme-midnight.css', this.themeMidnightText]
				,['xtheme-indigo.css', this.themeIndigoText]
				,['xtheme-silverCherry.css', this.themeSilverCherryText]
				,['xtheme-gray.css', this.themeGrayText]
				,['xtheme-darkgray.css', this.themeDarkGrayText]
				,['xtheme-black.css', this.themeBlackText]
				
				]
			})
			,valueField: 'themeFile'
			,displayField: 'themeName'
			,triggerAction:'all'
			,mode: 'local'
			,forceSelection:true
			,editable:false
			,fieldLabel: this.selectThemeText
		});
		// end of apply  
		this.store.sort('themeName');
		// call parent  
		Ext.ux.ThemeCombo.superclass.initComponent.apply(this, arguments);
		if(false !== this.stateful && Ext.state.Manager.getProvider()) {
			this.setValue(Ext.state.Manager.get(this.themeVar) || 'xtheme-silverCherry.css');
		}
		else {
			this.setValue('xtheme-default.css');
		}
	}
	,setValue:function(val) {
		Ext.ux.ThemeCombo.superclass.setValue.apply(this, arguments);
		// set theme 
		Ext.util.CSS.swapStyleSheet(this.themeVar, this.cssPath + val);
		if(false !== this.stateful && Ext.state.Manager.getProvider()) {
			Ext.state.Manager.set(this.themeVar, val);
		}
}
});
Ext.reg('themecombo', Ext.ux.ThemeCombo);
