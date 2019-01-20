app.controller('indexController',function($scope, loginService) {

    $scope.getLoginUsername = function () {
        loginService.getLoginUsername().success(
            function (response) {
                $scope.loginUsername = response.loginName;
            }
        );
    }
});