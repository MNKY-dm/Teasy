package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO {

    public static List<User> getAll(){
        String sql = "SELECT * " +
                "FROM user";

        List<User> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            var stmt  = conn.createStatement();
            var rs = stmt.executeQuery(sql) ;

            while (rs.next()) {
                User user = new User(rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tel"),
                        rs.getString("role"));
                user.setId(rs.getInt("id"));
                user.setCreated_at(rs.getTimestamp("created_at"));
                all.add(user);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static User getRowById(Integer id) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM user " +
                "WHERE id = ?";

        User user = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tel"),
                        rs.getString("role"));
                user.setId(rs.getInt("id"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    public static User getRowByEmail(String email) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM user " +
                "WHERE email = ?";

        User user = null;

        try (var conn = MySQLConnection.getConnection();
            var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println(email + " existe");
                user = new User(rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("tel"),
                        rs.getString("role"));
                user.setId(rs.getInt("id"));
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    public static boolean updateRowById(User user){
        // Update
        String sql = "UPDATE user " +
                "SET nom = ?, email = ?,  password = ?, role = ?, tel = ? " +
                "WHERE id = ?";

        int userId = user.getId();

        if (getRowById(userId) != null) { // Ne mettre à jour la ligne que si l'user est bien trouvé, que le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id)

            try (var conn = MySQLConnection.getConnection();
                var stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, user.getNom());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setString(4, user.getRole());
                stmt.setString(5, user.getTel());

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
                "FROM user " +
                "WHERE id = ?";

        if (getRowById(id) != null) {

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
        return false;
    }

    public static boolean insertNewRow(User user) {
        String sql = "INSERT INTO user (nom, email, password, role, tel) " +
                "VALUES (?, ?, ?, ?, ?) ";

        try (Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getTel());

            int rows = stmt.executeUpdate();
            if (rows == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false; // Indique que la fonction a fonctionné si le nombre de lignes insérée est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public static void main(String[] args) {
//        System.out.println(getRowByEmail("ty389@outlook.com"));
    }
}
