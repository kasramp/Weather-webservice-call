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
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

public class LocationDetectorResponseJson {
	public static final String URL = "http://ipinfo.io/json";
	@SerializedName("coord")
	public String ip;
	@SerializedName("hostname")
	public String hostName;
	@SerializedName("city")
	public String city;
	@SerializedName("region")
	public String region;
	@SerializedName("country")
	public String country;
	@SerializedName("loc")
	public String loc;
	@SerializedName("org")
	public String org;
	@SerializedName("postal")
	public String postal;
	public TreeMap<String,String> error = new TreeMap<String,String>();
	public LocationDetectorResponseJson() {
		this.ip = "";
		this.hostName = "";
		this.city = "";
		this.region = "";
		this.country = "";
		this.loc = "";
		this.org = "";
		this.postal = "";
	}
	public LocationDetectorResponseJson detectLocation() throws Exception {
		try {
			URL url = new URL(URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept","application/json");
			if(conn.getResponseCode() != 200){
				int errorCodeInt = conn.getResponseCode();
				this.error.put("HTTP error code : ", Integer.toString(errorCodeInt));
				this.error.put("HTTP error message : ", conn.getResponseMessage());
				throw new Exception();	
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output = "";
			String line = "";
			while ((line = br.readLine()) != null) {
				output+= line;
			}
			output = output.trim();
			//output = br.readLine();
			if(output != null){
				try {
					Gson gson = new Gson();
					return gson.fromJson(output,LocationDetectorResponseJson.class);
				}
				catch(Exception ex) {
					this.error.put("Cannot parse response", "Cannot parse response");
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return null;
	}
}
