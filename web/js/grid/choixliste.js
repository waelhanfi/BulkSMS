//Variables globales utilis?es pour g?rer la grid
var xg;
var gridStore;
var win_datagrid;

Ext.onReady(function(){


	//Icones pour le menu contextuel
	Ext.menu.RangeMenu.prototype.icons = {
	  gt: 'javascript/grid/img/greater_then.png',
	  lt: 'javascript/grid/img/less_then.png',
	  eq: 'javascript/grid/img/equals.png'
	};
	Ext.grid.filter.StringFilter.prototype.icon = 'javascript/grid/img/find.png';

	//On d?clare la grid
	xg = Ext.grid;

	//Le store qui contient les donn?es que l'on r?cupere
	gridStore = new Ext.data.Store({
       // autoLoad: true,
	proxy: new Ext.data.HttpProxy({ url: './GetListe'}),
	reader: new Ext.data.JsonReader({ root: 'rows',totalProperty: 'results'}, [
		                                                
								{name:"code"},
								{name:"nom"},
								{name:"date"}
	])
});





	// Le row expander qui contient les informations d?taill?es
//	var expander = new xg.RowExpander({
//		tpl : new Ext.Template(
//			'<p><b>Informations :</b> {prenom} {nom} - {email}</p><br>'
//		)
//	});

	//Les filtres pour les recherches sur chaque colonne
	var filters = new Ext.grid.GridFilters({
	  filters:[
	    {type: 'numeric',  dataIndex: 'code'},
		{type: 'string',  dataIndex: 'nom'},
		{type: 'string',  dataIndex: 'date'},
		
		{
	      type: 'list',
	      dataIndex: 'rang',
	      options: ['1 : Utilisateur', '3 : Administrateur']
	      //phpMode: true
	    }
	]});

	//variable temporaire pour savoir le type de l'utilisateur
	var cellROW=1;

	// Fonction pour changer l'icone de stage valid? ou non
    function icon_etat(val,cell){

		cellROW=val;
		cellRenderer(val,cell);

		if(val==1)
			return '<img src="css/images/utilisateur.gif" alt="Utilisateur" />';
		/*else if(val==2)
			return '<img src="css/images/moderateur.gif" alt="Modérateur" />';*/
		else if(val==3)
			return '<img src="css/images/administrateur.gif" alt="Administrateur" />';
    }

	// Fonction pour changer l'icone de stage valid? ou non
    function cellRenderer(val,cell){

		if(cellROW==1)
			cell.css = "utilisateur";
		/*else if(cellROW==2)
			cell.css = "modérateur";*/
		else if(cellROW==3)
			cell.css = "administrateur";

         return val;
    }

	//Renderer pour la date
	function cellDateRenderer(val,cell){
		if(cellROW==1)
			cell.css = "utilisateur";
		/*else if(cellROW==2)
			cell.css = "modérateur";*/
		else if(cellROW==3)
			cell.css = "administrateur";

		 var dt = new Date(val);
		 return dt.format('d-m-Y');
    }

    // La configuration type de notre tableau
    var rang = 3;
    var colModel = new xg.ColumnModel([

       
		{dataIndex: 'code', width:30, header: 'ID', renderer:cellRenderer, hidden:false},
		{dataIndex: 'nom',width:150, header: 'Nom',renderer:cellRenderer, hidden:false},
		{dataIndex: 'date',width:150,header: 'Date',renderer:cellRenderer, hidden:false}
		
    ]);

	//Le fait que les colonnes soit triables
	colModel.defaultSortable = true;


	//Ici la forme, le panel
    var gridForm = new Ext.FormPanel({
        id: 'maliste',
        frame: true,
        labelAlign: 'left',
		style:'padding:0px; margin:0px;',
		region: 'center',
        width: '100%',
		height: 430,
		//autoScroll:true,
        layout: 'fit',

		//Ici a gauche on met notre grid contenant le tableau, et a droite les infos
        items: [{
			layout: 'column',
			style:'padding:0px; margin:0px;',
			autoWidth: true,
			autoHeight: true,
			items:[{ //////TABLEAU///////
	            width:'100%',
	            layout: 'fit',
	            items: {
		            xtype: 'grid',
					id: 'grid_rows',
					name: 'grid_rows',
					style:'border-left:1px solid #ccc;border-right:1px solid #ccc;',
					ds: gridStore,
		            cm: colModel,
		            sm: new Ext.grid.RowSelectionModel({
		                singleSelect: true,

		            listeners: {

	                    rowselect: function(sm, row, rec) {
                            Ext.getCmp("maliste").getForm().loadRecord(rec);

                          document.liste.listenvoi.value = rec.data.nom;
                          document.liste.idListe.value = rec.data.code;

                         }



		               }
		            }),

					plugins: [filters  ],
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
				
				  tbar:[
		                         {
				             xtype:'themecombo',
					     width:130
				         }
		                       ],
                      buttons: [{
                    text: 'OK',
                    handler: function(){
                        win_datagrid.hide();
                               }
                           }]

	        },{
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////
				///////////////////////////// ICI ? droite on met le r?capitulatif de la ligne s?lectionn?e //////////////////////////////
	            width: '38%',
	            autoHeight: true,
				style:   {
	                "margin": "0px",
	                "padding": "0px",
	                "padding-left": "1%",
	                "padding-right": "1%",
	                "padding-top": "10px"
	            }

	        }]},


{
			//Ligne de footer et de contact pour envoyer un mail
			html:"<div id=\"footer\"> 2010&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" onclick=\"show_contact('','','');\" title=\"Nous contacter\">Get Wireless</a></div>"
		 }]
    });


	if(!win_datagrid){
		win_datagrid = new Ext.Window({
			id: 'win_datagrid',
			modal: true,
			shadow:false,
			resizable: true,
			title: 'MES LISTES',
			el:'madiv_datagrid',			//********* el = id du div dans le code html de la page qui contiendra la popup ! ************//
			width:700,
			autoHeight:true,
			closeAction:'hide',
			plain: true,
			items: gridForm			//On met dans cette fenetre le panel pr?c?dent
		});
	}
	//On charge la grid avec 10 enregistrements
	gridStore.load({params:{start: 0, limit: 5}});
});


//Fonction lancer lors du clic sur le bouton d'ajout d'un utilisateur au dessus de la grid
function ajouter_utilisateur()
{
	//Cette fonction est d?clar?e dans ajouter_utilisateur.js
	show_ajout();
}

//Fonction lancer lors du clic sur le bouton de modification d'un utilisateur au dessus de la grid
function modifier_utilisateur()
{
	//Si on a s?lectionn? un et un seul utilisateur
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
		Ext.MessageBox.alert('Erreur', 'Vous devez selectionner un utilisateur');
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
				//Ext.MessageBox.alert('Suppression effectu?e','Utilisateur supprim? avec succ?s');
				gridStore.reload();
			},
			failure: function(response)
			{
				Ext.MessageBox.alert('Erreur','Un problème a eu lieu lors de la connexion à la BDD.');
			}
		});
	}
}