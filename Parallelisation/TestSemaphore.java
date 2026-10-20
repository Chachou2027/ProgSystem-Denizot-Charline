public class TestSemaphore {
	
	// Test scénario équilibré 2 producteurs 3 consommateurs
	public final static int NB_PROD_EQUILIBRE = 2;
	public final static int NB_CONSO_EQUILIBRE = 2;
	
	public final static int NB_PROD_DESEQUILIBRE = 5;
	public final static int NB_CONSO_DESEQUILIBRE = 2;
		
	private static Thread[] threadProducteurs = new Thread[NB_PROD_EQUILIBRE];
	private static Thread[] threadConsommateur = new Thread[NB_CONSO_EQUILIBRE];
	
	private static Thread[] threadProdDesequilibre = new Thread[NB_PROD_DESEQUILIBRE];
	private static Thread[] threadConDesequilibre = new Thread[NB_CONSO_DESEQUILIBRE];
	
	private static ProducteurConsommateur pc = new ProducteurConsommateur();
		
	private int nbProduit = 0;
	
	public static void main(String args[]) {
		
		
		System.out.println("--- DEBUT DU SCENARIO EQUILIBRE ---\n 2 producteurs de 5 et deux consommateurs de 5");
	
		creerThreadProd(NB_PROD_EQUILIBRE, threadProducteurs);
		creerThreadConso(NB_CONSO_EQUILIBRE, threadConsommateur);
		attendreThreadProd(NB_PROD_EQUILIBRE, threadProducteurs);
		attendreThreadConso(NB_CONSO_EQUILIBRE, threadConsommateur);
		
		System.out.println("\n--- FIN DU SCENARIO EQUILIBRE ---\n");
		System.out.println("Total produits générés : " + pc.getNbDeProduitsProduits());
		System.out.println("Total produits consommés : " + pc.getNbProduitsConsommes());
		
		
		System.out.println("\n--- DEBUT DU SCENARIO DESEQUILIBRE ---\n 5 producteurs de 5 produits et 2 consommateurs de 5\n");
	
		creerThreadProd(NB_PROD_DESEQUILIBRE, threadProdDesequilibre);
		creerThreadConso(NB_CONSO_DESEQUILIBRE, threadConDesequilibre);
		attendreThreadProd(NB_PROD_DESEQUILIBRE, threadProdDesequilibre);
		attendreThreadConso(NB_CONSO_DESEQUILIBRE, threadConDesequilibre);
		
		System.out.println("\n--- FIN DU SCENARIO DESEQUILIBRE ---");
		System.out.println("Total produits générés : " + pc.getNbDeProduitsProduits());
		System.out.println("Total produits consommés : " + pc.getNbProduitsConsommes());
		
	}
	
	public static void creerThreadProd(int nbThread, Thread[] tabProd) {
		for (int p = 0; p < nbThread; p++) {
			tabProd[p] = new Thread(new Producteur(pc));
			tabProd[p].start();
		}
	}
	
	public static void creerThreadConso(int nbThread, Thread[] tabConso) {
		for (int c = 0; c < nbThread; c++) {
			tabConso[c] = new Thread(new Consommateur(pc));
			tabConso[c].start();
		}
	}
	
	public static void attendreThreadConso(int nbThread, Thread[] tabConso) {
		for (int c = 0; c < nbThread; c++) {
			try {
				tabConso[c].join();
			} catch(InterruptedException e) {
				System.out.print("Erreur dans l'attente de fin des Threads");
			}
		}
	}
	
	public static void attendreThreadProd(int nbThread, Thread[] tabProd) {
		for (int p = 0; p < nbThread; p++) {
			try {
				tabProd[p].join();
			} catch(InterruptedException e) {
				System.out.print("Erreur dans l'attente de fin des Threads");
			}
		}
	}

	
}