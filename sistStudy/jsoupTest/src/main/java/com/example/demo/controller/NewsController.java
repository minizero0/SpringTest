package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.NewBook;

@RestController
public class NewsController {
	
	//이미지 경로와 파일이름 매개변수로 받아옴
	public void downloadImage(String imgURL, String fname) {
		try {
			String path = "/Users/mini0/Desktop/webtoon";
			FileOutputStream fos = new FileOutputStream(path+"/"+fname+".jpg");
			
			URL url = new URL(imgURL);
			InputStream is = url.openStream();
			FileCopyUtils.copy(is.readAllBytes(), fos);
			fos.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	@GetMapping("/imgDown")
	public String imgDown() {
		String addr = "https://shared-comic.pstatic.net/thumb/webtoon/641253/thumbnail/thumbnail_IMAG21_01672165-03c8-44b1-ba0e-ef82c9cfcd10.jpg";
		String fname = "외모지상주의";
		downloadImage(addr, fname);
		return "ok";
	}
	
	@GetMapping("/allImageDown")
	public String allImageDown() {
		String url = "https://comic.naver.com/webtoon/weekday";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements list = doc.select("#content > div.list_area.daily_all").get(0).getElementsByTag("img");
			for(Element img : list) {
				String src = img.attr("src");
				String title = img.attr("title");
				System.out.println(src);
				System.out.println(title);
				downloadImage(src, title);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "ok";
	}
	
	
	
	@GetMapping("/news")
	public List<NewBook> news() {
		String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=230";
		ArrayList<NewBook> bookList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			int totalPage = Integer.parseInt(doc.select("#main_content > div.paging > a:nth-child(8)").get(0).text());
			String base = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=230&sid1=105&mid=shm&date=20221223&page=";
			for(int i = 1; i <= totalPage; i++) {
				String url2 = base + i;
				Document doc2 = Jsoup.connect(url2).get();
				Elements list = doc2.select("#main_content > div.list_body.newsflash_body > ul.type06_headline > li");
				for(Element e : list) {
					Element a = e.getElementsByTag("a").get(1);
					bookList.add(new NewBook(a.text(), a.attr("href")));
				}

			}
			System.out.println(totalPage);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return bookList;
	}

	
//	@GetMapping("/news")
//	public List<NewBook> news() {
//		String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=105&sid2=230";
//		ArrayList<NewBook> bookList = new ArrayList<>();
//		try {
//			
//			Document doc = Jsoup.connect(url).get();
//	Elements page = doc.select("#main_content > div.paging > a");
//			Elements list = doc.select("#main_content > div.list_body.newsflash_body > ul.type06_headline > li");
//			for(Element e : list) {
//				Element a = e.getElementsByTag("a").get(1);
//				bookList.add(new NewBook(a.text(), a.attr("href")));
//			}
//			
//			for(Element d : page) {
//				Element g = d.getElementsByTag("a").get(0);
//				url = g.attr("href");
//			}
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		return bookList;
//	}
}
