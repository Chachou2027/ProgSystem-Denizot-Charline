public class Inode {

    private MemoryManager memoryManager;
    private int inodeNumber;

    public static final int INODE_SIZE = 128;
    public static final int DIRECT_POINTERS = 10;

    public Inode(MemoryManager memoryManager, int inodeNumber) {
        this.memoryManager = memoryManager;
        this.inodeNumber = inodeNumber;
    }

    public int getInodeOffset() {
		// Offset(N) = INODE_TABLE_OFFSET + N * INODE_SIZE
        return 1024 + this.inodeNumber * INODE_SIZE;
    }

    public int getFileType() {
        int offsetFileType = getInodeOffset() + 4;// Lire le type à offset + 4.
        return Utils.readInt(memoryManager.getFilesystemMemory(), offsetFileType);
    }

    public int getFileSize() {
        // Lire la taille à offset + 8.
		int offsetFileSize = getInodeOffset() + 8;
        return Utils.readInt(memoryManager.getFilesystemMemory(), offsetFileSize);
    }

    public int[] getDirectPointers() {

        byte[] memory = memoryManager.getFilesystemMemory();

        int[] pointers = new int[DIRECT_POINTERS];

        // Lire les 10 pointeurs directs.
		for (int i = 0; i < DIRECT_POINTERS; i++) {
			int offsetPointeur = this.getInodeOffset() + 28 + 4*i;
			pointers[i] = Utils.readInt(memory, offsetPointeur);
		}

        return pointers;
    }
}