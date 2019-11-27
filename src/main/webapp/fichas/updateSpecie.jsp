<%@page import="entidades.fichas_tecnicas.Tbl_family"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
if(request.getAttribute("pass") == null){
	response.sendRedirect("./updateSpecie");
	return;
}



%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=Server.getAppName() %> - Editar Especie</title>
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
	            <h1>Registro [Nueva Especie]</h1>
	          </div>
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="">Gestión de Fichas Técnicas</a></li>
	              <li class="breadcrumb-item active">Nuevo Hongo</li>
	            </ol>
	          </div>
	        </div>
	      </div><!-- /.container-fluid -->
	    </section>
	
	<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
       
       
	      <c:if test="${msg != null}">
		    <div class="alert alert-${type} alert-dismissible fade show" role="alert">
			  ${cont}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		 </c:if>
        <div class="row">
          <!-- left column -->
          <div class="col-md-12">
            <!-- general form elements -->
            <div class="card card-primary">
                    <div class="card-header">
                        <h3 class="card-title">Nueva Especie</h3>
                    </div>

                    <form role="form" action="../updateSpecie" method="POST">
                        <input type="hidden" name="idSpecie" id="idSpecie" value="${specie.id}">
                        <div class="card-body">
                            <h5>Datos de Especie</h5>
                            
                            <div class="form-group">
                                <label for="common_name">Nombre Común</label>
                                <input type="text" class="form-control" id="common_name" placeholder="Nombre Común" name="common_name"
                                value="${specie.common_name}" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="scientific_name">Nombre Científico</label>
                                <input type="text" class="form-control" id="scientific_name" placeholder="Nombre Científico" 
                                name="scientific_name" value="${specie.scientific_name}" required>
                            </div>
                            
                            
                            <div class="form-group">
                                <label for="Description">Descripción</label>
                                <textarea class="form-control" id="description" rows="3"
                                    placeholder="Descripción" name="description">${specie.description}</textarea>
                            </div>

                            <div class="form-group">
                                <label for="family">Familia</label>
                                <select class="form-control" id="family" name="family" required>
                                   <option value="">Seleccione...</option>
                                   <c:if test="${families != null}">
                                     <c:forEach items="${families}" var="fam">
                                        <option value="${fam.id}" ${specie.family.id == fam.id?"selected":"" }>${fam.name}</option>
                                     </c:forEach>
                                   </c:if>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="genus">Género</label>
                                <select class="form-control" id="genus" name="genus">
                                   <option value="">Seleccione...</option>
                                   <c:if test="${genus != null}">
                                     <c:forEach items="${genus}" var="gen">
                                         <option value="${gen.id}" ${specie.genus.id == gen.id?"selected":""}>${gen.name}</option>
                                     </c:forEach>
                                   </c:if>
                                </select>
                            </div>

                            
                            <div class="form-group">
                                <label for="type">Tipo</label>
                                <select class="form-control" id="type" name="type" required>
                                  <option value="">Seleccione...</option>
                                  <option value="planta" ${specie.type == "planta"?"selected":""}>Planta</option>
                                  <option value="hongo" ${specie.type == "hongo"?"selected":""}>Hongo</option>
                                </select>
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
  <!-- Custom scripts for all pages-->
  <script src="../js/sb-admin-2.min.js"></script>

 <!-- jAlert js -->
  <script src="../vendor/jAlert/dist/jAlert.min.js"></script>
  <script src="../vendor/jAlert/dist/jAlert-functions.min.js"> </script>
  <script src="../js/showImage.js"></script>
  
  
</body>
</html>