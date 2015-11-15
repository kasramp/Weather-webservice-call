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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.TreeMap;
import com.google.gson.Gson;

public class WeatherForecast {
	private TreeMap<String,String> countriesCodeName;
	private Boolean isUserQuery;
	public WeatherForecast() {
		this.countriesCodeName = new TreeMap<String,String>();
		this.isUserQuery = false;
	}
	public TreeMap<String,String> getWeather(Boolean isUserQuery) {
		this.isUserQuery = isUserQuery;
		return this.getWeather();
	}
	public TreeMap<String,String> getWeather()
	{
		loadCountriesToMap();
		TreeMap<String,String> results = new TreeMap<String,String>();
		try {
			String city = "";
			String country = "";
			String region = "";
			if(this.isUserQuery) {
				// Construct request String
				Scanner input = new Scanner(System.in);
				System.out.println("Please enter country name");
				country = input.next();
				country = country.trim();
				System.out.println("Please enter city name");
				city = input.next();
				if(city.length()>2) {
					city = Character.toUpperCase(city.charAt(0)) + city.substring(1).toLowerCase();
				}
				//city = city.trim();
				input.close(); 
			} else {
				LocationDetectorResponseJson ldrj = new LocationDetectorResponseJson();
				try {
					ldrj = ldrj.detectLocation();
				} catch(Exception ex) {
					ex.printStackTrace();
					results = ldrj.error;
					throw new Exception("Error in detecting location function");
				}
				country = ldrj.country;
				city = ldrj.city;
				region = ldrj.region + ",";
			}
			String urlString = "http://api.openweathermap.org/data/2.5/weather?appid=c15e2598880e57fad011a64061948fac&q=";
			if ((country != null && country.length()>0)&& (city != null && city.length()>0)){
				urlString += city + "," + country;
			} else {
				results.put("Country-City error : ", "Country or City name have not been provided!!");
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
			output = br.readLine();
			if(output != null){
				WebServiceResponseJson resultClass = parseJsonObj(output);
				results.put("Country", countriesCodeName.get(resultClass.sys.country));
				results.put("City", (region.isEmpty()?"":region) + city);
				results.put("Weather description", resultClass.weather.get(0).description);
				results.put("Current temperature", resultClass.main.temp.toString() + " °C");
				results.put("Maximum temperature", resultClass.main.temp_max.toString() + " °C");
				results.put("Minimum temperature", resultClass.main.temp_min.toString() + " °C");
				results.put("Humidity level", resultClass.main.humidity.toString());
				results.put("Wind speed", resultClass.wind.speed.toString() + " Km");
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
	private WebServiceResponseJson parseJsonObj(String jsonString) {
		WebServiceResponseJson result = null;
		try {
			Gson gson = new Gson();
			result = gson.fromJson(jsonString,WebServiceResponseJson.class);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	private void loadCountriesToMap() {
		InputStream in = getClass().getResourceAsStream("/countries.txt"); 
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split("[|]");
				countriesCodeName.put(values[0], values[1]);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
