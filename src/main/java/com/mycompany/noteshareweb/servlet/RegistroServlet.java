package com.mycompany.noteshareweb.servlet;

import com.mycompany.noteshareweb.dao.UsuarioDAO;
import com.mycompany.noteshareweb.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Obtener datos del formulario y limpiar espacios
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        System.out.println("----- DEBUG RegistroServlet -----");
        System.out.println("Formulario recibido:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contrasena);

        // 2️⃣ Validación simple
        if(nombre == null || correo == null || contrasena == null ||
           nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            System.out.println("Error: algún campo está vacío");
            response.sendRedirect("registro.jsp?msg=error");
            return;
        }

        // 3️⃣ Crear objeto Usuario
        Usuario u = new Usuario();
        u.setNombre(nombre.trim());
        u.setCorreo(correo.trim());
        u.setContrasena(contrasena.trim());

        UsuarioDAO dao = new UsuarioDAO();

        try {
            // 4️⃣ Intentar registrar usuario
            boolean registrado = dao.registrarUsuario(u);

            System.out.println("Resultado de dao.registrarUsuario: " + registrado);

            if(registrado) {
                System.out.println("Registro exitoso");
                response.sendRedirect("index.jsp?msg=registrado");
            } else {
                System.out.println("Registro fallido: probablemente el correo ya existe o hay error en SQL");
                response.sendRedirect("registro.jsp?msg=error");
            }

        } catch (Exception e) {
            // 5️⃣ Mostrar excepción completa en consola
            System.out.println("Excepción durante registro:");
            e.printStackTrace();
            response.sendRedirect("registro.jsp?msg=error");
        }

        System.out.println("------------------------------");
    }
}
