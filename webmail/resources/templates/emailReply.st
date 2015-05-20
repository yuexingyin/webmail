emailReply(username,accountName,email)::=<<
<html>
<head>
<link href="/files/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        function openWin() {
            window.open ("/email", "Send an email", "height=420, width=600,top=100px,left=300px, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, status=no")
    }
    </script>
    <script src="/files/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="/files/js/check.js"></script>
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
                <div class="panel-heading"><h4>Content</h4></div>
                    <table class="table">
                    $email:{e |<form id="replyEmail" method="post" action="/replyEmail?emailId=$e.id$">
                    <tr><td>Reply to:<input type="text" id="div1" name="reply" size="30px" value="$e.from$"></td></tr>
                    <tr><td>Subject:&nbsp;<input type="text" id="div1" name="subject" value="$e.subject$" size="30px"></td></tr>
                    <tr><td><span>$e.content$</span></td></tr>}$
                    <tr><td><textarea class="form-control" rows="10" name="replyContent"></textarea></td></tr>
                    <tr><td><input type="submit" value="Reply" class="btn btn-success btn-sm"></form></td></tr>
                    </table>
            </div>
        </div>
    </div>
</div>
<div style="position:absolute; bottom:0;right:0;"><font color="white">DESIGNED BY YUEXING YIN</font></div>
<script src="/files/js/bootstrap.min.js"></script>
</body>
</html>
>>