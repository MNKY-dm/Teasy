package dao;

import models.Photo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoDAO implements DAO {

    public static List<Photo> getAll(){
        String sql = "SELECT * " +
                "FROM photo";

        List<Photo> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            var stmt  = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Photo photo = new Photo(rs.getInt("event_id"),
                        rs.getString("url"),
                        rs.getString("alt"),
                        rs.getString("type"));

                all.add(photo);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Photo getRowById(Integer id) {
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
                Photo = new Photo(rs.getInt("event_id"),
                        rs.getString("url"),
                        rs.getString("alt"),
                        rs.getString("type"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Photo;
    }

    public static boolean updatePhotoById(Photo photo){
        // Update
        String sql = "UPDATE Photo " +
                "SET event_id = ?, url = ?,  alt = ?, type = ? " +
                "WHERE id = ?";

        int photoId = photo.getId();

        if (getRowById(photoId) != null) {
            try (var conn = MySQLConnection.getConnection();
                var stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, photo.getEvent_id());
                stmt.setString(2, photo.getUrl());
                stmt.setString(3, photo.getAlt());
                stmt.setString(4, photo.getType());

                var rs = stmt.executeUpdate();
                return rs == 1 ; // Indique que la fonction a fonctionné si le nombre de lignes mises à jour est 1, et false sinon

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false ;
            }
        }
        return false;
    }

    public static boolean deleteRowById(Integer id) {
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

    public static boolean createPhoto(Photo photo) {
        String sql = "INSERT INTO Photo " +
                "VALUES (event_id = ?, url = ?,  alt = ?, type = ?, created_at = ? ) ";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, photo.getEvent_id());
            stmt.setString(2, photo.getUrl());
            stmt.setString(3, photo.getAlt());
            stmt.setString(4, photo.getType());
            stmt.setTimestamp(5, photo.getCreated_at());

            var rs = stmt.executeUpdate();
            return rs == 1; // Indique que la fonction a fonctionné si le nombre de lignes insérées est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
