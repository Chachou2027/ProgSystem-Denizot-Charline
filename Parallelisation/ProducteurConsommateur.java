import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;

public class ProducteurConsommateur {

    private static final int TAILLE_BUFFER = 5;
    private final Integer[] buffer = new Integer[TAILLE_BUFFER];

    //  Au début, le buffer est vide donc 5 places libres
    private final Semaphore placesLibres = new Semaphore(TAILLE_BUFFER);
    
    // 0 - Au début, aucun élément à consommer
    private final Semaphore elementsDisponibles = new Semaphore(0);  
    
    // 1 - Un seul thread peut manipuler le buffer à la fois (mutex classique)
    private final Semaphore mutexBuffer = new Semaphore(1); // quel erreur ici ? vous pouvez le résoudre plus tard
    
    private int teteLecture = 0;
    
    private int teteEcriture = 0;
    
    
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
    
    
}