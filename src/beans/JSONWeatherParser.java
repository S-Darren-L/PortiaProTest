package beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONWeatherParser {
	
	Weather weather = new Weather();

    JSONObject jObj = null;
	
	public Weather getWeather(String data){
				
		try {
			jObj = new JSONObject(data);
			
			weather.setCityString(jObj.optString("name"));
			
			JSONObject sysObj = getObject("sys", jObj);
			weather.setCountryString(getString("country", sysObj));
			
			JSONArray jArr = jObj.getJSONArray("weather");
			JSONObject JSONWeather = jArr.getJSONObject(0);
			weather.setWeatherMainString(getString("main", JSONWeather));
			weather.setWeatherDescriptionString(getString("description", JSONWeather));
			weather.setWeatherIconString(getString("icon", JSONWeather) + ".png");
			
			JSONObject mainObj = getObject("main", jObj);
			weather.setMainTempString(getString("temp", mainObj) + " \u2103");
			weather.setMainHumidityString(getString("humidity", mainObj) + " %");
			weather.setMainPressureString(getString("pressure", mainObj) + "hPa");
			weather.setMainTempMinString(getString("temp_min", mainObj) + " \u2103");
			weather.setMainTempmaxString(getString("temp_max", mainObj) + " \u2103");	

			JSONObject windObj = getObject("wind", jObj);
			weather.setWindSpeedString(getString("speed", windObj) + " meter/sec");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return weather;		
	}
	
	private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
	    JSONObject subObj = jObj.getJSONObject(tagName);
	    return subObj;
	}
	
	private static String getString(String tagName, JSONObject jObj) throws JSONException {
	    return jObj.getString(tagName);
	}
}
