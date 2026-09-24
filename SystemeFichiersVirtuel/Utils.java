import java.lang.Math;

public class Utils {

    public static int writeInt(byte[] memory, int offset, int value) {
		memory[offset] = (byte) ((value >> 24) & 0xFF);
		memory[offset + 1] = (byte) ((value >> 16) & 0xFF);
		memory[offset + 2] = (byte) ((value >> 8) & 0xFF);
		memory[offset + 3] = (byte) ((value) & 0xFF);
				
        return 4;
    }

    public static int readInt(byte[] memory, int offset) {
		int b1 = (int) (memory[offset] & 0xFF) << 24;
		int b2 = (int) (memory[offset + 1] & 0xFF) << 16;
		int b3 = (int) (memory[offset + 2] & 0xFF) << 8;
		int b4 = (int) (memory[offset + 3] & 0xFF);
		
        return b1 + b2 + b3 + b4;
    }

    public static int writeShort(byte[] memory, int offset, short value) {
		memory[offset] = (byte)((value >> 8) & 0xFF);
        memory[offset + 1] = (byte) ((value) & 0xFF);
        return 2;
    }

    public static short readShort(byte[] memory, int offset) {
		int b1 = (memory[offset] & 0xFF) << 8;
		int b2 = (memory[offset + 1] & 0xFF);
		
        return (short) (b1 + b2);
    }
	
	public static long writeLong(byte[] memory, int offset, long value) {
		memory[offset] = (byte) ((value >> 56) & 0xFF);
		memory[offset + 1] = (byte) ((value >> 48) & 0xFF);
		memory[offset + 2] = (byte) ((value >> 40) & 0xFF);
		memory[offset + 3] = (byte) ((value >> 32) & 0xFF);
		memory[offset + 4] = (byte) ((value >> 24) & 0xFF);
		memory[offset + 5] = (byte) ((value >> 16) & 0xFF);
		memory[offset + 6] = (byte) ((value >> 8) & 0xFF);
		memory[offset + 7] = (byte) ((value) & 0xFF);
				
        return 8;
    }
	
	public static long readLong(byte[] memory, int offset) {
		long b1 =  ((long)(memory[offset] & 0xFF ) << 56);
		long b2 =  ((long)(memory[offset + 1] & 0xFF )<< 48);
		long b3 =  ((long)(memory[offset + 2] & 0xFF )<< 40);
		long b4 =  ((long)(memory[offset + 3] & 0xFF )<< 32);
		long b5 =  ((long)(memory[offset + 4] & 0xFF )<< 24);
		long b6 =  ((long)(memory[offset + 5] & 0xFF )<< 16);
		long b7 =  ((long)(memory[offset + 6] & 0xFF )<< 8);
		long b8 =  ((long)memory[offset + 7] & 0xFF);
		
        return (long) (b1 + b2 + b3 + b4 + b5 + b6 + b7 + b8);
    }
	
	
	public static int writeString(byte[] memory, int offset,
                                  String str, int maxLength) {

		byte[] tableauChaine = str.getBytes();
		int longTabChaine = tableauChaine.length;
		int nbBytesACopier = Math.min(longTabChaine, maxLength);
        System.arraycopy(tableauChaine, 0, memory, offset, nbBytesACopier);
		
		for (int indice = nbBytesACopier; indice < maxLength; indice++) {
			memory[offset + indice] = 0x00;
		}
		
		return maxLength;
	}
	
	public static String readString(byte[] memory, int offset,
									int maxLength) {

		int longueurChaine = 0;
		while (longueurChaine < maxLength && memory[offset + longueurChaine] != 0x00) {
			longueurChaine++;
		}
	
		return new String(memory, offset, longueurChaine);
	}
	
}