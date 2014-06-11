package com.example.httpurlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText editText;
	private Button downloadButton;
	private TextView showTextView;
	private Handler handler;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.edittext1);
		downloadButton = (Button) findViewById(R.id.button1);
		showTextView = (TextView) findViewById(R.id.text1);
		editText.setText("http://m.weather.com.cn/data/101010100.html");
		downloadButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						sendRequest();
						
					}

				}).start();

			}
		});
		
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(0x12==msg.what){
					if(msg.obj!=null){
						String jsonString= (String)msg.obj;
						Weather currentWeather = JsonToObject(jsonString);
						showTextView.setText(currentWeather.toString());
					}
				}
				
			}
			
		};

	}

	public void sendRequest() {
		String requestString = editText.getText().toString().trim();
		URL url;
		String inputline = "";
		String info = "";

		try {
			url = new URL(requestString);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setReadTimeout(5000);
			httpURLConnection.setRequestMethod("GET");
			InputStreamReader inStream = new InputStreamReader(
					httpURLConnection.getInputStream(), "UTF-8");
			BufferedReader buffer = new BufferedReader(inStream);

			while ((inputline = buffer.readLine()) != null) {
				info += inputline;
			}
			Message message = handler.obtainMessage();
			message.obj = info;
			message.what = 0x12;
			handler.sendMessage(message);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public  Weather JsonToObject(String string){
		Weather weather = new Weather();
//		public String city;// 城市名  
//		public String date;// 日期：yyyy年MM月d日  
//		public String lunarDate;// 农历日期/当日有  
//		public String week;// 星期  
//		public String fcTime;// 预报时间：24制小时数/当日有  
//		public String temperature;// 当日气温  
//		public String weather;// 天气  
//		public String wind;// 风力  
		try {
			JSONObject jsonObject = new JSONObject(string);
			JSONObject wetherJsonObject = jsonObject.getJSONObject("weatherinfo");
			weather.city = wetherJsonObject.getString("city");
			weather.date = wetherJsonObject.getString("date");
			weather.week = wetherJsonObject.getString("week");
			weather.fcTime = wetherJsonObject.getString("date_y");
			//weather.lunarDate = wetherJsonObject.getString("");
			weather.temperature = wetherJsonObject.getString("temp1");
			weather.weather = wetherJsonObject.getString("weather1");
			weather.wind = wetherJsonObject.getString("wind1");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return weather;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
