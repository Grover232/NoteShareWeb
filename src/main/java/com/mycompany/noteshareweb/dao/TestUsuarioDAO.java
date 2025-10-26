package com.mycompany.noteshareweb.dao;

import com.mycompany.noteshareweb.model.Usuario;

public class TestUsuarioDAO {
    public static void main(String[] args) {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario u = new Usuario();
            u.setNombre("Prueba");
            u.setCorreo("prueba@example.com");
            u.setContrasena("12345");

            if(usuarioDAO.registrarUsuario(u)) {
                System.out.println("Usuario registrado correctamente.");
            }

            Usuario login = usuarioDAO.login("prueba@example.com", "12345");
            if(login != null) {
                System.out.println("Login exitoso: " + login.getNombre());
            } else {
                System.out.println("Login fallido.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
