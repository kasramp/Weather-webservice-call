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

For more information contact,
kasra@madadipouya.com 
kasra_mp@live.com
