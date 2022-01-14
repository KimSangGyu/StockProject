package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Condition {
	private Date standardDate;
	private String period;
	private String strategy;
}
