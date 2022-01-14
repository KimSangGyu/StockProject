package com.example.demo.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.NewMagic;
import com.example.demo.vo.Sv;
import com.example.demo.vo.Svqm;

@Repository
@Mapper
public interface StockMapper {
	
	List<Sv> getSvList(String date);
	
	List<Svqm> getSvqmList(String date);
	
	List<NewMagic> getNewMagicList(String date);
}
