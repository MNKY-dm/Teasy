package models;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

public class UserModel {

    private User user;

    public List<User> getAll() {
        return dao.UserDAO.getAll();
    }

    public User getUserById(Integer id) {
        return dao.UserDAO.getUserById(id);
    }

    public boolean updateUserById(Integer id, String[] newValues) {
        // Update

        User user = getUserById(id); // Récupère l'id de l'user que l'on va modifier

        if (user != null && newValues != null && newValues.length == 6) { // Ne mettre à jour la ligne que si l'user est bien trouvé, que le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id)
            for (int i = 0; i < newValues.length; i++) {
                if (newValues[i] instanceof String) {
                    newValues[i] = newValues[i].trim();
                }
            }
            return dao.UserDAO.updateUserById(id, newValues);
        }
        return false;
    }

    public boolean deleteUserById(Integer id) {
        // Delete

        if (getUserById(id) != null) {
            return dao.UserDAO.deleteUserById(id);
        }
        return false;
    }

    public boolean createUser(Integer id, String[] values) {
        // Insert
        if (values != null && values.length == 2 && getUserById(id) == null) { // Ne mettre à jour la ligne que si le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id), et qu'aucune ligne avec cet id n'existe déjà
            return dao.UserDAO.createUser(values);
        }
        return false; // Indique que la fonction n'a pas fonctionné, car le tableau ne respecte pas les deux conditions
    }
}