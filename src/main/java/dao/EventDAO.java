package dao;

import models.Event;
import models.Photo;
import models.Seance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements DAO {

    public static List<Event> getAll() {
        String sql = "SELECT * " +
                "FROM event";

        List<Event> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("affiche"),
                        rs.getString("language"),
                        rs.getInt("creator_id")
                );
                event.setId(rs.getInt("id"));
                event.setCreated_at(rs.getTimestamp("created_at"));
                all.add(event);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Event getRowById(Integer id) {
        String sql = "SELECT * " +
                "FROM event " +
                "WHERE id = ?";

        Event event = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                event = new Event(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("affiche"),
                        rs.getString("language"),
                        rs.getInt("creator_id")
                );
                event.setId(rs.getInt("id"));
                event.setCreated_at(rs.getTimestamp("created_at"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return event;
    }

    public static Event getRowByName(String name) {
        String sql = "SELECT * " +
                "FROM event " +
                "WHERE name = ?";

        Event event = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                event = new Event(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("affiche"),
                        rs.getString("language"),
                        rs.getInt("creator_id")
                );
                event.setId(rs.getInt("id"));
                event.setCreated_at(rs.getTimestamp("created_at"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return event;
    }

    public static boolean updateRowById(Event event) {
        String sql = "UPDATE event " +
                "SET name = ?, description = ?, affiche = ?, language = ?, event.creator_id = ? " +
                "WHERE id = ?";

        int eventId = event.getId();

        if (getRowById(eventId) != null) {
            try (var conn = MySQLConnection.getConnection();
                 var stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, event.getName());
                stmt.setString(2, event.getDescription());
                stmt.setString(3, event.getAffiche());
                stmt.setString(4, event.getLanguage());
                stmt.setInt(5, event.getCreator_id());
                stmt.setInt(6, eventId);

                var rs = stmt.executeUpdate();
                return rs == 1;

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }

        return false;
    }

    public static boolean deleteRowById(Integer id) {
        String sql = "DELETE " +
                "FROM event " +
                "WHERE id = ?";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            var rs = stmt.executeUpdate();
            return rs == 1;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static void insertNewRow(Event event) {
        String sql = "INSERT INTO event (name, description, affiche, language, creator_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDescription());
            stmt.setString(3, event.getAffiche());
            stmt.setString(4, event.getLanguage());
            stmt.setInt(5, event.getCreator_id());

            int rows = stmt.executeUpdate();
            if (rows == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        event.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<Photo> getPictures(int eventId) {
        String sql = "SELECT * " +
                "FROM photo " +
                "WHERE event_id = ?";

        List<Photo> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventId);
            var rs = stmt.executeQuery();

            while (rs.next()) {
                Photo photo = new Photo(
                        rs.getInt("event_id"),
                        rs.getString("url"),
                        rs.getString("alt"),
                        rs.getString("type")
                );
                photo.setId(rs.getInt("id"));
                photo.setCreated_at(rs.getTimestamp("created_at"));
                all.add(photo);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static List<Seance> getSeances(int eventId) {
        String sql = "SELECT * " +
                "FROM seance " +
                "WHERE event_id = ? " +
                "ORDER BY date ASC";

        List<Seance> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, eventId);
            var rs = stmt.executeQuery();

            while (rs.next()) {
                Seance seance = new Seance(
                        rs.getInt("event_id"),
                        rs.getTimestamp("date"),
                        rs.getString("location"),
                        rs.getInt("nb_places"),
                        rs.getBoolean("is_cancelled")
                );
                seance.setId(rs.getInt("id"));
                seance.setCreated_at(rs.getTimestamp("created_at"));
                all.add(seance);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static void main(String[] args) {
        // System.out.println(getPictures(1));
    }
}
