package com.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.TopicDao;
import com.model.Answer;
import com.model.Follow;
import com.model.Question;
import com.model.Topic;


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
		if(session.getAttribute("id") != null){
			return "ok";
		}else{
			return "error";
		}
		
		       
	}
	
	//发布问题
	@RequestMapping(value = "/createQuestion",method = RequestMethod.POST) 
    public ModelAndView createQuestion(Question question){ 
		
		String result = TopicDao.createQuestion(question);
		if(result=="ok"){
			return new ModelAndView("redirect:/topic/showTopic?topicId="+question.getTopicId());
		}else{
			return new ModelAndView("redirect:/topic/showTopic?topicId="+question.getTopicId());
		}
	}
	
	//进入问题详情
	@RequestMapping(value = "/showQA",method = RequestMethod.GET) 
    public ModelAndView showQA(HttpServletRequest request,HttpSession session){  
	
		int questionId = Integer.parseInt(request.getParameter("questionId"));
		Map<String, Question> map=TopicDao.showQuestion(questionId);
		if(map!=null){
			Iterator iter = map.entrySet().iterator();
			if(iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String topicName = (String) entry.getKey();
				Question question = (Question) entry.getValue();
				request.setAttribute("topicName", topicName);
				request.setAttribute("question", question);
			}
		}
		String[] colors={"rgb(53, 189, 199);","rgb(252, 166, 30);","rgb(230, 90, 79);","rgb(161, 193, 92);","rgb(118, 203, 162);"
				,"rgb(143, 130, 188);","rgb(242, 156, 159);","rgb(255,235,205)"};
		request.setAttribute("colors", colors);
		ArrayList<Answer> answerList=TopicDao.showQA(questionId);
		request.setAttribute("answerList", answerList);
		
		return new ModelAndView("forward:/jsp/questionDetail.jsp");
		
	}
	
	//回答问题
	@RequestMapping(value = "/answerQuestion",method = RequestMethod.POST) 
    public ModelAndView answerQuestion(Answer answer){
		
		String result = TopicDao.answerQuestion(answer);
		if(result=="ok"){
			return new ModelAndView("redirect:/topic/showQA?questionId="+answer.getQuestionId());
		}else{
			return new ModelAndView("redirect:/topic/showQA?questionId="+answer.getQuestionId());
		}
	}
	
	//搜索问题
	@RequestMapping(value = "/searchQuestion",method = RequestMethod.POST) 
    public ModelAndView searchQuestion(HttpServletRequest request){
		String searchWord = request.getParameter("searchWord");
		int thisTopicId = Integer.parseInt(request.getParameter("thisTopicId"));
		ArrayList<Question> searchQuesList = TopicDao.searchQuestion(searchWord,thisTopicId);
		request.setAttribute("searchQuesList", searchQuesList);
		
		return new ModelAndView("forward:/jsp/searchQuesResults.jsp");
		
	}
	
	//关注
	@RequestMapping(value = "/follow",method = RequestMethod.POST) 
    public @ResponseBody String follow(@RequestBody Follow follow){
		
		return TopicDao.follow(follow);
		
		       
	}
	
	//检查用户是否已关注
	@RequestMapping(value = "/checkFollow",method = RequestMethod.POST) 
    public @ResponseBody String checkFollow(@RequestBody Follow follow){
		
		return TopicDao.checkFollow(follow);
		
		       
	}
	
	
		
}
