


//Fonction appelée en cliquant sur submit du formulaire
function submit_ajout_utilisateur() {
	var sb = Ext.getCmp('ajout_utilisateur_statusbar');		//on instancie la status bar en bas a gauche de la fenetre
	templateSubmit(sb,nouvel_utilisateur, ajout_utilisateur_finit, "Enregistrement r�ussi");
}

function ajout_utilisateur_finit() {
	nouvel_utilisateur.form.reset();
	win_ajout_utilisateur.hide();
	ugridStore.reload();
}
   

//nouvel_utilisateur, il s'agit d'un panel contenant le formulaire avec les champ login, password...
var nouvel_utilisateur = new Ext.FormPanel({
	id: 'nouvel_utilisateur',
	frame: true, 
	autoWidth: true,
	autoHeight:true,
	labelWidth: 200, 
	labelAlign: 'right',
	bodyCfg: {tag: 'center', cls: 'x-panel-body'},
	defaults: {width: 280, msgTarget: 'side'}, 
	bodyStyle: 'padding:5px 5px 0', 
	
	items: [{
			anchor:'95%',
			html: '<div id="titre_ajout">Cr�ation d\'un utilisateur</div>'
		},{
			xtype: 'textfield',
			fieldLabel: 'Votre Login',
			id: 'utilisateur_ajout_login',
			name: 'utilisateur_ajout_login',
			allowBlank: false,
			blankText:'Veuillez entrer votre Login !'
		},{ 
			xtype: 'textfield',
			fieldLabel: 'Votre mot de passe', 
			id: 'utilisateur_ajout_pass', 
			name: 'utilisateur_ajout_pass', 
			allowBlank: false, 
			minLength:4,
			minLengthText: 'Le mot de passe doit au moins contenir 4 caract�res',
			inputType: 'password',
			blankText:'Veuillez entrer votre mot de passe'
		},{ 
			xtype: 'textfield',	
			fieldLabel: 'Confirmation de mot de passe', 
			id: 'utilisateur_ajout_pass2',
			name: 'utilisateur_ajout_pass2',
			vtype: 'password',
			inputType: 'password',
			initialPassField: 'utilisateur_ajout_pass'
		},{
			html:'<br />'
		},
			new Ext.form.ComboBox({
				fieldLabel: 'Rang',
				id:'utilisateur_ajout_role',
				name:'utilisateur_ajout_role',
				store: new Ext.data.SimpleStore({
						fields: ['id_role', 'nom_role'],
						data : [['1','user'],['3','admin']]
					}),
				valueField:'nom_role',
				displayField:'nom_role',
				hiddenName: 'utilisateur_ajout_role_value',
			
				typeAhead: false,
				triggerAction: 'all',
				mode:'local',
				selectOnFocus:true,
				allowBlank: false,
				blankText:'Choisissez votre rang'
		}),{
			xtype: 'numberfield',
			fieldLabel: 'Credit',
			id: 'utilisateur_ajout_credit',
			name: 'utilisateur_ajout_credit',
			allowBlank: false,
			blankText:'Veuillez entrer le credit !'
		},
                {
			xtype: 'hidden',
			id: 'utilisateur_ajout_hidden',
			name: 'utilisateur_ajout_hidden'
	}],
	buttons: [{
		xtype: 'button',
			text: 'Enregistrer', 
			handler: submit_ajout_utilisateur
		},{
			xtype: 'button',
			text: 'Annuler', 
			handler: function() {win_ajout_utilisateur.hide();}
	}]
}); 

//Ce panel contiendra le panel précédent qui est lo formulaire, sauf qu'en bas de celui ci figure la status bar, permettant d'afficher le status de la connexion (chargement ....)
var nouvel_utilisateur_total = new Ext.Panel({
	autoWidth: true,
	autoHeight:true,
	items: nouvel_utilisateur,		//On met dans ce panel le panel de login
	bbar: new Ext.StatusBar({
		id: 'ajout_utilisateur_statusbar',
		defaultText: 'Pret',
		plugins: new Ext.ux.ValidationStatus({form:'nouvel_utilisateur'})
	})
});

var win_ajout_utilisateur;
if(!win_ajout_utilisateur){							//Si la fenetre de connexion n'éxiste pas, on la crée
	win_ajout_utilisateur = new Ext.Window({
		id: 'win_ajout_utilisateur',
		modal: true,
		resizable: false,
		title: 'Ajout d\'un utilisateur',		//titre de la fenetre
		el:'div_ajout',			//********* el = id du div dans le code html de la page qui contiendra la popup ! ************//
		width:600,
		autoHeight:true,
		closeAction:'hide',
		plain: true,
		items: nouvel_utilisateur_total			//On met dans cette fenetre le panel précédent
	});
}

function show_ajout()
{
	win_ajout_utilisateur.show();
}