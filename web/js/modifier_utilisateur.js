

//Fonction appelée en cliquant sur submit du formulaire
function submit_modif_utilisateur() {

var sb = Ext.getCmp('modif_utilisateur_statusbar');		//on instancie la status bar en bas a gauche de la fenetre
	templateSubmitUpdate(sb,modif_utilisateur, modif_utilisateur_finit, "Enregistrement r�ussi");

}

function modif_utilisateur_finit() {
	modif_utilisateur.form.reset();
	win_modif_utilisateur.hide();
	ugridStore.reload();
}
   
//Formulaire en lui meme
var modif_utilisateur = new Ext.FormPanel({
	id: 'modif_utilisateur',
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
			html: '<div id="titre_ajout">Modification d\'un utilisateur</div>'
		},
                {
			xtype: 'textfield',
			fieldLabel: 'Votre login',
			id: 'utilisateur_modif_login',
			name: 'utilisateur_modif_login',
			allowBlank: false,
			blankText:'Veuillez entrer votre login !'
		},
                {
			xtype: 'textfield',
			fieldLabel: 'Votre mot de passe', 
			id: 'utilisateur_modif_pass', 
			name: 'utilisateur_modif_pass', 
			allowBlank: false, 
			minLength:4,
			minLengthText: 'Le mot de passe doit au moins contenir 4 caractères',
			inputType: 'password',
			blankText:'Veuillez entrer votre mot de passe'
		},
                {
			xtype: 'textfield',
			fieldLabel: 'Votre mot de passe',
			id: 'utilisateur_modif_pass',
			name: 'utilisateur_modif_pass',
			allowBlank: false,
			minLength:4,
			minLengthText: 'Le mot de passe doit au moins contenir 4 caractères',
			inputType: 'password',
			blankText:'Veuillez entrer votre mot de passe'
		},{
			xtype: 'textfield',	
			fieldLabel: 'Confirmation de mot de passe', 
			id: 'utilisateur_modif_pass2',
			name: 'utilisateur_modif_pass2',
			vtype: 'password',
			inputType: 'password',
			initialPassField: 'utilisateur_modif_pass'
		},{
			html:'<br />'
		},
			new Ext.form.ComboBox({
				fieldLabel: 'Rang',
				id:'utilisateur_modif_rang',
				name:'utilisateur_modif_rang',
				store: new Ext.data.SimpleStore({
						fields: ['id_rang', 'nom_rang'],
						data : [['1','user'],['3','admin']]
					}),
				valueField:'nom_rang',
				displayField:'nom_rang',
				hiddenName: 'utilisateur_modif_rang_value',
				typeAhead: false,
				triggerAction: 'all',
				mode:'local',
				selectOnFocus:true,
				allowBlank: false,
				blankText:'Choisissez votre rang'
		}),{
			xtype: 'numberfield',
			fieldLabel: 'Credit',
			id: 'utilisateur_modif_credit',
			name: 'utilisateur_modif_credit',
			allowBlank: false,
			blankText:'Veuillez entrer le credit !'
		},
                {
			xtype: 'hidden',
			id: 'utilisateur_modif_hidden',
			name: 'utilisateur_modif_hidden'
		},{
			xtype: 'hidden',
			id: 'utilisateur_modif_id',
			name: 'utilisateur_modif_id'
	}],
	buttons: [{
		xtype: 'button',
			text: 'Enregistrer', 
			handler: submit_modif_utilisateur
		},{
			xtype: 'button',
			text: 'Annuler', 
			handler: function() {win_modif_utilisateur.hide();}
	}]
}); 

//Ce panel contiendra le panel précédent qui est lo formulaire, sauf qu'en bas de celui ci figure la status bar, permettant d'afficher le status de la connexion (chargement ....)
var modif_utilisateur_total = new Ext.Panel({
	autoWidth: true,
	autoHeight:true,
	items: modif_utilisateur,		//On met dans ce panel le panel de login
	bbar: new Ext.StatusBar({
		id: 'modif_utilisateur_statusbar',
		defaultText: 'Pret',
		plugins: new Ext.ux.ValidationStatus({form:'modif_utilisateur'})
	})
});

var win_modif_utilisateur;
if(!win_modif_utilisateur){							//Si la fenetre de connexion n'éxiste pas, on la crée
	win_modif_utilisateur = new Ext.Window({
		id: 'win_modif_utilisateur',
		modal: true,
		resizable: false,
		title: 'Modification',		//titre de la fenetre
		el:'div_modif',			//********* el = id du div dans le code html de la page qui contiendra la popup ! ************//
		width:600,
		autoHeight:true,
		closeAction:'hide',
		plain: true,
		items: modif_utilisateur_total			//On met dans cette fenetre le panel précédent
	});
}

//Fonction appelée pour modifier l'utilisateur sélectionner
function show_modif()
{
	modif_utilisateur.form.reset();
	
	var selections = Ext.getCmp("grid_rows").selModel.getSelections();
	Ext.getCmp("utilisateur_modif_id").setValue(selections[0].json.userId);
        Ext.getCmp("utilisateur_modif_login").setValue(selections[0].json.userName);
	Ext.getCmp("utilisateur_modif_pass").setValue(selections[0].json.userPassword);
	Ext.getCmp("utilisateur_modif_pass2").setValue(selections[0].json.userPassword);
	Ext.getCmp("utilisateur_modif_rang").setValue(selections[0].json.userRole);
        Ext.getCmp("utilisateur_modif_credit").setValue(selections[0].json.userCredit);
	
	
	win_modif_utilisateur.show();
}