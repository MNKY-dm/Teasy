package dao;

import models.Seance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO implements DAO {

    public static List<Seance> getAll(){
        String sql = "SELECT * " +
                "FROM seance";

        List<Seance> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            var stmt  = conn.createStatement();
             var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Seance seance = new Seance(rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getTimestamp("date"),
                        rs.getString("location"),
                        rs.getInt("nb_places"),
                        rs.getString("statut"),
                        rs.getTimestamp("created_at"));

                all.add(seance);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Seance getRowById(Integer id) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM seance " +
                "WHERE id = ?";

        Seance seance = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {
                seance = new Seance(rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getTimestamp("date"),
                        rs.getString("location"),
                        rs.getInt("nb_places"),
                        rs.getString("statut"),
                        rs.getTimestamp("created_at"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return seance;
    }

    public static boolean updateRowById(Seance seance){
        // Update
        String sql = "UPDATE Seance " +
                "SET event_id = ?, date = ?,  location = ?, nb_places = ?, statut = ? " +
                "WHERE id = ?";

        int seanceID = seance.getId();

        if (getRowById(seanceID) != null) {
            try (var conn = MySQLConnection.getConnection();
                var stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, seance.getEvent_id());
                stmt.setTimestamp(2, seance.getDate());
                stmt.setString(3, seance.getLocation());
                stmt.setInt(4, seance.getNb_places());
                stmt.setString(5, seance.getStatut());

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
                "FROM Seance " +
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

    public static boolean insertRow(Seance seance) {
        String sql = "INSERT INTO Seance " +
                "VALUES (event_id = ?, date = ?,  location = ?, nb_places = ?, statut = ?, created_at = ? ) ";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, seance.getEvent_id());
            stmt.setTimestamp(2, seance.getDate());
            stmt.setString(3, seance.getLocation());
            stmt.setInt(4, seance.getNb_places());
            stmt.setString(5, seance.getStatut());
            stmt.setTimestamp(6, seance.getCreated_at());

            var rs = stmt.executeUpdate();
            return rs == 1; // Indique que la fonction a fonctionné si le nombre de lignes insérée est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
