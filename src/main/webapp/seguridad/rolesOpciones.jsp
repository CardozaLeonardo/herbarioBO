<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList, util.*"%>
    <%@page import="org.json.JSONObject, java.util.Arrays"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
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

  <title><%=Server.getAppName() %> - Roles-Opciones</title>

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
	
		
DT_rol dtr = new DT_rol();
JSONObject objRoles = dtr.getRoles(request.getCookies());

if(objRoles.getInt("status") == 401 || objRoles.getInt("status") == 0){
	 response.sendRedirect("../login");
	 return;
}

Tbl_rol[] listaRoles = (Tbl_rol[]) objRoles.get("roles");

DT_permissions dtp = new DT_permissions();

JSONObject opc = dtp.getPermissions(request.getCookies());

if(opc.getInt("status") == 401 || opc.getInt("status") == 0){
	 response.sendRedirect("../login");
	 return;
}

Tbl_opcion[] permisos = (Tbl_opcion[]) opc.get("permissions");


 



 String str = "";
 
 

 
 
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
		 JSONObject rol = dtr.obtenerRol(idRol, request.getCookies());
		 
	     if(rol.getInt("status") == 200){
	    	 role = (Tbl_rol) rol.get("rol");
	    	 String[] cookies =  Util.extractTokens(request.getCookies());
	    	 
	    	 //JSONObject json = new JSONObject(role.getPermissions());
	    	 //System.out.println(Arrays.toString(role.getPermissions()));
	    	 str = Arrays.toString(role.getPermissions());
	    	 //Util.setTokenCookies(request, response, cookies);
	    	 
	     }
	     
		 if(role == null){
	 response.sendRedirect("rolesOpciones?error=1");
	 return;
	 //System.out.println("Adios");
		 }
		 
		 rolInput = role.getName();
		 id_rol += role.getId();
		 withRole = true;
	 
	 }catch(NumberFormatException e){
		 response.sendRedirect("rolesOpciones?error=2");
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
            <input type="hidden" id="permisos" name="permisos" value="<%=str%>">
            <div class="form-group">
		    <label for="listaOpciones">Opciones: </label>
		    <select name="listaOpciones" required class="form-control" id="listaOpciones">
		      <option value="" selected>Elegir</option>
		     <%
		     	for(Tbl_opcion op: permisos){
		     %>
		      <option value="<%=op.getId()%>"><%=op.getName()%></option>
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
		      	for(int r: role.getPermissions()){
		          for(Tbl_opcion op : permisos){
		        	if(r == op.getId()){
		      %>
		         <option class="cr-" value="<%=op.getId()%>"><%=op.getName()%></option>
		      <%
		        	}
		        	}
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
                      <th>Opcion</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>ID</th>
                      <th>Rol</th>
                      <th>Opcion</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <%
                    	for(Tbl_rol rol: listaRoles) {
                    %>
                    <tr>
                      <td><%=rol.getId() %></td>
                      <td><%=rol.getName() %></td>
                      <td>
                       <a role="button" href="./rolesOpciones?rol=<%=rol.getId()%>" id="selectRolBTN" 
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