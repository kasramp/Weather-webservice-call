Weather-webservice-call
=======================

Simple command line weather forecast

This application calls  Openweathermap.org Weather forecast web-service 
to get information about the weather condition of the entered location.
Link of Open Weather Map API
http://openweathermap.org/API#weather
For calling web-service, Http method is used which is quite simple and reliable.
The result recieved from web-service is parsed with JSON library which is also attached to the project.

For compling the application in the command line

Old Way [Deprecated] :

	$ javac -cp ../lib/org.json-20120521.jar CallWebservice.java WeatherForecast.java

New Way :

	$ ant

And for runing the appication

Old Way [Deprecated] :

	$ java -cp .:../lib/org.json-20120521.jar CallWebservice

New Way :

	$ ant

##Contact
* kasra@madadipouya.com  
* kasra_mp@live.com  

##License
Weather web-service call is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License version 3
as published by the Free Software Foundation.

Weather web-service call is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.  <http://www.gnu.org/licenses/>

Author(s):

© 2015 Kasra Madadipouya <kasra@madadipouya.com>