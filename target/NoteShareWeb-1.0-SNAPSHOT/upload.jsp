<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Subir Documento</title></head>
<body>
<h2>Subir Documento</h2>
<form action="uploadDocumento" method="post" enctype="multipart/form-data">
    Título: <input type="text" name="titulo" required><br>
    Descripción: <input type="text" name="descripcion"><br>
    Archivo: <input type="file" name="archivo" required><br>
    <input type="submit" value="Subir">
</form>
<p><a href="list.jsp">Ver documentos</a></p>
</body>
</html>

