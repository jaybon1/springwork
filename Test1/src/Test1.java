
public class Test1 {

	public static void main(String[] args) {

		int n = 5;
		int[] arr1 = { 9, 20, 28, 18, 11 };
		int[] arr2 = { 30, 1, 21, 17, 28 };

		String[] result = new String[5];

		for (int i = 0; i < n; i++) {

			String binaryString1 = Integer.toBinaryString(arr1[i]);

			String addZero1 = "";
			
			for (int j = 0; j < n - binaryString1.length(); j++) {
				addZero1 = addZero1 + "0";
			}

			String zeroBinaryString1 = addZero1 + binaryString1;

			String binaryString2 = Integer.toBinaryString(arr2[i]);

			String addZero2 = "";
			for (int j = 0; j < n - binaryString2.length(); j++) {
				addZero2 = addZero2 + "0";
			}

			String zeroBinaryString2 = addZero2 + binaryString2;

			String sharp = "";

			for (int j = 0; j < n; j++) {
				if (zeroBinaryString1.charAt(j) == '1' || zeroBinaryString2.charAt(j) == '1') {
					sharp = sharp + "#";
				} else {
					sharp = sharp + " ";
				}
			}
			
			result[i] = sharp;

		}
		
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
		System.out.println(result[3]);
		System.out.println(result[4]);

	}

}
