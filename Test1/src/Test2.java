import java.util.Calendar;

public class Test2 {
	
	public static void main(String[] args) {
		
		Calendar cal = Calendar.getInstance();
		
		int a = 5;
		int b = 24;
		
		cal.set(2016, a-1, b);
		
		String w = "";
		
		if(cal.get(Calendar.DAY_OF_WEEK) == 1) {
			w = "SUN";
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 2) {
			w = "MON";
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 3) {
			w = "TUE";
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 4) {
			w = "WED";
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 5) {
			w = "THU";
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 6) {
			w = "FRI";
		} else if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
			w = "SAT";
		}
		
		System.out.println(w);
		
	}

}
