import java.util.ArrayList;
import java.util.List;

public class PlateauDeJeu {
    private final int taille = 19; // taille par défaut du plateau de jeu
    private Pierre[][] grille; // tableau pour stocker les pierres

    // Constructeur pour initialiser le plateau de jeu avec des pierres vides
    public PlateauDeJeu() {
        grille = new Pierre[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = new Pierre(Couleur.VIDE);
            }
        }
    }

    // Méthode pour mettre à jour le plateau avec une nouvelle pierre
    public void mettreAJourPlateau(Position position, Couleur couleur) {
        grille[position.getX()][position.getY()] = new Pierre(couleur);
    }

    // Méthode pour récupérer la pierre à une certaine position
    public Pierre getPierre(Position position) {
        return grille[position.getX()][position.getY()];
    }

    // Méthode pour récupérer la taille du plateau
    public int getTaille() {
        return taille;
    }

    public boolean estDansLimites(Position position) {
        int x = position.getX();
        int y = position.getY();

        return x >= 0 && x < taille && y >= 0 && y < taille;
    }

    public Pierre getCase(Position position) {
        return grille[position.getX()][position.getY()];
    }

    public boolean estSuicide(Pierre pierre, Position position, Couleur couleur) {
        // On ajoute la pierre sur le plateau de jeu
        ajouterPierre(pierre, position);

        // On vérifie si la pierre n'est pas en état de capture
        if (!estCapturee(pierre, couleur)) {
            // On vérifie si la pierre entoure une zone vide
            if (entoureZoneVide(position, couleur)) {
                // La pierre est un suicide
                retirerPierre(position);
                return true;
            }
        }

        // La pierre n'est pas un suicide
        retirerPierre(position);
        return false;
    }

    private boolean estCapturee(Pierre pierre, Couleur couleur) {
        // Récupération de la position de la pierre
        Position position = pierre.getPosition();

        // Vérification des pierres adjacentes
        List<Pierre> pierresAdjacentes = new ArrayList<>();
        if (estDansLimites(new Position(position.getX() - 1, position.getY()))) {
            pierresAdjacentes.add(getCase(new Position(position.getX() - 1, position.getY())));
        }
        if (estDansLimites(new Position(position.getX() + 1, position.getY()))) {
            pierresAdjacentes.add(getCase(new Position(position.getX() + 1, position.getY())));
        }
        if (estDansLimites(new Position(position.getX(), position.getY() - 1))) {
            pierresAdjacentes.add(getCase(new Position(position.getX(), position.getY() - 1)));
        }
        if (estDansLimites(new Position(position.getX(), position.getY() + 1))) {
            pierresAdjacentes.add(getCase(new Position(position.getX(), position.getY() + 1)));
        }

        // Vérification si la pierre est entourée par l'adversaire
        boolean estCapturee = true;
        for (Pierre adjacente : pierresAdjacentes) {
            if (adjacente.getCouleur() == couleur || adjacente.getCouleur() == null) {
                estCapturee = false;
                break;
            }
        }

        return estCapturee;
    }

    public void ajouterPierre(Pierre pierre, Position position) {
        int ligne = position.getX();
        int colonne = position.getY();
        grille[ligne][colonne] = pierre;
    }

    public boolean entoureZoneVide(Position position, Set<Position> positionsVisitees) {
        if (!estDansLimites(position) || positionsVisitees.contains(position)) {
            return false;
        }
        Pierre pierre = getCase(position);
        if (pierre == null) {
            return true;
        }
        if (pierre.getCouleur() != null) {
            return false;
        }
        positionsVisitees.add(position);
        return entoureZoneVide(new Position(position.getLigne() - 1, position.getColonne()), positionsVisitees)
                || entoureZoneVide(new Position(position.getLigne() + 1, position.getColonne()), positionsVisitees)
                || entoureZoneVide(new Position(position.getLigne(), position.getColonne() - 1), positionsVisitees)
                || entoureZoneVide(new Position(position.getLigne(), position.getColonne() + 1), positionsVisitees);
    }




}

