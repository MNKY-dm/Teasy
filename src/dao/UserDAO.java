package dao;

import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO {

    public static List<User> getAll(){
        String sql = "SELECT * " +
                "FROM user";

        List<User> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            try (var stmt  = conn.createStatement();
                var rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    User user = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("email"), rs.getString("password"), rs.getString("tel"), rs.getString("created_at"));

                    all.add(user);
                }

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
                user = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("email"), rs.getString("password"), rs.getString("tel"), rs.getString("created_at"));
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
                user = new User(rs.getInt("id"), rs.getString("nom"), rs.getString("email"), rs.getString("password"), rs.getString("tel"), rs.getString("created_at"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    public static boolean updateRowById(User user){
        // Update
        String sql = "UPDATE user " +
                "SET nom = ?, email = ?,  password = ?, role = ?, tel = ?, created_at = ? " +
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
                stmt.setTimestamp(6, user.getCreated_at());

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
        String sql = "INSERT INTO user " +
                "VALUES (nom = ?, email = ?,  password = ?, role = ?, tel = ?, created_at = ? ) ";


        try (var conn = MySQLConnection.getConnection();
         var stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getTel());
            stmt.setTimestamp(6, user.getCreated_at());

        var rs = stmt.executeUpdate();
        return rs == 1; // Indique que la fonction a fonctionné si le nombre de lignes insérée est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }
}
