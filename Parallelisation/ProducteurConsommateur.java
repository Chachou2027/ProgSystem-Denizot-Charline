import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;

public class ProducteurConsommateur {

    private static final int TAILLE_BUFFER = 5;
    private final Integer[] buffer = new Integer[TAILLE_BUFFER];

    /* Au début, le buffer est vide donc 5 places libres */
    private final Semaphore placesLibres = new Semaphore(TAILLE_BUFFER);
    
    /* 0 - Au début, aucun élément à consommer */
    private final Semaphore elementsDisponibles = new Semaphore(0);  
    
    /* Un seul thread peut manipuler le buffer à la fois (mutex classique) */
    private final Semaphore mutexBuffer = new Semaphore(1); // quel erreur ici ? vous pouvez le résoudre plus tard
    
	/* Indice d'écriture pour les consommateurs */
    private int teteLecture = 0;
    
	/* Indice d'écriture pour les producteurs */
    private int teteEcriture = 0;
	
	/* Monitoring des résultats finaux */
	private int nbDeProduitsProduits = 0;
	
	private int nbProduitsConsommes = 0;
    	
    
    public Semaphore getPlacesLibres() {
        return placesLibres;
    }
    
    public Semaphore getElementsDisponibles() {
        return elementsDisponibles;
    }
    
    public Semaphore getMutexBuffer() {
        return mutexBuffer;
    }
    
    
    public Integer[] getBuffer() {
        return buffer;
    }
	
	public int getTeteLecture() {
		return teteLecture;
	}
    
	public int getTeteEcriture() {
		return teteEcriture;
	}
	
	
	public void insererProduit(int produit) {
		buffer[teteEcriture] = produit;
		teteEcriture = (teteEcriture + 1) %  TAILLE_BUFFER;
		nbDeProduitsProduits++;
		System.out.println("[PRODUCTION] Produit inséré. Total produits produits : " + nbDeProduitsProduits);

	}
	
	public Integer retirerProduit() {
		Integer valeurLue = buffer[teteLecture];
		buffer[teteLecture] = null;
		teteLecture = (teteLecture + 1) % TAILLE_BUFFER;
		nbProduitsConsommes++;
		System.out.println("[CONSOMMATION] Produit " + valeurLue + " consommé. Total consommé : " + nbProduitsConsommes);
		return valeurLue;
	}
	
	public int getNbDeProduitsProduits() {
		return nbDeProduitsProduits;
	}

	public int getNbProduitsConsommes() {
		return nbProduitsConsommes;
	}
    
}