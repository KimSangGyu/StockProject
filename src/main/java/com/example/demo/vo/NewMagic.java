package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class NewMagic {
	private String code;

	private String name;
	private float pbr;
	// 통합 순위
	private int order;
	// ?? 
	private float gpa;
	
	private Date date;
	// ??
	private int seq;
	// 진입 가격
	private int price;
}
