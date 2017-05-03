<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
   <head>
      <title>数据报表展示</title>
      <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
      <!-- å¼å¥ Bootstrap -->
      <link href="${ctx }/style/css/bootstrap.min.css" rel="stylesheet">
      <!--  <script src="${ctx }/style/js/jquery.js"></script>-->
      <script src="https://code.jquery.com/jquery.js"></script>
      <script src="${ctx }/style/js/bootstrap.min.js"></script>
	<script src="${ctx }/style/js/jquery.validate.min.js"></script>
	<script src="${ctx }/style/js/messages_zh.js"></script>
	<style>
.error{
	color:red;
}
</style>
   </head>
   <body>
      <div class="container">
   <div class="row clearfix">
      <div class="col-md-12 column">
         <nav class="navbar navbar-default navbar-inverse navbar-static-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="${ctx }/">数据报表</a>
            </div>
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav">
                  <li class="active">
                      <a href="${ctx }/datecontroller/uplist">升序</a>
                  </li>
                  <li>
                      <a href="${ctx }/datecontroller/downlist">降序</a>
                  </li>
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">下拉菜单<strong class="caret"></strong></a>
                     <ul class="dropdown-menu">
                        <li>
                            <a href="#">链接</a>
                        </li>
                        <li>
                            <a href="#">链接</a>
                        </li>
                        <li>
                            <a href="#">链接</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="#">链接</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="#">更多</a>
                        </li>
                     </ul>
                  </li>
               </ul>
               <form class="navbar-form navbar-left" role="search" action="${ctx }/datecontroller/findone">
                  <div class="form-group">
                     <input type="text" class="form-control" name="searchParam" placeholder="姓名及姓名关键字"/>
                  </div> <button type="submit" class="btn btn-default">提交</button>
               </form>
               <ul class="nav navbar-nav navbar-right">
                  <li>
                      <a href="#">王子凡</a>
                  </li>
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">下载数据集<strong class="caret"></strong></a>
                     <ul class="dropdown-menu">
                        <li>
                            <a href="${ctx }/datecontroller/exportExcelNow"><span class="glyphicon glyphicon-export" style="color: rgb(0, 140, 0);">Excel(Now)</span></a>
                        </li>
                        <li>
                            <a href="${ctx }/datecontroller/exportExcelAll"><span class="glyphicon glyphicon-export" style="color: rgb(0, 140, 0);">Excel(All)</span></a>
                        </li>
                     </ul>
                  </li>
               </ul>
            </div>
            
         </nav>
         <div class="panel panel-success">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseTwo">
					数据操作
				</a>
			</h4>
		</div>
		<div id="collapseTwo" class="panel-collapse collapse">
			<div class="panel-body">
				<div>
					<div style="height:55px;"><form name="select" id="select" action="${ctx }/datecontroller/findbyid">
            <div class="form-group">
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="id" id="id" placeholder="Id">
               </div>
            </div><button type="submit" class="btn btn-default btn-primary">查询</button>
         </form>
         </div>
         <div><form action="${ctx }/datecontroller/findmore">
            <div class="form-inline">
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="name" placeholder="姓名及关键字">
               </div>
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="occupation" placeholder="职业">
               </div>
            </div>
            <div>
               <button type="submit" class="btn btn-default btn-primary">查询</button>
               </div>
         </form>
         </div>
         <div style="height:60px;"><form name="add" id="add" action="${ctx }/datecontroller/add">
            <div class="form-inline">
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" id="addoccupation" name="addoccupation" placeholder="职业">
               </div>
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" id="addname" name="addname" placeholder="姓名">
               </div>
            </div>
            <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" id="addage" name="addage" placeholder="年龄">
               </div>
            <div>
               <button type="submit" class="btn btn-default btn-primary">添加</button>
            </div>
         </form>
         </div>
         <div style="height:55px;"><form name="edit" id="edit" action="${ctx }/datecontroller/edit">
            <div class="form-inline">
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" id="updateid" name="updateid" placeholder="Id">
               </div>
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="updateoccupation" placeholder="职业">
               </div>
            </div>
            <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="updatename" placeholder="姓名">
               </div>
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="updateage" placeholder="年龄">
               </div>
            <div>
               <button type="submit" class="btn btn-default btn-primary">修改</button>
            </div>
         </form>
         </div>
				</div>
			</div>
		</div>
	</div>
         <table class="table table-hover table-bordered">
            <thead>
               <tr>
                  <th>Id</th>
                  <th>职业</th>
                  <th>姓名</th>
                  <th>年龄</th>
                  <th>状态</th>
               </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.list}" var="lists">
               <tr class="success">
                  <td>
                     ${lists.id}
                  </td>
                  <td>
                     ${lists.occupation}
                  </td>
                  <td>
                     ${lists.name}
                  </td>
                  <td>
                     ${lists.age}
                  </td>
                  <td>
                     <a href="${ctx }/datecontroller/delete?id=${lists.id}"><span class="glyphicon glyphicon-trash" style="color: rgb(255, 0, 0);"></span></a>
                  </td>
               </tr>
              </c:forEach>
            </tbody>
         </table>
         <form action="${ctx }/datecontroller/page">
         <div>
  <label class="checkbox-inline">
    <input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="5" checked>每页5条
  </label>
  <label class="checkbox-inline">
    <input type="radio" name="optionsRadiosinline" id="optionsRadios2" value="10">每页10条
  </label>
  <label class="checkbox-inline">
    <input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="15">每页15条
  </label>
  <input class="btn btn-default" type="submit" value="提交">
</div>
</form>
         <ul class="pager">
         	<c:forEach begin="1" end="${page.totalPageNum }" var="pageNum">
            	<li>
                	<a name="pageNum" href="${ctx }/datecontroller/list?pageNum=${pageNum }">${pageNum }</a>
            	</li>
            </c:forEach>
         </ul>
      </div>
   </div>
</div>
      <script type="text/javascript">
$(document).ready(function(e) {
// 在键盘按下并释放及提交后验证提交表单
//$('#add input').click(function(e){
    		//$(this).parent('div').addClass('current').append('<em></em>').siblings().removeClass('current').find('em').remove();
    	//});
  $("#select").validate({
	    rules: {
	    	id:{
	    		required:true
	    	}
	    },
	    messages: {
	    	id:{
	    		required:"id不能为空"
	    	}
	    },
	    errorPlacement: function(error, element) {
	    	error.appendTo(element.parent());
		}
	});
	$("#add").validate({
		rules:{
			addoccupation:{
				required:true
			},
			addname:{
				required:true
			},
			addage:{
				required:true
			},
		},
		messages:{
			addoccupation:{
				required:"职业不能为空"
			},
			addname:{
				required:"姓名不能为空"
			},
			addage:{
				required:"年龄不能为空"
			},
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.parent());
		}
	});
	$("#edit").validate({
		rules:{
			updateid:{
				required:true
			},
		},
	messages:{
		updateid:{
			required:"id不能为空"
		}
	},
	errorPlacement: function(error, element) {
		error.appendTo(element.parent());
  	}
	});
});
</script>
   </body>
</html>