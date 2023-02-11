
public class ReglesDuJeu {
    private PlateauDeJeu plateau;

    // Constructeur pour initialiser la classe avec un plateau de jeu
    public ReglesDuJeu() {
        this.plateau = plateau;
    }

    // Méthode pour vérifier si une certaine position est valide
    public boolean estPositionValide(Position position) {
        int x = position.getX();
        int y = position.getY();
        int taillePlateau = plateau.getTaille();
        return (x >= 0 && x < taillePlateau && y >= 0 && y < taillePlateau);
    }

    // Méthode pour vérifier si une certaine position est libre
    public boolean estPositionLibre(Position position) {
        return plateau.getPierre(position).getCouleur() == Couleur.VIDE;
    }

    // Méthode pour vérifier si une certaine position est entourée
    public boolean estPositionEntouree(Position position) {
        Couleur couleur = plateau.getPierre(position).getCouleur();
        if (couleur == Couleur.VIDE) {
            return false;
        }
        int x = position.getX();
        int y = position.getY();
        int taillePlateau = plateau.getTaille();
        if ((x > 0 && plateau.getPierre(new Position(x-1, y)).getCouleur() != couleur) ||
                (x < taillePlateau-1 && plateau.getPierre(new Position(x+1, y)).getCouleur() != couleur) ||
                (y > 0 && plateau.getPierre(new Position(x, y-1)).getCouleur() != couleur) ||
                (y < taillePlateau-1 && plateau.getPierre(new Position(x, y+1)).getCouleur() != couleur)) {
            return true;
        }
        return false;
    }

    // Méthode pour vérifier si une certaine position est un point d'intersection du plateau
    public boolean estIntersection(Position position) {
        int x = position.getX();
        int y = position.getY();
        return (x % 2 == 0 && y % 2 == 0 && estPositionValide(position));
    }


}

