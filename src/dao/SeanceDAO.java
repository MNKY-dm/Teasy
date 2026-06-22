package dao;

import models.Pricing;
import models.Seance;
import utils.TypeConverter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeanceDAO implements DAO {

    public static List<Seance> getAll(){
        String sql = "SELECT * " +
                "FROM seance";

        List<Seance> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            var stmt  = conn.createStatement();
             var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Seance seance = new Seance(rs.getInt("event_id"),
                        rs.getTimestamp("date"),
                        rs.getString("location"),
                        rs.getInt("nb_places"),
                        rs.getBoolean("is_cancelled")); // TODO : retirer status de la bdd t le rendre uniquement calculé dynamiquement par les contrôleurs, stocké dans les objets quand-même
                seance.setId(rs.getInt("id"));
                seance.setCreated_at(rs.getTimestamp("created_at"));
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
                seance = new Seance(rs.getInt("event_id"),
                        rs.getTimestamp("date"),
                        rs.getString("location"),
                        rs.getInt("nb_places"),
                        rs.getBoolean("is_cancelled"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return seance;
    }

    public static List<Seance> getRowsByIds(List<Integer> seanceIds) {
        if (seanceIds == null || seanceIds.isEmpty()) {
            return new ArrayList<>();
        }
        String placeholders = seanceIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(","));
        String sql = "SELECT * FROM seance WHERE id IN (" + placeholders + ")";

        List<Seance> result = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            int index = 1;
            for (Integer id : seanceIds) {
                stmt.setInt(index++, id);
            }

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
                result.add(seance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static Pricing getPricingById(int id) throws SQLException {
        String sql = "SELECT * " +
                "FROM pricing " +
                "WHERE seance_id = ?";
        Pricing pricing = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {
                pricing = new Pricing(rs.getInt("seance_id"),
                        rs.getFloat("price_1"),
                        rs.getFloat("price_2"),
                        rs.getFloat("price_3"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return pricing;
    }

    public static boolean updateRowById(Seance seance){
        // Update
        String sql = "UPDATE Seance " +
                "SET event_id = ?, date = ?,  location = ?, nb_places = ?, is_cancelled = ? " +
                "WHERE id = ?";

        int seanceID = seance.getId();

        if (getRowById(seanceID) != null) {
            try (var conn = MySQLConnection.getConnection();
                var stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, seance.getEvent_id());
                stmt.setTimestamp(2, seance.getDate());
                stmt.setString(3, seance.getLocation());
                stmt.setInt(4, seance.getNb_places());
                stmt.setBoolean(5, seance.getIs_cancelled());

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

    public static int insertNewRow(Seance seance) {
        String sql = "INSERT INTO seance (event_id, date, location, nb_places) " +
                "VALUES (?, ?, ?, ?) ";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, seance.getEvent_id());
            stmt.setTimestamp(2, seance.getDate());
            stmt.setString(3, seance.getLocation());
            stmt.setInt(4, seance.getNb_places());

            var rows = stmt.executeUpdate();

            if (rows == 0) {
                return -1;
            }

            try (var rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    seance.setId(id);
                    return id;
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    public static void main (String[] args) {
        models.Seance seance = getRowById(1);
        System.out.println(TypeConverter.dateFormat(seance.getDate()));
    }
}
