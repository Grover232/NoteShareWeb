package com.mycompany.noteshareweb.servlet;

import com.mycompany.noteshareweb.dao.DocumentoDAO;
import com.mycompany.noteshareweb.model.Documento;
import com.mycompany.noteshareweb.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar sesión activa
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp?msg=login");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        DocumentoDAO dao = new DocumentoDAO();

        try {
            // 📄 Documentos del usuario logueado
            List<Documento> documentos = dao.listarPorUsuario(usuario.getId());
            request.setAttribute("documentos", documentos);

            // 🔥 Documentos populares
            List<Documento> populares = dao.listarPopulares();
            request.setAttribute("populares", populares);

            // Redirigir al home.jsp
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
    