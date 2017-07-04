package com.controller;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dao.MajorDao;
import com.model.Major;

@Controller
@RequestMapping("/major")
public class MajorController {

	@RequestMapping("/getKinds.do")
	protected String getMajorKinds(Model model) throws SQLException, PropertyVetoException
	{
		MajorDao md=new MajorDao();
		HashMap<String,ArrayList> classMajor=md.getClassMajor();
		model.addAttribute("classMajor",classMajor);
		model.addAttribute("userAction","displayMajorInfo");
		return "jsp/major";
		
	}
	@RequestMapping("/getMajor.do")
	protected String getMajor(String majorName,Model model) throws SQLException, PropertyVetoException
	{
		if(majorName!=null)System.out.println("传递majorName");
		MajorDao md=new MajorDao();
		Major m=new Major();
		m=md.transMajorInfo(majorName);
		model.addAttribute("major",m);
		
		HashMap<String,ArrayList> classMajor=md.getClassMajor();
		model.addAttribute("classMajor",classMajor);
		
		return "jsp/majorDetail";
	}
}
