public class TestSemaphore {
	
	/** Nombre de threads producteur du scénario équilibré */
	public final static int NB_PROD_EQUILIBRE = 2;
	
	/** Nombre de threads consommateur du scénario équilibré */
	public final static int NB_CONSO_EQUILIBRE = 2;
	
	/** Nombre de threads producteur du scénario déséquilibré */
	public final static int NB_PROD_DESEQUILIBRE = 5;
	
	/** Nombre de threads consommateur du scénario désequilibré */
	public final static int NB_CONSO_DESEQUILIBRE = 2;
	
	/** Tableau stockant les threads producteur dans le scénario équilibré */
	private static Thread[] threadProducteurs = new Thread[NB_PROD_EQUILIBRE];
	
	/** Tableau stockant les threads consommateur dans le scénario équilibré */
	private static Thread[] threadConsommateur = new Thread[NB_CONSO_EQUILIBRE];
	
	/** Tableau stockant les threads producteur dans le scénario déséquilibré */
	private static Thread[] threadProdDesequilibre = new Thread[NB_PROD_DESEQUILIBRE];
	
	/** Tableau stockant les threads consommateur dans le scénario déséquilibré */
	private static Thread[] threadConDesequilibre = new Thread[NB_CONSO_DESEQUILIBRE];
	
	/** Instance de la classe ProducteurConsommateur pour créer les Threads */
	private static ProducteurConsommateur pc = new ProducteurConsommateur();
	
	
	public static void main(String args[]) {
		
		
		System.out.println("--- DEBUT DU SCENARIO EQUILIBRE ---\n 2 producteurs de 5 et 2 consommateurs de 5");
	
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
	
	/* 
	 * Crée des threads Producteur et les démarrent
	 * @param nbThread le nombre de threads à créer
	 * @param tabConso le tableau dans lequel stocker les threads
	 */
	public static void creerThreadProd(int nbThread, Thread[] tabProd) {
		for (int p = 0; p < nbThread; p++) {
			tabProd[p] = new Thread(new Producteur(pc));
			tabProd[p].start();
		}
	}
	
	/* 
	 * Crée des threads Consommateur et les démarrent
	 * @param nbThread le nombre de threads à créer
	 * @param tabConso le tableau dans lequel stocker les threads
	 */
	public static void creerThreadConso(int nbThread, Thread[] tabConso) {
		for (int c = 0; c < nbThread; c++) {
			tabConso[c] = new Thread(new Consommateur(pc));
			tabConso[c].start();
		}
	}
	
	/* 
	 * Attend la fin de l'exécution des threads Consommateur
	 * @param nbThread le nombre de threads à attendre
	 * @param tabConso le tableau dans lequel sont stockés les threads
	 * à attendre
	 */
	public static void attendreThreadConso(int nbThread, Thread[] tabConso) {
		for (int c = 0; c < nbThread; c++) {
			try {
				tabConso[c].join();
			} catch(InterruptedException e) {
				System.out.print("Erreur dans l'attente de fin des Threads");
			}
		}
	}
	
	/* 
	 * Attend la fin de l'exécution des threads Producteurs
	 * @param nbThread le nombre de threads à attendre
	 * @param tabConso le tableau dans lequel sont stockés les threads
	 * à attendre
	 */
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