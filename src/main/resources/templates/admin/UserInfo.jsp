<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>UserInfo</title>

    <!-- Bootstrap core CSS -->

	<link rel="stylesheet" type="text/css" th:href="@{/vendor/bootstrap/css/bootstrap.min.css}" /> 

    <!-- Custom styles for this template -->
	<link rel="stylesheet" type="text/css" th:href="@{/css/AdminHome.css}" /> 
		<link rel="stylesheet" type="text/css" th:href="@{/css/UpdateIpcCode.css}" /> 
		<!-- Latest compiled and minified CSS -->

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="/admin/AdminHome.html">Law System</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            
             <li class="nav-item">
              <a class="nav-link" href="/admin/AddIpcCode">Add IPC Codes</a> 
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/admin/UpdateIpcCode.html">Update IPC Codes</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/admin/UserInfo.html">Check User Details</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/admin/Contact.html">Contact Us</a>
<!-- apply it here -->          
		 <li class="nav-item">
		 <form th:action="@{/logout}" method="get">
			<button class="btn btn-md btn-danger btn-block" name="registration"
				type="Submit">Logout</button>
		</form> 
   <!--     <a class="nav-link" href="contact.html">Logout</a> -->  
          </ul>
        </div>
      </div>
    </nav>

    <header>
      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
           <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <!-- Slide One - Set the background image for this slide in the line below -->
          <div class="carousel-item active" th:style="'padding-left: 26px; background-image: url(' + @{/images/justice.jpg}+');'">
            <div class="carousel-caption d-none d-md-block">
            </div>
          </div>
          <!-- Slide Two - Set the background image for this slide in the line below -->
          <div class="carousel-item"th:style="'padding-left: 26px; background-image: url(' + @{/images/table.jpg} +');'">
            <div class="carousel-caption d-none d-md-block">
            </div>
          </div>
          <!-- Slide Three - Set the background image for this slide in the line below -->
          <div class="carousel-item" th:style="'padding-left: 26px; background-image: url(' + @{/images/india_flag_emblem-colombotelegraph.jpg} +');'">
            <div class="carousel-caption d-none d-md-block">
            </div>
          </div>
	<div class="carousel-item" th:style="'padding-left: 26px; background-image: url(' + @{/images/courts.jpg} +');'">
            <div class="carousel-caption d-none d-md-block">
            </div>
          </div>

        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </header>
    <div class="container" style="margin-right:200px; margin-top:20px; margin-bottom:20px">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
			<form autocomplete="off" action="#" th:action="@{/admin/UserInfo}"
					th:object="${user}" method="post" class="form-horizontal"role="form">
                <table class="table table-striped">
            <thead>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email/login</th>
                    <th>Profession</th>
                    <th>Select<th>
                </tr>
            </thead>
            <tbody>
                <c:forEach varStatus="us" var="user" items="${userListWrapper.users}" >
                    <tr>
                        <td><form>input type="hidden" path="users[${us.index}].firstName"/>${user.firstName}</td>
                        <td><form:input type="hidden" path="users[${us.index}].lastName"/> ${user.lastName}</td>
                        <td><form:input type="hidden" path="users[${us.index}].login"/>${user.login}</td>
                        <td><form:input type="hidden" path="users[${us.index}].profession"/>${user.profession}</td>
                        <td><form:checkbox path="users[${us.index}].delete" value="${user.delete}"/></td>
         <form:input type="hidden" path="users[${us.index}].id"/>
                    </tr>
                </c:forEach>
            </tbody>
        </table>            
        <input type="submit" value="Delete user(s)" class="btn-danger" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
      </div>
      </div>
      </div>
      
      
      
      
      <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Online Law Sysetm &copy;  Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>
     <script type="text/javascript" th:src="@{/vendor/jquery/jquery.min.js}"></script>
      <script type="text/javascript" th:src="@{/vendor/bootstrap/js/bootstrap.bundle.min.js}"></script>
    </body>
    </html>