public class Pierre {
    private Couleur couleur;
    private Position position;

    // Constructeur pour initialiser une pierre avec une couleur
    public Pierre(Couleur couleur) {
        this.couleur = couleur;
    }

    // Méthode pour récupérer la couleur de la pierre
    public Couleur getCouleur() {
        return couleur;
    }

    // Méthode pour changer la couleur de la pierre
    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Position getPosition() {
        return position;
    }
    public boolean estOccupee() {
        return (this.couleur != null);
    }

}

