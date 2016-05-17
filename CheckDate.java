package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CheckDate {
	String subjectName;
	int dayOfWeek;
//	String[] result;
	ArrayList<String> list = new ArrayList<>();
	int i=0;
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
	String userId = "root";
	String userPass = "root";
	public CheckDate(String s) {
		subjectName=s;
	}
	public ArrayList<String> check(){
		Connection conn;
		String sql;
		PreparedStatement pstmt;
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT DISTINCT a.Date FROM Attendance a, "
					+ "(SELECT SubjectID FROM Subject WHERE Name LIKE '"+subjectName+"')sub "
					+ "WHERE a.SubjectID LIKE sub.SubjectID";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				list.add(rs.getString(1));
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
//	public ArrayList<String> showDayOfWeek(int dayOfWeek){
//		ArrayList<String> list = new ArrayList<>();
//		Calendar calendar = Calendar.getInstance();
//		int year = calendar.get(Calendar.YEAR);
//		int mon = calendar.get(Calendar.MONTH);
//		int day = calendar.get(Calendar.DAY_OF_MONTH);
//		calendar.set(year, mon ,day);
//		int b = calendar.getActualMaximum(Calendar.DATE);
//		int a = calendar.get(Calendar.DAY_OF_WEEK);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		if(2<=mon-1&&mon-1<=5){
//			for(int i=2;i<mon;i++){
//				calendar.set(year,i,1);
//				for(int j=1;j<=calendar.getActualMaximum(Calendar.DATE);j++){
//					calendar.set(year, i ,j);
//					if(calendar.get(Calendar.DAY_OF_WEEK)==dayOfWeek){
//						String today = df.format(calendar.getTime());
//						list.add(today);
//					}
//				}
//			}
//		}
//		if(2<=mon&& mon<=5){
//			for(int i=1;i<=day;i++){
//				calendar.set(year, mon ,i);
//				if(calendar.get(Calendar.DAY_OF_WEEK)==3){
//					String today = df.format(calendar.getTime());
//					list.add(today);
//				}
//			}
//		}
//		
//		
//		return list;
//		
//		
//	}
}
