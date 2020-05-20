
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList"%>
<%@ page import="entidades.fichas_tecnicas.Tbl_country" %>

<!--
AUTOR:  Leonardo Cardoza
FIN:    29/10/2019

-->

<%
    Tbl_country[] countries = (Tbl_country[]) request.getAttribute("countries");

    if(countries == null) {
        response.sendRedirect(request.getContextPath() + "/toLoginPage");
        return;
    }
%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><%=Server.getAppName() %> - Paises</title>

    <!-- Custom fonts for this template -->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link rel="shortcut icon" href="../img/Logo.png" type="image/x-icon">
    <!-- Custom styles for this template -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../jAlert/dist/jAlert.css" />

</head>
<%






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

                <c:if test="${msg != null}">
                    <div class="alert alert-${type} alert-dismissible fade show" role="alert">
                            ${cont}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>



                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Familias</h1>
                <p class="mb-4">Cree, edite y elimine familias</p>



                <form role="form" method="POST" id="roleForm" class="col-6" action="../saveFamily">
                    <h4 id="opcIndicador" class="text-gray-800">Operación actual: creación</h4>
                    <input type="hidden" id="idRol" name="idRol" value="">
                    <input type="hidden" id="opc" name="opc" value="1">

                    <div class="form-group">
                        <label for="rolName">Nombre de familia: </label>
                        <input type="text" class="form-control" id="familyName"  name="name" required>
                    </div>


                    <div class="form-group">
                        <label for="type">Tipo: </label>
                        <select class="form-control" name="type" id="type" required>
                            <option value="">Seleccione...</option>
                            <option value="planta">Planta</option>
                            <option value="hongo">Hongo</option>
                        </select>
                    </div>

                    <button id="submitRole" type="submit" class="btn btn-success">Guardar</button>
                    <button type="reset" id="cancelRoleBTN" class="btn btn-danger">Cancelar</button>
                </form>
                <br>


                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Opción</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Opción</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <%
                        for(Tbl_country cou: countries) {
                    %>
                    <tr>
                        <td id="cl-id-<%=cou.getId()%>"><%=cou.getId() %></td>
                        <td id="cl-name-<%=cou.getId()%>"><%=cou.getName() %></td>
                        <td>
                            <a href="#" id="<%=cou.getId()%>" class="">

                                <i class="fas fa-edit editRole"></i>
                            </a>
                            &nbsp;&nbsp;
                            <a href="#" id="<%=cou.getId()%>" onclick="deleteCountry(<%=cou.getId()%>)" class="deleteCountry">
                                <i class="fas fa-trash-alt"></i>
                            </a>
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
<script src="../js/roles.js"></script>

<script>

    function deleteCountry(id){
        idCou = id;
        confirm(function()
            {
                window.location.replace('../deleteCountry?id=' + idCou);
            },
            function(e,btn)
            {

            });

    }

</script>

</body>
</html>