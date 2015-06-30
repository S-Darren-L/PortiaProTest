package beans;

import android.R.integer;

public class Weather {

	private String cityString;
	private String countryString;
	private String weatherMainString;
	private String weatherDescriptionString;
	private String weatherIconString;
	private String mainTempString;
	private String mainHumidityString;
	private String mainPressureString;
	private String mainTempMinString;
	private String mainTempmaxString;
	private String windSpeedString;
	private byte[] iconData;
	
	public String getCityString() {
		return cityString;
	}
	public void setCityString(String cityString) {
		this.cityString = cityString;
	}
	public String getCountryString() {
		return countryString;
	}
	public void setCountryString(String countryString) {
		this.countryString = countryString;
	}
	public String getWeatherMainString() {
		return weatherMainString;
	}
	public void setWeatherMainString(String weatherMainString) {
		this.weatherMainString = weatherMainString;
	}
	public String getWeatherDescriptionString() {
		return weatherDescriptionString;
	}
	public void setWeatherDescriptionString(String weatherDescriptionString) {
		this.weatherDescriptionString = weatherDescriptionString;
	}
	public String getWeatherIconString() {
		return weatherIconString;
	}
	public void setWeatherIconString(String weatherIconString) {
		this.weatherIconString = weatherIconString;
	}
	public String getMainTempString() {
		return mainTempString;
	}
	public void setMainTempString(String mainTempString) {
		this.mainTempString = mainTempString;
	}
	public String getMainHumidityString() {
		return mainHumidityString;
	}
	public void setMainHumidityString(String mainHumidityString) {
		this.mainHumidityString = mainHumidityString;
	}
	public String getMainPressureString() {
		return mainPressureString;
	}
	public void setMainPressureString(String mainPressureString) {
		this.mainPressureString = mainPressureString;
	}
	public String getMainTempMinString() {
		return mainTempMinString;
	}
	public void setMainTempMinString(String mainTempMinString) {
		this.mainTempMinString = mainTempMinString;
	}
	public String getMainTempmaxString() {
		return mainTempmaxString;
	}
	public void setMainTempmaxString(String mainTempmaxString) {
		this.mainTempmaxString = mainTempmaxString;
	}
	public String getWindSpeedString() {
		return windSpeedString;
	}
	public void setWindSpeedString(String windSpeedString) {
		this.windSpeedString = windSpeedString;
	}
	public byte[] getIconData() {
		return iconData;
	}
	public void setIconData(byte[] iconData) {
		this.iconData = iconData;
	}
}
