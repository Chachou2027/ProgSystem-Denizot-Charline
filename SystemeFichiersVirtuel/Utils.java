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
	
	
}