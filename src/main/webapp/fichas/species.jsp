
<%@page import="entidades.fichas_tecnicas.Tbl_species"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 

  <title><%=Server.getAppName() %>- Especies</title> 

  <!-- Custom fonts for this template -->
  <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../css/sb-admin-2.css" rel="stylesheet">
  
  <!-- DATATABLE NEW -->
  <link href="../vendor/DataTablesNew/DataTables-1.10.18/css/jquery.dataTables.min.css" rel="stylesheet">
  
  <!-- DATATABLE NEW buttons -->
    <link href="../vendor/DataTablesNew/Buttons-1.5.6/css/buttons.dataTables.min.css" rel="stylesheet">
  
  <!-- Custom styles for this page -->
  <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  
  <!-- jAlert css  -->
  <link rel="stylesheet" href="../vendor/jAlert/dist/jAlert.css" /> 

<%

if(request.getAttribute("pass") == null){
	response.sendRedirect("./species");
	return;
}

Tbl_species[] species =(Tbl_species[]) request.getAttribute("species");

///////////

/* RECUPERAMOS EL VALOR DE LA VARIABLE MSJ */
String mensaje = "";
mensaje = request.getParameter("msj");
mensaje = mensaje==null?"":mensaje;
%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
</head>
<body id="page-top">
   <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <jsp:include page="../WEB-INF/layouts/sidebar.jsp"></jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Topbar -->
        <jsp:include page="../WEB-INF/layouts/header.jsp"></jsp:include>
        <!-- End of Topbar -->

			<section class="content-header">
	      <div class="container-fluid">
	      <c:if test="${msg != null}">
		    <div class="alert alert-${type} alert-dismissible fade show" role="alert">
			  ${cont}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
		 </c:if>
	      
	        <div class="row mb-2">
	          <div class="col-sm-6">
	            <h1>Especies</h1>
	          </div>
	          <div class="col-sm-6">
	            <ol class="breadcrumb float-sm-right">
	              <li class="breadcrumb-item"><a href="#">Catálogo</a></li>
	              <li class="breadcrumb-item active">Especies</li>
	            </ol>
	          </div>
	        </div>
	      </div><!-- /.container-fluid -->
	    </section>
			
	<!-- Main Content -->
      <div id="content">
        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- Page Heading -->
          <!-- <h1 class="h3 mb-2 text-gray-800">Roles</h1>
          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p> -->

            <h2>Especies</h2>
                <a href="<%=request.getContextPath()%>/fichas/newSpecie" class="btn btn-primary btn-icon-split">
                    <span class="icon text-white-50">
                      <i class="fas fa-plus"></i>
                    </span>
                    <span class="text">Agregar Especie</span>
                  </a>
                  <br>
                  <br>
            <table id="example2" class="display">
                  
                   <thead>
                    <tr>
                      <th>ID</th>
                      <th>Nombre Común</th>
                      <th>Nombre Cientifico</th>
                      <th>Familia</th>
                      <th>Género</th>
                      <th>Opciones</th>
                    </tr>
                  </thead>
                  <tbody>
                    
	                <%if(species != null) {%>
	                
	                <%for(Tbl_species specie : species){ %>
	                <tr>
	                   
	                  <td><%=specie.getId() %></td>
	                  <td><%=specie.getCommon_name()%></td>
	                  <td><%=specie.getScientific_name()%></td>
	                  <td><%=specie.getGenus().getFamily().getName()%></td>
	                  <td><%=specie.getGenus().getName()%></td>
	                  <td>
	                  	<a href="./updateSpecie?id=<%=specie.getId() %>" onclick="linkEditUser('<%=specie.getId() %>');"><i class="far fa-edit" title="Editar"></i></a>&nbsp;&nbsp;
	                  	  
	                  	<a href="./viewSpecie?id=<%=specie.getId()%>" onclick="linkEditUser('<%=specie.getId()%>');"><i class="far fa-eye" title="View"></i></a>&nbsp;&nbsp;
	                  	     
	                  	<a href="#" class="deleteSpecie" id="<%=specie.getId()%>"><i class="far fa-trash-alt" title="Eliminar"></i> </a>
	                  	  
	                  	      <!--  <a href="" onclick="deleteUser('${usr.id}');"><i class="far fa-trash-alt" title="Eliminar"></i> </a> -->
	                  	    
	                  </td>
	                </tr>
	                <%} %>
	                 
	                <%} %>
	                     
                  </tbody>
                   <tfoot>
                    <tr>
                      <th>ID</th>
                      <th>Nombre Común</th>
                      <th>Nombre Cientifico</th>
                      <th>Familia</th>
                      <th>Género</th>
                      <th>Opciones</th>
                    </tr>
                  </tfoot>
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
  
  
  <!-- jQuery -->
  <script src="../vendor/jquery/jquery.min.js"></script>
  
  <!-- Bootstrap core JavaScript-->
  <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- DATATABLE NEW -->
  <script src="../vendor/DataTablesNew/DataTables-1.10.18/js/jquery.dataTables.js"></script>
  
  <!-- DATATABLE NEW buttons -->
  <script src="../vendor/DataTablesNew/Buttons-1.5.6/js/dataTables.buttons.min.js"></script>

<!-- js DATATABLE NEW buttons print -->
  <script src="../vendor/DataTablesNew/Buttons-1.5.6/js/buttons.html5.min.js"></script>
  <script src="../vendor/DataTablesNew/Buttons-1.5.6/js/buttons.print.min.js"></script>

   <!-- js DATATABLE NEW buttons pdf -->
  <script src="../vendor/DataTablesNew/pdfmake-0.1.36/pdfmake.min.js"></script>
  <script src="../vendor/DataTablesNew/pdfmake-0.1.36/vfs_fonts.js"></script>

  <!-- js DATATABLE NEW buttons excel -->
  <script src="../vendor/DataTablesNew/JSZip-2.5.0/jszip.min.js"></script>

  

  <!-- Core plugin JavaScript-->
  <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="../js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="../vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="../js/demo/datatables-demo.js"></script>
  
  <!-- jAlert js -->
  
  
  <script src="../vendor/DataTablesNew/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
        <!-- DATATABLE -->
  <script src="../vendor/DataTablesNew/DataTables-1.10.18/js/jquery.dataTables.js"></script>

  <!-- DATATABLE buttons -->
  <script src="../vendor/DataTablesNew/Buttons-1.5.6/js/dataTables.buttons.min.js"></script>
  
  
  
  <script src="../vendor/DataTablesNew/Buttons-1.5.6/js/buttons.html5.min.js"></script>
  <script src="../vendor/DataTablesNew/Buttons-1.5.6/js/buttons.print.min.js"></script>

   <!-- js Datatable buttons pdf -->
  <script src="../vendor/DataTablesNew/pdfmake-0.1.36/pdfmake.min.js"></script>
  <script src="../vendor/DataTablesNew/pdfmake-0.1.36/vfs_fonts.js"></script>

  <!-- js Datatable buttons excel -->
  <script src="../vendor/DataTablesNew/JSZip-2.5.0/jszip.min.js"></script>
  
  <%if(request.getParameter("del") != null) { %>
    <script >
     successAlert('Eliminado', 'El registro ha sido eliminado!!!');
    </script>
   <%} %>
   
   <%if(request.getParameter("fail") != null) { %>
    <script >
     errorAlert('Error', 'Ha ocurrido un error al intentar eliminar');
    </script>
   <%} %>
  
  
  <script>
function deleteFungus(idHongo)
{
	var id= idHongo;
	confirm(function(e,btn)
     { 	//event + button clicked
     	e.preventDefault();
     	window.location.href="../deleteFungus?id="+id;
       	//successAlert('Confirmed!');
     }, 
     function(e,btn)
     {
       e.preventDefault();
       //errorAlert('Denied!');
     });
	
}
</script>
  
  <script>
  $(function () {
    $("#example1").DataTable();
//     $('#example2').DataTable({
//       "paging": true,
//       "lengthChange": false,
//       "searching": true,
//       "ordering": false,
//       "info": true,
//       "autoWidth": false,
      
//     });
    $('#example2').DataTable({
        dom: 'Bfrtip',
        buttons: [
        'pdf',
        'excel',
        'print'
        ]

      });
  });
  
</script>
<script>
  $(document).ready(function ()
  {
   
    /////////// VARIABLES DE CONTROL MSJ ///////////
    var msj = 0;
    msj = "<%=mensaje%>";

    if(msj == "1")
    {
      successAlert('ï¿½xito', 'El registro ha sido editado!!!');
    }
    if(msj == "3")
    {
      successAlert('ï¿½xito', 'El registro ha sido eliminado!!!');
    }
    if(msj == "2" || msj == "4")
    {
      errorAlert('Error', 'Revise los datos e intente nuevamente!!!');
    }
    if(msj == "ERROR")
    {
      errorAlert('Error', 'CONSULTE CON EL ADMINISTRADOR DEL SISTEMA!!!');
    }
   
  
    

  });
  </script>
  <script src="../jAlert/dist/jAlert.min.js"></script>
  <script src="../jAlert/dist/jAlert-functions.min.js"> </script>
  <script src="../js/fungus.js"></script>

</body>
</html>