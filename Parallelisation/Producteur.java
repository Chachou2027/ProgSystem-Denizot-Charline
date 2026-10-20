class Producteur implements Runnable {
    
	ProducteurConsommateur pc;
    
    public Producteur(ProducteurConsommateur pc) {
        this.pc = pc;

    }
    
    public void run() {
		int teteEcriture;
		try {
			for (int i = 0; i < 10; i++) {
				
				int produit = 10;
		
				// Attendre une place libre
				pc.getPlacesLibres().acquire();
				
				// Acquérir l'accès exclusif au buffer  
				pc.getMutexBuffer().acquire();
				
				/*
				Ajouter l'élément au buffer 
				teteEcriture = pc.getTeteEcriture();
				non respect de l'encapsulation : 
				pc.getBuffer()[teteEcriture] = produit;
				teteEcriture = (teteEcriture + 1) % pc.getBuffer().length;
				pc.setTeteEcriture(teteEcriture);
				*/
				
				// Pour respecter l'encapsulation
				pc.insererProduit(produit);
				
				// Libérer l'accès au buffer
				pc.getMutexBuffer().release();
				
				// Signaler qu'un élément est disponible
				pc.getElementsDisponibles().release();
            
			}
		} catch (InterruptedException e) {
			System.out.println("Le consommateur a été interrompu.");
		}
    }
	
	
	
}