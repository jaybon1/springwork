
public class test4 {

	public static void main(String[] args) {
		
		int n = 6;
		
		int temp = 626331;
		int count = -1;
		
		for (int i = 1; i < 501; i++) {
			
			
			
			if(temp%2 == 0) {
				temp = temp/2;
			} else {
				temp = temp*3+1;
			}
			
			System.out.println(temp);
			
			if(temp == 1) {
				count = i;
				
				break;
			}
			
		}
		
		System.out.println(count);
		
	}
}
