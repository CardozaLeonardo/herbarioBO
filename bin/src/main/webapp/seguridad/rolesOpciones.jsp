<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList"%>
    
<!-- 
       AUTOR:  Leonardo Cardoza
       FIN:    29/10/2019
   
 -->
    
<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Herbario Nacional - Roles-Opciones</title>

  <!-- Custom fonts for this template -->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  <link rel="stylesheet" href="../jAlert/dist/jAlert.css" />

</head>
<%
	//VALIDACIÓN DE LA EXISTENCIA DE LA SESIÓN
		String loginUser="";
		loginUser = (String)session.getAttribute("login");
		//VALIDA QUE LA VARIABLE loginUser NO SEA NULL
		loginUser = loginUser==null?"":loginUser;
		if(loginUser.equals(""))
		{
	response.sendRedirect("../login.jsp?status=2");
	return;
		}
		
// VALIDAR ACCESO
ArrayList<VW_user_opciones> opciones = (ArrayList<VW_user_opciones>) session.getAttribute("opciones");
boolean acceso = false;

for(VW_user_opciones vu: opciones) {
	 if(vu.getOpcion().equals("./seguridad/rolesOpciones.jsp")) {
		 acceso = true;
	 }
}

if(!acceso){
	 response.sendRedirect("../accesoDenegado.jsp");
}

///////////
 


 DT_rolUsuario tru = new DT_rolUsuario();
 ArrayList<VW_user_rol> rolesUser = null;
 
 DT_Rol dtr = new DT_Rol();
 ArrayList<Tbl_rol> listaRoles = dtr.listarRoles();
 
 ArrayList<VW_rol_opcion> opcionesRol = null;
 DT_rolOpcion  dtro = new DT_rolOpcion();
 
 
 
 DT_opcion dto = new DT_opcion();
 ArrayList<Tbl_opcion> listaOpciones = dto.listarOpciones();
 
 
 Tbl_rol role = null;
 
 boolean withRole = false;
 String rolInput = "";
 String id_rol ="";
 String errorMsg = "";
 boolean error = false; // Para indicar cualquier error a notificar
 
 
 if(request.getParameter("rol") != null)
 {
	 try{
	     int idRol = Integer.parseInt(request.getParameter("rol"));
		 opcionesRol = dtro.listarRolOpcion(idRol);
		 role = dtr.obtenerRol(idRol);
		 if(role == null){
	 response.sendRedirect("rolesOpciones.jsp?error=1");
	 return;
	 //System.out.println("Adios");
		 }
		 
		 rolInput = role.getRol_name();
		 id_rol += role.getId_rol();
		 withRole = true;
	 
	 }catch(NumberFormatException e){
		 response.sendRedirect("rolesUsuarios.jsp?error=2");
		 return;
	 }
 }
 
 if(request.getParameter("error") !=null){
	 error = true;
	 String errorVal = request.getParameter("error");
	 if(errorVal.equals("1")){
		 errorMsg = "El rol especificado no existe";
	 }else if(errorVal.equals("2")){
		 errorMsg = "Parámetro incorrecto";
	 }
 }
%>
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
        <%if(request.getParameter("del") != null) {%>
	       <div class="alert alert-success alert-dismissible fade show" role="alert">
			  ¡Se ha removido la opción<strong> correctamente</strong>!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		  <%} %>
        
          <%
          	if(request.getParameter("saved") != null) {
          %>
          <div class="alert alert-success alert-dismissible fade show" role="alert">
			  ¡La opción se ha asignado<strong> correctamente</strong>!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		  <%
		  	}
		  %>
		  
		  <%
		  		  	if(error) {
		  		  %>
          <div class="alert alert-danger alert-dismissible fade show" role="alert">
			  <%=errorMsg%>
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		  <%
		  	}
		  %>

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Roles - Opciones</h1>
          <p class="mb-4">Asocie roles con permisos</p>
          
          <%
		  	if(!withRole){
		  %>

            <h3>Seleccione un registro de la tabla antes de empezar</h3>
            <%} %>
            
            <form role="form" method="POST" class="col-6" action="../SL_asignarOpciones">
              
            <input type="hidden" id="idRol" name="idRol" value="<%=id_rol%>">
            <div class="form-group">
		    <label for="listaOpciones">Opciones: </label>
		    <select name="listaOpciones" required class="form-control" id="listaOpciones">
		      <option value="" selected>Elegir</option>
		     <%
		     	for(Tbl_opcion opc: listaOpciones){
		     %>
		      <option value="<%=opc.getId_opcion()%>"><%=opc.getOpcion()%></option>
		      <%
		      	}
		      %>
		    </select>
		  </div>
		  
		  
            <div class="form-group">
		    <label for="listaUsuarios">Rol: </label>
		    <input type="text" class="form-control" name="userField" id="userField" value="<%=rolInput%>" disabled>
		  </div>
		  <%
		  	if(withRole){
		  %>
		   <div class="form-group">
		   <label for="currentOpc">Opciones actuales: </label>
		   <select name="currentOpc" id="currentOpc" class="form-control">
		      <option value="" selected>Elegir</option>
		      <%
		      	for(VW_rol_opcion r: opcionesRol) {
		      %>
		      <option class="cr-<%=r.getId_opc()%>" value="<%=r.getId_rol_opcion()%>"><%=r.getOpcion()%></option>
		      <%
		      	}
		      %>
		   </select>
		   </div>
		   
		  
		  <%
		   		  		  	}
		   		  		  %>
		  
		    <button id="submitOPC" type="submit" class="btn btn-success">Agregar</button>
		    <button type="button" id="removeOpcBTN" class="btn btn-danger">Retirar</button>
            </form>
            <br>
            
           
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  
                   <thead>
                    <tr>
                      <th>ID</th>
                      <th>Rol</th>
                      <th>Descripción</th>
                      <th>Opcion</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>ID</th>
                      <th>Rol</th>
                      <th>Descripción</th>
                      <th>Opcion</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <%
                    	for(Tbl_rol rol: listaRoles) {
                    %>
                    <tr>
                      <td><%=rol.getId_rol() %></td>
                      <td><%=rol.getRol_name() %></td>
                      <td><%=rol.getRol_desc() %></td>
                      <td>
                       <a role="button" href="./rolesOpciones.jsp?rol=<%=rol.getId_rol()%>" id="selectRolBTN" 
                       class="btn btn-primary">Aceptar</a>
                      </td>
                    </tr>
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
  <script src="../js/userRol.js"></script>
  
  <script>
  
  </script>

</body>
</html>