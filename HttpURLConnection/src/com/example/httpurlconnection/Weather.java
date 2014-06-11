package com.example.httpurlconnection;

public class Weather {
	public String city;// 城市名  
	public String date;// 日期：yyyy年MM月d日  
	public String lunarDate;// 农历日期/当日有  
	public String week;// 星期  
	public String fcTime;// 预报时间：24制小时数/当日有  
	public String temperature;// 当日气温  
	public String weather;// 天气  
	public String wind;// 风力  
	@Override
	public String toString() {
		return "Weather [city=" + city + ", date=" + date + ", lunarDate="
				+ lunarDate + ", week=" + week + ", fcTime=" + fcTime
				+ ", temperature=" + temperature + ", weather=" + weather
				+ ", wind=" + wind + "]";
	}
	
}
