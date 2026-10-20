
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
    
	public final static Thread[] threads = new Thread[NB_THREADS];
    
    public static void main(String[] args) {
		
		int valAttendue = NB_THREADS * 1111111;
		double nbReussite = 0;
		int maxi = 0;
		int mini = Integer.MAX_VALUE;
		double pourcentageReussite = 0.0;
		int valGlobale = 0;
		
		
        for (int i = 0; i < NB_TESTS; i++) {
            CompteurDangereux.resetCompteur();
			
			for (int t = 0; t < NB_THREADS; t++) {
			threads[t] = new Thread(new CompteurDangereux.Incrementeur("" + t, 1111111));
		    }
			
            for (int j = 0; j < NB_THREADS; j++) {
                 threads[j].start();
            }
        
            for (int k = 0; k < NB_THREADS; k++) {
                try {
                   threads[k].join(); 
                } catch(InterruptedException e) {
                    System.err.print(e.getMessage());
                    System.out.print("Erreur dans l'attente de fin de tous les Threads");
                }
            } 
			
			valGlobale = CompteurDangereux.getCompteur();
			
			if (valGlobale == valAttendue) {
				nbReussite++;
			}
			
			if (valGlobale > maxi) {
				maxi = valGlobale;
			}
			
			if (valGlobale < mini) {
				mini = valGlobale;
			}
        }
		
		pourcentageReussite = (nbReussite * 100 / NB_TESTS);
		System.out.println("-----------AFFICHAGE STATISTIQUES---------");
		
		System.out.println("Nombre de réussite sur " + NB_TESTS + " : " + nbReussite);
		System.out.println("Pourcentage de réussite sur " + NB_TESTS + " : " + pourcentageReussite + "%");
		System.out.println("Valeur minimale obtenue sur " + NB_TESTS + " : " + mini);
		System.out.println("Valeur maximale obtenue sur " + NB_TESTS + " : " + maxi);
  
    }  
    
    // Faire un new Thread qui prend en paramètre le runnable, , ,
}
    