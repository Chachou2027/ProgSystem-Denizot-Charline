class Producteur implements Runnable {
    
    //  Au début, le buffer est vide donc 5 places libres
    private final Semaphore placesLibres;
    // 0 - Au début, aucun élément à consommer
    private final Semaphore elementsDisponibles; 
    
    // 1 - Un seul thread peut manipuler le buffer à la fois (mutex classique)
    private final Semaphore mutexBuffer; // quel erreur ici ? vous pouvez le résoudre plus tard
    
    private final Integer[] b;
    
    
    public Producteur(Semaphore pl, Semaphore ed, Semaphore mb, Integer[] buffer) {
        placesLibres = pl;
        elementsDisponibles = ed;
        mutexBuffer = mb;
        b = buffer;
    }
    
    
    //  int i = i++ % tailleBuffer
    // int teteLecture = teteLecture++ %n
    
    public void run() {
        for (int i = 0; i < 10; i++) {
            int produit = genererProduit();

            // TODO: Attendre une place libre
            placesLibres.acquire();
            // TODO: Acquérir l'accès exclusif au buffer  
            mutexBuffer.acquire();
            
            // TODO: Ajouter l'élément au buffer
            for (int teteEcriture = teteLecture++ % tailleBuffer.length; teteEcriture < buffer.length; teteEcriture++) {
                if (buffer[teteEcriture] != null) {
                    buffer[teteEcriture] = produit;
                }
            }
            
            placesLibres.release();
            // TODO: Libérer l'accès au buffer
            placesLibres.release();
            // TODO: Signaler qu'un élément est disponible
            mutexBuffer.release();
        }
    }
}