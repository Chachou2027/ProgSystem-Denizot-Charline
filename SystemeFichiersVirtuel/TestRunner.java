public class TestRunner {

    public static void testStep2() {
		System.out.println("=== TEST ÉTAPE 2 : Utils Entiers ===");

		byte[] buffer = new byte[32];

		int value = 0xF0A1B2E3;
		int written = Utils.writeInt(buffer, 3, value);

		assert written == 4 : "writeInt doit retourner 4";

		assert (buffer[3]  & 0xFF) == 0xF0 : "Octet 0 incorrect";
		assert (buffer[4]  & 0xFF) == 0xA1 : "Octet 1 incorrect";
		assert (buffer[5]  & 0xFF) == 0xB2 : "Octet 2 incorrect";
		assert (buffer[6]  & 0xFF) == 0xE3 : "Octet 3 incorrect";

		assert Utils.readInt(buffer, 3) == value :
				"Erreur writeInt / readInt";
				
				

		short shortValue = (short) 0xF0A1;
		int shortWritten = Utils.writeShort(buffer, 20, shortValue);

		assert shortWritten == 2 : "writeShort doit retourner 2";

		assert (buffer[20] & 0xFF) == 0xF0 :
				"Premier octet du short incorrect";

		assert (buffer[21] & 0xFF) == 0xA1 :
				"Deuxième octet du short incorrect";

		assert Utils.readShort(buffer, 20) == shortValue :
				"Erreur writeShort / readShort";
				
				
		/* Test de l'entier négatif */
		int negativeValue = 0xFFFFFFEA;
		int intWritten = Utils.writeInt(buffer, 7, negativeValue);

		assert intWritten == 4 : "writeInt doit retourner 4";

		assert (buffer[7]  & 0xFF) == 0xFF : "Octet 0 incorrect";
		assert (buffer[8]  & 0xFF) == 0xFF : "Octet 1 incorrect";
		assert (buffer[9]  & 0xFF) == 0xFF : "Octet 2 incorrect";
		assert (buffer[10]  & 0xFF) == 0xEA : "Octet 3 incorrect";

		assert Utils.readInt(buffer, 7) == negativeValue :
				"Erreur writeInt / readInt";
		

		System.out.println("[OK] Étape 2 validée !");
		
	}

	

    public static void main(String args[]) {
		testStep2();
		
	}
}

