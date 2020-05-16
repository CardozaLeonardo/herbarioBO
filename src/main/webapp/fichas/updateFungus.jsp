<%@page import="entidades.fichas_tecnicas.Tbl_plantSpecimen"%>
<%@page import="entidades.fichas_tecnicas.Tbl_formType"%>
<%@page import="entidades.fichas_tecnicas.Tbl_genus"%>
<%@page import="entidades.fichas_tecnicas.Tbl_mushroomSpecimen"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList"%>
<%@ page import="util.StringAdapt" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
if(request.getAttribute("pass") == null){
	response.sendRedirect("./updateFungus");
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
    <link rel="stylesheet" href="../js/select2/select2.min.css" />
  
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
	            <h1>Registro [Nuevo Hongo]</h1>
	          </div>
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="">Gesti�n de Fichas T�cnicas</a></li>
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
                        <h3 class="card-title">Nuevo Hongo</h3>
                    </div>

                    <form role="form" action="../actualizarHongo" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="idUser" id="idUser" value="<%=mus.getUser().getId()%>">
                        <input type="hidden" name="fungusID" value="<%=request.getParameter("id")%>">
                        <div class="card-body">
                            <h5>Datos de Esp�cimen</h5>

                            <div class="form-group">
                                <label for="specie">Especie</label>
                                <select class="form-control" id="specie" name="species">
                                   <option value="">Seleccione...</option>
                                   <c:if test="${species != null}">
                                     <c:forEach items="${species}" var="specie">
                                         <option value="${specie.id}" ${mus.species.id == specie.id?"selected":""}>${specie.common_name}</option>
                                     </c:forEach>
                                   </c:if>
                                </select>
                            </div>
                            

                            <div class="form-group">
                                <label for="specimenDescription">Descripci�n del esp�cimen</label>
                                <textarea class="form-control" id="specimenDescription" rows="3"
                                    placeholder="Descripci�n del esp�cimen" name="description"><%=mus.getDescription()%></textarea>
                            </div>

                            <div class="form-group">
                                <label for="crust">�Tiene costras?</label>
                                <select class="form-control" id="crust" name="crust" required>
                                  <option value="">Seleccione...</option>
                                  <option value="true" ${mus.crust?"selected":""}>S�</option>
                                  <option value="false" ${!mus.crust?"selected":""}>No</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="capType">Tipo de sombrero</label>
                                <select class="form-control" id="capType" name="cap" required>
                                  <option value="">Seleccione...</option>
                                   <c:if test="${caps != null}">
                                     <c:forEach items="${caps}" var="cap">
                                         <option value="${cap.id}" ${mus.cap.id == cap.id?"selected":""}>${cap.name}</option>
                                     </c:forEach>
                                   </c:if>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="formType">Tipo de forma</label>
                                <select class="form-control" id="formType" name="forms" required>
                                  <option value="">Seleccione...</option>
                                   <%if(forms != null) {%>
                                     <%for(Tbl_formType form: forms) {%>
                                         <%if(mus.getForms().getId() == form.getId()) {%>
                                         <option value="<%=form.getId()%>" selected><%=form.getName()%></option>
                                         <% } else{%>
                                           <option value="<%=form.getId()%>"><%=form.getName()%></option>
                                         <%} %>
                                         
                                     <%} %>
                                   <%} %>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="color">Color</label>
                                <input type="text" class="form-control" id="color" placeholder="Color" value="<%=mus.getColor()%>" name="color" required>
                            </div>

                            <div class="form-group">
                                <label for="changeOfColor">Cambios de color</label>
                                <textarea class="form-control" id="changeOfColor" rows="2"
                                    placeholder="Cambios de color" name="change_of_color" value=""><%=mus.getChange_of_color()%></textarea>
                            </div>

                            <div class="form-group">
                                <label for="smell">Olor</label>
                                <textarea class="form-control" id="smell" rows="2"
                                    placeholder="Olor" name="smell" ><%=mus.getSmell()%></textarea>
                            </div>

                            <div class="form-group">
                                <label for="specimenStatus">Estado</label>
                                <select class="form-control" id="specimenStatus">

                                </select>
                            </div>

                            <div class="form-group">
                                <label for="numberSpecimens">N�mero de espec�menes colectados</label>
                                <input type="number" class="form-control" id="numberSpecimens"
                                    placeholder="N�mero de espec�menes" name="number_of_samples" value="<%=mus.getNumber_of_samples()%>">
                            </div>

                            <h5>Datos de Ubicaci�n</h5>

                            <div class="form-group">
                                <label for="city">Lugar: Pa�s/Estado/Ciudad</label>
                                <select class="form-control" id="city" name="city" required>
                                    <option value="">Sin seleccionar...</option>
                                    <c:if test="${cities != null}">
                                        <c:forEach items="${cities}" var="city">
                                            <option value="${city.id}" ${mus.city.id == city.id?"selected":""}>${city.state.country.name}/${city.state.name}/${city.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>


                            <div class="form-group">
                                <label for="specificCollectionArea">Area de recolecci�n espec�fica</label>
                                <select class="form-control" id="specificCollectionArea" name="recolection_area_status" required>
                                  <option value="">Seleccione...</option>
                                   <c:if test="${reco != null}">
                                     <c:forEach items="${reco}" var="rec">
                                         <option value="${rec.id}" ${mus.recolection_area_status.id == rec.id?"selected":""}>${rec.name}</option>
                                     </c:forEach>
                                   </c:if>
                                </select>
                            </div>

                            <h6>Coordenadas</h6>

                            <div class="form-group">
                                <label for="latitude">Latitud</label>
                                <input type="number" class="form-control" id="latitude
                                    placeholder="Latitud" name="latitude" value="<%=mus.getLatitude()%>">
                            </div>

                            <div class="form-group">
                                <label for="longitude">Longitud</label>
                                <input type="number" class="form-control" id="longitude" placeholder="Longitud" name="longitude" value="<%=mus.getLongitude()%>">
                            </div>

                            <div class="form-group">
                                <label for="location">Ubicaci�n</label>
                                <input type="text" class="form-control" id="location" placeholder="" name="location"
                                       required value="<%=mus.getLocation()%>">
                            </div>

                            <h5>Datos de H�bitat</h5>

                            <div class="form-group">
                                <label for="habitat">H�bitat</label>
                                <select class="form-control" id="habitat" name="ecosystem" required>
                                  <option value="">Seleccione...</option>
                                   <c:if test="${ecosystems != null}">
                                     <c:forEach items="${ecosystems}" var="ecosystem">
                                         <option value="${ecosystem.id}" ${mus.ecosystem.id == ecosystem.id?"selected":""}>${ecosystem.name}</option>
                                     </c:forEach>
                                   </c:if>
                                </select>
                            </div>

                            <!--  <div class="form-group">
                                <label for="habitatDescription">Descripci�n de h�bitat</label>
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
                           <input type="file" id="photo" name="photo" class="form-control-file">
                           <div class="card bg-light" style="min-height: 400px; width:90%;margin-left: auto;margin-right:auto;">
                             <%if(mus.getPhoto() != null) {%>
                             <img id="imagePreview" src="<%=StringAdapt.adaptDriveImage(mus.getPhoto())%>" alt="image preview" width="60%" height="auto"
                                  style="margin-left: auto;margin-right:auto;"/>
                             <%}else{ %>
                              <img id="imagePreview" src="" alt="image preview" width="60%" height="auto" 
                             style="margin-left: auto;margin-right:auto;"/>
                             <%} %>
                             
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

<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
  <!-- <script src="../../plugins/jquery/jquery.min.js"></script> -->
  <!-- Custom scripts for all pages-->
  <script src="../js/sb-admin-2.min.js"></script>

 <!-- jAlert js -->
  <script src="../vendor/jAlert/dist/jAlert.min.js"></script>
  <script src="../vendor/jAlert/dist/jAlert-functions.min.js"> </script>
  <script src="../js/showImage.js"></script>
  <script src="../js/zoneFetcher.js"></script>
<script src="../js/select2/select2.full.min.js"></script>

<script>
    $(document).ready(function() {
        $('#city').select2();
    });
</script>
  
</body>
</html>