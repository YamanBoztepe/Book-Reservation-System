<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.yeditepe.bookrez.helper.Keys"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">  
<title>Login</title>
<style>   
Body {  
  font-family: Calibri, Helvetica, sans-serif;  
  background-color: gray;  
}  
button {   
       background-color: #4CAF50;   
       width: 100%;  
        color: white;   
        padding: 15px;   
        margin: 10px 0px;   
        border: none;   
        cursor: pointer;   
         }   
 form {   
        border: 3px solid #f1f1f1;   
    }   
 input[type=text], input[type=password] {   
        width: 100%;   
        margin: 8px 0;  
        padding: 12px 20px;   
        display: inline-block;   
        border: 2px solid green;   
        box-sizing: border-box;   
    }  
 button:hover {   
        opacity: 0.7;   
    }   
       
 .container {   
        padding: 25px;   
        background-color: lightblue;  
    }   
</style>   
</head>
<body>
	<center> <h1>Login Form </h1> </center>   
    <form action="<%=request.getContextPath() + Keys.Paths.login%>" method="post"> 
        <div class="container">   
            <label>Email : </label>   
            <input type="text" placeholder="Enter Email" name="<%=Keys.User.email%>" required>  
            <label>Password : </label>   
            <input type="password" placeholder="Enter Password" name="<%=Keys.User.password%>" required>  
            <button type="submit">Login</button>  
            <a href="<%=request.getContextPath() + Keys.Paths.signUp%>">Sign up</a>
        </div>   
    </form>  
</body>
</html>
