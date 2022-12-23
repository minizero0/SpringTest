package com.example.demo.controller;


import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.NewBook;

@RestController
public class HanbController {
	
	@GetMapping("/seat")
	public String seat() {
		String url = "http://mpllc-seat.sen.go.kr/seatinfo/Seat_Info/1_count.asp";
		String str = "";
		ArrayList<NewBook> bookList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			//#Layer110 > table > tbody > tr > td
			Elements e = doc.select("#Layer110 > table > tbody > tr > td.wating_f");
			//System.out.println(e.get(0).text());
			
			str = e.get(0).text();
			Elements list = doc.getElementsByClass("wating_f");
		}catch (Exception e) {
			System.out.println("예외:"+e.getMessage());
		}
		return str;
	}
	
	@GetMapping("/newbook")
	public ArrayList<NewBook> newBook() {
		String url = "https://www.hanbit.co.kr/store/books/new_book_list.html";
		ArrayList<NewBook> bookList = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements list = doc.getElementsByClass("book_tit");
			for(Element e : list) {
				Element a = e.getElementsByTag("a").get(0);
				System.out.println(a);
				String title = a.text();
				System.out.println(title);
				String link = a.attr("href");
				System.out.println(link);
//				System.out.println(title);
//				System.out.println(link);
//				System.out.println("---------------------------------------------");
				
				bookList.add(new NewBook(title, link));
			}
		}catch (Exception e) {
			System.out.println("예외:"+e.getMessage());
		}
		return bookList;
	}
}
