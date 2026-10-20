class Consommateur implements Runnable {
    
	private ProducteurConsommateur pc;
	
	public Consommateur(ProducteurConsommateur pc) {
		this.pc = pc;
	}
    
     
    public void run() {
        for (int i = 0; i < 7; i++) {
            
			// Attendre qu'un élément soit disponible
			pc.getElementsDisponibles().acquire();
            
			// Acquérir l'accès exclusif au buffer
			pc.getMutexBuffer().acquire();
            
			// Retirer un élément du buffer
			Integer produitRetire = pc.retirerProduit();
			System.out.print("Produit consommé : " + produitRetire);
            
			//Libérer l'accès au buffer
			pc.getMutexBuffer().release();
            
			// Signaler qu'une place est libre
			pc.getPlacesLibres().release();
        }
    }
}