regist() ::= <<
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link href="/files/css/bootstrap.min.css" rel="stylesheet">
    <script src="/files/jquery-2.1.1.min.js"></script>
</head>
<body background="/files/LoginImage.jpg">
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <p class="navbar-brand">Mymail</p>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/"><button type="button" class="btn btn-danger">Sign in</button></a></li>
            </ul>
        </div>
    </div>
</nav><br><br><br><br><br>
<div class="container-fluid">
    <div class="row"><center><font color="white"><h2>Create your New Mymail Account</h2></font></center></div><br><br>
        <div class="row">
            <div class="col-sm-6 col-md-6 " style="width:480px;height:620px"></div>
            <div class="col-sm-6 col-md-6 " style="position:absolute;right:10px;">
                <form method= "post" action= "/regist">
                        &nbsp;&nbsp;&nbsp;<font color="white">Please choose your username&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;How old are you</font><br><br>&nbsp;&nbsp;&nbsp;<span id="nameResult" style="color:red;"></span><input type="text" id=“username” name="username">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="age"><br><br>
                        &nbsp;&nbsp;&nbsp;<font color="white">Please choose your password&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;You are....?</font><br><br>&nbsp;&nbsp;&nbsp;<input type="password" name="password">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="gender"><br><br>
                        &nbsp;&nbsp;&nbsp;<font color="white">Please confirm your password</font><br><br>&nbsp;&nbsp;&nbsp;<input type="password" name="confirm"><br><br>
                        &nbsp;&nbsp;&nbsp;<font color="white">Your Email Account</font><br><br>&nbsp;&nbsp;&nbsp;<input type="text" name="accountName">&nbsp;&nbsp;<font color="red">Only Gmail now!</font><br><br>
                        &nbsp;&nbsp;&nbsp;<font color="white">Please input email password</font><br><br>&nbsp;&nbsp;&nbsp;<input type="password" name="accountPass">&nbsp;&nbsp;<font color="red">Please make sure that password is right!</font><br><br>
                        &nbsp;&nbsp;&nbsp;<font color="white">Please confirm your password</font><br><br>&nbsp;&nbsp;&nbsp;<input type="password" name="accountConfirm">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
                        &nbsp;&nbsp;&nbsp;<input type="reset" class="btn btn-warning" value="Reset">&nbsp;&nbsp;&nbsp;<input type="submit" value="Submit" class="btn btn-danger"><br><br><br><br>
                </form>
            </div>
        </div>
    </div>
<script src="/files/js/bootstrap.min.js"></script>
</body>
</html>
>>