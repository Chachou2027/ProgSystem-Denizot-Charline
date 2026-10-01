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
    
    
    public boolean writeFile(
        int inodeNum,
        byte[] data) {

        int blocksNeeded =
                (data.length
                + MemoryManager.BLOCK_SIZE - 1)
                / MemoryManager.BLOCK_SIZE;

        if (blocksNeeded > Inode.DIRECT_POINTERS) {
            return false;
        }

        int[] blockPointers =
                new int[Inode.DIRECT_POINTERS];

        // Allouer blocksNeeded blocs.
        for (int i = 0; i < blocksNeeded; i++) {
            int numBlocAloue = memoryManager.allocateBlock();
            if (numBlocAloue != -1) {
                blockPointers[i] = numBlocAloue;
            } else {
                // Plus de place ds la mémoire, l'allocation échoue
                return false;
            }
        }

        byte[] memory =
                memoryManager.getFilesystemMemory();

        int bytesRemaining =
                data.length;

        int dataSrcOffset = 0;

        // TODO:
        // Pour chaque bloc :
        for (int i = 0; i < blocksNeeded; i++) {
            
            // - calculer la quantité à copier ;
            int idDebutACopier =  i * memoryManager.BLOCK_SIZE;
            int idFinACopier = Math.min(memoryManager.BLOCK_SIZE, bytesRemaining);
            int qteACopier = idFinACopier - idDebutACopier;
            // - récupérer le numéro du bloc ;
            int numBloc = blockPointers[i];
            
            // - calculer son offset physique ;
            int offsetPhysique = numBloc * memoryManager.BLOCK_SIZE;
            
            // - copier les données.
            System.arraycopy(data, i * memoryManager.BLOCK_SIZE, memory, offsetPhysique, qteACopier);
            
            bytesRemaining -= qteACopier;
        }

        // Mettre à jour l'inode.
        Inode inode = new Inode(memoryManager, inodeNum);
        inode.writeToMemory(1, data.length, System.currentTimeMillis(), 
                           System.currentTimeMillis(), blockPointers, 0, (short) 0664, 1);

        return true;
    }
    
}