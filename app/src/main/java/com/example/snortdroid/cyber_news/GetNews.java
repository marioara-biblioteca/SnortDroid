package com.example.snortdroid.cyber_news;

import android.os.AsyncTask;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class GetNews extends AsyncTask<String, Integer, List<CyberNews>> {
    @Override
    protected List<CyberNews> doInBackground(String... strings) {
        URL url= null;
        Integer index=0;
        List<CyberNews> news=new ArrayList<>();
        try {
            url = new URL(strings[0]);
            HttpURLConnection http=(HttpURLConnection)url.openConnection();
            http.setRequestProperty("Content-Type","application/octet-stream");
            http.setRequestProperty("X-RapidAPI-Key", "f52bb558fcmsha7cabad9a73d3abp159410jsn14728b3e1d58");
            http.setRequestProperty("X-RapidAPI-Host", "cyber-security-news.p.rapidapi.com");
            http.setRequestMethod("GET");

            InputStream inputStream=http.getInputStream();
            StringBuilder response = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            JSONParser parser = new JSONParser();
            Object object =parser.parse(response.toString());
            JSONArray jsonArr=( ((JSONArray) object));
            for(int i=0;i<jsonArr.size();i++) {
                news.add(
                        new CyberNews(
                                ((JSONObject) jsonArr.get(i)).get("title").toString(),
                                ((JSONObject) jsonArr.get(i)).get("url").toString().substring(23),
                                ((JSONObject) jsonArr.get(i)).get("source").toString()

                        ));
                Thread.sleep(1000);
                publishProgress(++index);
            }
            } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }  catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return news;
    }

    protected abstract void onProgressUpdate(Integer... values);

    protected abstract void onPostExecute(List<CyberNews> news);
}

