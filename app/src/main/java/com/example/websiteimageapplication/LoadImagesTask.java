package com.example.websiteimageapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class LoadImagesTask extends AsyncTask<ArrayList<String>, Void, ArrayList<String>> {

    public static final String TAG = LoadImagesTask.class.getSimpleName();


    @Override
    protected ArrayList<String> doInBackground(ArrayList<String>... urls) {

        Log.i("trace : Input size ", "" + urls[0].size());
        int error = 0;

        ArrayList<String> output = new ArrayList<>();

        for (String url : urls[0]) {

            StringBuffer buffer = new StringBuffer();
            try {
                Document doc1 = Jsoup.connect(url).get();
                //Elements img = doc1.getElementsByTag("img");
                Elements img2 = doc1.getElementsByTag("meta");
                for (Element element : img2) {
                    if ("og:image".equals(element.attr("property"))) {
                        // System.out.println(element.attr("content"));
                        Log.i("trace : ", "" + element.attr("content"));
                        output.add(element.attr("content"));
                    }
                }

            } catch (IOException e) {
                error++;
                e.printStackTrace();

            }
        }
        Log.i("trace : Error size ", "" + error);
        Log.i("trace : Output size ", "" + output.size());
        return output;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        MainActivity.bindImages(strings);
    }
}
