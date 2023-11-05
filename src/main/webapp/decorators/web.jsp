<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
	   <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="<c:url value='/template/web/css/styles.css'/>" rel="stylesheet" />
</head>
<body>
	  <!-- Responsive navbar-->
   	  <%@include file="/common/web/header.jsp"%>
        <!-- Page Content-->
    	 <dec:body/>
        <!-- Footer-->
       	  <%@include file="/common/web/footer.jsp"%>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
		<script src="<c:url value='/template/web/js/scripts.js'/>"></script>        

</body>
</html>