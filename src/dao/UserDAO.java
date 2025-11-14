package dao;

import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

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

    public static User getUserById(Integer id) {
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

    public static boolean updateUserById(Integer id, String[] newValues){
        // Update
        String sql = "UPDATE user " +
                "SET nom = ?, email = ?,  password = ?, role = ?, tel = ?, created_at = ? " +
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

    public static boolean deleteUserById(Integer id) {
        // Delete

        if (getUserById(id) != null) {
            String sql = "DELETE " +
                    "FROM user " +
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
        return false; // Indique que la fonction n'a pas fonctionné, car aucune ligne n'a été trouvée avec l'id
    }

    public static boolean createUser(String[] values) {
        String sql = "INSERT INTO user " +
                "VALUES (nom = ?, email = ?,  password = ?, role = ?, tel = ?, created_at = ? ) ";

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
