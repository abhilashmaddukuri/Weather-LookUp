# Weather-LookUp

Project 'Weather-LookUp' is a coding challenge exercise which retrieves weather details from openweathermap.org API, parse data and display as shown in the below screenshots. 

<img src="https://github.com/abhilashmaddukuri/Weather-LookUp/blob/master/screenshots/EnterCityName.jpeg?" width="200" height="390">    <img src="https://github.com/abhilashmaddukuri/Weather-LookUp/blob/master/screenshots/WeatherInfoList.jpeg?" width="200" height="390">    <img src="https://github.com/abhilashmaddukuri/Weather-LookUp/blob/master/screenshots/WeatherInfoDetail.jpeg?" width="200" height="390">

## Main Features: 
- Fetch results from below API
  - https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={api_key}&units=imperial
- Parse and display response in the recycler-view(with date/time), and onclick of any weather info displays the detailed description
- Validated the city name, and Display generic error message when no response is received from API

## Core Architectural components used: 
* MVVM Architecture with LiveData
* Kotlin/XML
* Retrofit
* Dagger2
* Data Binding
* Coroutine
