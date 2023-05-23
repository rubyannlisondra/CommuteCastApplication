package com.example.commutecast;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import java.text.DecimalFormat;

public class Weather extends AppCompatActivity {
    EditText etCity, etCountry;
    TextView tvResult1, tvResult2, tvResult3, tvResult4;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "794d19f99581be3c58b7e56a0b261c18";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        tvResult1 = findViewById(R.id.tvResult1);
        tvResult2 = findViewById(R.id.tvResult2);
        tvResult3 = findViewById(R.id.tvResult3);
        tvResult4 = findViewById(R.id.tvResult4);
    }

    public void getWeatherDetails(View view) {
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if(city.equals("")){
            tvResult1.setText("City field can not be empty!");
        }else{
            if(!country.equals("")){
                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
            }else{
                tempUrl = url + "?q=" + city + "&appid=" + appid;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String output1 = "";
                    String output2 = "";
                    String output3 = "";
                    String output4 = "";

                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        float pressure = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");
                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");
                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                        String clouds = jsonObjectClouds.getString("all");
                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");
                        String cityName = jsonResponse.getString("name");
                        long sunriseTimestamp = jsonObjectSys.getLong("sunrise");
                        long sunsetTimestamp = jsonObjectSys.getLong("sunset");


                        // Assuming you have extracted the sunriseTimestamp and sunsetTimestamp from the JSON response

                        // Get the timezone offset in seconds
                        int timezoneOffsetSeconds = jsonResponse.getInt("timezone");

                        // Adjust the sunrise and sunset timestamps
                        long adjustedSunriseTimestamp = (sunriseTimestamp + timezoneOffsetSeconds) * 1000;
                        long adjustedSunsetTimestamp = (sunsetTimestamp + timezoneOffsetSeconds) * 1000;

                        // Create a SimpleDateFormat with the desired format and timezone
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                        sdf.setTimeZone(TimeZone.getDefault());

                        // Format the adjusted timestamps into human-readable times
                        String sunriseTime = sdf.format(new Date(adjustedSunriseTimestamp));
                        String sunsetTime = sdf.format(new Date(adjustedSunsetTimestamp));


                        output1 += "Current weather of " + cityName + " (" + countryName + ")"
                                + "\n Temp: " + df.format(temp) + " °C"
                                + "\n Feels Like: " + df.format(feelsLike) + " °C"
                                + "\n Humidity: " + humidity + "%"
                                + "\n Description: " + description;

                        output2 += "Wind Speed: " + wind + "m/s (meters per second)";

                        output3 += "Cloudiness: " + clouds + "%"
                                + "\n Pressure: " + pressure + " hPa";

                        output4 += "Sunrise: " + sunriseTime
                                + "\n Sunset: " + sunsetTime;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Set the outputs to the respective TextViews
                    tvResult1.setText(output1);
                    tvResult2.setText(output2);
                    tvResult3.setText(output3);
                    tvResult4.setText(output4);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}