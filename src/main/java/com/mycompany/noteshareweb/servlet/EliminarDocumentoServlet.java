package com.mycompany.noteshareweb.servlet;

import com.mycompany.noteshareweb.dao.DocumentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/eliminarDocumento")  // 👈 ESTA LÍNEA ES FUNDAMENTAL
public class EliminarDocumentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String archivo = request.getParameter("archivo");

        if (idStr == null || archivo == null) {
            response.sendRedirect("home?msg=error");
            return;
        }

        int idDocumento = Integer.parseInt(idStr);
        DocumentoDAO dao = new DocumentoDAO();

        try {
            boolean eliminado = dao.eliminarDocumento(idDocumento);
            if (eliminado) {
                // 🔹 Eliminar archivo físico
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                File archivoFisico = new File(uploadPath, archivo);
                if (archivoFisico.exists()) archivoFisico.delete();

                response.sendRedirect("home?msg=eliminado");
            } else {
                response.sendRedirect("home?msg=error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("home?msg=error");
        }
    }
}

