package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class DiffPrice {
	private String code;
	private String name;
	private Date beginDate;
	private int beginPrice;
	private Date endDate;
	private int endPrice;
	private int profit;
}
