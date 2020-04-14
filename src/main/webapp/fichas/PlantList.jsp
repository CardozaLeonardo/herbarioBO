<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.* , entidades.fichas_tecnicas.* , datos.*, datos.fichas_tecnicas.* , java.util.* , utils.* , org.json.JSONObject"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 

  <title>Herbario Nacional-Especimenes</title> 


  <jsp:include page="../WEB-INF/layouts/meta.jsp"></jsp:include>

<%
	Cookie[] cookies = request.getCookies();

if(cookies == null){
	response.sendRedirect("../login");
}

List <String> get_cookies = Cookies.getCookies(cookies);

String token = get_cookies.get(0);
String tokenRefresh = get_cookies.get(1);

if(token == null && tokenRefresh == null){
	response.sendRedirect("../login");
}

DT_plantSpecimen dtPlantspecimen = new DT_plantSpecimen();
JSONObject specimenJson  = dtPlantspecimen.getPlantSpecimens(request.getCookies());

Tbl_plantSpecimen[] listSpecimens = null;

if(specimenJson.getInt("status") == 200){
  listSpecimens = (Tbl_plantSpecimen[]) specimenJson.get("plant_specimens");
	
}else if(specimenJson.getInt("status") == 401){
	response.sendRedirect("../login");
	return;
}

if(listSpecimens == null){
	response.sendRedirect("../login");
	return;
}
 
System.out.println(specimenJson);
 
 /*Tbl_plantSpecimen  tbl_specimen = null;*/
 
 boolean withRole = false;
 String rolInput = "";
 String id_rol ="";
 String errorMsg = "";
 boolean error = false; // Para indicar cualquier error a notificar


 // Getting user permissions

 HttpSession hts = request.getSession();

 Tbl_opcion[] permisions = (Tbl_opcion[]) hts.getAttribute("user_permissions");

boolean viewPermission = false;
boolean editPermission = false;
boolean deletePermission = false;
boolean addPermission = false;

for(Tbl_opcion p: permisions) {
    if (p.getCodename().equals("view_plantspecimen")) {
        viewPermission = true;
    }

    if(p.getCodename().equals("change_plantspecimen")) {
        editPermission = true;
    }

    if(p.getCodename().equals("delete_plantspecimen")) {
        deletePermission = true;
    }

    if(p.getCodename().equals("add_plantspecimen")) {
        addPermission = true;
    }
}

if(!viewPermission) {
    response.sendRedirect(request.getContextPath() + "/accesoDenegado.jsp");
    return;
}

 
 System.out.println("");
///////////

%>

 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<body id="page-top">
   <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="../WEB-INF/layouts/sidebar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <jsp:include page="../WEB-INF/layouts/header.jsp"></jsp:include>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">
          
		  <c:if test="${msg != null}">
		    <div class="alert alert-${type} alert-dismissible fade show" role="alert">
			  ${cont}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		 </c:if>
		  
		  

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Plantas</h1>
          <p class="mb-4">Cree, edite y elimine fichas de plantas</p>

            
            
            <form role="form" method="POST" id="especimenForm" class="col-6" action="../nuevoEspecimen">
              <h4 id="opcIndicador" class="text-gray-800">Operación actual: creación</h4>
            <input type="hidden" id="idEspecimen" name="idEspecimen" value="">
            <input type="hidden" id="opc" name="opc" value="1">
            
            <div class="form-group">
		    <label for="rolName">Especimen: </label>
		    <input type="text" class="form-control" id="rolName" value="" name="rolName" required>
		  </div>
		  
		  
            <div class="form-group">
		    <label for="rolDesc">Descripción: </label>
		    <input type="text" class="form-control" name="rolDesc" id="rolDesc" value="">
		  </div>
		  
		    <button id="submitRole" type="submit" class="btn btn-success">Guardar</button>
		    <button type="reset" id="cancelRoleBTN" class="btn btn-danger">Cancelar</button>
            </form>
            <br>

            <%if(addPermission) {%>
            <a href="<%=request.getContextPath()%>/fichas/newPlant" class="btn btn-primary btn-icon-split">
                <span class="icon text-white-50">
                  <i class="fas fa-plus"></i>
                </span>
                <span class="text">Agregar Planta</span>
              </a>
            <%}%>
            <br>
            <br>
           
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  
                   <thead>
                    <tr>
                      <th>ID</th>
                      <th>Recolector</th>
                      <th>Especie</th>
                      <th>Género</th>
                      <th>Ecosistema</th>
                      <th>Opciones</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                    <th>ID</th>
                       <th>Recolector</th>
                      <th>Especie</th>
                      <th>Género</th>
                      <th>Ecosistema</th>
                      <th>Opciones</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <%
                    	for(Tbl_plantSpecimen plant: listSpecimens) {
                    %>
                    <%if((plant.getStatus().getId() != 3 && plant.getStatus().getId() != 4) && plant.isApproved()) {%>
                    
                    <tr>
                      <td id="cl-id-<%=plant.getId()%>"><%=plant.getId() %></td>
                      <td id="cl-name-<%=plant.getId()%>"><%=plant.getUser().getUsername() %></td>
                       <td id="cl-name-<%=plant.getId()%>"><%=plant.getSpecies().getCommon_name() %></td>
                      <td id="cl-genus-<%=plant.getId()%>"><%=plant.getSpecies().getGenus().getName() %></td>
                      <td id="cl-ecosystem-<%=plant.getId()%>"><%=plant.getEcosystem().getName() %></td>
                      
                      <td>
                       <% if(editPermission) {%>
                       <a href="./updatePlant?id=<%=plant.getId()%>" id="<%=plant.getId()%>" class="editSpecimen">
                       
                       <i class="fas fa-edit editRole"></i>
                       </a>
                       &nbsp;<%}%>
                       <a href="./viewPlant?id=<%=plant.getId()%>" >
                       <i class="far fa-eye" title="View"></i></a>&nbsp;&nbsp;
                       <%if(deletePermission) {%>
                       <a href="#" id="<%=plant.getId()%>" onclick="deletePlant('<%=plant.getId()%>')" class="deletePlant">
                        <i class="fas fa-trash-alt"></i>
                       </a>
                        <%}%>
                      </td>
                    </tr>
                    <%} %>
                    <%} %>
                    
                  </tbody>
                </table>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <jsp:include page="../WEB-INF/layouts/footer.jsp"></jsp:include>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  
  

  <!-- Bootstrap core JavaScript-->
  <script src="../vendor/jquery/jquery.min.js"></script>
  <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="../js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="../vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="../js/demo/datatables-demo.js"></script>
  
  <script src="../jAlert/dist/jAlert.min.js"></script>
  <script src="../jAlert/dist/jAlert-functions.min.js"> </script>
  <!--  <script src="../js/roles.js"></script> -->
  
  <script>
  function deletePlant(id){
	    idPlant = id;
	    url = '../deletePlant?id=' + idPlant;
	    console.log(url);
	    confirm(function()
	    { 	
	        window.location.replace(url);
	    }, 
	    function(e,btn)
	    {
	    
	    });
	  
}
  </script>

</body>
</html>