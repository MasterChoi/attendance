package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InquiryByStudentId {
	String subjectName;
	String studentId;
	
	String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
	String userId = "root";
	String userPass = "root";
//	ArrayList<AttendanceData> list = new ArrayList<>();
	ArrayList<ArrayList<String>> list = new ArrayList<>();
	ArrayList<String> cList;

	public InquiryByStudentId(String a, String b) {
		// TODO Auto-generated constructor stub
		subjectName = a;
		studentId = b;
	}
		
	public ArrayList<ArrayList<String>> check(){
		Connection conn;
		String sql;
		PreparedStatement pstmt;
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT a.StudentID, sub.Name, a.Date, a.Hour, a.State FROM Attendance a, "
					+ "(SELECT SubjectID, Name FROM Subject WHERE Name LIKE '"+subjectName+"')sub "
					+ "WHERE a.SubjectID LIKE sub.SubjectID AND a.StudentID LIKE '"+studentId+"'";
			pstmt = conn.prepareStatement(sql);
				
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int i = 1;
				cList = new ArrayList<>();
				cList.add(rs.getString(i++));
				cList.add(rs.getString(i++));
				cList.add(rs.getString(i++));
				cList.add(rs.getString(i++));
				cList.add(rs.getString(i++));
				System.out.println(cList);
				list.add(cList);
				
			}
			for(int i=0; i<list.size();i++){
				System.out.println(list.get(i).get(0));
				System.out.println(list.get(i).get(1));
				System.out.println(list.get(i).get(2));
				System.out.println(list.get(i).get(3));
				System.out.println(list.get(i).get(4));
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return list;
	}
	
}

