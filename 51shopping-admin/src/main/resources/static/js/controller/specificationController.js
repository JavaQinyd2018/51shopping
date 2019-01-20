 //控制层 
app.controller('specificationController' ,function($scope,$controller ,specificationService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		specificationService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	};
	
	//分页
	$scope.findPage=function(page,rows){			
		specificationService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	};
	
	//查询实体
    $scope.entity={specification:{}, specificationOptionList:[]};
	$scope.findOne=function(id){				
		specificationService.findOne(id).success(
			function(response){
				$scope.entity= response.data;
			}
		);				
	};
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.specification.id!=null){//如果有ID
			serviceObject=specificationService.update( $scope.entity ); //修改  
		}else{
			serviceObject=specificationService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询
					alert(response.message);
		        	$scope.reloadList();//重新加载
                    $scope.entity={}
				}else{
					alert(response.message);
				}
			}		
		);				
	};
	
	 
	//批量删除 
	$scope.dele=function(){

        if ( $scope.selectIds.length === 0) {
            alert("请选择删除商品");
            return;
        }
        if (confirm('确定要删除嘛？')) {
            //获取选中的复选框
            specificationService.dele( $scope.selectIds ).success(
                function(response){
                    if(response.success){
                    	alert(response.message);
                        $scope.selectIds=[];
                    }else {
                    	alert(response.message);
					}
                    $scope.reloadList();//刷新列表
                }
            );
        }
	};
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		specificationService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.list;
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	};
	
	//$scope.entity={specificationOptionList:[]};
	
	//增加规格选项行
	$scope.addTableRow=function(){
		$scope.entity.specificationOptionList.push({});			
	};
	
	//删除规格选项行
	$scope.deleteTableRow=function(index){
		$scope.entity.specificationOptionList.splice(index,1);//index-索引，1-代表删除1行
	};
    
});	
