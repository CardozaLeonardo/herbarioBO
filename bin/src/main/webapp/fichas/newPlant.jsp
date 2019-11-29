<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nueva Planta</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="../img/Logo.png" type="image/x-icon">
<!-- Custom fonts for this template -->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../css/sb-admin-2.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  
  <!-- jAlert css  -->
  <link rel="stylesheet" href="../vendor/jAlert/dist/jAlert.css" /> 
  
<!-- Font Awesome -->
<!-- <link rel="stylesheet" href="../../plugins/fontawesome-free/css/all.min.css">
Ionicons
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
Theme style
<link rel="stylesheet" href="../../dist/css/adminlte.min.css">
Google Font: Source Sans Pro
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
jAlert css 
<link rel="stylesheet" href="../../plugins/jAlert/dist/jAlert.css" /> -->


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
	            <h1>Registro [Nueva Planta]</h1>
	          </div>
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="">Gestión de Fichas Técnicas</a></li>
	              <li class="breadcrumb-item active">Nueva Planta</li>
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
                        <h3 class="card-title">Nueva Planta</h3>
                    </div>

                    <form role="form" action="" method="POST">
                        <div class="card-body">
                            <h5>Datos de Espécimen</h5>
                            <div class="form-group">
                                <label for="commonName">Nombre común</label>
                                <input type="text" class="form-control" id="commonName" placeholder="Nombre común">
                            </div>

                            <div class="form-group">
                                <label for="family">Familia</label>
                                <select class="form-control" id="family">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="genus">Género</label>
                                <select class="form-control" id="genus">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="specie">Especie</label>
                                <select class="form-control" id="specie">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="descriptionSpecimen">Descripción del espécimen</label>
                                <textarea class="form-control" id="descriptionSpecimen" rows="3"
                                    placeholder="Descripción del espécimen"></textarea>
                            </div>

                            <div class="form-group">
                                <label for="specimenStatus">Estado</label>
                                <select class="form-control" id="specimenStatus">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="numberSpecimens">Número de especímenes colectados</label>
                                <input type="number" class="form-control" id="numberSpecimens"
                                    placeholder="Número de especímenes">
                            </div>

                            <h5>Datos de Ubicación</h5>

                            <div class="form-group">
                                <label for="country">País</label>
                                <select class="form-control" id="country">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="state">Estado/Provincia/Condado</label>
                                <input type="text" class="form-control" id="state"
                                    placeholder="Estado/Provincia/Condado">
                            </div>

                            <div class="form-group">
                                <label for="city">Ciudad</label>
                                <input type="text" class="form-control" id="city" placeholder="Ciudad">
                            </div>

                            <div class="form-group">
                                <label for="specificCollectionArea">Area de recolección específica</label>
                                <input type="text" class="form-control" id="specificCollectionArea"
                                    placeholder="Area de recolección específica">
                            </div>

                            <h6>Coordenadas</h6>

                            <div class="form-group">
                                <label for="latitude">Latitud</label>
                                <input type="text" class="form-control" id="latitude"
                                    placeholder="Latitud">
                            </div>

                            <div class="form-group">
                                <label for="longitude">Longitud</label>
                                <input type="text" class="form-control" id="longitude" placeholder="Longitud">
                            </div>

                            <h5>Datos de Hábitat</h5>

                            <div class="form-group">
                                <label for="habitat">Hábitat</label>
                                <select class="form-control" id="habitat">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="habitatDescription">Descripción de hábitat</label>
                                <select class="form-control" id="habitatDescription">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="biostatus">Bioestado</label>
                                <select class="form-control" id="biostatus">Bio

                                </select>
                            </div>

                        </div>
                        <div class="card-footer">
                            <button type="submit" class="btn btn-primary">Submit</button>

                            <a href="" class="btn btn-danger btn-icon-split">
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
  
</body>
</html>