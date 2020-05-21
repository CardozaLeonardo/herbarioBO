
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="entidades.*, datos.*, java.util.ArrayList"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%



%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title><%=Server.getAppName() %> Editar País</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="../img/Logo.png" type="image/x-icon">
    <!-- Custom fonts for this template -->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">

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
    HttpSession hts = request.getSession();
    boolean editPermission = false;

    Tbl_opcion[] permisions = (Tbl_opcion[]) hts.getAttribute("user_permissions");

    for(Tbl_opcion op : permisions) {
        if(op.getCodename().equals("change_country")) {
            editPermission = true;
        }
    }

    if(!editPermission) {
        response.sendRedirect(request.getContextPath() + "/accesoDenegado.jsp");
        return;
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
                        <h1>Registro</h1>
                    </div>
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="./country">Países</a></li>
                            <li class="breadcrumb-item active">Nuevo País</li>
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
                                <h3 class="card-title">Editar País</h3>
                            </div>

                            <form role="form" action="../changeCountry" method="POST" enctype="multipart/form-data">
                                <input type="hidden" name="countryID" value="${country.id}">
                                <div class="card-body">
                                    <h5>Datos de País</h5>

                                    <div class="form-group">
                                        <label for="name">Nombre</label>
                                        <input type="text" class="form-control" id="name" placeholder="Nombre"
                                               name="name" value="${country.name}" required>
                                    </div>


                                    <div class="card-footer">
                                        <button type="submit" class="btn btn-primary">Guardar</button>

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
<!-- Bootstrap core JavaScript-->
<script src="../vendor/jquery/jquery.min.js"></script>
<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- jQuery -->
<!--<script src="../vendor/jquery/jquery.min.js"></script> -->
<!-- <script src="../../plugins/jquery/jquery.min.js"></script> -->
<!-- Custom scripts for all pages-->
<script src="../js/sb-admin-2.min.js"></script>

<!-- jAlert js -->
<script src="../vendor/jAlert/dist/jAlert.min.js"></script>
<script src="../vendor/jAlert/dist/jAlert-functions.min.js"> </script>
<script src="../js/showImage.js"></script>

</body>
</html>
