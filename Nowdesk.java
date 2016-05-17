package sampleServer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Nowdesk {
	String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
	String userId = "root";
	String userPass = "root";
	
	public String nowDesk(){
		System.out.println("NowDesk");
		Connection conn;
		String sql;
		PreparedStatement pstmt;
		String re = "";
		
		System.setProperty("user.timezone", "Asia/Seoul");
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		String sHour = Integer.toString(hour);
		System.out.println("time"+ sHour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(calendar.getTime());
		
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT * FROM NFC";
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			int num = 0;
			while(rs.next()){ num++;}
			
			ResultSet rss[] = new ResultSet[num];
			for (int i = 0; i < num; i++) {	rss[i] = null; }
			PreparedStatement pp[] = new PreparedStatement[num];
			int j=0;
			for (int i = 1; i < num+1; i++) {
				//sql  = "SELECT COUNT(*) FROM `Attendance` WHERE NFCID LIKE ( SELECT NFCID FROM `NFC` WHERE `DaskID` LIKE '"+i+"' )";
				sql  = "SELECT COUNT(*) FROM `Attendance` "
						+ "WHERE NFCID LIKE ( SELECT NFCID FROM `NFC` WHERE `DaskID` LIKE '"+i+"' ) "
								+ "AND Date LIKE '"+today+"' AND Hour LIKE '"+10+"'";
				pp[i-1] = conn.prepareStatement(sql);
				rss[i-1] = pp[i-1].executeQuery();
				
				if(rss[i-1].next()){
					j = rss[i-1].getInt(1);
				}

				if(j==1){
					re+="A";
				} else
					re+="B";
				j=0;
				pp[i-1].close();
			}
			conn.close();
			return re;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "b";
	}
}
