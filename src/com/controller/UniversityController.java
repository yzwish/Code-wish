package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dao.UniversityDao;
import com.model.University;

@Controller
@RequestMapping("/university")
public class UniversityController  {
	
	@RequestMapping("/rankUniversity.do") 
	protected String rankUniversity(Model model,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		UniversityDao ud=new UniversityDao();
		List<University> universityRankList=new ArrayList<University>();
		universityRankList=ud.getUniversiyByRank();
		model.addAttribute("universityRankList",universityRankList);
		//if(request.getParameter("unifind")==null)
		//model.addAttribute("unifind",2);
		//2表示未搜索
		return "jsp/universityRank";
		
	}
	
	@RequestMapping("/followUniversity.do") 
	protected String followUniversity(Model model) throws Exception {
		// TODO Auto-generated method stub
		UniversityDao ud=new UniversityDao();
		List<University> universityRankList=new ArrayList<University>();
		universityRankList=ud.getUniversiyByRank();
		model.addAttribute("universityRankList",universityRankList);

		return "jsp/universityRank";
		
	}
	
	@RequestMapping("/getUniversityDetail.do") 
	protected String getUniversityDetail(String universityId,Model model) throws Exception {
		UniversityDao ud=new UniversityDao();
		University u=ud.getUniversityDetail(universityId);
		model.addAttribute("university",u);
		return "jsp/universityDetail";
	
	}
	
	
	@RequestMapping("/searchUniversity.do") 
	protected void searchUniversity(String universityId,Model model, HttpServletRequest request, ServletResponse response) throws Exception {
		
		System.out.println("----searchUniversity");
		
		UniversityDao ud=new UniversityDao();
		List<University> universityRankList=new ArrayList<University>();
		String name=request.getParameter("searchBox");
		System.out.println("获取搜索大学："+name);
		universityRankList=ud.getUniversityByName(name);
		if(universityRankList.isEmpty()==false)
		{
		System.out.println("universityRankList.isEmpty()==false");
		model.addAttribute("universitySearchList",universityRankList);
		model.addAttribute("unifind",1);
		}
		else
		{   System.out.println("universityRankList.isEmpty()!=false");
			model.addAttribute("unifind",0);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/university/rankUniversity.do");  
		  try {  
		      rd.forward(request, response);  
		           
		  }catch(Exception e){} 
	
	
	}
}
