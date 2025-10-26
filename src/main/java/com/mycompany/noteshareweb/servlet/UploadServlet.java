package com.mycompany.noteshareweb.servlet;

import com.mycompany.noteshareweb.dao.DocumentoDAO;
import com.mycompany.noteshareweb.model.Documento;
import com.mycompany.noteshareweb.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

@WebServlet("/uploadDocumento")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp?msg=login");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Obtener datos del formulario
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        Part archivoPart = request.getPart("archivo");
        String nombreArchivo = Paths.get(archivoPart.getSubmittedFileName()).getFileName().toString();

        // 📂 Guardar dentro de webapp/uploads (visible públicamente)
        String uploadPath = getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // 📁 Guardar archivo físico
        archivoPart.write(uploadPath + File.separator + nombreArchivo);

        // 📄 Registrar en BD
        Documento doc = new Documento();
        doc.setTitulo(titulo);
        doc.setDescripcion(descripcion);
        doc.setArchivo(nombreArchivo);
        doc.setIdUsuario(usuario.getId());

        DocumentoDAO dao = new DocumentoDAO();
        try {
            boolean subido = dao.subirDocumento(doc);
            if (subido) {
                response.sendRedirect("home?msg=subido");
            } else {
                response.sendRedirect("upload.jsp?msg=error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("upload.jsp?msg=error");
        }
    }
}


