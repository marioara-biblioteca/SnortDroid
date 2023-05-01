package com.example.snortdroid.cyber_news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.snortdroid.MainActivity;
import com.example.snortdroid.R;
import com.example.snortdroid.maps.MapsActivity;
import com.google.android.gms.maps.model.LatLng;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivityJson extends AppCompatActivity {
    private List<LatLng> latLngList;
    private String url="https://cyber-security-news.p.rapidapi.com/news/bbc";
    private String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_json);
        GetNews getNews=new GetNews(){
            @Override
            protected void onProgressUpdate(Integer... values) {
                ProgressBar pb = findViewById(R.id.progress);
                pb.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(List<CyberNews> news) {
                LinearLayout ll = findViewById(R.id.linearLayout);
                for(CyberNews n:news){

                    TextView url=new TextView(MainActivityJson.this);
                    TextView source=new TextView(MainActivityJson.this);

                    source.setText("Source: " + n.getSource());

                    SpannableString spannableString=new SpannableString(n.getTitle());
                    ClickableSpan clickableSpan=new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View view) {
                            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(n.getUrl()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        @Override
                        public void updateDrawState(@NonNull TextPaint ds) {
                            ds.setColor(Color.BLUE);
                        }
                    };
                    spannableString.setSpan(clickableSpan,0,n.getTitle().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    url.setText(spannableString);
                    url.setMovementMethod(LinkMovementMethod.getInstance());

                    ll.addView(url);
                    ll.addView(source);
                }


            }
        };

        Button goTomain=findViewById(R.id.goToMain);
        goTomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivityJson.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Spinner channelSpinner=findViewById(R.id.chooseChannel);
        ArrayAdapter<CharSequence> channelAdapter = ArrayAdapter.createFromResource(MainActivityJson.this,R.array.apiNews, android.R.layout.simple_spinner_dropdown_item );
        channelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        channelSpinner.setAdapter(channelAdapter);
        channelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //url+=((String)adapterView.getItemAtPosition(i));
                url="https://cyber-security-news.p.rapidapi.com/news/"+((String)adapterView.getItemAtPosition(i));
//                ProgressBar pb=findViewById(R.id.progress);
//                pb.setIndeterminate(false);
//                pb.setMax(1);
//                getNews.execute(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                url+="bbc";
                ProgressBar pb=findViewById(R.id.progress);
                pb.setIndeterminate(false);
                pb.setMax(1);
                getNews.execute(url);
            }
        });

        //aici obtin coordonatele pentru un oras ce apare in lista de stiri, introdus de la utilizator
        //coordonatele vor fi trimise catre harta
        Button getCoords=findViewById(R.id.getCoordinates);
        getCoords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Geocoder.isPresent()){
                    try {
                        EditText locationEd=findViewById(R.id.enterCity);
                        city = locationEd.getText().toString();
                        Geocoder gc = new Geocoder(MainActivityJson.this);
                        List<Address> addresses= gc.getFromLocationName(city, 5); // get the found Address Objects

                        latLngList = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                        for(Address a : addresses){
                            if(a.hasLatitude() && a.hasLongitude()){
                                latLngList.add(new LatLng(a.getLatitude(), a.getLongitude()));
                            }
                        }
                        System.out.println(latLngList);
                    } catch (IOException e) {
                        // handle the exception
                    }
                }

            }
        });


        Button goToMap=findViewById(R.id.goToMap);
        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivityJson.this,MapsActivity.class);
                intent.putExtra("latitude",String.valueOf(latLngList.get(0).latitude));
                intent.putExtra("longitude",String.valueOf(latLngList.get(0).longitude));
                intent.putExtra("city",city);
                startActivity(intent);
            }
        });

        ProgressBar pb=findViewById(R.id.progress);
        pb.setIndeterminate(false);
        pb.setMax(1);
        getNews.execute(url);


    }

}
