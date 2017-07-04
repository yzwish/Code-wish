package ywq.dao;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager {
	//ʹ�õ���ģʽ�������ݿ����ӳ�  
    private static ConnectionManager instance;  
    private ComboPooledDataSource dataSource;
	
	//����ı�IP��ַ
	private static String DBIP="127.0.0.1";
	
	//�����war����������·��
	//public static String picUploadPath="D:\\Apache Software Foundation\\Tomcat 9.0\\webapps\\yzwish\\verifiedImg\\";
	public static String picUploadPath="D:\\Apache Software Foundation\\Tomcat 9.0\\webapps\\yzwish\\WebContent\\verifiedImg\\";
	//���ͼƬ�ϴ���ַ

	
    private ConnectionManager() throws SQLException, PropertyVetoException {  
        dataSource = new ComboPooledDataSource(); 
        
        //�û����ɱ�-----------------------------------
        dataSource.setUser("root");  
        //����ɱ�-------------------------------------
        dataSource.setPassword("changseop"); 
        
        dataSource.setJdbcUrl("jdbc:mysql://"+DBIP+":3306/yzwish?useUnicode=true&characterEncoding=utf-8&useSSL=false");//���ݿ��ַ 
        dataSource.setDriverClass("com.mysql.jdbc.Driver");  
        dataSource.setInitialPoolSize(5); //��ʼ��������  
        dataSource.setMinPoolSize(1);//��С������  
        dataSource.setMaxPoolSize(10);//���������  
        dataSource.setMaxStatements(50);//��ȴ�ʱ��  
        dataSource.setMaxIdleTime(60);//������ʱ�䣬��λ����  
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
    
    //�ر�����
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

	//ִ�в�ѯ���
	public static ResultSet excuteSelect(String sql,Connection conn,PreparedStatement ptmt,ResultSet rs) {
		System.out.println("ʹ�����ӳز�ѯ................................");  
        long beginTime = System.currentTimeMillis(); 
		 try {
			//Ԥ�������
			 ptmt=conn.prepareStatement(sql);
			 //ִ��
			 rs = ptmt.executeQuery();  
			 long endTime = System.currentTimeMillis();  
	         System.out.println("ִ�л���ʱ��Ϊ:" + (endTime - beginTime));  
			 return rs;
			} catch (SQLException e) {  
	             e.printStackTrace();  
	             return null;
	        }	 
	}
	
	//ִ����ɾ�����
	public static boolean excuteIUD(String sql,Connection conn,PreparedStatement ptmt){
		 System.out.println("ʹ�����ӳ���ɾ��................................");  
	     int n=0;
         long beginTime = System.currentTimeMillis(); 
         try {  
      		ptmt=conn.prepareStatement(sql);
      		n= ptmt.executeUpdate(); 
          } catch (SQLException e) {  
              e.printStackTrace();  
          } 
         long endTime = System.currentTimeMillis();  
         System.out.println("ִ�л���ʱ��Ϊ:" + (endTime - beginTime));  
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

			//���Լ���Ҫ�����ı���
			//synchronized(Īĳ����){
		    	String sql="select * from Account where id='"+12345+"' ";
		    	//�������ͨ�ú���
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

