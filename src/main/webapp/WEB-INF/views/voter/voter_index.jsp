<%@page import="com.Int.evs.bean.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <title>Voter-Dashboard</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
        <script src="js/voter_auth.js"></script>
        <script src="js/voter_requests.js"></script>
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

        <script>showProfileView();</script>

        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <p class="navbar-brand">Voter</p>
            <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
            <!-- Navbar-->
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Services</div>

                            <a class="nav-link" onclick="showProfileView()">Profile</a>
                            <a class="nav-link" onclick="showVoterIdView()">VoterId</a>
                            <a class="nav-link" onclick="showElections()">All Elections</a>
                            <a class="nav-link" onclick="showAllCandidates()">All Candidates</a>
                            <a class="nav-link" onclick="showCastVote()">Cast Vote</a>
                            <a class="nav-link" onclick="showElectionResults()">Election Results</a>
                               

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


        

        <div id="showProfileView" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    Profile
                </div>
                <div class="card-body">
                    <main>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-7">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-body">
                                            

                                            <form id="userProfileForm" action="" method="POST" onsubmit="return false">
                                                
                                                <div class="form-group">
                                                    
                                                    <label class="small mb-1" for="inputDOB">UserId</label>
                                                    <input class="form-control py-4" id="userId" name="userId" type="text" readonly/>
                                                </div>

                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="inputFirstName">First Name</label>
                                                            <input class="form-control py-4" id="firstName" name="firstName" type="text" placeholder="Enter first name" readonly/>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="inputLastName">Last Name</label>
                                                            <input class="form-control py-4" id="lastName" name="lastName" type="text" placeholder="Enter last name" />
                                                        </div>
                                                    </div>
                                                </div>
    
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputDOB">Date of Birth</label>
                                                    <input class="form-control py-4" id="dateOfBirth" name="dateOfBirth" type="date" />
                                                </div>
    
                                                <div class="form-group">
                                                    <label class="small mb-1" for="gender">Gender</label><br>
                                                    <input class="" type="radio" id="Female" name="gender" value="Female"> Female<br>
                                                    <input class="" type="radio" id="Male" name="gender" value="Male"> Male<br>
                                                    <input class="" type="radio" id="Other" name="gender" value="Other"> Other<br>
                                                </div>
    
                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="inputLocation">Location</label>
                                                            <input class="form-control py-4" id="location" name="location" type="text" placeholder="Enter location" />
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="inputCity">City</label>
                                                            <input class="form-control py-4" id="city" name="city" type="text" placeholder="Enter city" />
                                                        </div>
                                                    </div>
                                                </div>
    
                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="inputState">State</label>
                                                            <input class="form-control py-4" id="state" name="state" type="text" placeholder="Enter state" />
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="inputPincode">Pincode</label>
                                                            <input class="form-control py-4" id="pincode" name="pincode" type="text" placeholder="Enter pincode" />
                                                        </div>
                                                    </div>
                                                </div>
    
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputMobile">Mobile No.</label>
                                                    <input class="form-control py-4" id="mobileNo" name="mobileNo" placeholder="Enter mobile number" />
                                                </div>
    
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputEmailAddress">Email</label>
                                                    <input class="form-control py-4" id="emailId" name="emailId" type="email" aria-describedby="emailHelp" placeholder="Enter email address" />
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputPassword">Password</label>
                                                    <input class="form-control py-4" id="password" name="password" type="password" placeholder="Enter password" readonly/>
                                                </div>
                                                
                                                <div class="form-group mt-4 mb-0"><input class="btn btn-primary btn-block" onclick="updateProfile()" value="Update"></div>
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

        <div id="showVoterIdView" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    VoterId
                </div>
                <div class="card-body">
                    <main>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-7">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-body">
                                            <form onsubmit="return false">
                                                
                                                <div class="form-group">
                                                    <label class="small mb-1" for="constituency">Constituency</label>
                                                    <select class="form-control" name="constituency" id="voterIdConstituency" required>
                                                        <option value="Constituency1">Constituency1</option>
                                                        <option value="Constituency2">Constituency2</option>
                                                        <option value="Constituency3">Constituency3</option>
                                                        <option value="Constituency4">Constituency4</option>
                                                        <option value="Constituency5">Constituency5</option>
                                                    </select>
                                                </div>
                                            
    
                                                <div class="form-row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <button class="btn btn-primary btn-block" onclick="requestVoterId()">Request VoterId</button>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <button class="btn btn-primary btn-block" onclick="viewGeneratedVoterId()">View Generated VoterId</button>
                                                        </div>
                                                    </div>
                                                </div>
    
                                                <div class="form-group">
                                                    <label class="small mb-1" for="requestVoterIdStatus">VoterId</label>
                                                    <input class="form-control py-4" id="requestVoterIdStatus" name="requestVoterIdStatus" readonly />
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
                                </tr>
                            </thead>
                            <tbody id="showViewAllUpcomingElectionsTableBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>     

        <div id="showAllCandidates" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    VoterId
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
                                                            <select class="form-control" name="electionId" id="electionNameCandidateView" required>
                                                                
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="small mb-1" for="constituency">Constituency</label>
                                                            <select class="form-control" name="constituency" id="constituencyCandidateView" required>
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
                                                    <button class="btn btn-primary btn-block" onclick="requestCandidates()">Get Candidates</button>
                                                </div>
                                                   
                                                <div id="allCandidatesByelectionAndConstituency">


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
        

        <div id="showViewAllCandidates" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    All candidates
                </div>
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
                                </tr>
                            </thead>
                            <tbody id="showViewAllCandidatesTableBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>     


        <div id="showCastVoteView" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    CastVote
                </div>
                <div class="card-body">
                    <main>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-10">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-body" id="showCastVoteViewContent">
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        </div>

        <div id="showElectionResults" style="display: none;">
            <div class="card mb-4" style="height: 100%;">
                <div class="card-header">
                    Election results
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>SerialNo.</th>
                                    <th>ElectionId</th>
                                    <th>CandidateId</th>
                                    <th>Vote Count</th>
                                </tr>
                            </thead>
                            <tbody id="showElectionResultsTableBody">

                            </tbody>
                        </table>
                        
                        <button class="btn btn-primary" onclick="window.print()">Generate report</button>
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

