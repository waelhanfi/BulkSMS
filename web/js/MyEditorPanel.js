MyEditorPanel = Ext.extend(
	Ext.grid.EditorGridPanel,
	{
		insertUserRecords:[],

		constructor:function()
		{
			MyEditorPanel.superclass.constructor.call(
				this,
				{
					title:"Liste des GSM",
					width:525,
					autoHeight:true,
					//border:true,
					collapsible:true,
					frame:true,
                                       
					clicksToEdit:1,
					loadMask:new Ext.LoadMask(
						//Ext.get("EditorPanel"),
						Ext.getBody(),
						{
							msg:"chargement..."
						}
					),
					enableHdMenu:false,
				       footer:true,
                                                         
					tbar:[
						{
							text:"Ajouter",
							cls:"x-btn-text-icon",
							icon:"images/add.png",
							listeners:
							{
								"click":
								{
									fn:this.addClick,
									scope:this
								}
							}
						},
                                                "-",
						{
							text:"Enregistrer",
							cls:"x-btn-text-icon",
							icon:"images/save.gif",
							listeners:{
								"click":
								{
									fn:this.saveClick,
									scope:this
								}
							}
						},
						"-",
						{
							text:"Supprimer",
							cls:"x-btn-text-icon",
							icon:"images/del.png",
							handler:this.delUserClick,
							scope:this
						},
						"-",
						"Filtrer par nom:",
						{
							xtype:"combo",
							mode:"local",
							triggerAction:"all",
							value:"Tous",
							store:new Ext.data.SimpleStore({
								data:[["Tous"]],
								fields:["type"]
							}),
							width:100,
							listeners:{
								"change":{
									fn:this.comboChange,
									scope:this
								}
							},
							displayField:"type",
							id:"keyWord",
							allowBlank:false,
							blankText:"Nom ne peut pas etre vide!!"
						},
						{
							text:"Recherche",
							cls:"x-btn-text-icon",
							style:"margin-left:5",
							icon:"images/filter.png",
							handler:this.filterClick,
							scope:this
						}
					],

					sel:new Ext.grid.RowSelectionModel({}),
					cm:new Ext.grid.ColumnModel([
						{header:"ID",dataIndex:"idgsm"},
						{header:"Nom",dataIndex:"nom",width:170,editor:new Ext.form.TextField()},
						{header:"Gsm",dataIndex:"gsm",width:170,maxLength:8,editor:new Ext.form.NumberField()},
                                                {header:"Date",dataIndex:"date",width:170}
                                               
					]),
					ds:new Ext.data.Store({
						autoLoad:true,
                                                
						//proxy:new Ext.data.HttpProxy({url:"./GetUserInfo?idlist="+recap}),
                                                proxy:new Ext.data.HttpProxy({url:"./GetGsmListe"}),
                                                baseParams:{idlist:recap},
						reader:new Ext.data.JsonReader(
							{
								//id:"userId",
								totalproperty:"results",
								root:"rows"
							},
							Ext.data.Record.create([
							        {name:"idgsm"},
								{name:"nom"},
								{name:"gsm"},
								{name:"date"}
							])
						)
					}
                                         
                                    )
                                         

				}


			) ;
                             
			
		},

		comboChange:function(field,newValue,oldValue)
		{
			if("tous" == newValue)
			{
				this.getStore().clearFilter() ;
			}
		},
		addClick:function()
		{	
			var recordModel = new Ext.data.Record.create([
				
				{name:"nom"},
				{name:"gsm"},
				
				{name:"newFlag"}
			]);
			
			var tempStore = this.getStore() ;
			//var newGsm = tempStore.getAt(tempStore.getCount() - 1).get("gsm") + 1 ;
            var newGsm = tempStore.getAt(tempStore) ;
			var newFlag = true ;
			var record = new recordModel(
				{
					//userId:newGsm,
					nom:"",
					gsm:"",
					//userAge:"",
					newFlag:newFlag
				}
			) ;
			tempStore.add(record) ;
			//this.startEditing(newUserId - 1,1)
			this.startEditing(this.getStore().getCount() - 1,1)
			
			
		},
		saveClick:function()
		{
			var idgsms="";
			var noms = "" ;
			var gsms = "" ;
			
			
			
			var newNom = "" ;
			var newGsm = "" ;
			
			var storeModified = this.getStore().modified ;
			for(var i = 0 ; i < storeModified.length ;i++)
			{
				var oldRecord = storeModified[i] ;
				if(true == oldRecord.get("newFlag"))
				{	
					
					
					newNom += oldRecord.get("nom") + "zbanda9louch" ;
					newGsm += oldRecord.get("gsm") + "zbanda9louch" ;
					
					
					
					oldRecord.set("newFlag",false) ;
					
					this.insertUserRecords.push(oldRecord) ;
				}
				else
				{
					idgsms += oldRecord.get("idgsm") + "zbanda9louch" ;
					noms += oldRecord.get("nom") + "zbanda9louch" ;
					gsms += oldRecord.get("gsm") + "zbanda9louch" ;
					
				}
			}
			
			if("" == idgsms || "undefinedzbanda9louch"==idgsms)//c'est un ajout
        {

                if( ("zbanda9louch" == newNom || "zbanda9louch" == newGsm ||"" == newGsm||""==newNom)&&"undefinedzbanda9louch" != idgsms )
            {
                Ext.Msg.show({
                    title:"Alert",
                    width:250,
                    msg:"veuillez remplir tous les champs",
                    animEL:"EditorPanel",
                    icon: Ext.MessageBox.WARNING
                });

            }
            else
            {

                if("undefinedzbanda9louch"==idgsms && ""==newGsm && ""==newNom)
                    {
                        newNom=noms;
                        newGsm=gsms;
                    }

               Ext.Ajax.request(
					{
						url:"./AddGsmListe",
						method:"post",
						params:{newGsm:newGsm,newNom:newNom,idlist:recap}
					}
				) ;


            }

        }



            else //c'est une mise � jour
            {


                Ext.Ajax.request(
					{
						url:"./UpdateGsm",
						method:"post",
						params:{idgsms:idgsms,noms:noms,gsms:gsms}
					}
				)
                    exit(0);
            }
			
			Ext.Ajax.on("requestcomplete",function(conn,response,options){
				//alert(response.responseText) ;
				var resText = response.responseText ;
				var returnIds = resText.split(",") ;
				for(var i = 0 ; i < returnIds.length - 2 ; i++)
				{	
					this.insertUserRecords[i].set("userId",returnIds[i]) ;
					this.insertUserRecords.pop() ;
				}
			},this) ;
			this.getStore().commitChanges() ;
		},
		delUserClick:function()
		{	
			
			var record = this.getSelectionModel().getSelectedCell() ;
			if(null == record)
			{
				Ext.Msg.show({
					title:"Alert",
					width:250,
					msg:"Vous devez selectionner Num GSm",
					buttons:Ext.MessageBox.OK,
					animEL:"EditorPanel",
					icon: Ext.MessageBox.WARNING
				}) ;
			}
			else
			{
				Ext.Msg.confirm("Alert","Voulez-vous vraiment supprimer?",this.delUserDo,this) ;
			}
		},
		delUserDo:function(btn)
		{
			
			if(btn=='yes')
                            {
                        var selArray = this.getSelectionModel().getSelectedCell() ;
			var record = this.getStore().getAt(selArray[0,0]) ;
			Ext.Ajax.request(
				{
					url:"./DelGsm",
					method:"post",
					params:{gsm:record.get("gsm"),idlist:recap}
				}
			) ;
			this.getStore().remove(record) ;
			Ext.Msg.show({
				title:"Note",
				width:250,
				msg:"Supprim&eacute; avec succ&egrave;s!",
				buttons:Ext.MessageBox.OK,
				animEL:"EditorPanel",
				icon: Ext.MessageBox.INFO
			}) ;
                            }
		},
                ////////

//                deleteUtilisateur:function(btn)
//{
//	if(btn=='yes')
//	{
//		var selArray = this.getSelectionModel().getSelectedCell() ;
//		var record = this.getStore().getAt(selArray[0,0]) ;
//
//		Ext.Ajax.request({
//			waitMsg: 'Patience...',
//			url: './DelUser',
//                        method:"post",
//			params: {
//				tuserId:record.get("gsm")
//			},
//			success: function(response)
//			{
//				//Ext.MessageBox.alert('Suppression effectuée','Utilisateur supprimé avec succès');
//				MyEditorPanel.reload();
//			},
//			failure: function(response)
//			{
//				Ext.MessageBox.alert('Erreur','Un problème a eu lieu lors de la connexion à la BDD.');
//			}
//		});
//	}
//},
test:function(a)
{

    alert(a);
},
		filterClick:function()
		{	
			var keyword = Ext.getCmp("keyWord").getValue() ;
			if("" == keyword || null == keyword)
			{
				Ext.Msg.show({
					title:"Alert",
					width:250,
					msg:"Num GSM ne peut pas etre vide!",
					animEL:"EditorPanel",
					icon: Ext.MessageBox.WARNING
				}) ;
			}
			else if("Tous" == keyword)
			{
				this.getStore().clearFilter() ;                               
			}
			else
			{
				this.getStore().filter("gsm",keyword) ;

                  ///////////////////////////////////////ADDED/////////////////////////////////////////////
//
//                                this.getStore().clearFilter() ;
//                                Ext.Ajax.request(
//					{
////						url:"./GetUserInfo",
////						method:"post",
////						params:{idlist:'2'},
//
//                                                ds:new Ext.data.Store({
//
//                                                url:"./GetUserInfo",
//						method:"post",
//						params:{idlist:'2'},
//
//						//autoLoad:true,
//						//proxy:new Ext.data.HttpProxy({url:"./GetUserInfo",method:"post",params:{idlist:'2'}}),
//						reader:new Ext.data.JsonReader(
//							{
//								id:"userId",
//								totalproperty:"results",
//								root:"rows"
//							},
//							Ext.data.Record.create([
//								{name:"userId",type:"int"},
//								{name:"userName"},
//								{name:"userSex"},
//								{name:"userAge"}
//							])
//						)
//					}
//
//                                    )
//					}
//
//				) ;
//                                store.load();
                     //////////////////////////////////////////////////////////////////////////////////
                                
			}
		}
                
	}

) ;
//    function actionJS()
//{
//
//    alert(recap);
//}