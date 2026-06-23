package dao;

import java.util.List;

public interface DAO {
    public static List<models.Model> getAll() {
        return null;
    }

    public static models.Model getRowById(Integer id) {
        return null;
    }

    public static boolean updateRowById(models.Model newModel) {
        return false;
    }

    public static boolean deleteRowById(Integer id) {
        return false;
    }

    public static boolean insertNewRow(models.Model model) {
        return false;
    }
}
