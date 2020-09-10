import java.util.ArrayList;
import java.util.List;

public class Test5 {
	
	public static void main(String[] args) {
		
		int[] arr = {1, 1, 3, 3, 0, 1, 1};
		
		List<Integer> list = new ArrayList<>();
		
		int temp = -1;
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != temp) {
				list.add(arr[i]);
				temp = arr[i];
			}
		}
		
		int[] answer = new int [list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		
		System.out.println(list);
		
	}

}
