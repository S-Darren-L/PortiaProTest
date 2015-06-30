package beans;

import java.util.ArrayList;

import com.example.portiaprotest.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class WeatherAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Weather> cityWeatherList;
	LayoutInflater inflater;

	public WeatherAdapter(ArrayList<Weather> cityWeatherList, Context ctxt) {
		this.cityWeatherList = cityWeatherList;
		this.context = ctxt;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cityWeatherList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cityWeatherList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.weather_item, null);
		}

		ImageView ivWeatherIcon = (ImageView) convertView
				.findViewById(R.id.ivWeatherIcon);
		TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
		TextView tvCountry = (TextView) convertView.findViewById(R.id.tvCountry);
		TextView tvWeatherMain = (TextView) convertView
				.findViewById(R.id.tvWeatherMain);
		TextView tvWeatherDescription = (TextView) convertView
				.findViewById(R.id.tvWeatherDescription);
		TextView tvMainTemp = (TextView) convertView.findViewById(R.id.tvMainTemp);
		TextView tvMainTempRange = (TextView) convertView
				.findViewById(R.id.tvMainTempRange);
		TextView tvHumidityLable = (TextView) convertView
				.findViewById(R.id.tvHumidityLable);
		TextView tvMainHumidityString = (TextView) convertView
				.findViewById(R.id.tvMainHumidityString);
		TextView tvPressureLable = (TextView) convertView
				.findViewById(R.id.tvPressureLable);
		TextView tvMainPressureString = (TextView) convertView
				.findViewById(R.id.tvMainPressureString);
		TextView tvWindSpeedLable = (TextView) convertView
				.findViewById(R.id.tvWindSpeedLable);
		TextView tvWindSpeedString = (TextView) convertView
				.findViewById(R.id.tvWindSpeedString);

		if (cityWeatherList.get(position) != null) {
			tvCity.setText(cityWeatherList.get(position).getCityString());
			tvCountry.setText(cityWeatherList.get(position).getCountryString());
			tvWeatherMain.setText(cityWeatherList.get(position)
					.getWeatherMainString());
			tvWeatherDescription.setText(cityWeatherList.get(position)
					.getWeatherDescriptionString());
			tvMainTemp.setText(cityWeatherList.get(position)
					.getMainTempString());
			tvMainTempRange.setText(cityWeatherList.get(position)
					.getMainTempMinString()
					+ " ~ "
					+ cityWeatherList.get(position).getMainTempmaxString());
			tvMainHumidityString.setText(cityWeatherList.get(position)
					.getMainHumidityString());
			tvMainPressureString.setText(cityWeatherList.get(position)
					.getMainPressureString());
			tvWindSpeedString.setText(cityWeatherList.get(position)
					.getWindSpeedString());			
			
			Bitmap bitmap = BitmapFactory.decodeByteArray(
					cityWeatherList.get(position).getIconData(), 0,
					cityWeatherList.get(position).getIconData().length);			

			// test
			byte[] iconData = cityWeatherList.get(position).getIconData();
			String s = new String(iconData);
	        System.out.println("iconData: " + position + " : " + s);
	        System.out.println("bitmap£º " + bitmap);
	        
			ivWeatherIcon.setScaleType(ScaleType.FIT_XY);
			ivWeatherIcon.setImageBitmap(bitmap);
		} else {
			Toast.makeText(context, "Cannot get weather data",
					Toast.LENGTH_SHORT).show();
		}
		return convertView;
	}

}
