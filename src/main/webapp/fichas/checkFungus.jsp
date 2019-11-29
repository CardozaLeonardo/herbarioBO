<%@page import="entidades.fichas_tecnicas.Tbl_plantSpecimen"%>
<%@page import="entidades.fichas_tecnicas.Tbl_formType"%>
<%@page import="entidades.fichas_tecnicas.Tbl_genus"%>
<%@page import="entidades.fichas_tecnicas.Tbl_mushroomSpecimen"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
if(request.getAttribute("pass") == null){
	response.sendRedirect("./checkFungus");
	return;
}

Tbl_genus[] genus = null;

if(request.getAttribute("genus") != null){
	genus = (Tbl_genus[]) request.getAttribute("genus");
}

Tbl_mushroomSpecimen mus = null;



if(request.getAttribute("mus") != null){
	mus = (Tbl_mushroomSpecimen) request.getAttribute("mus");
}

Tbl_formType[] forms = null;

if(request.getAttribute("forms") != null){
	forms = (Tbl_formType[]) request.getAttribute("forms");
}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=Server.getAppName() %> Actualizar Hongo</title>
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

 String idUser = "";

 if(request.getSession().getAttribute("user") != null){
	 Tbl_user user = (Tbl_user) request.getSession().getAttribute("user");
	 idUser += user.getId();
 }
 
 /*Tbl_mushroomSpecimen mus = null;
 
 if(request.getAttribute("mus") != null){
	 mus = (Tbl_mushroomSpecimen) request.getAttribute("mus");
 }*/


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
	            <h1>Ver hongo</h1>
	          </div>
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="">Gestión de Fichas Técnicas</a></li>
	              <li class="breadcrumb-item active">Ver Hongo</li>
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
                        <h3 class="card-title">Ver Hongo</h3>
                    </div>

                    <form role="form" action="../actualizarHongo" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="idUser" id="idUser" value="<%=mus.getUser().getId()%>">
                        <input type="hidden" name="fungusID" value="<%=request.getParameter("id")%>">
                        <div class="card-body">
                            <h5>Datos de Recolector</h5>
                            
                            <div class="form-group">
                                <label for="username">Usuario</label>
                                <input disabled class="form-control" id="username" name="username" value="<%=mus.getUser().getUsername()%>">
                                
                            </div>
                            
                            <div class="form-group">
                                <label for="username">Nombre</label>
                                <input disabled class="form-control" id="username" name="username" 
                                value="<%=mus.getUser().getFirst_name() + " " + mus.getUser().getLast_name()%>">
                                
                            </div>
                            
                            <h5>Datos de Espécimen</h5>
                            
                            <div class="form-group">
                                <label for="family">Familia</label>
                                <input disabled class="form-control" id="family" name="family" value="<%=mus.getFamily().getName()%>">
                                
                            </div>

                            <div class="form-group">
                                <label for="genus">Género</label>
                                <input disabled class="form-control" id="genus" name="genus" value="<%=mus.getGenus().getName()%>">
                            </div>

                            <div class="form-group">
                                <label for="specie">Especie</label>
                                <input disabled class="form-control" id="specie" name="specie" value="<%=mus.getSpecies().getCommon_name()%>">
                            </div>
                            

                            <div class="form-group">
                                <label for="specimenDescription">Descripción del espécimen</label>
                                <textarea disabled class="form-control" id="specimenDescription" rows="3"
                                    placeholder="Descripción del espécimen" name="description" ><%=mus.getDescription()%></textarea>
                            </div>

                            <div class="form-group">
                                <label for="crust">¿Tiene costras?</label>
                                <%if(mus.getCrust() == true) {%>
                                  <input disabled class="form-control" id="crust" name="crust" value="Sí">
                                <%}else{ %>
                                 <input disabled class="form-control" id="crust" name="crust" value="No">
                                <%} %>
                            </div>

                            <div class="form-group">
                                <label for="capType">Tipo de sombrero</label>
                                <input disabled class="form-control" id="capType" name="capType" value="<%=mus.getCap().getName()%>">
                            </div>

                            <div class="form-group">
                                <label for="formType">Tipo de forma</label>
                                <input disabled class="form-control" id="formType" name="formType" value="<%=mus.getForms().getName()%>">
                            </div>

                            <div class="form-group">
                                <label for="color">Color</label>
                                <input disabled type="text" class="form-control" id="color" placeholder="Color" value="<%=mus.getColor()%>" name="color">
                            </div>

                            <div class="form-group">
                                <label for="changeOfColor">Cambios de color</label>
                                <textarea disabled class="form-control" id="changeOfColor" rows="2"
                                    placeholder="Cambios de color" name="change_of_color" ><%=mus.getChange_of_color()%></textarea>
                            </div>

                            <div class="form-group">
                                <label for="smell">Olor</label>
                                <textarea disabled class="form-control" id="smell" rows="2"
                                    placeholder="Olor" name="smell" ><%=mus.getSmell()%></textarea>
                            </div>

                            <div class="form-group">
                                <label for="specimenStatus">Estado</label>
                                <select class="form-control" id="specimenStatus">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="numberSpecimens">Número de especímenes colectados</label>
                                <input disabled type="number" class="form-control" id="numberSpecimens"
                                    placeholder="Número de especímenes" name="number_of_samples" value="<%=mus.getNumber_of_samples()%>">
                            </div>

                            <h5>Datos de Ubicación</h5>

                            <div class="form-group">
                                <label for="country">País</label>
                                <input disabled class="form-control" id="country" name="country" value="<%=mus.getCountry().getName()%>">
                            </div>

                            <div class="form-group">
                                <label for="state">Estado/Provincia/Condado</label>
                                <input disabled class="form-control" id="state" name="state" value="<%=mus.getState().getName()%>">
                            </div>

                            <div class="form-group">
                                <label for="city">Ciudad</label>
                                <input disabled class="form-control" id="city" name="city" value="<%=mus.getCity().getName()%>">
                            </div>

                            <div class="form-group">
                                <label for="specificCollectionArea">Estado Área de Recolección</label>
                                <input disabled class="form-control" id="specificCollectionArea" name="specificCollectionArea" 
                                value="<%=mus.getRecolection_area_status().getName()%>">
                            </div>

                            <h6>Coordenadas</h6>

                            <div class="form-group">
                                <label for="latitude">Latitud</label>
                                <input diabled type="number" class="form-control" id="latitude
                                    placeholder="Latitud" name="latitude" value="<%=mus.getLatitude()%>">
                            </div>

                            <div class="form-group">
                                <label for="longitude">Longitud</label>
                                <input disabled type="number" class="form-control" id="longitude" placeholder="Longitud" name="longitude" 
                                value="<%=mus.getLongitude()%>">
                            </div>

                            <h5>Datos de Hábitat</h5>

                            <div class="form-group">
                                <label for="habitat">Hábitat</label>
                                <input disabled class="form-control" id="habitat" name="habitat" value="<%=mus.getEcosystem().getName()%>">
                            </div>

                            <!--  <div class="form-group">
                                <label for="habitatDescription">Descripción de hábitat</label>
                                <select class="form-control" id="habitatDescription">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="biostatus">Bioestado</label>
                                <select class="form-control" id="biostatus">
                                  <option></option>
                                </select>
                            </div>-->

                        </div>
                        
                        <div class="form-group">
                             <input disabled type="file" id="photo" name="photo" class="form-control-file">
                           <div class="card bg-light" style="min-height: 400px; width:90%;margin-left: auto;margin-right:auto;">
                             <img id="imagePreview" src="<%=request.getContextPath() + mus.getPhoto() %>" alt="image preview" width="60%" height="auto" 
                             style="margin-left: auto;margin-right:auto;"/>
                           </div>
                        </div>
                        
                        <div class="card-footer">
                            <label for="opt">Opción</label>
                            <div class="form-group">
                            <select class="form-control" name="opt">
                               <option value="2">Aprobar</option>
                               <option value="3">Rechazar</option>
                            </select>
                            </div>
                              <button type="submit" class="btn btn-primary">Continuar</button>

                            <!--  <a href="" class="btn btn-danger btn-icon-split">
                                <span class="text">Cancelar</span> -->
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
  <!-- Custom scripts for all pages-->
  <script src="../js/sb-admin-2.min.js"></script>

 <!-- jAlert js -->
  <script src="../vendor/jAlert/dist/jAlert.min.js"></script>
  <script src="../vendor/jAlert/dist/jAlert-functions.min.js"> </script>
  <script src="../js/showImage.js"></script>
  
  
</body>
</html>