<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.ResearcherManagement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GadgetBadget REST Jersey RiWA</title>
<link rel="stylesheet" href="Views/main_form_styles.css">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/researchers.js"></script>
</head>
<body>


	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="ResearcherManagement.jsp">GadgetBadget</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class=""><a href="ResearcherManagement.jsp">Researcher Management</a></li>   
	    </ul>
	  </div>
	</nav>



	<div class="container"><div class="row"><div class="col-6"> 
		<h1 id="h1_header">Researcher Management</h1>
		<form id="formResearcher" name="formResearcher">
			 Researcher code: 
			 <input id="resCode" name="resCode" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Researcher name: 
			 <input id="resName" name="resName" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Researcher Email: 
			 <input id="resEmail" name="resEmail" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Researcher Age: 
			 <input id="resAge" name="resAge" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Researcher Address: 
			 <input id="resAddress" name="resAddress" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Researcher Role: 
			 <input id="resRole" name="resRole" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Researcher Joined Date: 
			 <input id="joinDate" name="joinDate" type="text" 
			 class="form-control form-control-sm">
			 
			 <br>
			 <input id="btnSave" name="btnSave" type="button" value="Save" 
			 class="btn btn-primary">
			 
			 <input type="hidden" id="hidItemIDSave" 
			 name="hidItemIDSave" value="">
		</form>
		
		<br>
		<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<h2 id="h2_header">All The Researchers' Records</h2><br>
				<div id="divItemsGrid">
				 <%
				 	 ResearcherManagement researcherManagementObj = new ResearcherManagement(); 
					 out.print(researcherManagementObj.readResearchers());
					 
				 %>
				</div>
			</div> 
		</div> 
	</div>


</body>
</html>