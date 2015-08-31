<%-- 
    Document   : index
    Created on : 30/08/2015, 09:33:39 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Por Favor digite su Usuario y Contraseña</h1>
        <div>
        <form action="Autenticacion">
            <p>Usuario: <input type ="text" name="user" id="user" /></p>
            <p>Contraseña: <input type="password" name ="pass" id="pass"/></p>
            <p><input type="submit" name="Entrar" id="Entrar"/></p>
        </form>
        </div>
    </body>
</html>
