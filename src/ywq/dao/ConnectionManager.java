package ywq.dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager {
	//使用单例模式创建数据库连接池  
    private static ConnectionManager instance;  
    private ComboPooledDataSource dataSource;
	
	//这里改变IP地址
	private static String DBIP="127.0.0.1";
	
	//打包成war包后用以下路径
	//public static String picUploadPath="D:\\Apache Software Foundation\\Tomcat 9.0\\webapps\\yzwish\\verifiedImg\\";
	public static String picUploadPath="D:\\Apache Software Foundation\\Tomcat 9.0\\webapps\\yzwish\\WebContent\\verifiedImg\\";
	//审核图片上传地址

	
    private ConnectionManager() throws SQLException, PropertyVetoException {  
        dataSource = new ComboPooledDataSource(); 
        
        //用户名可变-----------------------------------
        dataSource.setUser("root");  
        //密码可变-------------------------------------
        dataSource.setPassword("changseop"); 
        
        dataSource.setJdbcUrl("jdbc:mysql://"+DBIP+":3306/yzwish?useUnicode=true&characterEncoding=utf-8&useSSL=false");//数据库地址 
        dataSource.setDriverClass("com.mysql.jdbc.Driver");  
        dataSource.setInitialPoolSize(5); //初始化连接数  
        dataSource.setMinPoolSize(1);//最小连接数  
        dataSource.setMaxPoolSize(10);//最大连接数  
        dataSource.setMaxStatements(50);//最长等待时间  
        dataSource.setMaxIdleTime(60);//最大空闲时间，单位毫秒  
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
    
    //关闭连接
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
    public static void close(PreparedStatement ptmt,ResultSet rs,Connection conn) {
    	if(rs!=null){
    		try {
				rs.close();
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

	//执行查询语句
	public static ResultSet excuteSelect(String sql,Connection conn,PreparedStatement ptmt,ResultSet rs) {
		System.out.println("使用连接池查询................................");  
        long beginTime = System.currentTimeMillis(); 
		 try {
			//预编译语句
			 ptmt=conn.prepareStatement(sql);
			 //执行
			 rs = ptmt.executeQuery();  
			 long endTime = System.currentTimeMillis();  
	         System.out.println("执行花费时间为:" + (endTime - beginTime));  
			 return rs;
			} catch (SQLException e) {  
	             e.printStackTrace();  
	             return null;
	        }	 
	}
	
	//执行增删改语句
	public static boolean excuteIUD(String sql,Connection conn,PreparedStatement ptmt){
		 System.out.println("使用连接池增删改................................");  
	     int n=0;
         long beginTime = System.currentTimeMillis(); 
         try {  
      		ptmt=conn.prepareStatement(sql);
      		n= ptmt.executeUpdate(); 
          } catch (SQLException e) {  
              e.printStackTrace();  
          } 
         long endTime = System.currentTimeMillis();  
         System.out.println("执行花费时间为:" + (endTime - beginTime));  
         if(n!=0)return true;
 		 return false;         
	}
	
	
	/*
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Connection conn=ConnectionManager.getInstance().getConnection(); 
		PreparedStatement ptmt=null;
		ResultSet rs=null;
		try {

			//可以加需要锁定的变量
			//synchronized(莫某变量){
		    	String sql="select * from Account where id='"+12345+"' ";
		    	//如果调用通用函数
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
	}*/
}

