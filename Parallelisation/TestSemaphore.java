public class TestSemaphore {
	
	// Test scénario équilibré 2 producteurs 3 consommateurs
	public final static int NB_PROD_EQUILIBRE = 2;
	public final static int NB_CONSO_EQUILIBRE = 2;
		
	private static Thread[] threadProducteurs = new Thread[NB_PROD_EQUILIBRE];
	private static Thread[] threadConsommateur = new Thread[NB_CONSO_EQUILIBRE];
	private static ProducteurConsommateur pc = new ProducteurConsommateur();
		
	private int nbProduit = 0;
	
	public static void main(String args[]) {
		
		System.out.println("--- DEBUT DU SCENARIO EQUILIBRE ---\n 2 producteurs de 10 et deux consommateurs de 10");
	
		creerThreadProd(NB_PROD_EQUILIBRE);
		creerThreadConso(NB_CONSO_EQUILIBRE);
		attendreThreadProd(NB_PROD_EQUILIBRE);
		attendreThreadConso(NB_CONSO_EQUILIBRE);
		
		System.out.println("--- FIN DU SCENARIO EQUILIBRE ---");
		System.out.println("Total produits générés : " + pc.getNbDeProduitsProduits());
		System.out.println("Total produits consommés : " + pc.getNbProduitsConsommes());
		
	}
	
	public static void creerThreadProd(int nbThread) {
		for (int p = 0; p < nbThread; p++) {
			threadProducteurs[p] = new Thread(new Producteur(pc));
			threadProducteurs[p].start();
		}
	}
	
	public static void creerThreadConso(int nbThread) {
		for (int c = 0; c < nbThread; c++) {
			threadConsommateur[c] = new Thread(new Consommateur(pc));
			threadConsommateur[c].start();
		}
	}
	
	public static void attendreThreadConso(int nbThread) {
		for (int c = 0; c < nbThread; c++) {
			try {
				threadConsommateur[c].join();
			} catch(InterruptedException e) {
				System.out.print("Erreur dans l'attente de fin des Threads");
			}
		}
	}
	
	public static void attendreThreadProd(int nbThread) {
		for (int p = 0; p < nbThread; p++) {
			try {
				threadProducteurs[p].join();
			} catch(InterruptedException e) {
				System.out.print("Erreur dans l'attente de fin des Threads");
			}
		}
	}

	
}