package dao;

import models.Photo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoDAO {

    public static List<Photo> getAll(){
        String sql = "SELECT * " +
                "FROM photo";

        List<Photo> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            try (var stmt  = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Photo photo = new Photo(rs.getInt("id"), rs.getInt("event_id"), rs.getString("url"), rs.getString("alt"), rs.getString("type"), rs.getTimestamp("created_at"));

                    all.add(photo);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Photo getPhotoById(Integer id) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM Photo " +
                "WHERE id = ?";

        Photo Photo = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {
                Photo = new Photo(rs.getInt("id"), rs.getInt("event_id"), rs.getString("url"), rs.getString("alt"), rs.getString("type"), rs.getTimestamp("created_at"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Photo;
    }

    public static boolean updatePhotoById(Integer id, String[] newValues){
        // Update
        String sql = "UPDATE Photo " +
                "SET event_id = ?, url = ?,  alt = ?, type = ?, created_at = ? " +
                "WHERE id = ?";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            for (int i = 0 ; i < newValues.length ; i++) {
                stmt.setString(i + 1, newValues[i]); // Définir chaque valeur dans la commande SQL
            }

            stmt.setInt(newValues.length + 1, id); // Définir l'id dans la commande SQL

            var rs = stmt.executeUpdate();
            return rs == 1 ; // Indique que la fonction a fonctionné si le nombre de lignes mises à jour est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false ;
        }
    }

    public static boolean deletePhotoById(Integer id) {
        // Delete

        String sql = "DELETE " +
                "FROM Photo " +
                "WHERE id = ?";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeUpdate();
            return rs == 1; // Renvoie true si une ligne a bien été supprimée, false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static boolean createPhoto(String[] values) {
        String sql = "INSERT INTO Photo " +
                "VALUES (event_id = ?, url = ?,  alt = ?, type = ?, created_at = ? ) ";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                stmt.setString(i + 1, values[i]); // Définir chaque valeur dans la commande SQL
            }

            var rs = stmt.executeUpdate();
            return rs == 1; // Indique que la fonction a fonctionné si le nombre de lignes insérée est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
