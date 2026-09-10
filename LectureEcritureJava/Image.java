import java.io.FileWriter;
import java.io.IOException;

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
}
