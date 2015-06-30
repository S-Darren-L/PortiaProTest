package com.example.portiaprotest;

import java.util.ArrayList;

import org.json.JSONException;

import beans.JSONWeatherParser;
import beans.Weather;
import beans.WeatherAdapter;
import beans.WeatherHttpClient;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	private String[] cityList;
	private ListView cityWeatherItem;
	private WeatherAdapter weatherAdapter;
	private ArrayList<Weather> cityWeatherList = new ArrayList<Weather>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.activity_main);
		cityWeatherItem = (ListView) findViewById(android.R.id.list);

		cityList = getResources().getStringArray(R.array.city_list_items);

		new GetCityWeatherTask().execute();
	}

	private class GetCityWeatherTask extends AsyncTask<String, Void, String> {
		Weather weather = new Weather();

		@Override
		protected String doInBackground(String... params) {
			
			for(int i = 0; i < cityList.length; i++){
				String data = ((new WeatherHttpClient()).getWeatherData(cityList[i]));
				JSONWeatherParser jsonWeatherParser = new JSONWeatherParser();
				weather = jsonWeatherParser.getWeather(data);
				// Retrieve the icon
		        weather.setIconData((new WeatherHttpClient()).getImage(weather.getWeatherIconString()));
		        cityWeatherList.add(weather);
			}
			return null;
			
//			//test
//			//String data = ((new WeatherHttpClient()).getWeatherData("Toronto,Canada"));
//			
//			String data = ((new WeatherHttpClient()).getWeatherData(params[0]));
//			JSONWeatherParser jsonWeatherParser = new JSONWeatherParser();
//			weather = jsonWeatherParser.getWeather(data);
//			// Retrieve the icon
//	        weather.setIconData((new WeatherHttpClient()).getImage(weather.getWeatherIconString()));
//	        
//	        //test
//	        String s = new String(weather.getIconData());
//	        System.out.println("iconData: " + s);
		}

		@Override
		protected void onPostExecute(String result) {
			if(cityWeatherList != null){
				weatherAdapter = new WeatherAdapter(cityWeatherList, MainActivity.this);
				cityWeatherItem.setAdapter(weatherAdapter);
			} else{
				Toast.makeText(getApplicationContext(), "Cannot find cities!",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
