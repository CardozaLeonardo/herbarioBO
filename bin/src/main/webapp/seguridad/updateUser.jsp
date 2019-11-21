<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Usuario</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="../img/Logo.png" type="image/x-icon">
<!-- Custom fonts for this template -->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  
  <!-- jAlert css  -->
  <link rel="stylesheet" href="../vendor/jAlert/dist/jAlert.css" /> 
  
</head>

<%
 String mensaje = "Se ha guardado";

%>
<body class="hold-transition sidebar-mini">

<div id="wrapper">
	
	<!-- SIDEBAR -->
	  	<jsp:include page="../WEB-INF/layouts/sidebar.jsp"></jsp:include>
	<!-- SIDEBAR -->
	
	  <!-- Content Wrapper. Contains page content -->
	  <div id="content-wrapper" class="d-flex flex-column">
	    <!-- Content Header (Page header) -->
	    
	    <section class="content-header">
	<!-- Navbar -->
	  	<jsp:include page="../WEB-INF/layouts/header.jsp"></jsp:include>
	<!-- /.navbar -->
	
	      <div class="container-fluid">
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1>Editar Usuario</h1>
	          </div>
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="usuarios.jsp">Seguridad</a></li>
	              <li class="breadcrumb-item active">Editar Usuario</li>
	            </ol>
	          </div>
	        </div>
	      </div><!-- /.container-fluid -->
	    </section>
	
	<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <!-- left column -->
          <div class="col-md-12">
            <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">Editar Usuario</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form role="form" action="../updateUser" method="post">
                <div class="card-body">
                  <input name="id" id="id" type="hidden" value="${user.id}"> <!-- ESTE INPUT ES UTILIZADO PARA EL CASE DEL SERVLET -->
                  <div class="form-group">
                    <label for="username">Nombre de Usuario:</label>
                    <input type="text" id="username" value="${user.username}" name="username" class="form-control" required>
                  </div>
                  <div class="form-group">
                    <label for="password1">Contraseña: </label>
                    <input type="password"  id="password" name="password" class="form-control" 
                    title="Recuerde usar letras mayúsculas, minúsculas, números y caracteres especiales.">
                  </div>
                  <div class="form-group">
                    <label for="password2">Confirmar Contraseña: </label>
                    <input type="password" id="password2" name="password2" class="form-control" 
                    title="Recuerde usar letras mayúsculas, minúsculas, números y caracteres especiales.">
                  </div>
                  <div class="form-group">
                    <label for="nombre1">Primer Nombre:</label>
                    <input type="text" id="nombre1" name="nombre1"  value="${user.first_name}" class="form-control" pattern="[A-Za-z]{4-16}" required>
                  </div>
                  <div class="form-group">
                    <label for="nombre2">Segundo Nombre:</label>
                    <input type="text" id="nombre2" name="nombre2"  class="form-control" required  pattern="[A-Za-z]{4-16}">
                  </div>
                  <div class="form-group">
                    <label for="apellido1">Primer Apellido:</label>
                    <input type="text" id="apellido1" name="apellido1" value="${user.last_name}" class="form-control"  pattern="[A-Za-z]{4-16}" required>
                  </div>
                  <div class="form-group">
                    <label for="apellido2">Segundo Apellido:</label>
                    <input type="text" id="apellido2" name="apellido2" class="form-control" required pattern="[A-Za-z]{4-16}">
                  </div>
                  <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${user.email}" class="form-control" required>
                  </div>
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Editar</button>
                  
                  <a href="usuarios.jsp" class="btn btn-danger btn-icon-split">
                    <span class="text">Cancelar</span>
                  </a>
                  
                </div>
              </form>
            </div>
            <!-- /.card -->
           </div>
          </div>
         </div>       
    </section>
    <!-- /.content -->
	
	<!-- Footer -->
  		<jsp:include page="../WEB-INF/layouts/footer.jsp"></jsp:include>
  	<!-- ./Footer -->
	</div>
  </div>

  <!-- jQuery -->
  <script src="../vendor/jquery/jquery.min.js"></script>
  <!-- <script src="../../plugins/jquery/jquery.min.js"></script> -->

 <!-- jAlert js -->
  <script src="../vendor/jAlert/dist/jAlert.min.js"></script>
  <script src="../vendor/jAlert/dist/jAlert-functions.min.js"> </script>
  
  
  
  <script>
    $(document).ready(function ()
    {
     
      /////////// VARIABLES DE CONTROL MSJ ///////////
      var nuevo = 0;
      nuevo = "<%=mensaje%>";

      if(nuevo == "1")
      {
        successAlert('Éxito', 'El nuevo registro ha sido almacenado.');
      }
      if(nuevo == "2")
      {
        errorAlert('Error', 'Revise los datos e intente nuevamente.');
      }
    });
    </script>

</body>
</html>