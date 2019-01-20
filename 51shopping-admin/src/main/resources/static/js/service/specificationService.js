//服务层
app.service('specificationService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../specification/findAll.do');		
	};
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../specification/findPage.do?page='+page+'&rows='+rows);
	};
	//查询实体
	this.findOne=function(id){
		return $http.get('../specification/getById.do?id='+id);
	};
	//增加 
	this.add=function(entity){
		return  $http.post('../specification/add.do',entity );
	};
	//修改 
	this.update=function(entity){
		return  $http.put('../specification/update.do',entity );
	};
	//删除
	this.dele=function(ids){
		return $http.delete('../specification/batchDeleteByIds.do?ids='+ids);
	};
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../specification/searchByPage.do?page='+page+"&rows="+rows, searchEntity);
	};
	//下拉列表
	this.selectOptionList=function(){
		return $http.get('../specification/selectOptionList.do');
	};
	//获取规格列表
	this.getSpecOptionList = function () {
		return $http.get("../specification/getSpecList.do");
    }
});
