package com.example.httpurlconnection;

public class Weather {
	public String city;// ������  
	public String date;// ���ڣ�yyyy��MM��d��  
	public String lunarDate;// ũ������/������  
	public String week;// ����  
	public String fcTime;// Ԥ��ʱ�䣺24��Сʱ��/������  
	public String temperature;// ��������  
	public String weather;// ����  
	public String wind;// ����  
	@Override
	public String toString() {
		return "Weather [city=" + city + ", date=" + date + ", lunarDate="
				+ lunarDate + ", week=" + week + ", fcTime=" + fcTime
				+ ", temperature=" + temperature + ", weather=" + weather
				+ ", wind=" + wind + "]";
	}
	
}
