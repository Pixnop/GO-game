public class JeuDeGo {
    private PlateauDeJeu plateau;
    private int joueurActuel;

    public JeuDeGo(int taillePlateau) {
        plateau = new PlateauDeJeu();
        joueurActuel = 1;
    }

    public int getJoueurActuel() {
        return joueurActuel;
    }

    public void changerJoueurActuel() {
        joueurActuel = (joueurActuel == 1) ? 2 : 1;
    }

    public boolean estCoupValide(Pierre pierre) {
        // Vérifie si la pierre est dans les limites du plateau
        if (!plateau.estDansLimites(pierre.getPosition())) {
            return false;
        }

        // Vérifie si la case est vide
        if (plateau.getCase(pierre.getPosition()).estOccupee()) {
            return false;
        }

        // Vérifie si le coup est un suicide
        if (plateau.estSuicide(pierre)) {
            return false;
        }

        // Le coup est valide
        return true;
    }

    public void jouerCoup(Pierre pierre) {
        if (estCoupValide(pierre)) {
            plateau.poserPierre(pierre);
            changerJoueurActuel();
        } else {
            // Le coup n'est pas valide
            // Lancer une exception ou afficher un message d'erreur
        }
    }

    public boolean estFinDuJeu() {
        // Vérifie si les deux joueurs ont passé leur tour
        if (joueurActuel == 0) {
            return true;
        }

        // Vérifie si le plateau est rempli
        if (plateau.estRempli()) {
            return true;
        }

        return false;
    }

    // Autres méthodes de la classe JeuDeGo
}
