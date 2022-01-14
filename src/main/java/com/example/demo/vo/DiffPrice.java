package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class DiffPrice {
	private String code;
	private String name;
	private Date beginDate;
	private double beginPrice;
	private Date endDate;
	private double endPrice;
	private double profit;
}
