package com.example.demo.controller;

import java.util.ArrayList;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.NewBook;
import com.example.demo.vo.Result;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShopController {
	
//	@GetMapping("/shop")
//	public Result shop(String search, String sort) {
//		System.out.println(search);
//		String title = "";
//		String link = "";
//		String price_str = "";
//		
//		//String url = "https://search.shopping.naver.com/search/all?frm=NVSHATC&origQuery"+item+"&pagingSize=40&productSet=total&query="+item+"&sort=price_asc&timestamp=&viewType=list";
//		String url = "https://search.shopping.naver.com/search/all?frm=NVSHATC&origQuery="+search+"&pagingSize=40&productSet=total&query="+search+"&sort=price_"+sort+"&timestamp=&viewType=list";
//		try {
//			Document doc = Jsoup.connect(url).get();
//			Element e = doc.selectFirst("#__next > div > div.style_container__UxP6u > div.style_inner__i4gKy > div.style_content_wrap__Cdqnl > div.style_content__xWg5l > ul > div > div:nth-child(1) > li > div > div.basicList_info_area__TWvzp");
//			Element a = e.getElementsByTag("a").get(0);
//			Element p = e.getElementsByClass("price_num__S2p_v").get(0);
//			title = a.attr("title");
//			link = a.attr("href");
//			price_str = p.text();
//			price_str = price_str.substring(0,price_str.length()-1);
//			//int price = Integer.parseInt(price_str);
//			
//			System.out.println(title);
//			System.out.println(link);
//			System.out.println(price_str);
//			
////			for(Element a : list) {
////				 if(a.hasClass("basicList_title__VfX3c")) {
////					 System.out.println(a.text());
////					 System.out.println("-------------------------");
////				 }
////			}
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		return new Result(title, link, price_str);
//	}
	
	@GetMapping("/shop")
	public static String main(String[] args) {
        String clientId = "4UC8XtodCrK5jBI0puF0"; //?????????????????? ??????????????? ?????????
        String clientSecret = "ZBkfqyvI6o"; //?????????????????? ??????????????? ?????????


        String text = null;
        try {
            text = URLEncoder.encode("???????????????", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("????????? ????????? ??????",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // JSON ??????
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // XML ??????


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        
        return responseBody;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // ?????? ??????
                return readBody(con.getInputStream());
            } else { // ?????? ??????
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API ????????? ?????? ??????", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL??? ?????????????????????. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("????????? ??????????????????. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API ????????? ?????? ??? ??????????????????.", e);
        }
    }
	
	

	
	
}
