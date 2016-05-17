package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class InquiryTast {
	public static void main(String[] args){

		
		
		boolean flag =true;
		ArrayList<String> list = new ArrayList<>();
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		int i=0;

		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		String userId = "root";
		String userPass = "root";
		Connection conn;
		String sql;
		PreparedStatement pstmt;
		while(true){
			System.setProperty("user.timezone", "Asia/Seoul");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int mon = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int iDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			calendar.set(year, mon ,day);
//			calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR));
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			String sHour = Integer.toString(hour);
//			ArrayList<String> list = new ArrayList<String>;
			String sDayOfWeek = dayOfWeek(iDayOfWeek);
			int min = calendar.get(Calendar.MINUTE);
			System.out.println(min);
			if(min==30){
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT SubjectID FROM SubjectTime WHERE Hour LIKE '"+ sHour +"' AND DayOfTheWeek LIKE '"+ sDayOfWeek +"'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				list.add(rs.getString(1));
				System.out.println("SubjectID : "+list.get(i));
				i++;
			}
			
			ResultSet[] rss1 = new ResultSet[i];
			PreparedStatement[] pss1 = new PreparedStatement[i];
			ResultSet[] rss2 = new ResultSet[i];
			PreparedStatement[] pss2 = new PreparedStatement[i];
			ResultSet[] rss3 = new ResultSet[i];
			PreparedStatement[] pss3 = new PreparedStatement[i];
			for(int a=0;a<list.size();a++){
				sql = "SELECT StudentID FROM Course WHERE SubjectID LIKE '"+list.get(a)+"'";
				pss1[a] = conn.prepareStatement(sql);
				rss1[a] = pss1[a].executeQuery();
				sql = "SELECT StudentID FROM Attendance WHERE SubjectID LIKE '"+list.get(a)+"' AND Date LIKE '"+df.format(calendar.getTime())+"' AND Hour LIKE '"+sHour+"'";
				pss2[a] = conn.prepareStatement(sql);
				rss2[a] = pss2[a].executeQuery();
				while(rss1[a].next()){
					String result1 = rss1[a].getString(1);
					System.out.println("result1 : "+result1);
					System.out.println(df.format(calendar.getTime()));
					while(rss2[a].next()){
						String result2 = rss2[a].getString(1);
						System.out.println("result2 : " +result2);
						if(result1.equals(result2)){
							flag = false;
							System.out.println(a);
						}
					}
					if(flag){
						sql = "INSERT INTO Attendance(StudentID, SubjectID, NFCID,Date, Hour, State) "
								+ "VALUES ('"+result1+"', '"+list.get(a)+"', '0', '"+df.format(calendar.getTime())+ "', '"+ hour +"', 'Absence')";
						pss3[a]=conn.prepareStatement(sql);
						pss3[a].executeUpdate();
					}
					flag = true;
					rss2[a].first();
				}
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
			try{
				Thread.sleep(60000);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	public static String dayOfWeek(int a){
		String dayOfWeek = null;
		
		switch (a) {
		case 1:
			dayOfWeek= "Sun";
			break;
		case 2:
			dayOfWeek="Mon";
			break;
		case 3:
			dayOfWeek="Tue";
			break;
		case 4:
			dayOfWeek="Wed";
			break;
		case 5:
			dayOfWeek="Thu";
			break;
		case 6:
			dayOfWeek="Fri";
			break;
		case 7:
			dayOfWeek="Sat";
			break;
		}
		
		
		return dayOfWeek;
	}
}
