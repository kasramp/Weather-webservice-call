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
