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
   </head>
   <body>
      <div class="container">
   <div class="row clearfix">
      <div class="col-md-12 column">
         <nav class="navbar navbar-default navbar-inverse navbar-static-top" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="${ctx }/datecontroller/list">数据报表</a>
            </div>
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav">
                  <li class="active">
                      <a href="#">链接</a>
                  </li>
                  <li>
                      <a href="#">链接</a>
                  </li>
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">下拉菜单<strong class="caret"></strong></a>
                     <ul class="dropdown-menu">
                        <li>
                            <a href="#">Action</a>
                        </li>
                        <li>
                            <a href="#">Another action</a>
                        </li>
                        <li>
                            <a href="#">Something else here</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="#">Separated link</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="#">One more separated link</a>
                        </li>
                     </ul>
                  </li>
               </ul>
               <form class="navbar-form navbar-left" role="search" action="${ctx }/datecontroller/findone">
                  <div class="form-group">
                     <input type="text" class="form-control" name="id" placeholder="请输入您要查询的Id"/>
                  </div> <button type="submit" class="btn btn-default">提交</button>
               </form>
               <ul class="nav navbar-nav navbar-right">
                  <li>
                      <a href="#">王子凡</a>
                  </li>
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">下拉菜单<strong class="caret"></strong></a>
                     <ul class="dropdown-menu">
                        <li>
                            <a href="#">Action</a>
                        </li>
                        <li>
                            <a href="#">Another action</a>
                        </li>
                        <li>
                            <a href="#">Something else here</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="#">Separated link</a>
                        </li>
                     </ul>
                  </li>
               </ul>
            </div>
            
         </nav> <form action="">
            <div class="form-group">
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="" placeholder="Id、职业、姓名、年龄">
               </div>
            </div><button type="submit" class="btn btn-default btn-primary">查询</button>
         </form>
         <form>
            <div class="form-inline">
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="">
               </div>
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="">
               </div>
            </div>
            <div>
               <button type="submit" class="btn btn-default btn-primary">查询</button>
            </div>
         </form>
         <form name="add" id="add"  action="${ctx }/datecontroller/add">
            <div class="form-inline">
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="addoccupation" placeholder="职业">
               </div>
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="addname" placeholder="姓名">
               </div>
            </div>
            <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="addage" placeholder="年龄">
               </div>
            <div>
               <button type="submit" class="btn btn-default btn-primary">添加</button>
            </div>
         </form>
         <form action="${ctx }/datecontroller/edit">
            <div class="form-inline">
               <div class="col-xs-2">
                  <input class="form-control input-sm" type="text" name="updateid" placeholder="Id">
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
                     <a href="${ctx }/datecontroller/delete?id=${lists.id}">删除</a>
                  </td>
               </tr>
              </c:forEach>
            </tbody>
         </table>
         <ul class="pager">
         <c:forEach begin="1" end="${page.totalPageNum }" var="pageNum">
         	<li>
				<a name="pagen" href="${ctx }/datecontroller/list?pageNum=${pageNum }">${pageNum }</a>
			</li>
		</c:forEach>
		<li>
                <a name="pageNum" href="${ctx }/datecontroller/list?pageNum=${1 }">1</a>
            </li>
            <li>
                <a name="pageNum" href="${ctx }/datecontroller/list?pageNum=${2 }">2</a>
            </li>
         </ul>
      </div>
   </div>
</div>
      <!-- jQuery (Bootstrap ç JavaScript æä»¶éè¦å¼å¥ jQuery) -->
      <script src="https://code.jquery.com/jquery.js"></script>
      <!-- åæ¬ææå·²ç¼è¯çæä»¶ -->
      <script src="${ctx }/style/js/bootstrap.min.js"></script>
   </body>
</html>