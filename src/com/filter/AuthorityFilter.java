package com.filter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;

import com.dao.UserDao;
import com.dao.VisitsNumDao;


@WebFilter(
	filterName = "authorityFilter", 
	urlPatterns = { "/*" }, 
	initParams = { 
			@WebInitParam(name = "encoding", value = "UTF-8"), 
			@WebInitParam(name = "loginPage", value = "index.jsp"),
			@WebInitParam(name = "ignores", value = "jpg,html,htm,js,css,png")
		})

public class AuthorityFilter implements Filter {
	
	private Set<String> ignores = new HashSet<String>();  //只有初始化使用过一次
	private FilterConfig config;
	    // 实现初始化方法
    public void init(FilterConfig fConfig) throws ServletException {
    	
    	config = fConfig;	
    	String ignoresParam = config.getInitParameter("ignores");  	    
    	String[] ignoreArray = ignoresParam.split(","); 
        for (String s : ignoreArray) {  
            ignores.add(s);
        }
    }

    // 实现过滤方法
    public void doFilter(ServletRequest request, ServletResponse response, 
    		FilterChain chain) throws IOException, ServletException {
      
	   // 获取该过滤器的配置参数
       //String encoding = config.getInitParameter("");
       // 设置请求request的编码字符集
       //request.setCharacterEncoding(encoding);
       HttpServletRequest hrequest = (HttpServletRequest)request;
       HttpSession session = hrequest.getSession(true);

       //插入网站访问量
       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
       String today=df.format(new Date());	
       VisitsNumDao.addVisitsNum(today);
       
       //读取系统通知
       if(session.getAttribute("id")!=null){
    	    String id=(String) session.getAttribute("id");
    	    int unReadMsgNum=UserDao.getUnReadMsgNum(id);
    	    hrequest.setAttribute("unReadMsgNum", unReadMsgNum);
    	    chain.doFilter(request, response);
       }else{
    	   chain.doFilter(request, response);
       }
   	}
    // 实现销毁方法
	public void destroy() {
	        config = null;
	     }
}
