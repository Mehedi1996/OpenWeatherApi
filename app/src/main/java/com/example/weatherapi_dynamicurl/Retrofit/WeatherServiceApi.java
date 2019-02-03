package com.example.weatherapi_dynamicurl.Retrofit;

import com.example.weatherapi_dynamicurl.Model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;



public interface WeatherServiceApi {

    @GET("weather?lat=23.8103&lon=90.4125&units=metric&appid=6aa950a46a1afd8d9b41b5bfb6a4497a")
    Call<WeatherResponse>getCurrentWeatherResponse();

}
