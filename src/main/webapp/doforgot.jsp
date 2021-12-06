<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% String  codemail = (String)session.getAttribute("authcode");
	 String codemail1 = request.getParameter("code"); 
	 if(codemail==null){
			response.sendRedirect("login.jsp"); 
		}
		else{
			if(!(codemail.equals(codemail1))){
				System.out.println("codemail= "+ codemail);
				System.out.println("codemail1= "+ codemail1);
				 response.sendRedirect("login.jsp"); 
			}
			
		}%>
	 <%@page import="java.sql.ResultSet"%>
<%@page import="atweb.nhom6.dao.ConnectionToDB"%>
<%ConnectionToDB con = new ConnectionToDB();      %>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 -Do Change Password</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-2">Do Change Your Password</h1>
										<p class="mb-4">We get it, stuff happens. Just enter your
											email address below and we'll send you a link to reset your
											password!</p>
									</div>
									<% String email = request.getParameter("email");
                	 ResultSet pass = con.selectData("select * from account where email='"+email+"'");
                		
                	 	while(pass.next()){
                		 
                		 if(pass.getString("email").equals(null)){ %>	
                		
                		 <% }else{
                		 %>
									<form class="user"  action="${pageContext.request.contextPath}/forgot-password?action=xacthuc&email=<%pass.getString("email");%>" method="POST"  accept-charset="UTF-8"  enctype="multidata/form-data">
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="exampleInputEmail" name="password" aria-describedby="emailHelp"
												placeholder="Enter Password...">
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="exampleInputEmail" name="repassword" aria-describedby="emailHelp"
												placeholder="Enter rePassword Address...">
										</div>
										
											<button class="btn btn-primary btn-user btn-block"> Change
											Password</button>
									</form>
									<%}} %>
									<hr>
									<div class="text-center">
										<a class="small" href="register.html">Create an Account!</a>
									</div>
									<div class="text-center">
										<a class="small" href="<%=request.getContextPath()%>">Already have an
											account? Login!</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

</body>

</html>