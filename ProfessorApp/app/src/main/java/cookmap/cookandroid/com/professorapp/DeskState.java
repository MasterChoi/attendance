package cookmap.cookandroid.com.professorapp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016-01-28.
 */
public class DeskState extends AsyncTask<Void,Void,Void> {
        String re;
        String order = "";

        TextView textView, nameView;
        ImageView imageView;
        Button bt[];
        boolean flag = false;
        DeskState(Button b[]){
                bt = b;
        }
        DeskState(String kk, TextView t){
                order = kk;
                textView =t;
        }
        DeskState(String kk, TextView t, TextView n, ImageView i){
                order = kk;
                textView = t;
                nameView = n;
                imageView = i;
        }

        private boolean va = false;
        @Override
        protected Void doInBackground(Void... params){
                try{
                        Socket sendSocket=new Socket("52.69.56.103",8888);
                        DataOutputStream oo = new DataOutputStream(sendSocket.getOutputStream());
                        ObjectInputStream ois= new ObjectInputStream(sendSocket.getInputStream());

                        if (order.equals("")){
                                oo.writeUTF("a");
                                flag = true;
                        } else {
                                oo.writeUTF(order);
                                flag = false;
                        }

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
                if(flag) {
                        for (int i=0; i < 10; i++){
                                if ('A'== re.charAt(i)){
                                        bt[i].setBackgroundResource(R.drawable.greentable);
                                        bt[i].setEnabled(true);
                                }
                        }
                }
                else if(!flag){
                        String[] s = re.split("#");
                        textView.setText(s[0]);
                        nameView.setText(s[1]);
                        ImageUrlTask imageUrlTask = new ImageUrlTask(s[0], imageView);
                        imageUrlTask.execute();

                }
        }

        public String vv(){
                return re;
        }
}