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
        // Lire le type à offset + 4.
        return getInodeOffset() + 4;
    }

    public int getFileSize() {
        // Lire la taille à offset + 8.
        return getInodeOffset() + 8;
    }

    public int[] getDirectPointers() {

        byte[] memory = memoryManager.getFilesystemMemory();

        int[] pointers = new int[DIRECT_POINTERS];

        // TODO:
        // Lire les 10 pointeurs directs.
		for (int i = 0; i < DIRECT_POINTERS; i++) {
			pointers[i] = this.getInodeOffset() + 32 + 4*i;
		}

        return pointers;
    }
}