package com.example.commutecast;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Weather extends Fragment {
    private EditText etCity, etCountry;
    private TextView tvResult1, tvResult11, tvResult2, tvResult3, tvResult4, tvResult8, tvResult9;
    private Button btnGetWeather, btnConvert;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "794d19f99581be3c58b7e56a0b261c18";
    private DecimalFormat df = new DecimalFormat("#.##");
    private boolean isCelsius = true; // Flag to track the current unit (Celsius or Fahrenheit)
    private Button btnGoToList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);

        etCity = rootView.findViewById(R.id.etCity);
        etCountry = rootView.findViewById(R.id.etCountry);
        tvResult1 = rootView.findViewById(R.id.tvResult1);
        tvResult11 = rootView.findViewById(R.id.tvResult11);
        tvResult2 = rootView.findViewById(R.id.tvResult2);
        tvResult3 = rootView.findViewById(R.id.tvResult3);
        tvResult4 = rootView.findViewById(R.id.tvResult4);
        tvResult8 = rootView.findViewById(R.id.tvResult8);
        tvResult9 = rootView.findViewById(R.id.tvResult9);
        btnGetWeather = rootView.findViewById(R.id.btnGet);
        btnConvert = rootView.findViewById(R.id.btnConvert);

        btnGoToList = rootView.findViewById(R.id.btnGoToList);
        btnGoToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the ListActivity
                Intent intent = new Intent(getActivity(), List.class);
                startActivity(intent);
            }
        });


        btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherDetails();
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToCelsiusOrFahrenheit();
            }
        });

        return rootView;
    }

    private void getWeatherDetails() {
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();

        if (city.isEmpty()) {
            Toast.makeText(requireContext(), "City field cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        String tempUrl;
        if (!country.isEmpty()) {
            tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
        } else {
            tempUrl = url + "?q=" + city + "&appid=" + appid;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, tempUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("weather");

                            JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                            String description = jsonObjectWeather.getString("description");
                            String capitalizedDescription = capitalizeFirstLetter(description);

                            JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                            double temp = jsonObjectMain.getDouble("temp") - 273.15;
                            double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                            int humidity = jsonObjectMain.getInt("humidity");

                            JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                            String countryName = jsonObjectSys.getString("country");
                            String cityName = jsonResponse.getString("name");

                            long sunriseTimestamp = jsonObjectSys.getLong("sunrise");
                            long sunsetTimestamp = jsonObjectSys.getLong("sunset");
                            int timezoneOffsetSeconds = jsonResponse.getInt("timezone");
                            long adjustedSunriseTimestamp = (sunriseTimestamp + timezoneOffsetSeconds) * 1000;
                            long adjustedSunsetTimestamp = (sunsetTimestamp + timezoneOffsetSeconds) * 1000;
                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                            sdf.setTimeZone(TimeZone.getDefault());
                            String sunriseTime = sdf.format(new Date(adjustedSunriseTimestamp));
                            String sunsetTime = sdf.format(new Date(adjustedSunsetTimestamp));

                            String output1 = cityName + " (" + countryName + ")";
                            String output11 = capitalizedDescription;
                            String output2 = df.format(temp) + " °C";
                            String output3 = "Feels Like: \n" + df.format(feelsLike) + " °C";
                            String output4 = "Humidity: \n" + humidity + "%";
                            String output8 = "Sunrise: \n" + sunriseTime;
                            String output9 = "Sunset: \n" + sunsetTime;

                            // Set the outputs to the respective TextViews
                            tvResult1.setText(output1);
                            tvResult11.setText(output11);
                            tvResult2.setText(output2);
                            tvResult3.setText(output3);
                            tvResult4.setText(output4);
                            tvResult8.setText(output8);
                            tvResult9.setText(output9);

                            double temperatureThreshold = 30.0; // Set the temperature threshold
                            if (feelsLike >= temperatureThreshold) {
                                showTemperatureAlertDialog(feelsLike, countryName, cityName);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(requireContext(), "Failed to parse weather data!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }

    private String capitalizeFirstLetter(String text) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");
        for (String word : words) {
            String capitalizedWord = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            result.append(capitalizedWord).append(" ");
        }
        return result.toString().trim();
    }

    private void showTemperatureAlertDialog(double feelsLike, String countryName, String cityName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("AAAAAAAAAAAAAAAAAAAAAAA");
        builder.setMessage("It feels like " + df.format(feelsLike) + " °C in " + cityName + ", " + countryName + ". Please wear something light! (or else magkaligo kag singot doh!!)");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void convertToCelsiusOrFahrenheit() {
        double tempValue;
        double feelsLikeValue;

        if (isCelsius) {
            // Convert Celsius to Fahrenheit
            tempValue = Double.parseDouble(tvResult2.getText().toString().split(" ")[0]);
            feelsLikeValue = Double.parseDouble(tvResult3.getText().toString().split(" ")[2]);
            double tempFahrenheit = (tempValue * 9/5) + 32;
            double feelsLikeFahrenheit = (feelsLikeValue * 9/5) + 32;

            tvResult2.setText(df.format(tempFahrenheit) + " °F");
            tvResult3.setText("Feels Like: \n" + df.format(feelsLikeFahrenheit) + " °F");

            btnConvert.setText("Convert to Celsius");
            isCelsius = false;
        } else {
            // Convert Fahrenheit to Celsius
            tempValue = Double.parseDouble(tvResult2.getText().toString().split(" ")[0]);
            feelsLikeValue = Double.parseDouble(tvResult3.getText().toString().split(" ")[2]);
            double tempCelsius = (tempValue - 32) * 5/9;
            double feelsLikeCelsius = (feelsLikeValue - 32) * 5/9;

            tvResult2.setText(df.format(tempCelsius) + " °C");
            tvResult3.setText("Feels Like: \n" + df.format(feelsLikeCelsius) + " °C");

            btnConvert.setText("Convert to Fahrenheit");
            isCelsius = true;
        }
    }
}
