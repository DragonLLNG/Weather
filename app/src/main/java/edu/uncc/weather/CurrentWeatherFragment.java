package edu.uncc.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
                        JSONObject json = new JSONObject(response.body().string());

                        Weather weather = new Weather();

                        JSONObject main = json.getJSONObject("main");


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


            }
        });

    }

}