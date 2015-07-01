package com.example.portiaprotest;

import java.util.ArrayList;

import beans.JSONWeatherParser;
import beans.Weather;
import beans.WeatherAdapter;
import beans.WeatherHttpClient;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	
	private String[] cityList;
	private ListView cityWeatherItem;
	private WeatherAdapter weatherAdapter;
	private ArrayList<Weather> cityWeatherList = new ArrayList<Weather>();
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.activity_main);
		cityWeatherItem = (ListView) findViewById(android.R.id.list);

		//Get city name and country
		cityList = getResources().getStringArray(R.array.city_list_items);
		
		//Start AsyncTask
		new GetCityWeatherTask().execute();
	}

	private class GetCityWeatherTask extends AsyncTask<String, Void, String> {
		Weather weather = new Weather();
		
		//Show progress dialog while loading data
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setTitle("Contacting Servers");
			pDialog.setMessage("Loading ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			
			for(int i = 0; i < cityList.length; i++){
				String data = ((new WeatherHttpClient()).getWeatherData(cityList[i]));
				//Retrieve weather data
				JSONWeatherParser jsonWeatherParser = new JSONWeatherParser();
				weather = jsonWeatherParser.getWeather(data);
				// Retrieve the icon
		        weather.setIconData((new WeatherHttpClient()).getImage(weather.getWeatherIconString()));
		        cityWeatherList.add(weather);
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			//Close progress dialog
			pDialog.dismiss();
			if(cityWeatherList != null){
				//Set Adapter
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
