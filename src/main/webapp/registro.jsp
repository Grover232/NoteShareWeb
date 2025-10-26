<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>NoteShareWeb - Registro</title></head>
<body>
<h2>Registro de Usuario</h2>
<form action="registro" method="post">
    Nombre: <input type="text" name="nombre" required><br>
    Correo: <input type="email" name="correo" required><br>
    Contraseña: <input type="password" name="contrasena" required><br>
    <input type="submit" value="Registrar">
</form>
<p><a href="index.jsp">Volver al login</a></p>
</body>
</html>

