app.service('uploadFileService',function ($http) {

    this.uploadFile=function () {

        var formdata = new FormData();
        formdata.append('uploadFile',file.files[0]);//文件上传的name
        return $http({
            url:'../picture/upload.do',
            method:'post',
            data:formdata,
            headers:{'Content-Type':undefined},
            transformRequest:angular.identity
        });
    };



});