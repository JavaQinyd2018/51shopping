 //控制层 
app.controller('goodsController' ,function($scope,$controller ,$location ,goodsService,uploadFileService,itemCatService, typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	};
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	};
	
	//查询实体 
	$scope.findOne=function(){
		var id = $location.search()['id'];
		if (id == null) {
			return;
		}
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;
				editor.html($scope.entity.goodsDesc.introduction);
                $scope.entity.goodsDesc.itemImages=JSON.parse($scope.entity.goodsDesc.itemImages);
                $scope.entity.goodsDesc.customAttributeItems=JSON.parse($scope.entity.goodsDesc.customAttributeItems);
                $scope.entity.goodsDesc.specificationItems=JSON.parse($scope.entity.goodsDesc.specificationItems);
                alert($scope.entity.goodsDesc.specificationItems);
                for (var i=0; i < $scope.entity.itemList; i++) {
                	$scope.entity.itemList[i].spec=JSON.parse($scope.entity.itemList[i].spec);
				}
			}
		);				
	};
	
	//保存 
	$scope.save=function(){
        $scope.entity.goodsDesc.introduction=editor.html();
		var serviceObject;//服务层对象  				
		if($scope.entity.goods.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询
                    alert(response.message);
                    $scope.entity={};
                    editor.html("");//清空副文本编辑器的内容
				}else{
					alert(response.message);
				}
			}		
		);				
	};



    //批量删除
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	};

	$scope.status=['未审核','已审核','审核未通过','已关闭'];
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	};


	$scope.uploadFile=function () {
		uploadFileService.uploadFile().success(
			function (response) {
				if (response.success) {
                    $scope.image_entity.url=response.data;
                    alert(response.message)
				}else {
					alert(response.message);
				}
            }
		);
    };

    $scope.entity={ goodsDesc:{itemImages:[],specificationItems:[]}  };
    //将当前上传的图片实体存入图片列表
	$scope.addImageEntity=function () {
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    };
    
    $scope.removeImageEntity=function (index) {
    	$scope.entity.goodsDesc.itemImages.splice(index,1);
    };

    //获取商品1一级类目
	$scope.getOneItemCatList=function () {

		itemCatService.getByParentId(0).success(
			function (response) {
				$scope.oneItemCatList=response.data;
            }
		);
    };
    //获取商品1二级类目
	$scope.$watch('entity.goods.category1Id',function (newValue, oldValue) {
		itemCatService.getByParentId(newValue).success(
			function (response) {
                $scope.twoItemCatList=response.data;
        });
    });

    //获取商品1二级类目
    $scope.$watch('entity.goods.category2Id',function (newValue, oldValue) {
        itemCatService.getByParentId(newValue).success(
            function (response) {
                $scope.threeItemCatList=response.data;
            });
    });

    $scope.$watch('entity.goods.category3Id',function (newValue, oldValue) {
        itemCatService.findOne(newValue).success(
        	function (response) {
				$scope.entity.goods.typeTemplateId=response.data.typeId;
            }
		);
    });

    //监控变量
    $scope.$watch('entity.goods.typeTemplateId',function (newValue, oldValue) {
        typeTemplateService.findOne(newValue).success(
        	function (response) {
				$scope.typeTemplate=response.data;
				$scope.typeTemplate.brandIds=JSON.parse($scope.typeTemplate.brandIds);
				if ($location.search()['id'] == null) {
                    $scope.entity.goodsDesc.customAttributeItems=JSON.parse($scope.typeTemplate.customAttributeItems);
				}
            }
		);
        //根据模板ID查询规格列表喝规格项
        typeTemplateService.getSpecListById(newValue).success(
        	function (response) {
				$scope.specList=response;
            }
		);
    });

//勾选或者取消复选框得时候元素集合得变化
    $scope.updateSpecAttribute=function($event,name,value){

        var object= $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems ,'attributeName', name);

        if(object != null){
            if($event.target.checked){
                object.attributeValue.push(value);
            }else{//取消勾选
                object.attributeValue.splice( object.attributeValue.indexOf(value ) ,1);//移除选项
                //如果选项都取消了，将此条记录移除
                if(object.attributeValue.length === 0){
                    $scope.entity.goodsDesc.specificationItems.splice(
                        $scope.entity.goodsDesc.specificationItems.indexOf(object),1);
                }
            }
        }else{
            $scope.entity.goodsDesc.specificationItems.push({"attributeName":name,"attributeValue":[value]});
        }

    };

    //创建SKU列表
    $scope.createItemList=function(){

        $scope.entity.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0'} ];//列表初始化

        var items= $scope.entity.goodsDesc.specificationItems;

        for(var i=0;i<items.length;i++){
            $scope.entity.itemList= addColumn( $scope.entity.itemList, items[i].attributeName,items[i].attributeValue );
        }

    };

    addColumn=function(list,columnName,columnValues){

        var newList=[];
        for(var i=0;i< list.length;i++){
            var oldRow=  list[i];
            for(var j=0;j<columnValues.length;j++){
                var newRow=  JSON.parse( JSON.stringify(oldRow)  );//深克隆
                newRow.spec[columnName]=columnValues[j];
                newList.push(newRow);
            }
        }
        return newList;
    };

    $scope.itemCatArray=[];
    $scope.getAllItemArray= function(){
    	itemCatService.findAll().success(
    		function (response) {
				for (var i=0; i < response.length; i++) {
					$scope.itemCatArray[response[i].id ]=response[i].name;
				}
            }
		);
    };

    //判断规格是否被勾选
    $scope.checkAttributeValue=function (specName,optionName) {
		var items= $scope.entity.goodsDesc.specificationItems;
		var object=$scope.searchObjectByKey(items,"attributeName",specName);
		if (object != null) {
			if (object.attributeValue.indexOf(optionName) >= 0) {
				return true
			}else {
                return false;
			}
		}else {
			return false;
		}
    }

});	
