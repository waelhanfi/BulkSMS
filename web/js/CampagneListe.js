MyEditorPanel = Ext.extend(
	Ext.grid.EditorGridPanel,
	{
		insertUserRecords:[],

		constructor:function()
		{
			MyEditorPanel.superclass.constructor.call(
				this,
				{
					title:"Module CRUD",
					width:825,
					autoHeight:true,
					//border:true,
					collapsible:true,
					frame:true,

					clicksToEdit:1,//1:��ʾ����һ�¾Ϳ����޸�
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
						//{header:"ID",dataIndex:"userId"},
						{header:"Nom campagne",dataIndex:"nom_campagne",width:150},
						{header:"Date lancement",dataIndex:"date",width:150},
                                                {header:"Liste",dataIndex:"liste",width:150},
                                                {header:"Message",dataIndex:"message",width:170},
                                                {header:"Date envoi",dataIndex:"date_envoi",width:150},
                                                {header:"Etat",dataIndex:"etat",width:150}


					]),
					ds:new Ext.data.Store({
						autoLoad:true,

						//proxy:new Ext.data.HttpProxy({url:"./GetUserInfo?idlist="+recap}),
                                                proxy:new Ext.data.HttpProxy({url:"./GetCampagne"}),
                                                
						reader:new Ext.data.JsonReader(
							{
								//id:"userId",
								totalproperty:"results",
								root:"rows"
							},
							Ext.data.Record.create([
								//{name:"userId",type:"int"},
								{name:"nom_campagne"},
								{name:"date"},
								{name:"liste"},
                                                                {name:"message"},
								{name:"date_envoi"},
								{name:"etat"}
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
				//{name:"userId"},
				{name:"nom"},
				{name:"gsm"},
				//{name:"date"},
				{name:"newFlag"}
			])  ;

			var tempStore = this.getStore() ;
			var newGsm = tempStore.getAt(tempStore.getCount() - 1).get("gsm") + 1 ;
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
			//var ids = "" ;
			var noms = "" ;
			var gsms = "" ;
			//var ages = "" ;

			//var newIds = "" ;
			var newNom = "" ;
			var newGsm = "" ;
			//var newAges = "" ;
			var storeModified = this.getStore().modified ;
			for(var i = 0 ; i < storeModified.length ;i++)
			{
				var oldRecord = storeModified[i] ;
				if(true == oldRecord.get("newFlag"))
				{

					//newIds += oldRecord.get("userId") + "," ;
					newNom += oldRecord.get("nom") + "," ;
					newGsm += oldRecord.get("gsm") + "," ;
					//newAges += oldRecord.get("userAge") + "," ;


					oldRecord.set("newFlag",false) ;

					this.insertUserRecords.push(oldRecord) ;
				}
				else
				{
					/**
					 * ��������ӵļ�¼������ǰ�ļ�¼�����޸�
					 */
					//ids += oldRecord.get("userId") + "," ;
					noms += oldRecord.get("n") + "," ;
					gsms += oldRecord.get("userSex") + "," ;
					//ages += oldRecord.get("userAge") + "," ;
				}
			}

			if("" == gsms)
			{

			}
			else
			{
				Ext.Ajax.request(
					{
						url:"./UpdateUser",
						method:"post",
						params:{noms:noms,gsms:gsms}
					}
				)
			}

			if("" == newGsm)
			{}
			else
			{
				Ext.Ajax.request(
					{
						url:"./AddGsmListe",
						method:"post",
						params:{newGsm:newGsm,newNom:newNom,idlist:recap}
					}
				) ;
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
			/**
			 * �˴���GridPanel����Ĳ�һ��GridPanel�����selModelΪ: Ext.grid.RowSelectionModel
			 * ��EditorPanel�����selModelΪ:Ext.grid.CellSelectionModel
			 */
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
			/**
			 * getSelectedCell���ص���һ�����飬�������˵�ǰѡ���е����±������±꣬��0��ʼ����
			 */
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
				msg:"Supprimé avec succès!",
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
					msg:"Nom Campagne ne peut pas être vide!",
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
				this.getStore().filter("nom_campagne",keyword) ;



			}
		}

	}

) ;
