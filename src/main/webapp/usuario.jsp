<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Tbl_usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.DT_Usuario"%>

<!DOCTYPE html>
<html>
<head>
 <meta charset="ISO-8859-1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Tables</title>

  <!-- Custom fonts for this template -->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="WEB-INF/layouts/sidebar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <jsp:include page="WEB-INF/layouts/header.jsp"></jsp:include>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Roles</h1>
          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">Lista de Roles</h6>
              <span id="agregar" data-toggle="modal" data-target="#agregarRol1">
              	<i class="fas fa-plus-square"></i>
              </span>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Primer Nombre</th>
                      <th>Primer Apellido</th>
                      <th>Usuario</th>
                      <th>Acciones</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Primer Nombre</th>
                      <th>Primer Apellido</th>
                      <th>Usuario</th>
                      <th>Acciones</th>
                      
                    </tr>
                  </tfoot>
                  <tbody>
                    <%
                    	DT_Usuario dtu = new DT_Usuario();
                                                            ArrayList<Tbl_usuario> listaUsuarios = new ArrayList<Tbl_usuario>();
                                                            
                                                            listaUsuarios = dtu.listarUsuarios();
                                                            for(Tbl_usuario u: listaUsuarios)
                                                            {
                    %>
                    <tr>
                    	<td><%=u.getNombre1() %></td>
                    	<td><%=u.getApellido2() %></td>
                    	<td><%=u.getUsername() %></td>
                    	<td>
                    		<span>
                    			<a onclick="eliminarRol();">
                    				<i class="fas fa-trash"></i>
                    			</a>
                    		</span>
                    		<span id="modificar" data-toggle="modal" data-target="#modificarRol">
                    			<i class="fas fa-edit"></i>
                    		</span>
                    	</td>
                    </tr>
                    <%
                    } 
                    %>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <jsp:include page="WEB-INF/layouts/footer.jsp"></jsp:include>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  
  <!-- MODAL: Agregar Rol -->
  <form action="#" method="post">
  	<div class="modal fade" id="agregarRol" tabindex="-1" role="dialog" 
  		aria-labelledby="exampleModalLabel" aria-hidden="true">
  		<div class="modal-dialog" role="document">
  			<div class="modal-header">
  				<h5 class="modal-title">Agregar Rol</h5>
  				<button type="button" class="close" data-dismiss="modal"
  				aria-label="close">
  					<span aria-hidden="true">&times;</span>
  				</button>
  			</div>
  			<div class="modal-body">
  				<span>ID: </span> <input type="text" class="form-control" id="idrol"
  				name="idrol" />
  				<span>Descripcion: </span> <input type="text" class="form-control" id="dscrol"
  				name="dscrol" />
  			</div>
  			<div class="modal-footer">
  				<button type="reset" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
  				<button type="submit" class="btn btn-primary" data-dismiss="modal">Guardar</button>
  			</div>
  		</div>
  	</div>
  </form>

  <!-- Logout Modal-->
<!--   <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!--     <div class="modal-dialog" role="document"> -->
<!--       <div class="modal-content"> -->
<!--         <div class="modal-header"> -->
<!--           <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5> -->
<!--           <button class="close" type="button" data-dismiss="modal" aria-label="Close"> -->
<!--             <span aria-hidden="true">×</span> -->
<!--           </button> -->
<!--         </div> -->
<!--         <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div> -->
<!--         <div class="modal-footer"> -->
<!--           <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button> -->
<!--           <a class="btn btn-primary" href="login.html">Logout</a> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->

 <form action="./SLguardarRol" method="post">
  <div class="modal fade" id="agregarRol1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Agregar Rol</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
<!--         	<span>ID: </span> <input type="text" class="form-control" id="idrol" -->
<!--   				name="idrol" /> -->
  			<span>Descripcion: </span> <input type="text" class="form-control" id="dscrol"
  			name="dscrol" />
        </div>
        <div class="modal-footer">
         	<button type="reset" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
  			<button type="submit" class="btn btn-primary">Guardar</button>
        </div>
      </div>
    </div>
  </div>
  </form>
  
  <!-- MODAL: Modificar Rol -->
 <form action="./SLmodificarRol" method="post">
  <div class="modal fade" id="modificarRol" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modificar Rol</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">
        	<span>ID: </span> <input type="text" class="form-control" id="midrol"
  				name="midrol" />
  			<span>Descripcion: </span> <input type="text" class="form-control" id="mdscrol"
  			name="mdscrol" />
        </div>
        <div class="modal-footer">
         	<button type="reset" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
  			<button type="submit" class="btn btn-primary">Guardar</button>
        </div>
      </div>
    </div>
  </div>
  </form>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/datatables-demo.js"></script>
  
  <script type="text/javascript">
  	function eliminarRol(id)
  	{
  		window.open("SLeliminarRol?id="+id);
  	}
  </script>

</body>

</html>
