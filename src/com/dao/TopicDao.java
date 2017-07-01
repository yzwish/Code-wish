package com.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.Question;
import com.model.Topic;
import com.mysql.fabric.xmlrpc.base.Array;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TopicDao {

	//上传图片
	public static boolean uploadTopicPic(Topic topic){
		String topicBannerName=topic.getTopicBanner();
		String topicAvatarName=topic.getTopicAvatar();
		String uploadPath="D:\\javaeepro\\yzwish\\WebContent\\topicImages\\";
		File targetFile1 = new File(uploadPath,topicBannerName);  
		File targetFile2 = new File(uploadPath,topicAvatarName);  
        if(!targetFile1.exists()){  
            targetFile1.mkdirs();  
        }  
        if(!targetFile2.exists()){  
            targetFile2.mkdirs();  
        }  
        //保存  
        try {  
            topic.getBannerPic().transferTo(targetFile1);  
            topic.getAvatarPic().transferTo(targetFile2);  
            return true;
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;
        }  
		
	}
	//发起话题
	public static String createTopic(Topic topic,String userId){
		
		//上传话题banner和头像
		if(!uploadTopicPic(topic)){
			return "error";
		}
			
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
		ResultSet rst=null;
        String sql="INSERT INTO topic VALUES (null,?,?,0,0,?,?,?)";
        try{
        	ptmt = conn.prepareStatement(sql);				
    		ptmt.setString(1, topic.getTopicName());
    		ptmt.setString(2, topic.getTopicDiscription());
    		ptmt.setString(3, topic.getTopicAvatar());
    		ptmt.setString(4, topic.getTopicBanner());
    		ptmt.setString(5,userId);
    		int rs1 = ptmt.executeUpdate();	
    		//服务器错误
    		if(rs1==0){
    			return "error";
    		}else{
    			
    			return "ok";
    		}
    			
        }catch (SQLException e) {
			e.printStackTrace();
			return "error";
        }finally{
        	ConnectionManager.close(conn,rst,ptmt);

        }
        
	}
	
	//搜索话题
	public static String searchTopic(Topic topic){

		String sql="SELECT topicId,topicName FROM topic WHERE topicState=1"
				+ " AND topicName LIKE '%"+topic.getTopicName()+"%'";
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
		ResultSet rst=null;
        try{
        	ptmt = conn.prepareStatement(sql);
        	rst=ptmt.executeQuery();
        	JSONArray array=new JSONArray();
			java.sql.ResultSetMetaData metaData=rst.getMetaData();
			int columnCount =metaData.getColumnCount();
			while (rst.next()) {  
		        JSONObject jsonObj = new JSONObject();  
		         
		        // 遍历每一列  
		        for (int i = 1; i <= columnCount; i++) {  
		            String columnName =metaData.getColumnLabel(i);  
		            String value = rst.getString(columnName);  
						jsonObj.put(columnName, value);
		        }   
		        array.add(jsonObj);   
		    }  
		    
			return array.toString();
        	
        }catch (SQLException e) {
			e.printStackTrace();
			return "error";
        }finally{
        	ConnectionManager.close(conn,rst,ptmt);

        }
	}
	
	//显示话题主页
	public static Topic showTopic(int topicId){

		String sql="SELECT topicName,topicBanner,topicAvatar,followNumber FROM topic WHERE "
				+ "topicId="+topicId;
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
		ResultSet rst=null;
		Topic topic=null;
        try{
        	ptmt = conn.prepareStatement(sql);
        	rst=ptmt.executeQuery();
        	if(rst.next()){
        		topic=new Topic();
        		topic.setTopicName(rst.getString("topicName"));
        		topic.setTopicBanner(rst.getString("topicBanner"));
        		topic.setTopicAvatar(rst.getString("topicAvatar"));
        		topic.setFollowNumber(rst.getInt("followNumber"));
        		topic.setTopicId(topicId);
        		return topic;
        		 
        	}else{
        		return null;
        	}
        }catch (SQLException e) {
			e.printStackTrace();
			return null;
        }finally{
        	ConnectionManager.close(conn,rst,ptmt);

        }
		
	}
	
	//显示问题列表
	public static ArrayList<Question> showQuestionList(int topicId){
		String sql="SELECT questionTitle,userId,lastAnswerTime FROM question WHERE topicId="+topicId;
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
		ResultSet rst=null;
		ArrayList<Question> quesList=new ArrayList<Question>();
		Question question=null;
        try{
        	ptmt = conn.prepareStatement(sql);
        	rst=ptmt.executeQuery();
        	while(rst.next()){
        		question=new Question();
        		question.setQuesTitle(rst.getString("questionTitle"));
        		question.setUserId(rst.getString("userId"));
        		question.setLastAnswerTime(rst.getTimestamp("lastAnswerTime"));
        		quesList.add(question);
        		 
        	}
        	return quesList;
        }catch (SQLException e) {
			e.printStackTrace();
			return null;
        }finally{
        	ConnectionManager.close(conn,rst,ptmt);

        }
		
	}
	
	//发布问题
	public static String createQuestion(Question question){
		String sql="INSERT INTO question VALUES (null,?,?,?,now(),0,0,now(),?)";
		Connection conn = ConnectionManager.getInstance().getConnection();  
		PreparedStatement ptmt=null;
        try{
        	ptmt = conn.prepareStatement(sql);				
    		ptmt.setInt(1, question.getTopicId());
    		ptmt.setString(2,question.getQuesTitle());
    		ptmt.setString(3, question.getQuesContent());
    		ptmt.setString(4,question.getUserId());
    		int rs1 = ptmt.executeUpdate();	
    		//服务器错误
    		if(rs1==0){
    			return "error";
    		}else{
    			
    			return "ok";
    		}
    			
        }catch (SQLException e) {
			e.printStackTrace();
			return "error";
        }finally{
        	ConnectionManager.close(conn,ptmt);

        }
	}
	
}
