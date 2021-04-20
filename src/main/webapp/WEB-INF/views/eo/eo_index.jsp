<%@page import="com.Int.evs.bean.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <title>EO-Dashboard</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
        <script src="js/eo_auth.js"></script>
        <script src="js/eo_requests.js"></script>
    </head>
    <body class="sb-nav-fixed">
                
        <div>
            <%
            CredentialsBean cb = (CredentialsBean)request.getAttribute("currentUser");
            if (cb.getUserId()!=null)
            {
                out.println("<script>localStorage.setItem('userId','"+cb.getUserId()+"');</script>");
                out.println("<script>localStorage.setItem('password','"+cb.getPassword()+"');</script>");
                
            }
            %>
            <script>console.log(auth());</script>

        </div>
        <script>showViewAllEOPendingApplications();</script>

        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <p class="navbar-brand">Electoral Officer</p>
            <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
            <!-- Navbar-->
             
            
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Services</div>

                            <a class="nav-link" onclick="">View Pending requests</a>


                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts" style="color:rgba(255, 255, 255, 1);" onMouseOver="this.style.color='rgba(255, 255, 255, 0.5)'" onMouseOut="this.style.color='rgba(255, 255, 255, 1)'">
                            <div id="loggedInAs" class="large"></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" onclick="changePasswordView()" style="color:rgba(255, 255, 255, 0.5);" onMouseOver="this.style.color='rgba(255, 255, 255, 1)'" onMouseOut="this.style.color='rgba(255, 255, 255, 0.5)'">Change password</a>
                                <a class="nav-link" onclick="logout()" style="color:rgba(255, 255, 255, 0.5);" onMouseOver="this.style.color='rgba(255, 255, 255, 1)'" onMouseOut="this.style.color='rgba(255, 255, 255, 0.5)'">Logout</a>
                            </nav>
                        </div>
                        
                        <script>document.getElementById("loggedInAs").innerHTML="Logged in as : "+localStorage.getItem("userId");</script>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main  style="height: 100%;">             
                        <div id=placeHolder  style="height: 100%;">

                          

                        </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; EVS 2021</div>
                            <div id="auth-footer">
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>

        <div id="showViewAllEOPendingApplications" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    All EO pending elections
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <form onsubmit="return false">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>UserId</th>
                                        <th>Constituency</th>
                                        <th>Accept</th>
                                        <th>Reject</th>
                                    </tr>
                                </thead>
                                <tbody id="showViewAllEOPendingApplicationsTableBody">
    
                                </tbody>
                            </table>
                        </form>
                        <button class="btn btn-primary" onclick="approve_reject()">Approve/Reject</button>
                    </div>
                </div>
            </div>

        </div>
        <div id="showChangePasswordView" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    Change Password
                </div>
                <div class="card-body">
                    <main>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-5">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-body">
                                            <form id="changePasswordForm" method="POST" onsubmit="return false">
                                                
                                                <div class="form-group">
                                                    <label class="small mb-1" for="name">UserId</label>
                                                    <input class="form-control py-4" type="text" name="userId" id="changePasswordUserId" readonly/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="small mb-1" for="name">Old Password</label>
                                                    <input class="form-control py-4" type="text" name="password" id="changePasswordPassword" readonly/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="small mb-1" for="name">New Password</label>
                                                    <input class="form-control py-4" type="text" id="newPassword" name="newPassword"/>
                                                </div>
                                                
                                                <div>
                                                    <button class="btn btn-primary" onclick="changePassword()">changePassword</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>



