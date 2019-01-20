//服务层
app.service('contentService',function($http){
    this.getByCategoryId=function (categoryId) {
        return $http.get('content/getByCategoryId.do?categoryId='+categoryId);
    }
});
