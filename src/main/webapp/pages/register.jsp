<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Register</title>
        <link href="http://localhost:8076/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-dark">
        <div class="card-header bg-light">
            <center><h2 class="text-center font-weight-light my-4"">Electronic Voting System</h2></center>
        </div>
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h4 class="text-center font-weight-light">Create Account</h4></div>
                                    <div class="card-body">
                                        <form action="/register" method="POST">
                                            <div class="form-row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputFirstName">First Name</label>
                                                        <input class="form-control py-4" id="firstName" name="firstName" type="text" placeholder="Enter first name" />
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
                                                <input class="form-control py-4" id="password" name="password" type="password" placeholder="Enter password" />
                                            </div>
                                            
                                            <div class="form-group mt-4 mb-0"><input class="btn btn-primary btn-block" type="submit" value="Create Account"></div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center">
                                        <div class="small"><a href="/pages/login.jsp">Have an account? Go to login</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2020</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="http://localhost:8076/js/scripts.js"></script>
<!-- 
        <form action="/register" method="POST">
            <input type="text" id="firstName" name="firstName" placeholder="firstName"><br>
            <input type="text" id="lastName" name="lastName" placeholder="lastName"><br>
            <input type="date" id="dateOfBirth" name="dateOfBirth" placeholder="dateOfBirth"><br>
            <input type="radio" id="Male" name="gender" value="Male">Male<br>
            <input type="radio" id="Female" name="gender" value="Female">Female<br>
            <input type="radio" id="Other" name="gender" value="Other">Other<br>
            <input type="text" id="location" name="location" placeholder="location"><br>
            <input type="text" id="city" name="city" placeholder="city"><br>
            <input type="text" id="state" name="state" placeholder="state"><br>
            <input type="text" id="pincode" name="pincode" placeholder="pincode"><br>
            <input type="text" id="mobileNo" name="mobileNo" placeholder="mobileNo"><br>
            <input type="text" id="emailId" name="emailId" placeholder="emailId"><br>
            <input type="password" id="password" name="password" placeholder="password"><br>
            <input type="submit" value="Register">
        </form>  -->

    </body>
</html>

<!-- <html>
    <body bgcolor="pink">
        <form action="/register" method="POST">
            <input type="text" id="firstName" name="firstName" placeholder="firstName"><br>
            <input type="text" id="lastName" name="lastName" placeholder="lastName"><br>
            <input type="date" id="dateOfBirth" name="dateOfBirth" placeholder="dateOfBirth"><br>
            <input type="radio" id="Male" name="gender" value="Male">Male<br>
            <input type="radio" id="Female" name="gender" value="Female">Female<br>
            <input type="radio" id="Other" name="gender" value="Other">Other<br>
            <input type="text" id="location" name="location" placeholder="location"><br>
            <input type="text" id="city" name="city" placeholder="city"><br>
            <input type="text" id="state" name="state" placeholder="state"><br>
            <input type="text" id="pincode" name="pincode" placeholder="pincode"><br>
            <input type="text" id="mobileNo" name="mobileNo" placeholder="mobileNo"><br>
            <input type="text" id="emailId" name="emailId" placeholder="emailId"><br>
            <input type="password" id="password" name="password" placeholder="password"><br>
            <input type="submit" value="Register">
        </form> 
    </body>
</html> -->