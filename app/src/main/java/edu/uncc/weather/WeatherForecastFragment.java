package edu.uncc.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherForecastFragment extends Fragment {

    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private DataService.City mCity;

    private final OkHttpClient client = new OkHttpClient();

    public WeatherForecastFragment() {
        // Required empty public constructor
    }

    public static WeatherForecastFragment newInstance(DataService.City city) {
        WeatherForecastFragment fragment = new WeatherForecastFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Weather Forecast");


        TextView cityName = view.findViewById(R.id.textViewCityName);
        cityName.setText(mCity.getCity()+", "+mCity.getCountry());

        ArrayList<Forecast> list = new ArrayList<>();




        String lat = String.valueOf(mCity.getLat());
        String lon = String.valueOf(mCity.getLon());
        final String api = "56d221f0c4150fec4c8b239a88aec52a";
        //Weather weathers = new Weather();

        HttpUrl url = HttpUrl.parse("https://api.openweathermap.org/data/2.5/forecast").newBuilder()
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

                        JSONArray jsonList = json.getJSONArray("list");

                        for(int i=0; i<jsonList.length(); i++){
                            JSONObject weatherJson = jsonList.getJSONObject(i);
                            Forecast forecast = new Forecast();

                            JSONObject jsonMain = weatherJson.getJSONObject("main");
                            Log.d("demo1", "onResponse: " +jsonMain.get("temp"));
                            Log.d("demo1", "onResponse: " +jsonMain.get("temp_max"));
                            Log.d("demo1", "onResponse: " +jsonMain.get("temp_min"));
                            Log.d("demo1", "onResponse: " +jsonMain.get("humidity"));

                            Main main = new Main();
                            main.setTemp(jsonMain.getDouble("temp"));
                            main.setTemp_max(jsonMain.getDouble("temp_max"));
                            main.setTemp_min(jsonMain.getDouble("temp_min"));
                            main.setHumidity(jsonMain.getInt("humidity"));

                            ArrayList<Weathers> weathersArrayList = new ArrayList<>();
                            JSONArray weatherArray = weatherJson.getJSONArray("weather");
                            for (int j=0; j<weatherArray.length(); j++){
                                JSONObject weatherObject = weatherArray.getJSONObject(j);
                                Weathers weather = new Weathers();
                                weather.setId(weatherObject.getInt("id"));
                                weather.setMain(weatherObject.getString("main"));
                                weather.setDescription(weatherObject.getString("description"));
                                weather.setIcon(weatherObject.getString("icon"));
                                weathersArrayList.add(weather);

                                Log.d("demo1", "onResponse: " +weathersArrayList.get(j).getDescription());
                            }

                            Log.d("demo1", "run: "+Thread.currentThread().getId());

                            forecast.setMain(main);
                            forecast.setWeather(weathersArrayList);
                            forecast.setDt_txt(weatherJson.getString("dt_txt"));
                            Log.d("demo1", "onResponse: " +weatherJson.getString("dt_txt"));

                            list.add(forecast);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("demo", "run: "+Thread.currentThread().getId());

                                ListView listView = view.findViewById(R.id.listView);
                                ForecastAdapter adapter;
                                adapter = new ForecastAdapter(getActivity().getBaseContext(), R.layout.forecast_row_item, list);
                                listView.setAdapter(adapter);

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        });



    }
}