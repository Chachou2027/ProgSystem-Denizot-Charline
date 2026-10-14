
/**
 * La classe vérifie le non déterminisme des race conditions
 * Plus on crée de Threads qui font des calculs complexes
 * plus les erreurs apparaissent et plus il y a de chances que
 * plusieurs Threads modifient simultanément la variable globale
 */
public class TestCompteur {
    
    /** Le nombre de fois que le test est effectué */
    public final static int NB_TESTS = 50;
    
    public final static int NB_THREADS = 9;
    
    public final static CompteurDangereux.Incrementeur threads = new CompteurDangereux.Incrementeur[9];
    
    public static void main(String[] args) {
        for (int i = 0; i < NB_TESTS; i++) {
            CompteurDangereux.resetCompteur();
           for (int j = 0; j <= NB_THREADS; j++) {
                threads[j].run();
            }
        
            for (int k = 0; k < NB_THREADS; i++) {
                try {
                   threads[k].join(); 
                } catch(InterruptedException e) {
                    System.err.print(e.getMessage());
                    System.out.print("Erreur dans l'attente de fin de tous les Threads");
                }
            } 
        }  
    }  
    
    // Faire un new Thread qui prend en paramètre le runnable, , ,
}
    