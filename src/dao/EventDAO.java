package dao;

import models.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public static List<Event> getAll(){
        String sql = "SELECT * " +
                "FROM event";

        List<Event> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            try (var stmt  = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Event Event = new Event(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("affiche"), rs.getString("language"), rs.getTimestamp("created_at"));

                    all.add(Event);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Event getEventById(Integer id) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM Event " +
                "WHERE id = ?";

        Event Event = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {
                Event = new Event(rs.getInt("id"), rs.getString("nom"), rs.getString("email"), rs.getString("password"), rs.getString("tel"), rs.getTimestamp("created_at"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Event;
    }

    public static boolean updateEventById(Integer id, String[] newValues){
        // Update
        String sql = "UPDATE event " +
                "SET name = ?, description = ?,  affiche = ?, language = ?, created_at = ? " +
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

    public static boolean deleteEventById(Integer id) {
        // Delete

        String sql = "DELETE " +
                "FROM Event " +
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

    public static boolean createEvent(String[] values) {
        String sql = "INSERT INTO Event " +
                "VALUES (name = ?, description = ?,  affiche = ?, language = ?, created_at = ? ) ";

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
