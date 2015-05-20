home() ::= <<
<html>
<head>
    <title>Home Page</title>
    <style type="text/css">
        body {
        background-image: url('/files/LoginImage.jpg');
        }
        form {
        height:400px;
        width:400px;
        position: absolute;
        left: 50%;
        top: 50%;
        /* Set margins to offset 50% of the w/h */
        margin: -200px 0 0 -200px;
        }
        input[type=submit] {
        width: 5em;  height: 3em;
        }
        input[type=reset] {
        width: 5em;  height: 3em;
        }
    </style>
    <script type="text/javascript">
    function validate(){
        var username=document.frameLogin.username.value;
        var password=document.frameLogin.password.value;
        if(username=="" && password==""){
            alert("Please input username and password!");
            return false;
        }
        else if(username==""){
            alert("please input username!");
            return false;
        }else if(password==""){
            alert("Please input password!");
            return false;
        }
    }
    </script>
</head>
<body>
<center>
    <form method="post" name="frameLogin"action="/login">
        <font color="white"> <h1>Mymail</h1></font>
        <font color="white"> <h1>username:&nbsp;<input type="text" name="username" color="white"></h1></font>
        <font color="white"> <h1>password:&nbsp;<input type="password" name="password" color="white"></h1></font><br>
        <center><input type="submit" name="submit" value="Log in" onclick="return validate()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" name="reset" value="Reset"></center><br>
        <a href="/registpage" color="white"><h3>Create a new account</h3></a>
    </form>
</center>
</body>
</html>
>>
