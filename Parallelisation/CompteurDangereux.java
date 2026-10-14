public class CompteurDangereux {
    private static int compteurGlobal = 0;  // Variable partagée MODIFIABLE

    static class Incrementeur implements Runnable {
        private final String nom;
        private final int nombreIncrements;

        public Incrementeur(String nom, int nombreIncrements) {
            this.nom = nom;
            this.nombreIncrements = nombreIncrements;
        }

        @Override
        public void run() {
            for (int i = 0; i < nombreIncrements; i++) {
                // TODO: Expliquer pourquoi cette ligne pose problème
                /* Tous le monde peut modifier la même variable donc aucune
                 * garantit sur le résultat final de compteurGlobal car
                 * chaque Thread effectue un calcul différent
                 */
                compteurGlobal++;  // RACE CONDITION ICI !
            }
            System.out.println(nom + " terminé. Compteur vu : " + compteurGlobal);
        }
    }

    public static int getCompteur() {
        return compteurGlobal;
    }

    public static void resetCompteur() {
        compteurGlobal = 0;
    }
}