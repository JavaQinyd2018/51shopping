//定义一个服务
app.service('brandService',function($http) {

    this.getAllBrands = function () {
        return $http.get('../brand/list.do');
    };
    //分页
    this.getBrandsForPage = function (page,size) {
        return $http.get("../brand/getForPage.do?page="+page+"&size="+size);
    };

    //保存
    this.add = function(entity) {
        return $http.post("../brand/add.do",entity);
    };

    //更新
    this.update = function (entity) {
        return $http.post("../brand/update.do",entity);
    };

    this.get = function (id) {
        return $http.get("../brand/getById.do?id="+id);
    };

    this.delete = function (ids) {
        return $http.delete("../brand/deleteByIds.do?ids="+ids);
    };

    this.searchByConditionsPage = function (page, size,entity) {
        return $http.post("../brand/searchByPage.do?page="+page+"&size="+size, entity);
    };

    //查询品牌项
    this.getBrandOptionList = function () {
        return $http.get("../brand/getBrandList.do");
    }
});