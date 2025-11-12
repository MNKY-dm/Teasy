package models;

import dao.MySQLConnection;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

public class UserModel {
    private Integer id;
    private String nom;
    private String email;
    private String password;
    private String tel;
    private String created_at;

    public List<UserModel> getAll(){
        String sql = "SELECT * " +
                "FROM user";

        List<UserModel> all = new ArrayList<>();

        try (var conn = MySQLConnection.connect()) {
            assert conn != null;
            try (var stmt  = conn.createStatement();
                 var rs = stmt.executeQuery(sql)) {

                while (rs.next()) {

                    UserModel obj = new UserModel();

                    obj.setId(rs.getInt("id")) ;
                    obj.setNom(rs.getString("nom")) ;
                    obj.setEmail(rs.getString("email"));
                    obj.setTel(rs.getString("tel"));
                    obj.setCreated_at(rs.getString("created_at"));

                    all.add(obj);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public UserModel getUserById(Integer id){
        // Select row by id
        String sql = "SELECT * " +
                "FROM user " +
                "WHERE id = ?";

        UserModel obj = null;

        try (var conn = MySQLConnection.connect();
            var stmt  = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Remplace le '?' par l'id
            var rs = stmt.executeQuery();

            if (rs.next()) {

                obj = new UserModel();

                obj.setId(rs.getInt("id")) ;
                obj.setNom(rs.getString("nom")) ;
                obj.setEmail(rs.getString("email"));
                obj.setTel(rs.getString("tel"));
                obj.setCreated_at(rs.getString("created_at"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return obj;
    }

    public boolean updateRowId(Integer id, String[] newValues){
        // Update

        if (newValues != null && newValues.length == 2) { // Ne mettre à jour la ligne que si le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id)
            String sql = "UPDATE user " +
                    "SET column1 = ?, column2 = ? " +
                    "WHERE id = ?";

            try (var conn = MySQLConnection.connect();
                 var stmt = conn.prepareStatement(sql)) {

                for (int i = 0 ; i < newValues.length ; i++) {
                    stmt.setString(i + 1, newValues[i]); // Définir chaque valeur dans la commande SQL
                }

                stmt.setInt(newValues.length + 1, id); // Définir l'id dans la commande SQL

                var rs = stmt.executeUpdate();
                return rs == 1 ; // Indique que la fonction a fonctionné si le nombre de ligne mises à jour est 1, et false sinon 

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false ;
            }
        }
        return false ; // Indique que la fonction n'a pas fonctionné, car le tableau ne respecte pas les deux conditions
    }

    public boolean deleteRowId(Integer id){
        // Delete

        if (getUserById(id) != null) {
            String sql = "DELETE " +
                    "FROM user " +
                    "WHERE id = ?";

            try (var conn = MySQLConnection.connect();
                 var stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id); // Remplace le '?' par l'id
                var rs = stmt.executeUpdate();
                return rs == 1 ; // Renvoie true si une ligne a bien été supprimée, false sinon

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false ; // Indique que la fonction n'a pas fonctionné car aucune ligne n'a été trouvée avec l'id
    }

    public boolean insertNewRow(Integer id, String[] values){
        // Insert

        if (values != null && values.length == 2 && getUserById(id) == null) { // Ne mettre à jour la ligne que si le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id), et qu'aucune ligne avec cet id n'existe déjà
            String sql = "INSERT INTO user " +
                    "VALUES (column1 = ?, column2 = ?) " ;

            try (var conn = MySQLConnection.connect();
                 var stmt = conn.prepareStatement(sql)) {

                for (int i = 0 ; i < values.length ; i++) {
                    stmt.setString(i + 1, values[i]); // Définir chaque valeur dans la commande SQL
                }

                var rs = stmt.executeUpdate();
                return rs == 1 ; // Indique que la fonction a fonctionné si le nombre de lignes inséré est 1, et false sinon

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false ;
            }
        }
        return false ; // Indique que la fonction n'a pas fonctionné, car le tableau ne respecte pas les deux conditions
    }

    // getter + setter for your attributs

    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTel() {
        return this.tel;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setId(int id) {
        if (id >= 0) { // Affecter un id seulement s'il n'est pas négatif
            this.id = id;
        }
        else {
            throw new IllegalArgumentException("L'id ne peut pas être négatif.");
        }
    }

    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;
        } else {
            throw new IllegalArgumentException("Le nom ne peut pas être null.");
        }
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("L'email ne peut pas être null.");
        }
    }

    public void setTel(String tel) {
        this.tel = tel; // Peut être null (voir BDD)
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at; // Peut être null (voir BDD)
    }
}
