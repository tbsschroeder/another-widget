package com.tommasoberlose.anotherwidget.helpers

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.kwabenaberko.openweathermaplib.constants.Units
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper
import com.kwabenaberko.openweathermaplib.implementation.callbacks.CurrentWeatherCallback
import com.kwabenaberko.openweathermaplib.models.currentweather.CurrentWeather
import com.tommasoberlose.anotherwidget.R
import com.tommasoberlose.anotherwidget.global.Preferences
import com.tommasoberlose.anotherwidget.network.WeatherNetworkApi
import com.tommasoberlose.anotherwidget.ui.widgets.MainWidget


/**
 * Created by tommaso on 08/10/17.
 */

object WeatherHelper {

    fun updateWeather(context: Context) {
        val networkApi = WeatherNetworkApi(context)
        if (Preferences.customLocationAdd != "") {
            networkApi.updateWeather()
        } else {
            LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener {
                Preferences.customLocationLat = it.latitude.toString()
                Preferences.customLocationLon = it.longitude.toString()

                networkApi.updateWeather()
            }
        }
    }

    fun removeWeather(context: Context) {
        Preferences.remove(Preferences::weatherTemp)
        Preferences.remove(Preferences::weatherTempUnit)
        MainWidget.updateWidget(context)
    }

    fun getWeatherIconResource(icon: String): Int {
        when (icon) {
            "01d" -> {
                return R.drawable.clear_day
            }
            "02d" -> {
                return R.drawable.partly_cloudy
            }
            "03d" -> {
                return R.drawable.mostly_cloudy
            }
            "04d" -> {
                return R.drawable.cloudy_weather
            }
            "09d" -> {
                return R.drawable.storm_weather_day
            }
            "10d" -> {
                return R.drawable.rainy_day
            }
            "11d" -> {
                return R.drawable.thunder_day
            }
            "13d" -> {
                return R.drawable.snow_day
            }
            "50d" -> {
                return R.drawable.haze_day
            }
            "80d" -> {
                return R.drawable.windy_day
            }
            "81d" -> {
                return R.drawable.rain_snow_day
            }
            "82d" -> {
                return R.drawable.haze_weather
            }



            "01n" -> {
                return R.drawable.clear_night
            }
            "02n" -> {
                return R.drawable.partly_cloudy_night
            }
            "03n" -> {
                return R.drawable.mostly_cloudy_night
            }
            "04n" -> {
                return R.drawable.cloudy_weather
            }
            "09n" -> {
                return R.drawable.storm_weather_night
            }
            "10n" -> {
                return R.drawable.rainy_night
            }
            "11n" -> {
                return R.drawable.thunder_night
            }
            "13n" -> {
                return R.drawable.snow_night
            }
            "50n" -> {
                return R.drawable.haze_night
            }
            "80n" -> {
                return R.drawable.windy_night
            }
            "81n" -> {
                return R.drawable.rain_snow_night
            }
            "82n" -> {
                return R.drawable.haze_weather
            }
            else -> {
                return R.drawable.unknown
            }
        }
    }
}