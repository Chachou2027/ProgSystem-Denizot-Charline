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
	
	
	public void writeToMemory(
        int fileType,
        int fileSize,
        long creationTime,
        long modificationTime,
        int[] directPointers,
        int indirectPointer,
        short permissions,
        int linkCount) {

        byte[] memory =
            memoryManager.getFilesystemMemory();

        int offset = getInodeOffset();

        // 1. Numéro d'inode
        Utils.writeInt(memory, offset, this.inodeNumber);
        offset += 4;
        
        // 2. Type
        Utils.writeInt(memory, offset, fileType);
        offset += 4;
        
        // 3. Taille
        Utils.writeInt(memory, offset, fileSize);
        offset += 4;
        
        // 4. Création
        Utils.writeLong(memory, offset, creationTime);
        offset += 8;
        
        // 5. Modification
        Utils.writeLong(memory, offset, modificationTime);
        offset += 8;
        
        // 6. 10 pointeurs directs
        for (int i = 0; i < directPointers.length; i++) {
            Utils.writeInt(memory, offset, directPointers[i]);
            offset += 4;
        }
        
        // 7. Pointeur indirect
        Utils.writeInt(memory, offset, indirectPointer);
        offset += 4;
        
        // 8. Permissions
        Utils.writeShort(memory, offset, permissions);
        offset += 2;
        
        // 9. Nombre de liens
        Utils.writeInt(memory, offset, linkCount);
        offset += 4;
    }
	
}