package mx.edu.utez.manosmexicanas.dao;

import mx.edu.utez.manosmexicanas.model.Usuario;
import mx.edu.utez.manosmexicanas.utils.DbConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public  boolean insert(Usuario user){
        boolean flag = false;
        String query = "CALL registerUser(?, ?, ?,?,?)";
        try (Connection conn = DbConnectionManager.getConnection(); PreparedStatement ps = conn.prepareStatement(query);){
            ps.setString(1,user.getNombre());
            ps.setString(2, user.getApellido());
            ps.setString(3,user.getCorreo());
            ps.setString(4,user.getTelefono());
            ps.setString(5,user.getPassword());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public boolean loginAdmin(String correo, String password){
        boolean flag = false;
        String query = "CALL loginAdmin(?, ?)";
        try(Connection conn = DbConnectionManager.getConnection(); PreparedStatement ps = conn.prepareStatement(query);){
            ps.setString(1,correo);
            ps.setString(2,password);
           ResultSet rs  = ps.executeQuery();
            if(rs.next()){
                flag = true;
                rs.close();
            }
        }catch (SQLException e){
            e.getMessage();
        }


        return flag;
    }
}
