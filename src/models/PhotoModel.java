package models;

import java.util.List;

public class PhotoModel {

    private Photo photo;

    public List<Photo> getAll() {
        return dao.PhotoDAO.getAll();
    }

    public Photo getPhotoById(Integer id) {
        return dao.PhotoDAO.getPhotoById(id);
    }

    public boolean updatePhotoById(Integer id, String[] newValues) {
        // Update

        Photo photo = getPhotoById(id); // Récupère l'id de la photo que l'on va modifier

        if (photo != null && newValues != null && newValues.length == 6) { // Ne mettre à jour la ligne que si l'photo est bien trouvé, que le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id)
            for (int i = 0; i < newValues.length; i++) {
                if (newValues[i] instanceof String) {
                    newValues[i] = newValues[i].trim();
                }
            }
            return dao.PhotoDAO.updatePhotoById(id, newValues);
        }
        return false;
    }

    public boolean deletePhotoById(Integer id) {
        // Delete

        if (getPhotoById(id) != null) {
            return dao.PhotoDAO.deletePhotoById(id);
        }
        return false;
    }

    public boolean createPhoto(Integer id, String[] values) {
        // Insert
        if (values != null && values.length == 2 && getPhotoById(id) == null) { // Ne mettre à jour la ligne que si le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id), et qu'aucune ligne avec cet id n'existe déjà
            return dao.PhotoDAO.createPhoto(values);
        }
        return false; // Indique que la fonction n'a pas fonctionné, car le tableau ne respecte pas les deux conditions
    }
}
