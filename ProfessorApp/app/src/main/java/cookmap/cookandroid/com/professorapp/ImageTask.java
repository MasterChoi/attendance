package cookmap.cookandroid.com.professorapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016-02-01.
 */
public class ImageTask extends AsyncTask<String, Integer, Bitmap> {
    Bitmap bmImg;
    ImageView imView;
    ImageTask(ImageView i){
        imView = i;
    }
    @Override
    protected Bitmap doInBackground(String... urls) {
        // TODO Auto-generated method stub
        try{
            URL myFileUrl = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();

            bmImg = BitmapFactory.decodeStream(is);


        }catch(IOException e){
            e.printStackTrace();
        }
        return bmImg;
    }

    protected void onPostExecute(Bitmap img){
        imView.setImageBitmap(bmImg);
    }

}