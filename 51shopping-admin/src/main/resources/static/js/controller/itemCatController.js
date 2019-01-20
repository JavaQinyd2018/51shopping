 //控制层 
app.controller('itemCatController' ,function($scope,$controller ,itemCatService,typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	};

	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	};
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改  
		}else{
            $scope.entity.parentId= $scope.parentId;//赋予上级ID

			serviceObject=itemCatService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询
					$scope.getByParentId($scope.parentId);
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	};
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		itemCatService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	};
	
	$scope.searchEntity={};//定义搜索对象

	$scope.search=function(page,rows) {
		$scope.searchPageByParentId(page, rows, $scope.searchEntity);
    };

	$scope.searchByEntity=function(parentId, page,rows) {
		if (parentId == null) {
			parentId =0;
		}
		itemCatService.getPageListByParentId(parentId, page, rows, $scope.searchEntity).success(
			function (response) {
				$scope.list=response.list;
				$scope.paginationConf.totalItems=response.total;
			}
		)
	};

 	$scope.searchPageByParentId = function(page, rows, searchEntity) {
		var parentId = 0;
		if ($scope.entity.parentId != null) {
			parentId = $scope.entity.parentId;
		}
		itemCatService.getPageListByParentId(parentId, page, rows, searchEntity)
			.success(function (response) {
				$scope.list=response.list;
				$scope.paginationConf.totalItems=response.total;
			})
	};
    $scope.parentId=0;

    $scope.getByParentId = function(parentId) {
        $scope.parentId= parentId;
        itemCatService.getByParentId(parentId).success(
            function (response) {
                $scope.list=response.data;
            }
        );
    };

    $scope.grade=1;//当前级别
    //设置级别
    $scope.setGrade=function(value){
        $scope.grade=value;
    };


    $scope.selectList=function(p_entity){

        if($scope.grade==1){
            $scope.entity_1=null;
            $scope.entity_2=null;
        }
        if($scope.grade==2){

            $scope.entity_1=p_entity;
            $scope.entity_2=null;
        }
        if($scope.grade==3){
            $scope.entity_2=p_entity;
        }

        $scope.getByParentId(p_entity.id);

    };

    $scope.typeTemplateList = {date:[{'id':1},{'id':2}]};
    //$scope.typeTemplateList = {date:[]};
    $scope.getTypeTemplateList=function () {
        typeTemplateService.findAll().success(
        	function (response) {
                $scope.typeTemplateList = {date:response.id}
        });
    }

});	
