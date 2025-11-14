package models;

import java.util.List;

public class SeanceModel {

    private Seance seance;

    public List<Seance> getAll() {
        return dao.SeanceDAO.getAll();
    }

    public Seance getSeanceById(Integer id) {
        return dao.SeanceDAO.getSeanceById(id);
    }

    public boolean updateSeanceById(Integer id, String[] newValues) {
        // Update

        Seance seance = getSeanceById(id); // Récupère l'id de l'Seance que l'on va modifier

        if (Seance != null && newValues != null && newValues.length == 6) { // Ne mettre à jour la ligne que si l'Seance est bien trouvé, que le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id)
            for (int i = 0; i < newValues.length; i++) {
                if (newValues[i] instanceof String) {
                    newValues[i] = newValues[i].trim();
                }
            }
            return dao.SeanceDAO.updateSeanceById(id, newValues);
        }
        return false;
    }

    public boolean deleteSeanceById(Integer id) {
        // Delete

        if (getSeanceById(id) != null) {
            return dao.SeanceDAO.deleteSeanceById(id);
        }
        return false;
    }

    public boolean createSeance(Integer id, String[] values) {
        // Insert
        if (values != null && values.length == 2 && getSeanceById(id) == null) { // Ne mettre à jour la ligne que si le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id), et qu'aucune ligne avec cet id n'existe déjà
            return dao.SeanceDAO.createSeance(values);
        }
        return false; // Indique que la fonction n'a pas fonctionné, car le tableau ne respecte pas les deux conditions
    }
}
