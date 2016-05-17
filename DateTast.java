package sampleServer;

import java.util.Calendar;

public class DateTast {
	public static void main(String[] args){
//		System.out.println("dddd");
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
//					if(calendar.get(Calendar.DAY_OF_WEEK)==3){
//						String today = df.format(calendar.getTime());
//						System.out.println(today);
//
//					}
//				}
//			}
//		}
//		if(2<=mon&& mon<=5){
//			for(int i=1;i<=day;i++){
//				calendar.set(year, mon ,i);
//				if(calendar.get(Calendar.DAY_OF_WEEK)==3){
//					String today = df.format(calendar.getTime());
//					System.out.println(today);
//				}
//			}
//		}
		System.setProperty("user.timezone", "Asia/Seoul");
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		String sHour = Integer.toString(hour);
		int year = calendar.get(Calendar.YEAR);
		int mon = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, mon, day);

		System.out.println(year);
		System.out.println(mon);
		System.out.println(day);
		System.out.println(sHour);
		System.out.println("~~~~~~~~~~~~~~~~~~~");
		
		
	}

}
