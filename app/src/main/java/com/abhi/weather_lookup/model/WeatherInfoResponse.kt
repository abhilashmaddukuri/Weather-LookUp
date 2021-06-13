package com.abhi.weather_lookup.model

data class WeatherInfoResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: ArrayList<WeatherInfo>,
    val city: City
)

data class WeatherInfo(
    val dt: Long,
    val main: MainData,
    val weather: ArrayList<WeatherData>,
    val clouds: CloudsData,
    val wind: WindData,
    val visibility: Int,
    val pop: Float,
    val sys: SysData,
    val dt_txt: String
)

data class City(
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Long,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)

data class Coord(
    val lat: Float,
    val lon: Float
)

data class MainData(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Float
)

data class WeatherData(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class CloudsData(
    val all: Int
)

data class WindData(
    val speed: Float,
    val deg: Int,
    val gust: Float
)

data class SysData(
    val pod: String
)