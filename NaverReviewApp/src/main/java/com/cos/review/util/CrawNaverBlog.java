package com.cos.review.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cos.review.model.Product;

public class CrawNaverBlog {
	
	public String dayParse(String rawDay) {
		
		String parseDay = "";
		
		try {
			if(rawDay.contains("분 전") ||rawDay.contains("시간 전")) {
				
				return LocalDate.now().toString();
				
			} else if(rawDay.contains("일 전")) {
				
				parseDay = rawDay.replace("일 전", "");
				LocalDate today = LocalDate.now();
				return today.minusDays(Integer.parseInt(parseDay)).toString();
				
			} else if(rawDay.contains("어제")) {
				
				LocalDate today = LocalDate.now();
				return today.minusDays(1).toString();
				
			} else if(!rawDay.contains("-")) {
				
				parseDay = rawDay.substring(0, 4)+"-"+rawDay.substring(4, 7)+"-"+rawDay.substring(7);
				
				return parseDay.replace(".", "");
				
			} else if(rawDay.contains(".")) {
				
				return rawDay.replace(".", "");
				
			}
		} catch (Exception e) {
			return rawDay;
		}
		
		return parseDay;
	}
	
	// 작성해야됨
	public List<Product> startDayCraw() {
		return null;
	}

	// 스프링 스캐쥴!!
	public List<Product> startCraw(String keyword){
		int start = 1; //10씩 증가하면 됨.
		
		List<Product> products = new ArrayList<>();
		
		while (products.size() < 2) {
			
			String url = "https://search.naver.com/search.naver?date_from=&date_option=0&date_to=&dup_remove=1&nso=&post_blogurl=&post_blogurl_without=&query="+keyword+"&sm=tab_pge&srchby=all&st=sim&where=post&start="+start;
			
			
			try {
				Document doc = Jsoup.connect(url).get();
				Elements els1 = doc.select(".blog .sh_blog_top .sh_blog_title");
				Elements els2 = doc.select(".blog .sh_blog_top .txt_inline");
				Elements els3 = doc.select(".blog .sh_blog_top .sp_thmb img");
				for (int i=0; i<els1.size(); i++) {
					Product product = new Product();
					product.setTitle(els1.get(i).attr("title"));
					product.setBlogUrl(els1.get(i).attr("href"));
					product.setDay(dayParse(els2.get(i).text()));
					if(els3.size() > i) {
						product.setThumnail(els3.get(i).attr("src"));		
					}else {
						product.setThumnail("사진없음");
					}
					products.add(product);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (Product product : products) {
				System.out.println("===========================");
				System.out.println("제목 : "+product.getTitle());
				System.out.println("주소 : "+product.getBlogUrl());
				System.out.println("섬네일 : "+product.getThumnail());
				System.out.println("날짜 : "+product.getDay());
				System.out.println();
			}
//			System.out.println(url);
			
			start = start + 10;
		}	
		
		return products;
	}
}
