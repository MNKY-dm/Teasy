package dao;

import models.Ticket;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements DAO {
    public static List<Ticket> getAll(){
        String sql = "SELECT * " +
                "FROM ticket";

        List<Ticket> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            var stmt  = conn.createStatement();
            var rs = stmt.executeQuery(sql) ;

            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getString("code"),
                        rs.getString("title"),
                        rs.getInt("user_id"),
                        rs.getInt("seance_id"),
                        rs.getString("type"),
                        rs.getFloat("price"),
                        rs.getString("status"),
                        rs.getTimestamp("used_at"),
                        rs.getBoolean("is_refunded"));
                ticket.setId(rs.getInt("id"));
                ticket.setCreated_at(rs.getTimestamp("created_at"));
                all.add(ticket);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Ticket getRowById(Integer id) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM ticket " +
                "WHERE id = ?";

        Ticket ticket = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {
                ticket = new Ticket(rs.getString("code"),
                        rs.getString("title"),
                        rs.getInt("user_id"),
                        rs.getInt("seance_id"),
                        rs.getString("type"),
                        rs.getFloat("price"),
                        rs.getString("status"),
                        rs.getTimestamp("used_at"),
                        rs.getBoolean("is_refunded"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ticket;
    }

    public static boolean updateRowById(Ticket ticket) {
        String sql = "UPDATE ticket SET code=?, title=?, user_id=?, seance_id=?, type=?, price=?, status=?, used_at=?, is_refunded=? WHERE id=?";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ticket.getCode());
            stmt.setString(2, ticket.getTitle());
            stmt.setInt(3, ticket.getUser_id());
            stmt.setInt(4, ticket.getSeance_id());
            stmt.setString(5, ticket.getType());
            stmt.setFloat(6, ticket.getPrice());
            stmt.setString(7, ticket.getStatus());
            stmt.setTimestamp(8, ticket.getUsed_at());
            stmt.setBoolean(9, ticket.getIs_refunded());
            stmt.setInt(10, ticket.getId());

            int rs = stmt.executeUpdate();
            return rs == 1;
        } catch (SQLException ex) {
            System.out.println("Erreur UPDATE Ticket : " + ex.getMessage());
            return false;
        }
    }

    public static boolean deleteRowById(Integer id) {
        // Delete

        String sql = "DELETE " +
                "FROM ticket " +
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

    public static int insertRow(Ticket ticket) {
        String sql = "INSERT INTO ticket (code, title, user_id, seance_id, type, price, status, used_at, is_refunded, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  // ← clé

            stmt.setString(1, ticket.getCode());
            stmt.setString(2, ticket.getTitle());
            stmt.setInt(3, ticket.getUser_id());
            stmt.setInt(4, ticket.getSeance_id());
            stmt.setString(5, ticket.getType());
            stmt.setFloat(6, ticket.getPrice());
            stmt.setString(7, ticket.getStatus());
            stmt.setTimestamp(8, ticket.getUsed_at());
            stmt.setBoolean(9, ticket.getIs_refunded());
            stmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                // ← Récupérer l'ID généré
                var rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // retourne l'auto-increment ID
                }
            }
            return -1; // erreur
        } catch (SQLException ex) {
            System.out.println("Erreur INSERT Ticket : " + ex.getMessage());
            return -1;
        }
    }
}
