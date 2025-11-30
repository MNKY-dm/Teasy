package dao;

import models.Event;
import models.Photo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements DAO {

    public static List<Event> getAll(){
        String sql = "SELECT * " +
                "FROM event";

        List<Event> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            var stmt  = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Event Event = new Event(rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("affiche"),
                        rs.getString("language"));

                all.add(Event);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Event getRowById(Integer id) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM event " +
                "WHERE id = ?";

        Event event = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {
                event = new Event(rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tel"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return event;
    }

    public static boolean updateRowById(Event event){
        // Update
        String sql = "UPDATE event " +
                "SET name = ?, description = ?,  affiche = ?, language = ? " +
                "WHERE id = ?";

        int eventId = event.getId();

        if (getRowById(eventId) != null) { // Ne mettre à jour la ligne que si l'event est bien trouvé, que le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id)

            try (var conn = MySQLConnection.getConnection();
                 var stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, event.getName());
                stmt.setString(2, event.getDescription());
                stmt.setString(3, event.getAffiche());
                stmt.setString(4, event.getLanguage());

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

    public static void insertNewRow(Event event) { // Methode utilisée si un artiste ou un admin ajoute un événement (fonctionnalité non implémentée pour le moment)
        String sql = "INSERT INTO event (name, description, affiche, language) " +
                "VALUES (?, ?,  ?, ?) ";

        try (var conn = MySQLConnection.getConnection();
            var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getAffiche());
            stmt.setString(4, event.getLanguage());
            stmt.setTimestamp(5, event.getCreated_at());

            int rows = stmt.executeUpdate();
            if (rows == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        event.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<Photo> getPictures(int eventId) {
        String sql = "SELECT * " +
                "FROM photo " +
                "WHERE event_id = ?";

        List<Photo> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection();
            var stmt  = conn.prepareStatement(sql)) {

//            System.out.println("eventId reçu : " + eventId); // Débugger la méthode
//            System.out.println("SQL avant : " + sql);

            stmt.setInt(1, eventId);

            var rs = stmt.executeQuery(); // Erreur bloquante ici : j'avais passé sql en paramètre

            while (rs.next()) {
//                System.out.println("Photo trouvée : event_id=" + rs.getInt("event_id") + ", url=" + rs.getString("url")); // Débugger la méthode
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

    public static void main(String[] args) {
//        System.out.println(getPictures(1)); // débugger
    }
}
