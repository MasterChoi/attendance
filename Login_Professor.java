package sampleServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login_Professor {
	String id, pass, serialNumber;
	public Login_Professor(String i, String p, String n) {
		id = i;
		pass = p;
		serialNumber = n;
	}
	public String login(){
		String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
		String userId = "root";
		String userPass = "root";

		Connection conn;
		String sql;
		PreparedStatement pstmt;
		String re = null;
		
		try { Class.forName("com.mysql.jdbc.Driver");} catch (Exception e) {}
		
		try {
			conn = DriverManager.getConnection(jdbcUrl,userId,userPass);
			sql = "SELECT * FROM `Professor` WHERE ProfessorID LIKE '"+id+"' AND PassWord LIKE '"+pass+"'";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			int num = 0;
			while(rs.next()){ 
				System.out.println(rs.getString(1));
				num++;
				}
			System.out.println(num);
			
			if (num >= 1){
				System.out.println("Insert");
				PreparedStatement pstmt2;
				System.out.println("AAAAA id : "+id);
				System.out.println("bbbbb pass : "+serialNumber);
				sql = "INSERT INTO ProLoginTable (`ProfessorId`, `MACID`) VALUES ('"+id+"', '"+serialNumber+"')";
				pstmt2 = conn.prepareStatement(sql);
				System.out.println("ddd");
				pstmt2.executeUpdate();
				System.out.println("ddd");
				pstmt2.close();
				System.out.println("ddd");
				return "LoginSuccess";
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "LoginFail";
	}
}
