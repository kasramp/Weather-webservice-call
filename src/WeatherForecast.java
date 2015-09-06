/* 
 * This file is part of Weather web-service call.
 * Weather web-service call is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3
 * as published by the Free Software Foundation.
 *
 * Weather web-service call is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.  <http://www.gnu.org/licenses/>
 *
 * Author(s):
 * © 2015 Kasra Madadipouya <kasra@madadipouya.com>
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

	/**
	 * @param args
	 */

	
	public TreeMap<String,String> getWeather()
	{
		TreeMap<String,String> results = new TreeMap<String,String>();
		try {
			
			// Construct request String
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter country name");
			String country = input.next(); 
			System.out.println("Please enter city name");
			String city = input.next();
			String urlString = "http://api.openweathermap.org/data/2.5/weather?q=";
			
			if ((country != null && country.length()>0)&& (city != null && city.length()>0)){
				
				urlString += city + "," + country;
				
			} else {
				
				results.put("Country-City error : ", "Country or City name did not provided!!");
				throw new Exception();
				
			}
			
			urlString += "&units=metric";
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept","application/json");
			
			if(conn.getResponseCode() != 200){
				
				int errorCodeInt = conn.getResponseCode();
				results.put("HTTP error code : ", Integer.toString(errorCodeInt));
				results.put("HTTP error message : ", conn.getResponseMessage());
				throw new Exception();
					
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output = null;
			//System.out.println("Result from HTTP request!");
			output = br.readLine();
			
			if(output != null){
				
				JSONObject jsonObject = new JSONObject(output);
				JSONObject sys = jsonObject.getJSONObject("sys");
				JSONArray weather = jsonObject.getJSONArray("weather");
				JSONObject main = jsonObject.getJSONObject("main");
				JSONObject wind = jsonObject.getJSONObject("wind");
				results.put("Country", sys.getString("country"));
			    results.put("Weather description",weather.getJSONObject(0).getString("description"));
				results.put("Current temperature", Double.toString(main.getDouble("temp")) + " °C");
				results.put("Maximum temperature", Double.toString(main.getDouble("temp_max")) + " °C");
				results.put("Minimum temperature", Double.toString(main.getDouble("temp_min")) + " °C");
				results.put("Humidity level", Double.toString(main.getDouble("humidity")));
				results.put("Wind speed", Double.toString(wind.getDouble("speed")) + " Km");
			} else {
				
				throw new Exception("Unable get result from web-service call!");
				
			}
			conn.disconnect();
			return results;
				
		} catch (Exception ex) {
			
			ex.printStackTrace();
			return results;
			
		}
	}

}
