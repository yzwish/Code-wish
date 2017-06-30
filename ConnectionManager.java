package com.dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager {
	//浣跨敤鍗曚緥妯″紡鍒涘缓鏁版嵁搴撹繛鎺ユ睜  
    private static ConnectionManager instance;  
    private ComboPooledDataSource dataSource;
	
	//杩欓噷鏀瑰彉IP鍦板潃
	private static String DBIP="127.0.0.1";
	
	//鎵撳寘鎴恮ar鍖呭悗鐢ㄤ互涓嬭矾寰�
	//public static String picUploadPath="D:\\Apache Software Foundation\\Tomcat 9.0\\webapps\\yzwish\\verifiedImg\\";
	public static String picUploadPath="D:\\Apache Software Foundation\\Tomcat 9.0\\webapps\\yzwish\\WebContent\\verifiedImg\\";
	//瀹℃牳鍥剧墖涓婁紶鍦板潃

	
    private ConnectionManager() throws SQLException, PropertyVetoException {  
        dataSource = new ComboPooledDataSource(); 
        
        //鐢ㄦ埛鍚嶅彲鍙�-----------------------------------
        dataSource.setUser("root");  
        //瀵嗙爜鍙彉-------------------------------------
        dataSource.setPassword("jwc1.usst.edu.cn"); 
        
        dataSource.setJdbcUrl("jdbc:mysql://"+DBIP+":3306/yzwish?useUnicode=true&characterEncoding=utf-8&useSSL=false");//鏁版嵁搴撳湴鍧� 
        dataSource.setDriverClass("com.mysql.jdbc.Driver");  
        dataSource.setInitialPoolSize(5); //鍒濆鍖栬繛鎺ユ暟  
        dataSource.setMinPoolSize(1);//鏈�灏忚繛鎺ユ暟  
        dataSource.setMaxPoolSize(10);//鏈�澶ц繛鎺ユ暟  
        dataSource.setMaxStatements(50);//鏈�闀跨瓑寰呮椂闂�  
        dataSource.setMaxIdleTime(60);//鏈�澶х┖闂叉椂闂达紝鍗曚綅姣  
    }  
  
    public static final ConnectionManager getInstance() {  
        if (instance == null) {  
            try {  
                instance = new ConnectionManager();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return instance;  
    }  
  
    public synchronized final Connection getConnection() {  
        Connection conn = null;  
        try {  
            conn = dataSource.getConnection();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
    
    public static void close(Connection conn){
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public static void close(Connection conn,PreparedStatement ptmt) {
    	
    	if(ptmt!=null){
    		try {
				ptmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(conn!=null){
    		try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    
}
    //鍏抽棴杩炴帴
    public static void close(Connection conn,ResultSet rst,PreparedStatement ptmt) {
    	
        	if(rst!=null){
        		try {
					rst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	if(ptmt!=null){
        		try {
					ptmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	if(conn!=null){
        		try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        
	}

	//鎵ц鏌ヨ璇彞
	public static ResultSet excuteSelect(String sql,Connection conn,PreparedStatement ptmt,ResultSet rs) {
		System.out.println("浣跨敤杩炴帴姹犳煡璇�................................");  
        long beginTime = System.currentTimeMillis(); 
		 try {
			//棰勭紪璇戣鍙�
			 ptmt=conn.prepareStatement(sql);
			 //鎵ц
			 rs = ptmt.executeQuery();  
			 long endTime = System.currentTimeMillis();  
	         System.out.println("鎵ц鑺辫垂鏃堕棿涓�:" + (endTime - beginTime));  
			 return rs;
			} catch (SQLException e) {  
	             e.printStackTrace();  
	             return null;
	        }	 
	}
	
	//鎵ц澧炲垹鏀硅鍙�
	public static boolean excuteIUD(String sql,Connection conn,PreparedStatement ptmt){
		 System.out.println("浣跨敤杩炴帴姹犲鍒犳敼................................");  
	     int n=0;
         long beginTime = System.currentTimeMillis(); 
         try {  
      		ptmt=conn.prepareStatement(sql);
      		n= ptmt.executeUpdate(); 
          } catch (SQLException e) {  
              e.printStackTrace();  
          } 
         long endTime = System.currentTimeMillis();  
         System.out.println("鎵ц鑺辫垂鏃堕棿涓�:" + (endTime - beginTime));  
         if(n!=0)return true;
 		 return false;         
	}
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		try {

			//鍙互鍔犻渶瑕侀攣瀹氱殑鍙橀噺
			//synchronized(鑾煇鍙橀噺){
		    	String sql="select * from Account where id='"+12345+"' ";
		    	//濡傛灉璋冪敤閫氱敤鍑芥暟
		    	rs=ConnectionManager.excuteSelect(sql,conn,ptmt,rs);	
		    	while(rs.next()){
		    		System.out.println(rs.getString(1));
		    	}
			//}	    			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			ConnectionManager.close(ptmt,rs,conn);
		}
	}
}

