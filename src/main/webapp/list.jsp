<%@ page import="com.mycompany.noteshareweb.dao.DocumentoDAO" %>
<%@ page import="com.mycompany.noteshareweb.model.Documento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Lista de Documentos</title></head>
<body>
<h2>Documentos Subidos</h2>
<%
    DocumentoDAO dao = new DocumentoDAO();
    List<Documento> lista = dao.listarDocumentos();
    for(Documento d : lista){
%>
    <p><b><%= d.getTitulo() %></b> - <%= d.getDescripcion() %> - 
    <a href="download?file=<%= d.getArchivo() %>">Descargar</a></p>
<%
    }
%>
<p><a href="upload.jsp">Subir otro documento</a></p>
</body>
</html>

