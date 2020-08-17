package crawnewsapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

// 1번 JSOUP를 빌드패스
// 2번 JSOUP로 URL 요청
// 3번 oid의 번호는 어디까지 있는지 + oid마다의 신문사명을 매칭

public class OidParse {
	
	public static void main(String[] args) {
		
		Map<String, String> oidMap = new HashMap<>();
		
		Document doc = null;
		
		String oid = "0";
		
		int failCount = 0;
		
		for (int i = 1; i < 1000; i++) {
			if (failCount > 10) {
				System.out.println("언론사가 더 이상 없는 것 같습니다.");
				break;
			}
			
			if((i+"").length() == 1) {
				
				oid = "00"+i;
				
			} else if((i+"").length() == 2) {
				
				oid = "0"+i;
				
			} else if((i+"").length() == 3) {
				
				oid = ""+i;
			}
			
			try {
				doc = Jsoup.connect("https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101&oid="+oid+"&aid=0000000001").get();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				int count = 0;
				for (int j = 1; j < 10; j++) {
					try {
						doc = Jsoup.connect("https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101&oid="+oid+"&aid=000000000"+j).get();
					} catch (IOException e1) {
						count++;
					}
				}
				if(count == 9) {
					failCount++;
					continue;
				} 
			}
			
			failCount = 0;
			
			String press = doc.select(".press_logo").select("img").attr("title");
			
			if (press.length() < 2) {
				int count = 0;
				for (int j = 1; j < 5; j++) {
					try {
						doc = Jsoup.connect("https://news.naver.com/main/read.nhn?mode=LSD&mid=shm&sid1=101&oid="+oid+"&aid=000000000"+j).get();
						if (doc.select(".press_logo").select("img").attr("title").length() > 1) {
							press = doc.select(".press_logo").select("img").attr("title");
							break;
						}else {
							count++;
						}
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
				if(count == 4) {
					System.out.println("익명의 언론사는 있으나 내용이 없습니다.");
					continue;
				} 
			}
			
			System.out.println(press +" : "+ oid);
			if (press.length() > 1) {
				oidMap.put(press, oid);				
			}

		}
		
		System.out.println(oidMap);
		System.out.println(oidMap.size());
		
	}
}
