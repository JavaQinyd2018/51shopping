//服务层
app.service('contentCategoryService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../contentCategory/list.do');
	};
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../contentCategory/findPage.do?page='+page+'&rows='+rows);
	};
	//查询实体
	this.findOne=function(id){
		return $http.get('../contentCategory/getById.do?id='+id);
	};
	//增加 
	this.add=function(entity){
		return  $http.post('../contentCategory/add.do',entity );
	};
	//修改 
	this.update=function(entity){
		return  $http.put('../contentCategory/update.do',entity );
	};
	//删除
	this.dele=function(ids){
		return $http.delete('../contentCategory/deleteByIds.do?ids='+ids);
	};
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../contentCategory/searchByPage.do?page='+page+"&rows="+rows, searchEntity);
	}    	
});
