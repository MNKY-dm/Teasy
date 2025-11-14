package models;

import java.util.List;

public class EventModel {
    private Event Event;

    public List<Event> getAll() {
        return dao.EventDAO.getAll();
    }

    public Event getEventById(Integer id) {
        return dao.EventDAO.getEventById(id);
    }

    public boolean updateEventById(Integer id, String[] newValues) {
        // Update

        Event Event = getEventById(id); // Récupère l'id de l'Event que l'on va modifier

        if (Event != null && newValues != null && newValues.length == 5) { // Ne mettre à jour la ligne que si l'Event est bien trouvé, que le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id)
            for (int i = 0; i < newValues.length; i++) {
                if (newValues[i] instanceof String) {
                    newValues[i] = newValues[i].trim();
                }
            }
            return dao.EventDAO.updateEventById(id, newValues);
        }
        return false;
    }

    public boolean deleteEventById(Integer id) {
        // Delete

        if (getEventById(id) != null) {
            return dao.EventDAO.deleteEventById(id);
        }
        return false;
    }

    public boolean createEvent(Integer id, String[] values) {
        // Insert
        if (values != null && values.length == 2 && getEventById(id) == null) { // Ne mettre à jour la ligne que si le nombre de valeurs correspond au nombre de colonnes dans la table (sans compter id), et qu'aucune ligne avec cet id n'existe déjà
            return dao.EventDAO.createEvent(values);
        }
        return false; // Indique que la fonction n'a pas fonctionné, car le tableau ne respecte pas les deux conditions
    }
}
