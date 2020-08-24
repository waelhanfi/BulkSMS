
var xgu;
var ugridStore;
var win_datagriduser;

Ext.onReady(function(){

	
	//Icones pour le menu contextuel
	Ext.menu.RangeMenu.prototype.icons = {
	  gt: 'javascript/grid/img/greater_then.png', 
	  lt: 'javascript/grid/img/less_then.png',
	  eq: 'javascript/grid/img/equals.png'
	};
	Ext.grid.filter.StringFilter.prototype.icon = 'javascript/grid/img/find.png';
	
	//On dï¿½clare la grid
	xgu = Ext.grid;
	
	//Le store qui contient les données que l'on rï¿½cupere
	ugridStore = new Ext.data.Store({
       // autoLoad: true,
	proxy: new Ext.data.HttpProxy({ url: './GetUserInfo'}),
	reader: new Ext.data.JsonReader({ root: 'rows',totalProperty: 'results', id: 'userId'}, [
		{name:"userId",type:"int"},
								{name:"userName"},
								{name:"userPassword"},
								{name:"userRole"},
                                                                {name:"userCredit"}

	])
});
								
						
						

   
	// Le row expander qui contient les informations dï¿½taillï¿½es
//	var expander = new xgu.RowExpander({
//		tpl : new Ext.Template(
//			'<p><b>Informations :</b> {prenom} {nom} - {email}</p><br>'
//		)
//	});
    
	//Les filtres pour les recherches sur chaque colonne
	var filters = new Ext.grid.GridFilters({
	  filters:[
	    {type: 'numeric',  dataIndex: 'userId'},
		{type: 'string',  dataIndex: 'userName'},
		{type: 'string',  dataIndex: 'userPassword'},
		{type: 'string',  dataIndex: 'userRole'},
                {type: 'numeric',  dataIndex: 'userCredit'},
		/*{type: 'string',  dataIndex: 'email'},
		{type: 'date',  dataIndex: 'date_enregistrement'},
		{type: 'string',  dataIndex: 'signature'},
		{type: 'string',  dataIndex: 'ip'},*/
		{
	      type: 'list',  
	      dataIndex: 'rang', 
	      options: ['1 : Utilisateur', '3 : Administrateur']
	      //phpMode: true
	    }
	]});
	
	//variable temporaire pour savoir le type de l'utilisateur
	var cellROW=1;
	
	// Fonction pour changer l'icone de stage validï¿½ ou non
    function icon_etat(val,cell){
		
		cellROW=val;
		cellRenderer(val,cell);
		
		if(val==1)
			return '<img src="css/images/utilisateur.gif" alt="Utilisateur" />';
		/*else if(val==2)
			return '<img src="css/images/moderateur.gif" alt="ModÃ©rateur" />';*/
		else if(val==3)
			return '<img src="css/images/administrateur.gif" alt="Administrateur" />';
    }
	
	// Fonction pour changer l'icone de stage validï¿½ ou non
    function cellRenderer(val,cell){
	
		if(cellROW==1)
			cell.css = "utilisateur";
		/*else if(cellROW==2)
			cell.css = "modÃ©rateur";*/
		else if(cellROW==3)
			cell.css = "administrateur";
		
         return val;
    }
	
	//Renderer pour la date
	function cellDateRenderer(val,cell){
		if(cellROW==1)
			cell.css = "utilisateur";
		/*else if(cellROW==2)
			cell.css = "modÃ©rateur";*/
		else if(cellROW==3)
			cell.css = "administrateur";
		
		 var dt = new Date(val);
		 return dt.format('d-m-Y');                           
    }

    // La configuration type de notre tableau
    var rang = 3;
    var colModel = new xgu.ColumnModel([
	
       // {dataIndex: 'rang', width:50, header: 'Rang',renderer: icon_etat, hidden:false},
		{dataIndex: 'userId', width:30, header: 'ID', renderer:cellRenderer, hidden:false},
		{dataIndex: 'userName',width:150, header: 'Login',renderer:cellRenderer, hidden:false},
		{dataIndex: 'userPassword',width:200,header: 'Mot de pass',renderer:cellRenderer, hidden:false},
		{dataIndex: 'userRole',width:100,header: 'Role',renderer:cellRenderer, hidden:false},
                {dataIndex: 'userCredit',width:100,header: 'Credit',renderer:cellRenderer, hidden:false}
		/*{dataIndex: 'email',width:200,header: 'Email',renderer:cellRenderer, hidden:false},
		{dataIndex: 'signature',width:120,header: 'Signature',renderer:cellRenderer, hidden:true},
		{dataIndex: 'date_enregistrement', width:80, header: 'Date',renderer: cellDateRenderer, hidden:true},
		{dataIndex: 'ip', width:60, header: 'IP du post',renderer:cellRenderer, hidden:true}*/
    ]);
	
	//Le fait que les colonnes soit triables
	colModel.defaultSortable = true;
	
	
	//Ici la forme, le panel
    var gridForm = new Ext.FormPanel({
        id: 'form_utilisateurs',
        frame: true,
        labelAlign: 'left',
		style:'padding:0px; margin:0px;',
		region: 'center',
        width: '100%',
		height: 440,
		autoScroll:true,
        layout: 'fit',
		//Ici a gauche on met notre grid contenant le tableau, et a droite les infos
        items: [{
			layout: 'column',
			style:'padding:0px; margin:0px;',
			autoWidth: true,
			autoHeight: true,
			items:[{ //////TABLEAU///////
	            width:'60%',
	            layout: 'fit',
	            items: {
		            xtype: 'grid',
					id: 'grid_rows',
					name: 'grid_rows',
					style:'border-left:1px solid #ccc;border-right:1px solid #ccc;',
					ds: ugridStore,
		            cm: colModel,
		            sm: new Ext.grid.RowSelectionModel({
		                singleSelect: true,
		                listeners: {
		                    rowselect: function(sm, row, rec) {
		                        Ext.getCmp("form_utilisateurs").getForm().loadRecord(rec);
		                    }
		                }
		            }),
					plugins: [filters],
					enableColLock: false,
					loadMask: true,
					border:true,
		            height: 330,
			        listeners: {
			        	render: function(g) {
			        		g.getSelectionModel().selectRow(0);
			        	},
			        	delay: 10 // Allow rows to be rendered.
			        }
	        	},
				/*bbar: new Ext.PagingToolbar({
			    store: ugridStore,
			    pageSize: 10,
			    plugins: filters
			  }),*/
				  tbar:[
		          {
		            text: 'Ajouter un utilisateur',
		            tooltip: 'Ajouter un nouvel utilisateur',
		            iconCls:'add',
		            handler: ajouter_utilisateur
		          }, '-', { 
		            text: 'Modifier l\'utilisateur',
		            tooltip: 'Modifier l\'utilisateur selectionné',
		            handler: modifier_utilisateur,
		            iconCls:'modifier'
				}, '-', {
		            text: 'Supprimer l\'utilisateur',
		            tooltip: 'Supprimer l\'utilisateur selectionné',
		            handler: supprimer_utilisateur,
		            iconCls:'remove'
		          },{
				    xtype:'themecombo',
					width:130
				}
		      ]
	        },{
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////
				///////////////////////////// ICI à droite on met le récapitulatif de la ligne sélectionnée //////////////////////////////
	            width: '38%',
	            autoHeight: true,	
				style: {
	                "margin": "0px",
	                "padding": "0px",
	                "padding-left": "1%",
	                "padding-right": "1%",
	                "padding-top": "10px"
	            },
	            items: [{
					xtype: 'fieldset',
		            title:'Détails&nbsp;de&nbsp;l\'utilisateur',
		            autoHeight: true,
					border: false,
					defaults: {width: '95%'}, 
					
		            items: [{
						xtype: 'hidden',
		                id: 'id_utilisateur',
		                name: 'userID'
		            },{
						xtype: 'textfield',
		                fieldLabel: 'Login',
		                name: 'userName'
					},{
						xtype: 'textfield',
		                fieldLabel: 'Mot de passe',
		                name: 'userPassword'
					},{
						xtype: 'textfield',
		                fieldLabel: 'Role',
		                name: 'userRole'
                                        },{
						xtype: 'textfield',
		                fieldLabel: 'Credit',
		                name: 'userCredit'
					}
						]
	            }]
	        }]},{
			//Ligne de footer et de contact pour envoyer un mail
			html:"<div id=\"footer\"> 2010&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;Get Wireless</div>"
                        //<a href=\"javascript:void(0);\" onclick=\"show_contact('','','');\" title=\"Nous contacter\">
		 }]
    });
	
	
	if(!win_datagriduser){
		win_datagriduser = new Ext.Window({
			id: 'win_datagriduser',
			modal: true,
			shadow:false,
			resizable: true,
			title: 'Gestion des utilisateurs',
			el:'div_datagriduser',			//********* el = id du div dans le code html de la page qui contiendra la popup ! ************//
			width:1000,
			autoHeight:true,
			closeAction:'hide',
			plain: true,
			items: gridForm			//On met dans cette fenetre le panel prï¿½cï¿½dent
		});
	}
	//On charge la grid avec 10 enregistrements
	ugridStore.load({params:{start: 0, limit: 10}});
});


//Fonction lancer lors du clic sur le bouton d'ajout d'un utilisateur au dessus de la grid
function ajouter_utilisateur()
{
	//Cette fonction est dï¿½clarï¿½e dans ajouter_utilisateur.js
	show_ajout();
}

//Fonction lancer lors du clic sur le bouton de modification d'un utilisateur au dessus de la grid
function modifier_utilisateur()
{
	//Si on a sï¿½lectionnï¿½ un et un seul utilisateur
	if(Ext.getCmp("grid_rows").selModel.getCount() == 1)
    {
		show_modif();
    }
	else
	{
		Ext.MessageBox.alert('Erreur', 'Vous devez selectionner un utilisateur');
    }
}


/***********************************************************************************/
/******************************** SUPPRESSION ************************************/
function supprimer_utilisateur()
{
	if(Ext.getCmp("grid_rows").selModel.getCount() == 1)
    {
		if(Ext.getCmp("grid_rows").selModel.getSelections()[0].json.id == 1){
			Ext.MessageBox.alert('Erreur', 'Vous ne pouvez pas supprimer le compte de Pierre');
		}
		else{
			Ext.MessageBox.confirm('Confirmation','Etes-vous sur de vouloir supprimer cet utilisateur ?', deleteUtilisateur);
		}
    }
	else
	{
		Ext.MessageBox.alert('Erreur', 'Vous devez sélectionner un utilisateur');
    }
}

function deleteUtilisateur(btn)
{
	if(btn=='yes')
	{       
		var selections = Ext.getCmp("grid_rows").selModel.getSelections();
		var id_utilisateur_supprimer = selections[0].json.userId;
		
		Ext.Ajax.request({
			waitMsg: 'Patience...',
			url: './DelUser',
                        method:"post",
			params: {
				userId:id_utilisateur_supprimer
			},

			success: function(response)
			{ 
				Ext.MessageBox.alert('Suppression effectu&eacute;','Utilisateur supprim&eacute; avec succ&egrave;s');
				ugridStore.reload();
			},
			failure: function(response)
			{
				Ext.MessageBox.alert('Erreur','Un probl&egrave;me a eu lieu lors de la connexion &agrave; la BDD.');
			}
		});
	}  
}


/***********************************************************************************/
/***********************************************************************************/
