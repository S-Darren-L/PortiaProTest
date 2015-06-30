package beans;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherHttpClient {
	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?units=metric&q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
 
     
    public String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;        

        //test
        try {
			URL url = new URL(BASE_URL + location);
	        System.out.println("URL: " + url.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + location)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();            
             
            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");
             
            is.close();
            con.disconnect();
            
//            //test
//            String bufferString = buffer.toString();
//            System.out.println("bufferString: " + bufferString);
            
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
 
        return null;
                 
    }
     
    public byte[] getImage(String code) {
        HttpURLConnection con = null ;
        InputStream is = null;
        try {
            con = (HttpURLConnection) ( new URL(IMG_URL + code)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
             
            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
             
//            while ( is.read(buffer) != -1)
//                baos.write(buffer);
            int len = -1;
            while ((len = is.read(buffer)) != -1)
                baos.write(buffer, 0, len);
            baos.close();
             
            return baos.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
         
        return null;
         
    }
}
