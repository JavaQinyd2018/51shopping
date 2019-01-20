app.service('solrImportDataService',function ($http) {
    //导入数据
    this.importData=function () {
        return $http.get('../solrImportData/importData.do');
    };

    this.deleteData=function () {
        return $http.get('../solrImportData/deleteData.do');
    };
    this.deleteAllData=function () {
        return $http.get('../solrImportData/deleteAllData.do');
    };
});