<%@page import="com.Int.evs.bean.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <title>Admin-Dashboard</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
        <script src="js/admin_auth.js"></script>
        <script src="js/admin_requests.js"></script>
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

        <script>showViewAllAdminPendingApplications();</script>

        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <p class="navbar-brand">Admin</p>
            <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
            <!-- Navbar-->
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Services</div>

                            <a class="nav-link" onclick="showAddElection()">Add Election</a>
                            <a class="nav-link" onclick="showViewAllUpcomingElections()">Upcoming Elections</a>
                            <a class="nav-link" onclick="showViewElections()" id="allElections">All Elections</a>
                            <a class="nav-link" onclick="showAddParty()">Add Party</a>
                            <a class="nav-link" onclick="showViewAllParty()" id="allParties">All Parties</a>
                            <a class="nav-link" onclick="showAddCandidate()">Add Candidate</a>
                            <a class="nav-link" onclick="showViewAllCandidates()" id="allCandidates">All Candidates</a>
                            <a class="nav-link" onclick="showViewAllAdminPendingApplications()">VoterId Pending</a>
                            <a class="nav-link" onclick="showApproveElectionResults()">Approve election result</a>


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
                            

                                <!-- Dynamic content goes here -->

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



        <div id=showAddElection style="display: none;">                
                <div class="card mb-4" style="height: 100%;">
                    <div class="card-header">
                        Add election
                    </div>
                    <div class="card-body">
                        <main>
                            <div class="container">
                                <div class="row justify-content-center">
                                    <div class="col-lg-5">
                                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                                            <div class="card-body">
                                                <form id="addElectionForm" method="POST" onsubmit="return false">
                                                    <input type="hidden" name="electionId" value="">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="name">Election name</label>
                                                        <input class="form-control py-4" type="text" name="name" placeholder="Enter election name" />

                                                    </div>
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="electionDate">Election Date</label>
                                                        <input class="form-control py-4" type="date" name="electionDate" placeholder="electionDate"/>
                                                    </div>

                                                    <div class="form-row">
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label class="small mb-1" for="inputState">District</label>
                                                                <input class="form-control py-4" type="text" name="district" placeholder="Enter district">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label class="small mb-1" for="inputPincode">Constituency</label>
                                                                <select class="form-control" name="constituency" id="voterIdConstituency" required>
                                                                    <option value="Constituency1">Constituency1</option>
                                                                    <option value="Constituency2">Constituency2</option>
                                                                    <option value="Constituency3">Constituency3</option>
                                                                    <option value="Constituency4">Constituency4</option>
                                                                    <option value="Constituency5">Constituency5</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="small mb-1" for="electionDate">Counting Date</label>
                                                        <input class="form-control py-4" type="date" name="countingDate" placeholder="countingDate"/>
                                                    </div>
                                                    <div>
                                                        <button class="btn btn-primary" onclick="addElection()">Add election</button>
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


        <div id="showViewAllUpcomingElections" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    All upcoming elections
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>ElectionId</th>
                                    <th>Name</th>
                                    <th>Election Date</th>
                                    <th>District</th>
                                    <th>Constituency</th>
                                    <th>Counting Date</th>
                                    <th>Services</th>
                                </tr>
                            </thead>
                            <tbody id="showViewAllUpcomingElectionsTableBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>        


        <div id="showViewElections" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    All elections
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>ElectionId</th>
                                    <th>Name</th>
                                    <th>Election Date</th>
                                    <th>District</th>
                                    <th>Constituency</th>
                                    <th>Counting Date</th>
                                    <th>Services</th>
                                </tr>
                            </thead>
                            <tbody id="showViewElectionsTableBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>     


        <div id=showAddParty style="display: none;">                
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    Add party
                </div>
                <div class="card-body">
                    <main>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-5">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-body">
                                            <form method="POST" id="addPartyForm" onsubmit="return false">
                                                <input type="hidden" name="partyId" value="">
                                                <div class="form-group">
                                                    <label class="small mb-1" for="name">Party Name</label>
                                                    <input class="form-control py-4" type="text" name="name" placeholder="Enter party name" />
                                                </div>
                                                <div class="form-group">
                                                    <label class="small mb-1" for="leader">Leader Name</label>
                                                    <input class="form-control py-4" type="text" name="leader" placeholder="Enter leader name"/>
                                                </div>

                                                <div class="form-group">
                                                    <label class="small mb-1" for="symbol">Symbol</label>
                                                    <input class="form-control py-4" type="file" name="symbol" placeholder="Enter image url">
                                                </div>
                                                
                                                <div>
                                                    <button class="btn btn-primary" onclick="addParty()">Add Party</button>
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

        <div id="showViewAllParty" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    All parties
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>PartyId</th>
                                    <th>Name</th>
                                    <th>Leader</th>
                                    <th>Symbol</th>
                                    <th>Services</th>
                                </tr>
                            </thead>
                            <tbody id="showViewAllPartyTableBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>     


            
        <div id=showAddCandidate style="display: none;">                
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    Add candidate
                </div>
                <div class="card-body">
                    <main>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-5">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-body">
                                            <form id="addCandidateForm" method="POST" onsubmit="return false">
                                                <input type="hidden" name="candidateId" value="">
                                                <div class="form-group">
                                                    <label class="small mb-1" for="name">Candidate Name</label>
                                                    <input class="form-control py-4" type="text" name="name" placeholder="Enter candidate name" />
                                                </div>
                                                <div class="form-group">
                                                    <label class="small mb-1" for="electionOptions">Election</label>
                                                    <select class="form-control" id="electionOptions" name="electionId" required>
                                                        
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="small mb-1" for="partyOptions">Party</label>
                                                    <select class="form-control" id="partyOptions" name="partyId" required>
                                                        
                                                    </select>
                                                </div>

                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="inputState">District</label>
                                                            <input class="form-control py-4" type="text" name="district" placeholder="Enter district">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="inputPincode">Constituency</label>
                                                            <select class="form-control" name="constituency" id="voterIdConstituency" required>
                                                                <option value="Constituency1">Constituency1</option>
                                                                <option value="Constituency2">Constituency2</option>
                                                                <option value="Constituency3">Constituency3</option>
                                                                <option value="Constituency4">Constituency4</option>
                                                                <option value="Constituency5">Constituency5</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputDOB">Date of Birth</label>
                                                    <input class="form-control py-4" id="dateOfBirth" name="dateOfBirth" type="date" />
                                                </div>

                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputMobile">Mobile No.</label>
                                                    <input class="form-control py-4" id="mobileNo" name="mobileNo" placeholder="Enter mobile number" />
                                                </div>

                                                <div class="form-group">
                                                    <label class="small mb-1" for="address">Address</label>
                                                    <input class="form-control py-4" type="text" name="address" placeholder="Enter address"/>
                                                </div>

                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputEmailAddress">Email</label>
                                                    <input class="form-control py-4" id="emailId" name="emailId" type="email" aria-describedby="emailHelp" placeholder="Enter email address" />
                                                </div>
                                                                                        
                                                <div>
                                                    <button class="btn btn-primary" onclick="addCandidate()">Add Candidate</button>
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


        <div id="showAllCandidates" style="display: none;">
            <div class="card mb-4" style="height: 50%;">
                <div class="card-header">
                    Candidates
                </div>
                <div class="card-body">
                    <main>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-10">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-body">
                                            <form onsubmit="return false">
                                                
                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="electionId">Election</label>
                                                            <select class="form-control" name="electionId" id="electionIdCandidateView" required>
                                                                
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="partyId">Party</label>
                                                            <select class="form-control" name="partyId" id="partyIdCandidateView" required>
                                                                
                                                            </select>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                                
                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <div class="form-group">
                                                                <button class="btn btn-primary btn-block" onclick="showViewAllCandidatesByElection()">By election</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <div class="form-group">
                                                                <button class="btn btn-primary btn-block" onclick="showViewAllCandidatesByParty()">By party</button>
                                                            </div>
                                                        </div>
                                                    </div>
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

            <div id="showViewAllCandidates" style="">
                <div class="card mb-4" style="height: 100%;">
                    
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>candidateId</th>
                                        <th>electionId</th>
                                        <th>partyId</th>
                                        <th>name</th>
                                        <th>district</th>
                                        <th>constituency</th>
                                        <th>dateOfBirth</th>
                                        <th>mobileNo</th>
                                        <th>address</th>
                                        <th>emailId</th>
                                        <th>Services</th>
                                    </tr>
                                </thead>
                                <tbody id="showViewAllCandidatesTableBody">
    
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>

             


        <div id="showViewAllAdminPendingApplications" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    All parties
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <form onsubmit="return false">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>UserId</th>
                                        <th>Constituency</th>
                                        <th>Forward request</th>
                                    </tr>
                                </thead>
                                <tbody id="showViewAllAdminPendingApplicationsTableBody">

                                </tbody>
                            </table>
                            <button class="btn btn-primary"  onclick="forwardVoterIdRequests()">Forward selected requests</button>
                        </form>
                    </div>
                </div>
            </div>
  
        </div>

        <div id="showApproveElectionResults" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    All completed elections
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <form onsubmit="return false">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>ElectionId</th>
                                    <th>Name</th>
                                    <th>Election Date</th>
                                    <th>District</th>
                                    <th>Constituency</th>
                                    <th>Counting Date</th>
                                    <th>Approve</th>
                                </tr>
                            </thead>
                            <tbody id="showApproveElectionResultsTableBody">

                            </tbody>
                        </table>
                        <button class="btn btn-primary" onclick="approveElections()">Approve Elections</button>
                        </form>
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
