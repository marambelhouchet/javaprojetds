import java.util.Objects;

public class User {
    private String nom;
    private String prenom;
    private String specialite;
    private String club;

    public User(String nom, String prenom, String specialite, String club) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.club = club;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getClub() {
        return club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nom, user.nom) &&
               Objects.equals(prenom, user.prenom) &&
               Objects.equals(specialite, user.specialite) &&
               Objects.equals(club, user.club);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, specialite, club);
    }
}
