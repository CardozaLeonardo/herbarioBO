<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, java.util.ArrayList,datos.*"%>
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

  <title>Herbario Nacional - Roles-Usuarios</title>

  <!-- Custom fonts for this template -->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../css/sb-admin-2.css" rel="stylesheet">

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
	 if(vu.getOpcion().equals("./seguridad/rolesUsuarios.jsp")) {
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
 
 DT_Usuario dtus = new DT_Usuario();
 ArrayList<Tbl_usuario> usuarios = dtus.listarUsuarios();
 
 DT_rolOpcion dro = new DT_rolOpcion();
 ArrayList<VW_user_opciones> vus = null;
 
 
 Tbl_usuario usr = null;
 
 boolean withUser = false;
 String nameInput = "";
 String id_user ="";
 String errorMsg = "";
 boolean error = false; // Para indicar cualquier error a notificar
 
 
 if(request.getParameter("user") != null)
 {
	 try{
	     int idUser = Integer.parseInt(request.getParameter("user"));
		 rolesUser = tru.listarRolUsuario(idUser);
		 usr = dtus.obtenerUser(idUser);
		 if(usr == null){
	 response.sendRedirect("rolesUsuarios.jsp?error=1");
	 return;
	 //System.out.println("Adios");
		 }
		 
		 nameInput = usr.getUsername() + " - " + usr.getNombre1() + " " + usr.getApellido1();
		 id_user += usr.getId_user();
		 vus = dro.listarOpcionesUsuario(idUser);
		 withUser = true;
	 
	 }catch(NumberFormatException e){
		 response.sendRedirect("rolesUsuarios.jsp?error=2");
		 return;
	 }
 }
 
 if(request.getParameter("error") !=null){
	 error = true;
	 String errorVal = request.getParameter("error");
	 if(errorVal.equals("1")){
		 errorMsg = "El usuario especificado no existe";
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
			  ¡Se ha removido el rol<strong> correctamente</strong>!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		  <%} %>
        
          <%
          	if(request.getParameter("saved") != null) {
          %>
          <div class="alert alert-success alert-dismissible fade show" role="alert">
			  ¡El rol se ha asignado<strong> correctamente</strong>!
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
          <h1 class="h3 mb-2 text-gray-800">Roles - Usuario</h1>
          <p class="mb-4"> Asigne y quite roles a usuarios</p>
          
          <%
		    	if(!withUser) {
		    %>
            <h3>Seleccione un registro de la tabla antes de empezar</h3>
            <%} %>
            
            <form role="form" method="POST" class="col-6" action="../SL_asignarRol">
              
            <input type="hidden" id="idUser" name="idUser" value="<%=id_user%>">
            <div class="form-group">
		    <label for="listaRoles">Roles: </label>
		    <select name="rolUser" required class="form-control" id="listaRoles">
		      <option value="" selected>Elegir</option>
		     <%
		     	for(Tbl_rol rol: listaRoles){
		     %>
		      <option value="<%=rol.getId_rol()%>"><%=rol.getRol_name()%></option>
		      <%
		      	}
		      %>
		    </select>
		  </div>
		  
		  
            <div class="form-group">
		    <label for="listaUsuarios">Usuario: </label>
		    <input type="text" class="form-control" name="userField" id="userField" value="<%=nameInput%>" disabled>
		  </div>
		  <%
		  	if(withUser){
		  %>
		   <div class="form-group">
		   <label for="currentRoles">Roles actuales: </label>
		   <select name="currentRoles" id="currentRoles" class="form-control">
		      <option value="" selected>Elegir</option>
		      <%
		      	for(VW_user_rol r: rolesUser) {
		      %>
		      <option class="cr-<%=r.getId_rol()%>" value="<%=r.getId_user_rol()%>"><%=r.getRol_name()%></option>
		      <%
		      	}
		      %>
		   </select>
		   </div>
		   
		  
		  <%
		   		  		  	}
		   		  		  %>
		  
		    <button id="submitRole" type="submit" class="btn btn-success">Agregar</button>
		    &nbsp;&nbsp;
		    <button type="button" id="removeRoleBTN" class="btn btn-danger">Retirar</button>
		    <%
		    	if(withUser) {
		    %>
		      <span></span>
		      &nbsp;
		      <button type="button" data-toggle="modal" data-target="#modalOpciones" class="btn btn-info btn-icon-split">
		      <span class="icon text-white-50">
                <i class="fas fa-user-shield"></i>
              </span>
		      <span class="text">Mostrar permisos</span></button>
		      
		      <div class="modal fade" id="modalOpciones" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title text-gray-900" id="exampleModalCenterTitle">Opciones:</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <ol>
				         <%
				         	for(VW_user_opciones up: vus) {
				         %>
				         <li><%=up.getOpcion()%></li>
				         <%
				         	}
				         %>
				        </ol>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
				        
				      </div>
				    </div>
				  </div>
			    </div>
            <%
            	}
            %>
            </form>
            <br>
            
           
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  
                   <thead>
                    <tr>
                      <th>ID</th>
                      <th>Nombres</th>
                      <th>Apellido</th>
                      <th>Username</th>
                      <th>Email</th>
                      <th>Opcion</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>ID</th>
                      <th>Nombres</th>
                      <th>Apellido</th>
                      <th>Username</th>
                      <th>Email</th>
                      <th>Opcion</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <%
                    	for(Tbl_usuario user: usuarios) {
                    %>
                    <tr>
                      <td><%=user.getId_user() %></td>
                      <td><%=user.getNombre1() %></td>
                      <td><%=user.getApellido1() %></td>
                      <td><%=user.getUsername() %></td>
                      <td><%=user.getEmail() %></td>
                      <td>
                       <a role="button" href="./rolesUsuarios.jsp?user=<%=user.getId_user()%>" id="selectUserBTN" 
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