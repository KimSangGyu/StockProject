package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Sv {
	private String code;
	private String name;
	// 시가 총액
	private String sigaChong;
	// PER
	private float per;
	// PBR
	private float pbr;
	// PCR
	private float pcr;
	// PSR
	private float psr;
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
