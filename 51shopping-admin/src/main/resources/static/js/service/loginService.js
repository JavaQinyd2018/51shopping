//登陆
app.service('loginService', function($http){
    this.getLoginUsername = function(){
      return $http.get("../login/getLoginUsername.do");
    };
});