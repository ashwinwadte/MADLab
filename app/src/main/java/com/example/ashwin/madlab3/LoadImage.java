package com.example.ashwin.madlab3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ashwin on 10/28/2015.
 */
public class LoadImage extends AsyncTask<String, Integer, Bitmap> {
    String uri;

    //public LoadImage(String uri) {
    //this.uri = uri;
    //}

    @Override
    protected Bitmap doInBackground(String... params) {
        uri = params[0];
        Bitmap bitmap = null;
        InputStream inputStream;
        int response = -1;

        URL url = null;
        try {
            url = new URL(uri);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection))
                throw new IOException("URL is not an Http url!");

            HttpURLConnection conn = (HttpURLConnection) urlConnection;
            conn.setRequestMethod("GET");
            conn.connect();

            response = conn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                inputStream = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
