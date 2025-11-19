package dao;

import models.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements DAO {

    public static List<Event> getAll(){
        String sql = "SELECT * " +
                "FROM event";

        List<Event> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            try (var stmt  = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Event Event = new Event(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("affiche"),
                            rs.getString("language"));

                    all.add(Event);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Event getRowById(Integer id) {
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
                Event = new Event(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tel"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Event;
    }

    public static boolean updateRowById(Event event){
        // Update
        String sql = "UPDATE event " +
                "SET name = ?, description = ?,  affiche = ?, language = ?, created_at = ? " +
                "WHERE id = ?";

        int eventId = event.getId();

        if (getRowById(eventId) != null) { // Ne mettre à jour la ligne que si l'event est bien trouvé, que le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id)

            try (var conn = MySQLConnection.getConnection();
                 var stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, event.getName());
                stmt.setString(2, event.getDescription());
                stmt.setString(3, event.getAffiche());
                stmt.setString(4, event.getLanguage());
                stmt.setTimestamp(6, event.getCreated_at());

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

    public static boolean insertRow(Event event) {
        String sql = "INSERT INTO Event " +
                "VALUES (name = ?, description = ?,  affiche = ?, language = ?, created_at = ? ) ";

        try (var conn = MySQLConnection.getConnection();
            var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getAffiche());
            stmt.setString(4, event.getLanguage());
            stmt.setTimestamp(6, event.getCreated_at());

            var rs = stmt.executeUpdate();
            return rs == 1; // Indique que la fonction a fonctionné si le nombre de lignes insérée est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
