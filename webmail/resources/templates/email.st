email() ::= <<
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="/files/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form method="post" action="/sendMail">
    <table class="table">
        <tr><td>To:<input type="text" name="to" class="form-control"></td></tr>
        <tr><td>Subject:<input type="text" name="subject" class="form-control"></td></tr>
        <tr><td><textarea class="form-control" rows="10" name="content"></textarea></td></tr>
        <tr><td><input type="submit" name="submit" value="Send"></td></tr>
    </table>
</form>
<script src="/files/jquery-2.1.1.min.js"></script>
<script src="/files/js/bootstrap.min.js"></script>
</body>
</html>
>>