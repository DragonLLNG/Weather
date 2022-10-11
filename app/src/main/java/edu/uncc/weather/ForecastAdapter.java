package edu.uncc.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ForecastAdapter extends ArrayAdapter<Forecast> {

    public ForecastAdapter(@NonNull Context context, int resource, @NonNull List<Forecast> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.forecast_row_item,parent,false);
        }
        Forecast forecast = getItem(position);


        ImageView imageView = convertView.findViewById(R.id.imageViewWeatherIcon);
        TextView date =  convertView.findViewById(R.id.textViewDateTime);
        TextView temp =  convertView.findViewById(R.id.textViewTemp);
        TextView max_temp =  convertView.findViewById(R.id.textViewTempMax);
        TextView min_temp = convertView.findViewById(R.id.textViewTempMin);
        TextView humid = convertView.findViewById(R.id.textViewHumidity);
        TextView description = convertView.findViewById(R.id.textViewDesc);


        date.setText(forecast.getDt_txt());
        temp.setText(forecast.getMain().getTemp()+"");
        max_temp.setText(forecast.getMain().getTemp_max()+"");
        min_temp.setText(forecast.getMain().getTemp_min()+"");
        humid.setText(forecast.getMain().getHumidity()+"");
        description.setText(forecast.getWeather().get(0).getDescription());


        String imageUrl = "https://openweathermap.org/img/wn/"+forecast.getWeather().get(0).getIcon()+"@2x.png";
        Picasso.get().load(imageUrl).into(imageView);


        return convertView;
    }

}
