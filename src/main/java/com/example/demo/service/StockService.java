package com.example.demo.service;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StockMapper;
import com.example.demo.util.StockCrawling;
import com.example.demo.vo.Condition;
import com.example.demo.vo.DailyQuotations;
import com.example.demo.vo.DiffPrice;
import com.example.demo.vo.NewMagic;
import com.example.demo.vo.Sv;
import com.example.demo.vo.Svqm;

@Service
public class StockService {
	
	@Autowired
	public StockMapper mapper;
	
	public List<DiffPrice> getStrategy(Condition condition) throws IOException {
		List<DiffPrice> diffList = new ArrayList<DiffPrice>();
		
		if (condition.getStandardDate() == null) {
			return diffList;
		}
		
		Date endDate = condition.getStandardDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		
		switch (condition.getPeriod()) {
			case "oneYear":
				cal.add(Calendar.YEAR, -1);
				break;
			case "threeMonth":
				cal.add(Calendar.MONTH, -3);
				break;
			case "oneMonth":
				cal.add(Calendar.MONTH, -1);
				break;
		}
		
		switch (condition.getStrategy()) {
			case "sv":
				diffList = svStrategy(condition, cal);
				break;
			case "svqm":
				diffList = svqmStrategy(condition, cal);
				break;
			case "newmagic":
				diffList = newMagicStrategy(condition, cal);
				break;
		}
		return diffList;
	}
	
	private List<DiffPrice> svStrategy(Condition condition, Calendar cal) throws IOException {
		List<DiffPrice> diffList = new ArrayList<DiffPrice>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = condition.getStandardDate();
		Date beginDate;
		DailyQuotations beginQuotation;
		DailyQuotations endQuotation;
		
		List<Sv> svList = mapper.getSvList(df.format(cal.getTime()));
		
		beginDate = svList.get(0).getDate();
		cal.setTime(beginDate);
		
		switch (condition.getPeriod()) {
			case "oneYear":
				cal.add(Calendar.YEAR, 1);
				break;
			case "threeMonth":
				cal.add(Calendar.MONTH, 3);
				break;
			case "oneMonth":
				cal.add(Calendar.MONTH, 1);
				break;
		}
		
		endDate = new Date(cal.getTimeInMillis());
		 
		for (Sv sv : svList) {
			DiffPrice diffPrice = new DiffPrice();
			
			beginQuotation = StockCrawling.getDailyQuotations(sv.getCode(), beginDate);
			endQuotation = StockCrawling.getDailyQuotations(sv.getCode(), endDate);
					
			diffPrice.setCode(sv.getCode());
			diffPrice.setName(sv.getName());
			
			diffPrice.setBeginDate(beginQuotation.getDate());
			diffPrice.setBeginPrice(beginQuotation.getOpenPrice());
			diffPrice.setEndDate(endQuotation.getDate());
			diffPrice.setEndPrice(endQuotation.getOpenPrice());
			
			// 수익율 계산식
			int profit = (endQuotation.getOpenPrice() - beginQuotation.getOpenPrice()) / 100;
			diffPrice.setProfit(profit);
			
			diffList.add(diffPrice);
		}
		
		return diffList;
	}
	
	private List<DiffPrice> svqmStrategy(Condition condition, Calendar cal) throws IOException {
		List<DiffPrice> diffList = new ArrayList<DiffPrice>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = condition.getStandardDate();
		Date beginDate;
		DailyQuotations beginQuotation;
		DailyQuotations endQuotation;
		
		List<Svqm> svqmList = mapper.getSvqmList(df.format(cal.getTime())); 
				
		beginDate = svqmList.get(0).getDate();
		cal.setTime(beginDate);
		
		switch (condition.getPeriod()) {
			case "oneYear":
				cal.add(Calendar.YEAR, 1);
				break;
			case "threeMonth":
				cal.add(Calendar.MONTH, 3);
				break;
			case "oneMonth":
				cal.add(Calendar.MONTH, 1);
				break;
		}
		
		endDate = new Date(cal.getTimeInMillis());
		 
		for (Svqm svqm : svqmList) {
			DiffPrice diffPrice = new DiffPrice();
			
			beginQuotation = StockCrawling.getDailyQuotations(svqm.getCode(), beginDate);
			endQuotation = StockCrawling.getDailyQuotations(svqm.getCode(), endDate);
					
			diffPrice.setCode(svqm.getCode());
			diffPrice.setName(svqm.getName());
			
			diffPrice.setBeginDate(beginQuotation.getDate());
			diffPrice.setBeginPrice(beginQuotation.getOpenPrice());
			diffPrice.setEndDate(endQuotation.getDate());
			diffPrice.setEndPrice(endQuotation.getOpenPrice());
			
			// 수익율 계산식
			int profit = (endQuotation.getOpenPrice() - beginQuotation.getOpenPrice()) / 100;
			diffPrice.setProfit(profit);
			
			diffList.add(diffPrice);
		}
		
		return diffList;
	}
	
	private List<DiffPrice> newMagicStrategy(Condition condition, Calendar cal) throws IOException {
		List<DiffPrice> diffList = new ArrayList<DiffPrice>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = condition.getStandardDate();
		Date beginDate;
		DailyQuotations beginQuotation;
		DailyQuotations endQuotation;
		
		List<NewMagic> newMagicList = mapper.getNewMagicList(df.format(cal.getTime())); 
				 
		beginDate = newMagicList.get(0).getDate();
		cal.setTime(beginDate);
		
		switch (condition.getPeriod()) {
			case "oneYear":
				cal.add(Calendar.YEAR, 1);
				break;
			case "threeMonth":
				cal.add(Calendar.MONTH, 3);
				break;
			case "oneMonth":
				cal.add(Calendar.MONTH, 1);
				break;
		}
		
		endDate = new Date(cal.getTimeInMillis());
		 
		for (NewMagic newMagic : newMagicList) {
			DiffPrice diffPrice = new DiffPrice();
			
			beginQuotation = StockCrawling.getDailyQuotations(newMagic.getCode(), beginDate);
			endQuotation = StockCrawling.getDailyQuotations(newMagic.getCode(), endDate);
					
			diffPrice.setCode(newMagic.getCode());
			diffPrice.setName(newMagic.getName());
			
			diffPrice.setBeginDate(beginQuotation.getDate());
			diffPrice.setBeginPrice(beginQuotation.getOpenPrice());
			diffPrice.setEndDate(endQuotation.getDate());
			diffPrice.setEndPrice(endQuotation.getOpenPrice());
			
			// 수익율 계산식
			int profit = (endQuotation.getOpenPrice() - beginQuotation.getOpenPrice()) / 100;
			diffPrice.setProfit(profit);
			
			diffList.add(diffPrice);
		}
		
		return diffList;
	}
}
