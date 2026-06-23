package models.enums;

public enum SeanceStatus {
    DISPONIBLE,
    COMPLET,
    EXPIRE,
    ANNULE;

    @Override
    public String toString() {
        return switch (this) {
            case DISPONIBLE -> "disponible";
            case COMPLET    -> "complet";
            case EXPIRE    -> "expiré";
            case ANNULE     -> "annulé";
        };
    }
}
