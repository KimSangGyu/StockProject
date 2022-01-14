package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class DailyQuotations {
	// 코드
	private String code;
	//날짜
	private Date date;
	// 종가
	private double closingPrice;
	// 전일비
	private double preRatio;
	// 시가
	private double openPrice;
	// 고가
	private double highPrice;
	// 저가
	private double lowPrice;
	// 거래량
	private double volume;
}
