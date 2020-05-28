<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="datos.*,entidades.*,org.json.JSONObject"%>
    <%
    
    //Limpia la CACHE del navegador
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setDateHeader("Expires", -1);


    
    DT_user dtu = new DT_user();
    JSONObject obj = dtu.obtenerUsuarioIngresado(request.getCookies());
    
    if(obj.getInt("code") == 200)
    {
    	response.sendRedirect(request.getContextPath() + "/");
    	
    	return;
    }

    if(request.getSession() != null) {
        request.getSession().invalidate();
    }
    
    %>
    
    
    <%
    
    //DT_rol dtr = new DT_rol();
    //String[] sessions = dtr.optenerCredenciales();
    
    %>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title><%=Server.getAppName() %> - Login</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link rel="shortcut icon" href="img/Logo.png" type="image/x-icon">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.css" rel="stylesheet">

</head>

<%
	boolean error = false;
 String msg = "";
 String msgType = "";
 //String status = request.getParameter("status");
 
 // Cargando los roles para ingresar
 
 //DT_rol dtrol = new DT_rol();
 
// Tbl_rol[] listaRoles = dtrol.getRoles(response, ck, ck2);
//Tbl_rol[] listaRoles = new Tbl_rol[10];
 
 
 /*if(status != null)
 {
	 error = true;
	 if(status.equals("2")) {
		 msg = "¿A dónde crees que vas? Tienes que <strong>iniciar sesión</strong>";
		 msgType = "info";
	 }else if(status.equals("1")){
		 msg = "Error: <strong>usuario o contrasela</strong> incorrecta";
		 msgType = "danger";
	 }
 }*/
%>

<body class="bg-gradient-primary">

  <div class="container">
 <c:if test="${error != null}">
    <div class="alert alert-${type} alert-dismissible fade show" role="alert">
	  ${msg}
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>
 </c:if>

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-login-image" >
              	<img class = "login-image" src = "img/Logo.png">
              </div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Login</h1>
                    <img class = "login-image-mobile" src = "img/Logo.png">
                  </div>
                  <form method="post" action="verify" class="user">
                      <%if(request.getAttribute("cont") != null){%>
                      <input type="hidden" name="cont" value="<%=request.getAttribute("cont")%>"/>
                      <%}%>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="exampleInputEmail" placeholder="Usuario" aria-describedby="emailHelp" name="username">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Contraseña" name="password">
                    </div>
                    
                    <input type="submit" class="btn btn-primary btn-user btn-block" value="Login" /> 
                  </form>

                    <div>
                        <p>¿Olvidó contraseña?</p>
                        <a href="./forgot_password">Restablecer contraseña</a>
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