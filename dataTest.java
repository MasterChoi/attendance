package sampleServer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class dataTest {
	public static void main(String[] args) {
			System.out.println("in dataPro");
			Connection conn = null;
			String sql, sql1;
			PreparedStatement pstmt = null;
			String re = null;
			String jdbcUrl = "jdbc:mysql://localhost:3306/testdb";
			String userId = "root";
			String userPass = "root";
			String id = "20112001";
			String nfcid = "04023C52A34081";
			String place1 = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {}

			try {
				
				String pl="";
				sql = "SELECT SubjectID FROM Course WHERE StudentId LIKE '"+id +"'";
				PreparedStatement pstmt4;
				pstmt4 = conn.prepareStatement(sql);
				ResultSet rs4 = null;
				rs4 = pstmt4.executeQuery();
				if(rs4.next())
					pl = rs4.getString(1);
				System.out.println("subId = "+pl);
				pstmt4.close();

				
			} catch (Exception e) {}
		}

	}
