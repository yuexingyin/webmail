sortPage(username,accountName,email,contact) ::= <<
<html>
<head>
<link href="/files/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        function openWin() {
            window.open ("/email", "Send an email", "height=420, width=600,top=100px,left=300px, toolbar=no, menubar=no, scrollbars=no, resizable=yes, location=no, status=no")
    }
    </script>
    <script type="text/javascript" src="/files/check.js"></script>
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
                        <li>$accountName$</li>
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
            <ul class="nav nav-sidebar">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    Contact<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        $contact:{c|<li><a href="/sendContact?contactName=$c.contactName$">$c.contactName$</li>}$
                                    <li><a href="/contactPage"><input type="submit" value="Edit Contact" class="btn btn-danger btn-sm"></a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="col-sm-9 col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading"><h4>Inbox</h4></div><tr>
                <form method="get" action="/checkInbox"><input type="submit" value="Check mail" class="btn btn-success btn-sm"></form><ul class="nav navbar-nav navbar"><li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Sort by<span class="caret"></span></a><ul class="dropdown-menu">
                                                                                                                                                                                                                                                                                               <li><a href="/sortPage?column=subject">subject</a></li>
                                                                                                                                                                                                                                                                                               <li><a href="/sortPage?column=sender">sender</a></li>
                                                                                                                                                                                                                                                                                               <li><a href="/sortPage?column=date">date</a></li>
                                                                                                                                                                                                                                                                                               </ul>
                                                                                                                                                                                                                                                                                               </li></ul>
                <table class="table">
                    <th>From</th><th>Title</th><th>Date</th>
                    $email:emailrow()$
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