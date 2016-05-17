package sampleServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class StServer extends Thread {
	ServerSocket serverSocket = null;
	Socket socket = null;
	DataInputStream in;
	DataOutputStream oo;
	String nfcId, dayOfTheWeek, hour, min, studentId;
	String re;
	@Override
	public void run() {
		while(true){
			try { 
				serverSocket = new ServerSocket(7777);
				System.out.println("7777 Set");
				
				socket =serverSocket.accept();
				System.out.println("accept");
				in = new DataInputStream(socket.getInputStream());
				oo = new DataOutputStream(socket.getOutputStream());
				String select = in.readUTF();
				System.out.println("Stserver Selected : "+select);
				if(select.equals("ScanSerialNumber")){
					String who = in.readUTF();
					String serialNumber = in.readUTF();
					System.out.println("SerialNumber : "+serialNumber);
					ScanSerialNumber scanSerialNumber = new ScanSerialNumber(serialNumber, who);
					re = scanSerialNumber.scanSerialNumber();
					
				} else if (select.equals("AttendanceInsert")){
					nfcId = in.readUTF(); 
					studentId = in.readUTF();
					
					AttendanceInsert attendanceInsert = new AttendanceInsert(nfcId, studentId);
					re = attendanceInsert.insert();
				} else if (select.equals("Login")){
					String id = in.readUTF();
					String pass = in.readUTF();
					String serialNumber = in.readUTF();
					Login_Student login = new Login_Student(id,pass,serialNumber);
					re = login.login();
				} else if (select.equals("Logout")){
					//String id = in.readUTF();
					studentId = in.readUTF();
					System.out.println("Logout Selected student Id : "+studentId);
					
					Logout_Student logout = new Logout_Student(studentId);
					re = logout.logout();
				}
				

				oo.writeUTF(re);
				
				in.close();
				oo.close();
				socket.close();
				serverSocket.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
