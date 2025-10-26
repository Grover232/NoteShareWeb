package com.mycompany.noteshareweb.servlet;

import com.mycompany.noteshareweb.dao.UsuarioDAO;
import com.mycompany.noteshareweb.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        UsuarioDAO dao = new UsuarioDAO();
        try {
            Usuario u = dao.login(correo, contrasena);
            if (u != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", u);
                response.sendRedirect("home"); // 👈 Redirige al servlet, no al JSP
            } else {
                response.sendRedirect("index.jsp?msg=error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?msg=error");
        }
    }
}
