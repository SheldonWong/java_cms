<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <pg:pager items="1001" maxPageItems="30" maxIndexPages="20" export="currentPageNumber=pageNumber" >
	  <pg:first>
	  	<a href="${pageUrl }">首页</a>	
	  </pg:first>
	  <pg:prev>
	  	<a href="${pageUrl }">前页</a>
	  </pg:prev>
	  <pg:pages>
	  	<c:choose>
	  		<c:when test="${currentPageNumber eq pageNumber }"><font color ="red">${pageNumber}</font></c:when>
	  		<c:otherwise><a href="${pageUrl }">${pageNumber }</a></c:otherwise>
	  	</c:choose>
	  </pg:pages>
	  <pg:next>
	  	<a href="${pageUrl }">下页</a>
	  </pg:next>
	  <pg:last>
	  	<a href="${pageUrl }">尾页</a>
	  </pg:last>
	  
  </pg:pager>
  
  <body>
   
  </body>
</html>
