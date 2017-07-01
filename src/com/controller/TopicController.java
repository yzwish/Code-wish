package com.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.TopicDao;
import com.dao.UserDao;
import com.model.Account;
import com.model.Question;
import com.model.Topic;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/topic")
public class TopicController {

	//发起话题
	@RequestMapping(value = "/createTopic",method = RequestMethod.POST) 
    public ModelAndView createTopic(Topic topic,HttpServletRequest request,HttpSession session){  
	
		String userId = (String) session.getAttribute("id");
		topic.setTopicBanner("../topicImages/"+topic.getBannerPic().getOriginalFilename());
		topic.setTopicAvatar("../topicImages/"+topic.getAvatarPic().getOriginalFilename());
		String result = TopicDao.createTopic(topic, userId);
		if(result == "ok"){
			return new ModelAndView("redirect:/jsp/success.jsp");
		}else{
			return new ModelAndView("/jsp/topicHome");
		}
	}
	
	//搜索话题
	@RequestMapping(value = "/searchTopic",method = RequestMethod.POST) 
    public @ResponseBody String searchTopic(@RequestBody Topic topic){  
		
		return TopicDao.searchTopic(topic);
		       
	}
	
	//进入话题主页
	@RequestMapping(value = "/showTopic",method = RequestMethod.GET) 
    public ModelAndView showTopic(HttpServletRequest request,HttpSession session){  
	
		int topicId = Integer.parseInt(request.getParameter("topicId"));
		Topic topic = TopicDao.showTopic(topicId);
		request.setAttribute("topic", topic);
		ArrayList<Question> quesList = TopicDao.showQuestionList(topicId);
		request.setAttribute("quesList", quesList);
		
		return new ModelAndView("forward:/jsp/topicDetail.jsp");
		
	}
	
	//判断用户是否登录
	@RequestMapping(value = "/loginVerify",method = RequestMethod.GET) 
    public  @ResponseBody String loginVerify(HttpSession session){  
		if(session.getAttribute("login_status") != null){
			return "ok";
		}else{
			return "error";
		}
		
		       
	}
	
	//发布问题
	@RequestMapping(value = "/createQuestion",method = RequestMethod.POST) 
    public ModelAndView createQuestion(Question question){ 
		System.out.println(question.getTopicId());
		System.out.println(question.getUserId());
		System.out.println(question.getQuesContent());
		String result = TopicDao.createQuestion(question);
		if(result=="ok"){
			return new ModelAndView("redirect:/topic/showTopic?topicId="+question.getTopicId());
		}else{
			return new ModelAndView("redirect:/topic/showTopic?topicId="+question.getTopicId());
		}
	}
	
	
}
