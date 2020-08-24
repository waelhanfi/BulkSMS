/*************************************************************************************************************
************************************************* contact.js **************************************************

Représente le formulaire extJS pour la prise de contact par mail

auteur : p.hoffmann - mai 2009
*************************************************************************************************************/

//Fonction appelée en cliquant sur submit du formulaire
function submit_contact() {
	var sb = Ext.getCmp('contact_statusbar');		//on instancie la status bar en bas a gauche de la fenetre
	templateSubmit(sb,form_contact, fermer_contact, "Mail envoyé");
	
	//templateSubmit est déclaré dans global.js
}

//Pour fermer la boite de dialogue
function fermer_contact()
{
	win_contact.hide();
}

//Panel qui contient le corps du formulaire
var form_contact = new Ext.FormPanel({
        labelAlign: 'top',
        id: 'form_contact',
        frame:true,
        bodyStyle:'padding:5px 5px 0',
        autoWidth: true,
        height:480,
        items: [{
            layout:'column',
			
            items:[{
                columnWidth:1,
                layout: 'form',
                items: [{
                    anchor:'95%',
					html: '<div id="titre_contact">Formulaire de contact</div>'
                }]
            },{
                columnWidth:.5,
                layout: 'form',
                items: [{
                    xtype: 'textfield',				//NOM
					fieldLabel: 'Nom',
					id: 'contact_nom',
					name: 'contact_nom',
					vtype: 'alphanum2',
					vtypeText: 'Votre nom ne doit pas contenir de caractère spéciaux.',
					allowBlank: false,
					blankText:'Veuillez saisir votre nom.',
                    anchor:'90%'
                }, {
                    xtype:'textfield',
                    fieldLabel: 'Société',
                    id: 'contact_societe',
                    name: 'contact_societe',
                    anchor:'90%'
                }]
            },{
                columnWidth:.5,
                layout: 'form',
                items: [{
                    xtype: 'textfield',				//PRENOM
					fieldLabel: 'Prénom', 
					id: 'contact_prenom', 
					name: 'contact_prenom', 
					allowBlank: false, 
					vtype: 'alphanum2',
					vtypeText: 'Votre prénom ne doit pas contenir de caractère spéciaux.',	
					blankText:'Veuillez saisir votre prénom.',
					anchor:'90%'
                },{
                    xtype: 'textfield',				//EMAIL
					fieldLabel: 'Adresse Email',
					id: 'contact_email', 
					name: 'contact_email', 
					vtype: 'email',
					vtypeText: 'Votre adresse email doit être de la forme de "user@domain.com"',
					allowBlank: false,
					blankText:'Veuillez saisir votre adresse email.',
                    anchor:'90%'
                }]
            },{
                columnWidth:1,
                layout: 'form',
                items: [{
                    xtype: 'textfield',				//PRENOM
					fieldLabel: 'Objet', 
					id: 'contact_objet', 
					name: 'contact_objet', 
					value: 'Prise de contact',
					allowBlank: false, 
					blankText:'Veuillez saisir le sujet de votre message',
					anchor:'95%'
                }]
            }]
        },{
            xtype:'htmleditor',
            id:'contact_message',
            name:'contact_message',
            fieldLabel:'Message',
            autoHeight:true,
            anchor:'98%'
		},{
			xtype: 'hidden',		//Balise cachée afin de dire qu'il s'agit d'une connexion
			id: 'hidden_contact',
			name: 'hidden_contact',
			value: ''
        }],

        buttons: [{
            text: 'Envoyer',
			handler: submit_contact
        },{
            text: 'Cancel',
			handler: fermer_contact
        }]
    });

//Ce panel contiendra le panel précédent qui est lo formulaire, sauf qu'en bas de celui ci figure la status bar, permettant d'afficher le status de la connexion (chargement ....)
var form_contact_total = new Ext.Panel({
	autoWidth: true,
	autoHeight:true,
	bodyStyle: 'padding:0px;margin:0px;', 
	items: form_contact,		//On met dans ce panel le panel de login
	bbar: new Ext.StatusBar({
		id: 'contact_statusbar',
		defaultText: 'Prêt',
		plugins: new Ext.ux.ValidationStatus({form:'form_contact'})
	})
});


//Fenetre modale qui contiendra le formulaire de contact
var win_contact;
if(!win_contact)
{
	var win_contact = new Ext.Window(
	{
		id: 'win_contact',
		modal: true,
		resizable: false,
		title: 'Formulaire de contact',
		el:'popup_contact',
		width:600,
		autoHeight:true,
		closeAction:'hide',
		//plain: false,
		items: form_contact_total
	});
}


//Fonction appelée en HTML pour lancer le formulaire
function show_contact(email, nom, prenom)
{
	win_contact.show();
	Ext.getCmp('contact_email').setValue(email);
	Ext.getCmp('contact_nom').setValue(nom);
	Ext.getCmp('contact_prenom').setValue(prenom);
}

