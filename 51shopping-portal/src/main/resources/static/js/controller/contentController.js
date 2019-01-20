//控制层
app.controller('contentController' ,function($scope,contentService){

    $scope.adContentList=[];
    $scope.getAdContentList=function (categoryId) {
        contentService.getByCategoryId(categoryId).success(
            function (response) {
                $scope.adContentList[categoryId]=response.data;
        });
    };

    //搜索查询传递
    $scope.search=function () {
        location.href='http://localhost:9400/search.html#?keywords='+$scope.keywords;
    }
});
