app.controller('solrImportDataController',function ($scope, solrImportDataService) {

    $scope.importData=function () {
        solrImportDataService.importData().success(
            function (response) {
               alert(response.message);
            }
        );
    };
    $scope.deleteData=function () {
        solrImportDataService.deleteData().success(
            function (response) {
                alert(response.message);
        });
    };

    $scope.deleteAllData=function () {
        solrImportDataService.deleteAllData().success(
            function (response) {
                alert(response.message);
            });
    };
});