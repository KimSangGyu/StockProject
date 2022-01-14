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
	private int closingPrice;
	// 전일비
	private int preRatio;
	// 시가
	private int openPrice;
	// 고가
	private int highPrice;
	// 저가
	private int lowPrice;
	// 거래량
	private int volume;
}
