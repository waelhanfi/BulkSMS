Ext.onReady(function(){

    // create the data store
    var store = new Ext.data.JsonStore({	
		totalProperty: 'total',	// total data, see json output
		root: 'rows',	// see json output
		url: './GetCampagne',
        fields: [
           {name: 'id', type: 'int'},
		   'nom_campagne', 'date', 'liste', 'message','date_envoi','etat'
        ]
    });	    

	// load data from the url ( data.php ) and add start and limit parameter
	store.load({params:{start: 0, limit: 10}});
	
    // create the Grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            
			{header: 'Nom campagne', width: 120, sortable: true, dataIndex: 'nom_campagne'},
			{header: 'Date lancement', width: 80, sortable: true, dataIndex: 'date'},
			{header: 'Liste', width: 100, sortable: true, dataIndex: 'liste'},
                        {header: 'Message', width: 250, sortable: true, dataIndex: 'message'},
                        {header: 'Date envoi', width: 80, sortable: true, dataIndex: 'date_envoi'},
                        {header: 'Etat', width: 65, sortable: true, dataIndex: 'etat'}

        ],
        stripeRows: true,
        height:250,
        width:700,
        title:'Liste des campagnes enregistrées',
		
		bbar: new Ext.PagingToolbar({
			pageSize: 10,	// data to display
			store: store,
			displayInfo: true,
			displayMsg: 'Affichage {0} - {1} of {2}',
			emptyMsg: "Pas d'enregistrement à afficher"
		})
                
    });

	// render this grid to paging-grid element
    grid.render('paging-grid');
	
});