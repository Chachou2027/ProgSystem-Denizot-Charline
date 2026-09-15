import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;

public class Image {
    /*
     * Les int sont utilisés car c'est  le type utilisé
     * de base par la JVM
     * On pourrait utiliser des bytes 
     */
	 
	 
	/** Largeur de l'image */
    private int width;
	
	/** Longueur de l'image */
    private int height;
	
	/** tableau représentant les pixels de l'image */
    // pixels[y][x][0=R,1=G,2=B]
    private int[][][] pixels; // pixels[y][x][0=R,1=G,2=B]

    public int getWidth() { 
		return width; 
	}
	
    public int getHeight() { 
		return height; 
	 }

    /**
     * Constructeur : initialise une image vide.
     */
    public Image(int width, int hauteur) {
        this.width = width;
        this.height = hauteur;
        pixels = new int[height][width][3];
    }

    /**
     * Définit la couleur d'un pixel à la position (x, y)
	 * @param x l'abscisse du pixel
	 * @param y l'ordonnée du pixel
	 * @param r la valeur de la couleur rouge
	 * @param g la valeur de la couleur vert
	 * @param b la valeur de la couleur bleu
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[y][x][0] = r;
            pixels[y][x][1] = g;
            pixels[y][x][2] = b;
        }
    }

    /**
     * Sauvegarde l'image au format texte PPM (P3)
	 * en écrivant la valeur de la couleur de chaque pixel
	 * dans le fichier
	 * @param filename le nom du fichier 
     */
    public void save_txt(String filename) throws IOException {
        try {
            FileWriter writer = new FileWriter(filename); 
            writer.write("P3\n");
            writer.write(this.getWidth() + " " + this.getHeight() + "\n");
            writer.write("255\n");
			
			// Ligne
			for (int pixOrd = 0; pixOrd < this.height; pixOrd++) {
				
				// Colonne
				for (int pixAbs = 0; pixAbs < this.width; pixAbs++) {
					
					// Ecriture de la couleur 
					for (int couleur = 0; couleur <= 2 ; couleur++) {
						writer.write(pixels[pixOrd][pixAbs][couleur] + " ");
					}
				}
				writer.write("\n");
			}
			
			writer.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }
	
	
	/**
     * Sauvegarde l'image au format texte binaire (P6)
	 * @param filename le nom du fichier 
     */
    public void saveBinary(String filename) throws IOException {
					
		/*
		 * height * width = nombre de pixels
		 * nb pixel * 3 = nb de byte total car on a 3 couleurs
		 */
		byte[] aEcrire = new byte[this.getWidth() * this.getHeight() * 3];
		byte couleurConvertie = 0;
			
		// Pour se déplacer dans le grand tableau de byte
		int numeroCouleur = 0;
		int couleurPixel;
		String header = "P6\n" + this.getWidth() + " " + this.getHeight() + "\n255\n"; 
			
        try {
			
			FileOutputStream ecritureBinaire = new FileOutputStream(filename);
			ecritureBinaire.write(header.getBytes());
			
			for (int pixOrd = 0; pixOrd < this.height; pixOrd++) {
				for (int pixAbs = 0; pixAbs < this.width; pixAbs++) {
					for (int couleur = 0; couleur <= 2 ; couleur++) {
						couleurPixel = pixels[pixOrd][pixAbs][couleur]; // R, G ou B
						
						// Conversion en unsigned byte : 
						couleurConvertie = (byte) (couleurPixel & 0xFF);
						aEcrire[numeroCouleur] = couleurConvertie;
						numeroCouleur++;
					}
				}
			}
			
			/* Chaque write provoque un appel system et donc une attente matérielle
			 * Pendant l'appel système, le programme est en pause. Le noyau attend le 
			 * que matériel finisse le traitement et il donne la main à d'autres processus
			 * (Context Switch). Réduire les write améliore les performances car
			 * moins d'appels systèmes donc moins de pauses du programme
			*/
			
			ecritureBinaire.write(aEcrire);
			ecritureBinaire.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }
	
	
}
