//定义一个控制器
app.controller('brandController',function ($scope, $controller, brandService) {

    $controller('baseController',{$scope:$scope});//继承


    $scope.getAllBrands = function () {
        brandService.getAllBrands().success(
            function (response) {
            $scope.list = response;
        });
    };



    //定义一个获取品牌数据的方法getBrandsForPage
    $scope.getBrandsForPage = function (page, size) {
        brandService.getBrandsForPage(page,size).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    };

    //保存
    $scope.add=function(){
        var serviceObject;//服务层对象
        if($scope.entity.id!=null){//如果有ID
            serviceObject=brandService.update( $scope.entity ); //修改
        }else{
            serviceObject=brandService.add($scope.entity  );//增加
        }
        serviceObject.success(
            function(response){
                if(response.success){
                    //重新查询
                    alert(response.message);
                    $scope.reloadList();//重新加载
                }else{
                    alert(response.message);
                }
                $scope.entity={};
            }
        );
    };

    //
    $scope.getBrandById = function (id) {
        brandService.get(id).success(
            function (response) {
                if (response != null) {
                    $scope.entity = response.data;
                }
            });
    };


    $scope.batchDelete = function() {

        if ( $scope.selectIds.length === 0) {
            alert("请选择删除商品");
            return;
        }

        if (confirm('确定要删除嘛？'))  {
            brandService.delete($scope.selectIds).success(
                function (response) {
                    if (response.success) {
                        alert(response.message);
                        $scope.reloadList();//刷新列表
                        $scope.selectIds=[];
                    }else {
                        alert(response.message);
                    }
                }
            );
        }
    };

    //查询方法
    $scope.searchEntity={};

    $scope.search = function (page,size) {
        brandService.searchByConditionsPage(page,size,$scope.searchEntity).success(
            function (response) {
                $scope.list = response.list;
                $scope.paginationConf.totalItems = response.total;
            }
        )
    }
});