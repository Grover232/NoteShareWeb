package com.mycompany.noteshareweb.dao;

import com.mycompany.noteshareweb.model.Documento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentoDAO {

    // Subir documento
    public boolean subirDocumento(Documento doc) throws SQLException {
        String sql = "INSERT INTO documentos(titulo, descripcion, archivo, id_usuario) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, doc.getTitulo());
            ps.setString(2, doc.getDescripcion());
            ps.setString(3, doc.getArchivo());
            ps.setInt(4, doc.getIdUsuario());
            return ps.executeUpdate() > 0;
        }
    }

    // Listar documentos de un usuario específico
    public List<Documento> listarPorUsuario(int idUsuario) throws SQLException {
        List<Documento> lista = new ArrayList<>();
        String sql = "SELECT * FROM documentos WHERE id_usuario = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Documento d = new Documento();
                    d.setIdDocumento(rs.getInt("id_documento"));
                    d.setTitulo(rs.getString("titulo"));
                    d.setDescripcion(rs.getString("descripcion"));
                    d.setArchivo(rs.getString("archivo"));
                    d.setIdUsuario(rs.getInt("id_usuario"));
                    lista.add(d);
                }
            }
        }
        return lista;
    }

    // 🔥 NUEVO: Listar documentos de distintos usuarios (populares/recientes)
    public List<Documento> listarPopulares() throws SQLException {
        List<Documento> lista = new ArrayList<>();
        String sql = "SELECT d.id_documento, d.titulo, d.descripcion, d.archivo, u.nombre AS nombre_usuario "
                + "FROM documentos d JOIN usuarios u ON d.id_usuario = u.id_usuario "
                + "ORDER BY d.id_documento DESC LIMIT 5"; // los 5 más recientes

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Documento d = new Documento();
                d.setIdDocumento(rs.getInt("id_documento"));
                d.setTitulo(rs.getString("titulo"));
                d.setDescripcion(rs.getString("descripcion"));
                d.setArchivo(rs.getString("archivo"));
                d.setNombreUsuario(rs.getString("nombre_usuario")); // 👈 agregado
                lista.add(d);
            }
        }
        return lista;
    }
    // Eliminar documento por ID

    public boolean eliminarDocumento(int idDocumento) throws SQLException {
        String sql = "DELETE FROM documentos WHERE id_documento = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDocumento);
            return ps.executeUpdate() > 0;
        }
    }

}
