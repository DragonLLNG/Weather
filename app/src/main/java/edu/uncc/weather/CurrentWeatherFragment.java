package edu.uncc.weather;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import edu.uncc.weather.databinding.FragmentCurrentWeatherBinding;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CurrentWeatherFragment extends Fragment {
    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private DataService.City mCity;
    FragmentCurrentWeatherBinding binding;

    private final OkHttpClient client = new OkHttpClient();

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    public static CurrentWeatherFragment newInstance(DataService.City city) {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = (DataService.City) getArguments().getSerializable(ARG_PARAM_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Current Weather");
        Log.d("demon", "run: "+Thread.currentThread().getId());
        TextView cityName = view.findViewById(R.id.textViewCityName);
        cityName.setText(mCity.getCity()+", "+mCity.getCountry());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewWeatherIcon);
        TextView temp = view.findViewById(R.id.textViewTemp);
        TextView maxTemp = view.findViewById(R.id.textViewTempMax);
        TextView minTemp = view.findViewById(R.id.textViewTempMin);
        TextView description = view.findViewById(R.id.textViewDesc);
        TextView humidity = view.findViewById(R.id.textViewHumidity);
        TextView windSpeed = view.findViewById(R.id.textViewWindSpeed);
        TextView windDeg = view.findViewById(R.id.textViewWindDegree);
        TextView cloudiness = view.findViewById(R.id.textViewCloudiness);


        String lat = String.valueOf(mCity.getLat());
        String lon = String.valueOf(mCity.getLon());
        final String api = "56d221f0c4150fec4c8b239a88aec52a";



        HttpUrl url = HttpUrl.parse("https://api.openweathermap.org/data/2.5/weather").newBuilder()
                .addQueryParameter("lat",lat)
                .addQueryParameter("lon",lon)
                .addQueryParameter("appid",api)
                .build();


        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if (response.isSuccessful()){
                    try {
                        String body = response.body().string();
                        JSONObject json = new JSONObject(body);

                        Log.d("demo", "onResponse: " + body);
                        Weather weathers = new Weather();

                        JSONObject jsonMain = json.getJSONObject("main");
                        Log.d("demo", "onResponse: " +jsonMain.get("temp"));
                        Log.d("demo", "onResponse: " +jsonMain.get("temp_max"));
                        Log.d("demo", "onResponse: " +jsonMain.get("temp_min"));
                        Log.d("demo", "onResponse: " +jsonMain.get("humidity"));

                        Main main = new Main();
                        main.setTemp(jsonMain.getDouble("temp"));
                        main.setTemp_max(jsonMain.getDouble("temp_max"));
                        main.setTemp_min(jsonMain.getDouble("temp_min"));
                        main.setHumidity(jsonMain.getInt("humidity"));
                        weathers.setMain(main);


                        JSONObject jsonWind = json.getJSONObject("wind");
                        Wind wind = new Wind();
                        Log.d("demo", "onResponse: " +jsonWind.get("speed"));
                        Log.d("demo", "onResponse: " +jsonWind.get("deg"));

                        wind.setSpeed(jsonWind.getDouble("speed"));
                        wind.setDeg(jsonWind.getDouble("deg"));
                        weathers.setWind(wind);


                        JSONObject jsonCloud = json.getJSONObject("clouds");
                        Cloud cloud = new Cloud();
                        Log.d("demo", "onResponse: " +jsonCloud.get("all"));
                        cloud.setAll(jsonCloud.getInt("all"));
                        weathers.setClouds(cloud);



                        ArrayList<Weathers> weathersArrayList = new ArrayList<>();
                        JSONArray weatherArray = json.getJSONArray("weather");
                        for (int i=0; i<weatherArray.length(); i++){
                            JSONObject weatherObject = weatherArray.getJSONObject(i);
                            Weathers weather = new Weathers();
                            weather.setId(weatherObject.getInt("id"));
                            weather.setMain(weatherObject.getString("main"));
                            weather.setDescription(weatherObject.getString("description"));
                            weather.setIcon(weatherObject.getString("icon"));
                            weathersArrayList.add(weather);

                            Log.d("demo", "onResponse: " +weathersArrayList.get(i).getDescription());
                        }

                        Log.d("demo", "run: "+Thread.currentThread().getId());

                        weathers.setWeather(weathersArrayList);


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("demo", "run: "+Thread.currentThread().getId());

                                temp.setText(weathers.getMain().getTemp()+"");
                                maxTemp.setText(weathers.getMain().getTemp_max()+"");
                                minTemp.setText(weathers.getMain().getTemp_min()+"");
                                description.setText(weathers.getWeather().get(0).getDescription()+"");
                                humidity.setText(weathers.getMain().getHumidity()+"");
                                windSpeed.setText(weathers.getWind().getSpeed()+"");
                                windDeg.setText(weathers.getWind().getDeg()+"");
                                cloudiness.setText(weathers.getClouds().getAll()+"");


                                String imageUrl = "https://openweathermap.org/img/wn/"+weathers.getWeather().get(0).getIcon()+"@2x.png";
                                Picasso.get().load(imageUrl).into(imageView);



                            }
                        });



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        });



        Button checkForecast = view.findViewById(R.id.buttonCheckForecast);
        checkForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoWeatherForeCast(mCity);

            }
        });

    }

    CurrentWeatherFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CurrentWeatherFragmentListener) context;
    }

    interface CurrentWeatherFragmentListener {
        void gotoWeatherForeCast(DataService.City city);
    }





}