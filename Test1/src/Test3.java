import java.util.ArrayList;
import java.util.List;

public class Test3 {

	public static void main(String[] args) {

		int[] answers = {1,2,3,4,5};
		
		int[] answer = {};
		
		int[] supo1 = {1,2,3,4,5};
		int[] supo2 = {2,1,2,3,2,4,2,5};
		int[] supo3 = {3,3,1,1,2,2,4,5,5};
		
//		int[] supoAnswer1 = new int[answers.length];
//		int[] supoAnswer2 = new int[answers.length];
//		int[] supoAnswer3 = new int[answers.length];
		
		List<Integer> supo1List = new ArrayList<>();
		List<Integer> supo2List = new ArrayList<>();
		List<Integer> supo3List = new ArrayList<>();
		
		
		for (int i = 0; i < answers.length; i++) {
			
			if(answers[i] == supo1[(i%5)]) {
				supo1List.add(supo1[(i%5)]);
			}
			
			if(answers[i] == supo2[(i%8)]) {
				supo2List.add(supo2[(i%8)]);
			}
			
			if(answers[i] == supo3[(i%9)]) {
				supo3List.add(supo2[(i%8)]);
			}
			
		}
		
		if(supo1List.size() > supo2List.size() && supo1List.size() > supo3List.size()) {
			
		} else if(supo2List.size() > supo1List.size() && supo2List.size() > supo3List.size()) {
			
		} else if(supo3List.size() > supo1List.size() && supo3List.size() > supo2List.size()) {
			
		} else if(supo3List.size() == supo1List.size() && supo3List.size() > supo2List.size()) {
			
		}
		
		
		System.out.println(supo1List);
		System.out.println(supo2List);
		System.out.println(supo3List);
		
		
	}
}
