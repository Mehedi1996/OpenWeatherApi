package com.example.weatherapi_dynamicurl.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapi_dynamicurl.Model.WeatherResponse;
import com.example.weatherapi_dynamicurl.Retrofit.WeatherServiceApi;
import com.example.weatherapi_dynamicurl.R;
import com.example.weatherapi_dynamicurl.Retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   TextView textView;

    private WeatherServiceApi weatherServiceApi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvWeather);

        weatherServiceApi = ApiClient.getRetrofitInstance().create(WeatherServiceApi.class);


        //String urlString = String.format("weather?q=%s&units=%s&appid=%s", name, units, getString(R.string.weather_api));

        Call<WeatherResponse> weatherResponseCall = weatherServiceApi.getCurrentWeatherResponse();

        weatherResponseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();

                    if (weatherResponse != null) {
                        String city = weatherResponse.getName();
                        double temp = weatherResponse.getMain().getTemp();
                        int humidity = weatherResponse.getMain().getHumidity();
                        double speed = weatherResponse.getWind().getSpeed();
                        String description =weatherResponse.getSys().getCountry();
                        int sunrise =weatherResponse.getSys().getSunrise();



                        textView.setText("Country: "+description+"\n"+"City: "+city+"\n"+"Temp: "+temp+"\n"+"Humidity: "+humidity+"\n"+"Speed: "
                                +speed+"\n"+"Sunrise: "+sunrise);

                        Toast.makeText(MainActivity.this, "" + city + "\n" + temp, Toast.LENGTH_LONG).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}
