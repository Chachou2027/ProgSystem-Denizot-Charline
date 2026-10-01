import java.util.*;

public class VirtualFileSystem {

    private MemoryManager memoryManager;

    public VirtualFileSystem() {
        this.memoryManager =
                new MemoryManager();
    }

    private int allocateInode() {

        byte[] memory =
                memoryManager.getFilesystemMemory();

        // TODO:
        // Parcourir les inodes de 0 à MAX_INODES - 1.
        for (int numIndode = 0; numIndode < memoryManager.MAX_INODES; numIndode++) {
            Inode inode = new Inode(memoryManager, numIndode);
            if (inode.getFileType() == 0) {
                return numIndode;
            }
        }
        // Identifier le premier inode libre.
        // Retourner son numéro.

        return -1;
    }

    public boolean createFile(
            String directory,
            String filename) {

        int inodeNum = allocateInode();

        if (inodeNum == -1) {
            return false;
        }

        // TODO:
        // Construire l'inode.
        Inode inode = new Inode(memoryManager, inodeNum);
        // L'initialiser comme fichier vide.
        inode.writeToMemory(1, 0, System.currentTimeMillis(), 
                            System.currentTimeMillis(), 
                            new int[10], 0, (short) 0644, 1);

        return true;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }
}