package sampleServer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AttendanceInsert {
	
	String nfcId, dayOfTheWeek, hour, min, studentId;
	public AttendanceInsert (String n, String s) {
		// TODO Auto-generated constructor stub
		nfcId = n;
		studentId = s;
	}
	
	public String insert() {
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		String userId = "root";
		String userPass = "root";
		
		Connection conn;
		String sql;
		PreparedStatement pstmt;
		String pl = "";
		String ppl, day, hr,subjectId;
		int re;
		dateData();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT NFCID FROM NFC";
			pstmt = conn.prepareStatement(sql);

			//re = pstmt.executeUpdate();
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int i=1;

				if(nfcId.equals(rs.getString(i++))){
					sql = "SELECT Place FROM NFC WHERE NFCID LIKE '"+nfcId +"'";
					PreparedStatement pstmt2;
					pstmt2 = conn.prepareStatement(sql);
					ResultSet rs2 = null;
					rs2 = pstmt2.executeQuery();
					if(rs2.next())
						pl = rs2.getString(1);

					pstmt2.close();
					
					sql = "SELECT Place, DayOfTheWeek, Hour, sub.SubjectID FROM Subject sub , SubjectTime subT, "
							+ "(SELECT co.SubjectID FROM Course co WHERE co.StudentID LIKE " + studentId +")coT "
							+ "WHERE sub.SubjectID LIKE subT.SubjectID AND sub.SubjectID LIKE coT.SubjectID";
					
					PreparedStatement pstmt3;
					pstmt3 = conn.prepareStatement(sql);
					ResultSet rs3 = null;
					rs3 = pstmt3.executeQuery();
					System.out.println("1111111111");
					while (rs3.next()){
						int k=1;
						ppl = rs3.getString(k++);
						day = rs3.getString(k++);
						hr = rs3.getString(k++);
						subjectId= rs3.getString(k++);
						System.out.println("2222222222222222");
						if(pl.equals(ppl)&&dayOfTheWeek.equals(day)&&hour.equals(hr)){
							Calendar calendar = Calendar.getInstance();
							Date d = new Date(calendar.getTimeInMillis());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							
							sql = "SELECT StudentId, Date, Hour FROM Attendance WHERE StudentId LIKE '"+studentId+"' AND Date LIKE '"+sdf.format(d)+"' AND Hour LIKE '"+hour+"'";
							PreparedStatement pstmt5;
							pstmt5 = conn.prepareStatement(sql);
							ResultSet rs5 = null;
							rs5 = pstmt5.executeQuery();

							if(rs5.next()){
								 String attendance_studentId = rs5.getString(1);
								 String attendance_date = rs5.getString(2);
								 String attendance_hour = rs5.getString(3);
								 System.out.println("333333333333333333333");
								 if(attendance_studentId.equals(studentId) && attendance_date.equals(sdf.format(d)) && attendance_hour.equals(hour)){
									 break;
								 } 
							}
							
							sql = "SELECT NFCID, Date, Hour FROM Attendance WHERE NfCID LIKE '"+nfcId+"' AND Date LIKE '"+sdf.format(d)+"' AND Hour LIKE '"+hour+"'";
							PreparedStatement pstmt6;
							pstmt6 = conn.prepareStatement(sql);
							ResultSet rs6 = null;
							rs6 = pstmt6.executeQuery();
							
							if(rs6.next()){
								 String attendance_nfcid = rs6.getString(1);
								 String attendance_date = rs6.getString(2);
								 String attendance_hour = rs6.getString(3);
								 System.out.println("44444444444444444444");
								 if(attendance_nfcid.equals(nfcId) && attendance_date.equals(sdf.format(d)) && attendance_hour.equals(hour)){
									 break;
								 } 
							}
							
		
							if(Integer.parseInt(min)<=10){
								System.out.println("%555555555555555555555");
								sql = "INSERT INTO `Attendance` (`StudentID`, `SubjectID`, `NFCID`, `Date`, `Hour`, `State`)"
										+ " VALUES ('" + studentId + "', " + subjectId +", '" + nfcId + "', '" + sdf.format(d) +"', '"+ hour +"', '"+ "Attendence" +"')";

							} else if(Integer.parseInt(min)>10 && Integer.parseInt(min)<=30){
								System.out.println("66666666666666666");
								sql = "INSERT INTO `Attendance` (`StudentID`, `SubjectID`, `NFCID`, `Date`, `Hour`, `State`)"
										+ " VALUES ('" + studentId + "', " + subjectId +", '" + nfcId + "', '" + sdf.format(d) +"', '"+ hour +"', '"+ "Lateness" +"')";
							} else{
								System.out.println("7777777777777777777");
								return "AttendanceInsert_fail";
							}
							System.out.println("888888888888888888");
							PreparedStatement pstmt4;
							System.out.println("999999999999999999999");
							pstmt4 = conn.prepareStatement(sql);
							System.out.println("aaaaaaaaaaaaaaaaaaa");
							pstmt4.executeUpdate();
							System.out.println("bbbbbbbbbbbbbbbbb");
							return "AttendanceInsert_success";
						}
					}
				}
			}
			
			pstmt.close();
			conn.close();
		} catch (Exception e) {}
		return "AttendanceInsert_fail";
	}
	
	public void dateData(){
		System.setProperty("user.timezone", "Asia/Seoul");
		Calendar calendar = Calendar.getInstance();
//		hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		hour = "10";
		dayOfTheWeek = getDayOfTheWeek();
		min = Integer.toString(calendar.get(Calendar.MINUTE));
		System.out.println(hour);
		System.out.println(dayOfTheWeek);
		System.out.println(min);
	}
	
	public String getDayOfTheWeek() {
        Calendar calendar = Calendar.getInstance( );
        int n = calendar.get(Calendar.DAY_OF_WEEK);
        switch (n){
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
        }
        return "fail";
    }
}
