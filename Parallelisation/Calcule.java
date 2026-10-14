/**
 * Représente un Thread qui va effectuer un calcul
 * Utilisation de la classe Thread de Java
 * Chaque thread aura sa propre copie de ces valeurs, stockées dans ses attributs privés.
 */

public class Calcule extends Thread {
    // TODO: Déclarer les attributs nécessaires
    /* Représente le résultat de la deuxieme boucle */
    private long resultat;
    
    /** la donnée sur laquel le thread travail */
    private int valeur;
    
    /** l'intensité du calcul du thread */
    private int multiplicateur;
    
    
    /** index pour débugging et affichage */
    private int index;
    
    
    /**
     * Crée un thread, et le configure avec ses données de travail
     * @param valeur la valeur à traiter dans le calcul
     * @param multiplicateur entier qui contrôle l'intensité du calcul
     * (nombre de tour de la deuxieme boucle de CalculSequentiel)
     * @param index sert pour le debugging et l'affichage
     */
    public Calcule(int valeur, int multiplicateur, int index) {
        this.valeur = valeur;
        this.multiplicateur = multiplicateur;
        this.index = index;
        this.resultat = 0;
        
    }
    
    
    /**
     * Programme principal du thread
     * Quand on appelle start() java crée un nouveau fil d'éxecution et appelle
     * run()
     */
    @Override 
    public void run() {
        System.out.println("=== LANCEMENT DU THREAD ===" + index);
        for (int i = index; i < multiplicateur; i++) {
            resultat += valeur * valeur + valeur;
        }

        System.out.println("Traitement de " + valeur + " terminé : " + resultat);
    }
    
    
    /**
     * Permet de rcupérer le résultat du calcul effectué 
     * par le Thread
     */     
    public long getResultat() {
        return resultat;
    }
     
    
}