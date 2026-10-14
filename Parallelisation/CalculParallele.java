/**
 * Le rôle de cette classe est d'orchestrer plusieurs Threads
 */
public class CalculParallele {
    
    /** Numéros des Threads */
    private static final int[] DONNEES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int MULTIPLICATEUR = 1000;    

    public static void main(String[] args) {
        System.out.println("=== Calcul Parallele ===");

        /** On doit garder une référence de chaque Thread pour pouvoir les controller 
         * Tableau de taille 10 pour stocker les refs de 10 Threads
         */
        Calcule[] threads = new Calcule[DONNEES.length];

        long debut = System.nanoTime();
        long sommeTotal = 0;

        /* La boucle va créer les 10 threads qui vont executer un calcul différents
         * Grâce aux paramètres des constructeurs et a DONNEES et MULTIPLICATEUR
         * On démarre chaque Thread avec start()
         */
        for (int i = 0; i < DONNEES.length; i++) {
            
            /* Créer les instances de Calcule et stocker dans le tableau de Threads */
            threads[i] = new Calcule(DONNEES[i], MULTIPLICATEUR, i);
            threads[i].start();
        }
        
        
        /* 
         * La boucle va attendre le résultat des 10 threads -> parallelisation
         */
        for (int i = 0; i < DONNEES.length; i++) {
            try {
               threads[i].join();
            } catch(InterruptedException e) {
                System.err.print(e.getMessage());
                System.out.print("Erreur dans l'attente de fin de tous les Threads");
            }
            sommeTotal += threads[i].getResultat();
            
        }
        

        long duree = (System.nanoTime() - debut) / 1_000_000;
        System.out.println("Résultat total : " + sommeTotal);
        System.out.println("Durée : " + duree + " ms");
    }
}