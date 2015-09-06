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
import java.util.TreeMap;
import java.util.Map.Entry;


public class CallWebservice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeatherForecast wfc = new WeatherForecast();
		TreeMap <String,String> results = new TreeMap<String,String>(); 
		results = wfc.getWeather();
		for (Entry<String, String> entry : results.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();

		    System.out.printf("%s : %s\n", key, value);
		}
		
	}
}
