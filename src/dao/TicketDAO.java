package dao;

import models.Ticket;

import java.sql.SQLException;
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
                        rs.getString("place"),
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
                        rs.getString("place"),
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

    public static boolean updateRowById(Ticket ticket){
        // Update
        String sql = "UPDATE ticket " +
                "SET code = ?, " +
                "title = ?, "+
                "  place = ?, "+
                " user_id = ?, "+
                " seance_id = ?, "+
                " type = ?, "+
                " price = ?, "+
                "status = ?, "+
                " used_at = ?, "+
                "is_refunded = ? " +
                "WHERE id = ?;";

        int ticketId = ticket.getId();

        if (getRowById(ticketId) != null) {
            try (var conn = MySQLConnection.getConnection();
                 var stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, ticket.getCode());
                stmt.setString(2, ticket.getTitle());
                stmt.setString(3, ticket.getPlace());
                stmt.setInt(4, ticket.getUser_id());
                stmt.setInt(5, ticket.getSeance_id());
                stmt.setString(6, ticket.getType());
                stmt.setFloat(7, ticket.getPrice());
                stmt.setString(8, ticket.getStatus());
                stmt.setTimestamp(9, ticket.getUsed_at());
                stmt.setBoolean(10, ticket.getIs_refunded());

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

    public static boolean insertRow(Ticket ticket) {
        String sql = "INSERT INTO Ticket " +
                "VALUES (code = ?,"+
                " title = ?,"+
                "  place = ?,"+
                " user_id = ?,"+
                " seance_id = ?,"+
                " type = ?,"+
                " price = ?,"+
                " status = ?,"+
                " used_at = ?,"+
                " is_refunded = ?,"+
                " created_at = ?) ";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ticket.getCode());
            stmt.setString(2, ticket.getTitle());
            stmt.setString(3, ticket.getPlace());
            stmt.setInt(4, ticket.getUser_id());
            stmt.setInt(5, ticket.getSeance_id());
            stmt.setString(6, ticket.getType());
            stmt.setFloat(7, ticket.getPrice());
            stmt.setString(8, ticket.getStatus());
            stmt.setTimestamp(9, ticket.getUsed_at());
            stmt.setBoolean(10, ticket.getIs_refunded());
            stmt.setTimestamp(11, ticket.getCreated_at());

            var rs = stmt.executeUpdate();
            return rs == 1; // Indique que la fonction a fonctionné si le nombre de lignes insérées est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
