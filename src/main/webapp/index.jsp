
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="datos.*, entidades.*, org.json.JSONObject, util.*"%>
    <%
    
    //Limpia la CACHE del navegador
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setDateHeader("Expires", -1);
    
    
    DT_user dtu = new DT_user();
    JSONObject obj = dtu.obtenerUsuarioIngresado(request.getCookies());
    
    if(obj.getInt("code") == 0 || obj.getInt("code") == 401)
    {
    	response.sendRedirect(request.getContextPath() + "/login");
    	return;
    }
    String[] cks = (String[]) obj.get("cookies");
	Util.setTokenCookies(request, response, cks);
    
    %>
   
<!DOCTYPE html>
<html lang="es">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title><%=Server.getAppName() %> - Inicio</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/dist/slick.css"/>
  <link rel="stylesheet" type="text/css" href="css/dist/slick-theme.css"/>


</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

   <!-- SIDEBAR -->
   <jsp:include page="WEB-INF/layouts/sidebar.jsp"></jsp:include>
   <!-- SIDEBAR -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">
      
      <!-- HEADER -->
      <jsp:include page="WEB-INF/layouts/header.jsp"></jsp:include>
      <!-- HEADER -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">Menú principal</h1>

        </div>
        <!-- /.container-fluid -->
        <div class="container-fluid">
        
        <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
            <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
          </div>

          <!-- Content Row -->
          <div class="row">

            
             <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Cantidad de plantas:</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>
                    </div>
                    <div class="col-auto">
                       <!--  <i class="fas fa-calendar fa-2x text-gray-300"></i> -->
                      <img class = "img-icon" src = "img/plant.png">
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
             <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Cantidad de hongos:</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                    </div>
                    <div class="col-auto">
                      <!--  <i class="fas fa-calendar fa-2x text-gray-300"></i> -->
                      <img class = "img-icon" src = "img/mushrooms.png">
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
             <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-secondary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Cantidad de recolectores:</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                    </div>
                    <div class="col-auto">
                      <!--  <i class="fas fa-calendar fa-2x text-gray-300"></i> -->
                      <img class = "img-icon" src = "img/user.png">
                    </div>
                  </div>
                </div>
              </div>
            </div>
            

           



          <!-- Content Row -->

          <div class="row slider-row">

            <!-- Area Chart -->
            <div class="col-xl-8 col-lg-7">
              <div class="card slider-card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Últimas Adquisiciones</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body card-body-slider">
                    <div class="last-plants">
 						 <div><img src = "img/placeholder-plant.jpg"></div>
 						 <div><img src = "img/placeholder-plant.jpg"></div>
  						<div><img src = "img/placeholder-plant.jpg"></div>
					</div>
                </div>
              </div>
            </div>

            <!-- Pie Chart -->
            <div class="col-xl-4 col-lg-5">
              <div class="card slider-card-companion shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Revenue Sources</h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Dropdown Header:</div>
                      <a class="dropdown-item" href="#">Action</a>
                      <a class="dropdown-item" href="#">Another action</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                  </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-pie pt-4 pb-2">
                    <canvas id="myPieChart"></canvas>
                  </div>
                  <div class="mt-4 text-center small">
                    <span class="mr-2">
                      <i class="fas fa-circle text-primary"></i> Direct
                    </span>
                    <span class="mr-2">
                      <i class="fas fa-circle text-success"></i> Social
                    </span>
                    <span class="mr-2">
                      <i class="fas fa-circle text-info"></i> Referral
                    </span>
                  </div>
                </div>
              </div>
            </div>
          </div>

         

            <div class="col-lg-12 mb-4">

              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">=Acerca de:</h6>
                </div>
                <div class="card-body">
                  <div class="text-center">
                    <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;" src="img/woman-plant.jpg" alt="">
                  </div>
                  <p>Add some quality, svg illustrations to your project courtesy of <a target="_blank" rel="nofollow" href="https://undraw.co/">unDraw</a>, a constantly updated collection of beautiful svg images that you can use completely free and without attribution!</p>
                  <a target="_blank" rel="nofollow" href="https://undraw.co/">Browse Illustrations on unDraw &rarr;</a>
                </div>
              </div>

         

      </div>
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

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.jsp">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>
  <script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  <script type="text/javascript" src="js/dist/slick.min.js"></script>
  
  <script>
  $(document).ready(function(){
	  $('.last-plants').slick({
		  dots: true,
		  slidesToShow: 1,
	  });
	});
  </script>
  
  

</body>

</html>
