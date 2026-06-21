package models.enums;

public enum SeanceStatus {
    DISPONIBLE,
    COMPLET,
    ANNULE;

    @Override
    public String toString() {
        return switch (this) {
            case DISPONIBLE -> "disponible";
            case COMPLET    -> "complet";
            case ANNULE     -> "annulé";
        };
    }
}
