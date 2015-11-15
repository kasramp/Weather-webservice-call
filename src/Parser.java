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
import java.util.Map;
import java.util.TreeMap;
public class Parser {
	private Map<String,Boolean> results;
	public static final String QUERY = "--query";
	public Parser() {
		this.results = new TreeMap<String,Boolean>();
	}
	public void parse(String[] args) {
		try {
			for(String arg : args) {
				this.results.put(arg,true);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public boolean has(String val) {
		try {
			Boolean rtn = this.results.get(val);
			if(rtn != null) {
				return rtn;
			} else {
				return false;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
