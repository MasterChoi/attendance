package sampleServer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.crypto.Data;

public class PrServer extends Thread {
	ServerSocket serverSocket = null;
	//Socket socket = null;
	//DataOutputStream out;
	ObjectOutputStream oout;
	ObjectInputStream iin;
	DataInputStream in;
	String receivedData;
	String re;
	String subjectName;
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {
				//serverSocket = new ServerSocket(8888);
				System.out.println("8888 set");
				
				Socket socket =serverSocket.accept();
				in = new DataInputStream(socket.getInputStream());
				//out = new DataOutputStream(socket.getOutputStream());
				oout= new ObjectOutputStream(socket.getOutputStream());

				receivedData = in.readUTF();
				System.out.println("R : "+receivedData);
				
				if(receivedData.equals("a")){
					Nowdesk nowdesk = new Nowdesk();
					re = nowdesk.nowDesk();
					oout.writeObject(re);
					oout.flush();
				} else if(receivedData.equals("imageUrl")){
					String stid = in.readUTF();
					ImageUrl imageurl = new ImageUrl(stid);
					re = imageurl.imageurl();
					oout.writeObject(re);
				} else if(receivedData.equals("checked subject")){
					String proId = in.readUTF();
					CheckSubject checkSubject = new CheckSubject(proId);
					oout.writeObject(checkSubject.check());
					oout.flush();
				} else if(receivedData.equals("checked date")){
					String subjectName = in.readUTF();
					CheckDate checkDate = new CheckDate(subjectName);
					oout.writeObject(checkDate.check());
					oout.flush();
				} else if(receivedData.equals("checked student")){
					String subjectName = in.readUTF();
					CheckStudent checkStudent = new CheckStudent(subjectName);
					oout.writeObject(checkStudent.check());
					oout.flush();
				} else if(receivedData.equals("Inquiry By StudentID")){
					String subjectName = in.readUTF();
					String studentId = in.readUTF();
					InquiryByStudentId inquiryByStudentId = new InquiryByStudentId(subjectName,studentId);
					oout.writeObject(inquiryByStudentId.check());
					oout.flush();
				} else if(receivedData.equals("Inquiry By Date")){
					String subjectName = in.readUTF();
					String date = in.readUTF();
					InquiryByDate inquiryByDate = new InquiryByDate(subjectName, date);
					oout.writeObject(inquiryByDate.check());
					oout.flush();
				} else if(receivedData.equals("State modify")){
					String studentId = in.readUTF();
					String date = in.readUTF();
					String hour = in.readUTF();
					String subjectName = in.readUTF();
					String state = in.readUTF();
					Modify modify = new Modify(studentId, date, hour, subjectName, state);
					modify.dbUpdate();
				} else if(receivedData.equals("Login")){
					String id = in.readUTF();
					String pass = in.readUTF();
					String serN = in.readUTF();
					Login_Professor login_Professor = new Login_Professor(id, pass, serN);
					re = login_Professor.login();
					oout.writeObject(re);
					oout.flush();	
				} else if(receivedData.equals("ScanSerialNumber")){
					String who = in.readUTF();
					String num = in.readUTF();
					ScanSerialNumber scanSerialNumber = new ScanSerialNumber(num, who);
					re = scanSerialNumber.scanSerialNumber();
					oout.writeObject(re);
					oout.flush();
				} else if(receivedData.equals("Logout")){
					String id = in.readUTF();
					System.out.println("asdfasdf");
					Logout_Professor logout_Professor = new Logout_Professor(id);
					re = logout_Professor.logout();
					oout.writeObject(re);
					oout.flush();
				} else {
					SelectStudentId selectStudentId = new SelectStudentId(receivedData);
					re = selectStudentId.selectStudentId();
					oout.writeObject(re);
					oout.flush();
				}
				
				in.close();
				oout.close();
				System.out.println("Stream close");
				socket.close();
				//serverSocket.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
