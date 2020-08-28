package com.jaybon.reviewEx01;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class NaverBlogCrawTest {
	
	private String keyword = null;
	
	@Test
	public void 제품리뷰_크롤링() {
		
		keyword = "갤럭시20";
		String host = "https://search.naver.com/search.naver?query="+keyword;
	
		
		try {
			Document doc =Jsoup.connect(host).get();
//			System.out.println(doc);
			
			Elements els = doc.select(".blog .sh_blog_top a");
			for (Element element : els) {
				System.out.println(element.attr("title"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// http://hare.kr/222065140009
		// https://search.naver.com/search.naver?where=post&sm=tab_jum&query=%EA%B0%A4%EB%9F%AD%EC%8B%9C20
		
	}
}
