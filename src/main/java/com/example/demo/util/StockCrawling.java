package com.example.demo.util;

import java.io.IOException;
import java.sql.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.demo.vo.DailyQuotations;

public class StockCrawling {
	private static final String URL = "https://finance.naver.com/item/sise_day.nhn?";
	
	public static DailyQuotations getDailyQuotations(String code , Date targetDate) throws IOException {
		DailyQuotations quotation = new DailyQuotations();
		boolean isEnd = false;
		Document document;
		Elements trElements;
		Elements spanElements;
		Date date;
		int page = 1;
		
		while (isEnd == false) {
			document = Jsoup.connect(makeUrlParam(code, page)).get();
			
			trElements = document.select(".type2 tr[onmouseover=\'mouseOver(this)\']");
	
			for(Element tr : trElements) {
				spanElements = tr.select(".tah");
				
				date = Date.valueOf(spanElements.get(0).text().replace('.', '-'));
				
				if (date.equals(targetDate) || date.before(targetDate)) {
					quotation.setCode(code);
					quotation.setDate(date);
					quotation.setClosingPrice(Integer.parseInt(spanElements.get(1).text().replaceAll("\\,", "")));
					quotation.setPreRatio(Integer.parseInt(spanElements.get(2).text().replaceAll("\\,", "")));
					quotation.setOpenPrice(Integer.parseInt(spanElements.get(3).text().replaceAll("\\,", "")));
					quotation.setHighPrice(Integer.parseInt(spanElements.get(4).text().replaceAll("\\,", "")));
					quotation.setLowPrice(Integer.parseInt(spanElements.get(5).text().replaceAll("\\,", "")));
					quotation.setVolume(Integer.parseInt(spanElements.get(6).text().replaceAll("\\,", "")));
					
					isEnd = true;
					break;
				}
			}
			
			page++;	
		}
		
		return quotation;
	}
	
	private static String makeUrlParam(String code, int page) {
		return URL + 
				"&code=" + code + 
				"&page=" + page + "";
	}
}
