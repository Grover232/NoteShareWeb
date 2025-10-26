<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.noteshareweb.model.Documento" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Documento> documentos = (List<Documento>) request.getAttribute("documentos");
    List<Documento> populares = (List<Documento>) request.getAttribute("populares");
    String msg = request.getParameter("msg");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Documentos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: #f9f9f9;
        }

        h1, h2 { color: #333; }

        a { text-decoration: none; }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            vertical-align: top;
        }

        th { background-color: #f2f2f2; }

        .btn {
            padding: 5px 10px;
            border-radius: 5px;
            color: white;
            text-decoration: none;
            transition: 0.3s;
            display: inline-block;
            margin-top: 5px;
        }

        .btn-descargar { background-color: #2196F3; }
        .btn-descargar:hover { background-color: #1976D2; }

        .btn-eliminar { background-color: #f44336; }
        .btn-eliminar:hover { background-color: #c62828; }

        .btn-ver { background-color: #4CAF50; }
        .btn-ver:hover { background-color: #388E3C; }

        .populares {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 15px;
            margin-top: 20px;
        }

        .card {
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 15px;
            background: white;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            transition: transform 0.3s;
        }

        .card:hover {
            transform: scale(1.03);
            background: #f0f8ff;
        }

        .mensaje {
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
        }

        .exito {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        iframe {
            border: none;
            border-radius: 6px;
        }
    </style>
</head>
<body>
    <h1>Bienvenido a NoteShareWeb</h1>
    <a href="upload.jsp">📤 Subir documento</a>
    <a href="logout" style="float:right;">Cerrar sesión</a>

    <%-- ✅ Mensajes de estado --%>
    <%
        if ("eliminado".equals(msg)) {
    %>
        <div class="mensaje exito">✅ Documento eliminado correctamente</div>
    <%
        } else if ("error".equals(msg)) {
    %>
        <div class="mensaje error">⚠️ Ocurrió un error al procesar la solicitud</div>
    <%
        }
    %>

    <h2>📄 Mis documentos</h2>
    <table>
        <tr>
            <th>Título</th>
            <th>Descripción</th>
            <th>Vista previa</th>
            <th>Acciones</th>
        </tr>
        <%
            if (documentos != null && !documentos.isEmpty()) {
                for (Documento doc : documentos) {
                    // ⚙️ URL pública de Ngrok — cámbiala cuando generes un nuevo túnel
                    String dominioPublico = "https://homier-chelsey-indiscerptibly.ngrok-free.dev";

                    // 🧩 Encode del nombre del archivo y salto del aviso de Ngrok
                    String archivoEnc = URLEncoder.encode(doc.getArchivo(), "UTF-8");
                    String urlArchivo = dominioPublico + request.getContextPath()
                            + "/uploads/" + archivoEnc + "?ngrok-skip-browser-warning=1";

                    // 🧠 Google Docs Viewer (soporta .docx, .pptx, .pdf, .xls, etc.)
                    String googleViewer = "https://docs.google.com/gview?embedded=true&url="
                            + URLEncoder.encode(urlArchivo, "UTF-8");
        %>
        <tr>
            <td><%= doc.getTitulo() %></td>
            <td><%= doc.getDescripcion() %></td>

            <!-- ✅ Mini vista previa funcional -->
            <td style="width: 400px; height: 250px;">
                <iframe src="<%= googleViewer %>" width="100%" height="250px"></iframe>
            </td>

            <td>
                <a href="<%= urlArchivo %>" class="btn btn-descargar" target="_blank">📥 Descargar</a>
                <a href="<%= googleViewer %>" class="btn btn-ver" target="_blank">👁 Ver completo</a>
                <a href="eliminarDocumento?id=<%= doc.getIdDocumento() %>&archivo=<%= doc.getArchivo() %>"
                   class="btn btn-eliminar"
                   onclick="return confirm('¿Seguro que deseas eliminar este documento?');">🗑 Eliminar</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="4">No tienes documentos subidos.</td></tr>
        <% } %>
    </table>

    <hr>

    <h2>🔥 Documentos populares</h2>
    <div class="populares">
        <%
            if (populares != null && !populares.isEmpty()) {
                for (Documento doc : populares) {
        %>
        <div class="card">
            <strong><%= doc.getTitulo() %></strong><br>
            <em>Publicado por: <%= doc.getNombreUsuario() %></em><br>
            <p><%= doc.getDescripcion() %></p>
            <a href="uploads/<%= doc.getArchivo() %>" class="btn btn-descargar">📥 Descargar</a>
        </div>
        <%
                }
            } else {
        %>
        <p>No hay documentos populares aún.</p>
        <% } %>
    </div>
</body>
</html>






