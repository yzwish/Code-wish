package com.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dao.UniversityDao;
import com.model.University;

@Controller
@RequestMapping("/university")
public class UniversityController  {
	
	@RequestMapping("/rankUniversity.do") 
	protected String rankUniversity(Model model) throws Exception {
		// TODO Auto-generated method stub
		UniversityDao ud=new UniversityDao();
		List<University> universityRankList=new ArrayList<University>();
		universityRankList=ud.getUniversiyByRank();
		model.addAttribute("universityRankList",universityRankList);

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
}
