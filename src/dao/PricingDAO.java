package dao;

import models.Photo;
import models.Pricing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PricingDAO implements DAO {

    public static List<Pricing> getAll(){
        String sql = "SELECT * " +
                "FROM pricing";

        List<Pricing> all = new ArrayList<>();

        try (var conn = MySQLConnection.getConnection()) {
            var stmt  = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Pricing pricing = new Pricing(rs.getInt("seance_id"),
                        rs.getFloat("price_1"),
                        rs.getFloat("price_2"),
                        rs.getFloat("price_3"));

                all.add(pricing);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return all;
    }

    public static Pricing getRowById(Integer id) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM pricing " +
                "WHERE id = ?";

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

    public static Pricing getRowBySeanceId(Integer seanceId) {
        // Select row by id
        String sql = "SELECT * " +
                "FROM pricing " +
                "WHERE seance_id = ?";

        Pricing pricing = null;

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, seanceId); // Remplace le '?' par l'id
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

    public static boolean updatePricing(Pricing pricing){
        // Update
        String sql = "UPDATE pricing " +
                "SET seance_id = ?, price_1 = ?,  price_2 = ?, price_3 = ? " +
                "WHERE id = ?";

        int pricingId = pricing.getId();

        if (getRowById(pricingId) != null) {
            try (var conn = MySQLConnection.getConnection();
                 var stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, pricing.getSeance_id());
                stmt.setFloat(2, pricing.getPrice1());
                stmt.setFloat(3, pricing.getPrice2());
                stmt.setFloat(4, pricing.getPrice3());
                stmt.setInt(5, pricing.getId());

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
                "FROM pricing " +
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

    public static boolean createPhoto(Photo photo) {
        String sql = "INSERT INTO Photo " +
                "VALUES (event_id = ?, url = ?,  alt = ?, type = ?, created_at = ? ) ";

        try (var conn = MySQLConnection.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, photo.getEvent_id());
            stmt.setString(2, photo.getUrl());
            stmt.setString(3, photo.getAlt());
            stmt.setString(4, photo.getType());
            stmt.setTimestamp(5, photo.getCreated_at());

            var rs = stmt.executeUpdate();
            return rs == 1; // Indique que la fonction a fonctionné si le nombre de lignes insérées est 1, et false sinon

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
