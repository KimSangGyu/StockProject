package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.StockService;
import com.example.demo.vo.Condition;
import com.example.demo.vo.DiffPrice;
import com.example.demo.vo.NewMagic;
import com.example.demo.vo.Sv;
import com.example.demo.vo.Svqm;

@Controller
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@RequestMapping(value="/home")
	public void home(@ModelAttribute("condition") Condition condition, ModelMap model) throws Exception {
		List<DiffPrice> diffList = stockService.getStrategy(condition);

		model.addAttribute("diffList", diffList);
	}
}
