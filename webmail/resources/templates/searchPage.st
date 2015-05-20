searchPage(username,accountName)::=<<
<html>
<head>
<link href="/files/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        function openWin() {
            window.open ("/email", "Send an email", "height=420, width=600,top=100px,left=300px, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, status=no")
    }
    </script>
</head>
<body background="/files/LoginImage.jpg">
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <p class="navbar-brand">Welcome to Mymail</p>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        $username$<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">$accountName$</a></li>
                    </ul>
                </li>
                <li><a href="/settings">Settings</a></li>
                <li><form method="get" action="/logout"><input type="submit" value="Sign out" class="btn btn-danger"></form></li>
            </ul>
            <a href="/searchPage"><button type="button" class="btn btn-info">Search</button></a>
        </div>
    </div>
</nav>
<br><br><br><br><br><br>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><button type="button" class="btn btn-primary btn-lg " onclick="openWin()">
                    Compose
                </button></li><br><br>
                <li><a href="/mainpage">Inbox</a></li>
                <li><a href="/sentMail">Sent mail</a></li>
            </ul><br><br>
            <ul class="nav nav-sidebar">
                <li><a href="/spam">Spam</a></li>
                <li><a href="/trash">Trash</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading"><h4>Result</h4></div>
                <table class="table">
                    <tr><td><form method="post" action="/searchResult?option=sender">Search by Sender:<input type="text" class="form-control" id="sender" name="sender" placeholder="Sender's address"></form></td></tr>
                    <tr><td><form method="post" action="/searchResult?option=subject">Search by Subject:<input type="text" class="form-control" id="subject" name="subject" placeholder="Subject name"></form></td></tr>
                </table>
            </div>
            <center>
                <ul class="pagination">
                    <li><a href="#">&laquo;</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&raquo;</a></li>
                </ul><br>
            </center>
        </div>
    </div>
</div>
<div style="position:absolute; bottom:0;right:0;"><font color="white">DESIGNED BY YUEXING YIN</font></div>
<script src="/files/jquery-2.1.1.min.js"></script>
<script src="/files/js/bootstrap.min.js"></script>
</body>
</html>
>>