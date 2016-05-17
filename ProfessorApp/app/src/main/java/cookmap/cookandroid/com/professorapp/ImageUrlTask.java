package cookmap.cookandroid.com.professorapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by EmbLab on 2016-02-14.
 */
public class ImageUrlTask extends AsyncTask<Void,Void,Void> {
    String re;
    String stid;
    ImageView imageView;
    ImageUrlTask(String id, ImageView i){
        stid = id;
        imageView = i;
    }
    @Override
    protected Void doInBackground(Void... params){
        try{
            Socket sendSocket=new Socket("52.69.56.103",8888);
            DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
            ObjectInputStream ois= new ObjectInputStream(sendSocket.getInputStream());

            oo.writeUTF("imageUrl");
            oo.writeUTF(stid);
            re = (String)ois.readObject();
            oo.close();
            ois.close();
            sendSocket.close();

        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    protected void  onPostExecute(Void result) {
        super.onPostExecute(result);
        ImageTask imageTask = new ImageTask(imageView);
        imageTask.execute(re);
    }

}
