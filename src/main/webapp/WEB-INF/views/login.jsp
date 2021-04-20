<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Login</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <body class="bg-dark">
            <div class="card-header bg-light">
                <center><h2 class="text-center font-weight-light my-4"">Electronic Voting System</h2></center>
            </div>
            <div id="layoutAuthentication">
                <div id="layoutAuthentication_content">
                    <main>
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col-lg-5">
                                    <div class="card shadow-lg border-0 rounded-lg mt-5">
                                        <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                                        <div class="card-body">
                                            <form action="/profile" method="POST">
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputUserId">UserId</label>
                                                    <input class="form-control py-4" id="inputUserId" type="text" placeholder="Enter userId" name="userId" />
                                                </div>
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputPassword">Password</label>
                                                    <input class="form-control py-4" id="inputPassword" type="password" placeholder="Enter password" name="password"/>
                                                </div>
                                                <div>
                                                    <button class="btn btn-primary" type="submit">Login</button>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="card-footer text-center">
                                            <div class="small"><a href="/pages/register.jsp">Register</a></div>
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
            <script src="js/scripts.js"></script>

        <!-- <form action="/profile" method="POST">
            <input type="text" placeholder="userId" name="userId"><br>
            <input type="password" placeholder="password" name="password"><br>
            <select name="userType">
                <option name="A" value="A">Admin</option>
                <option name="E" value="E">Electoral Officer</option>
                <option name="V" value="V">Voter</option>
            </select>
            <input type="submit">
        </form>
        <a href="/pages/register.jsp">Register</a> -->
    </body>
</html>