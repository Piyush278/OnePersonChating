package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Message extends AppCompatActivity {
    ListView lv1;
    ImageView sendButton;
    EditText messageArea;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        lv1 = findViewById(R.id.lv1);

        sendButton = (ImageView) findViewById(R.id.sendButton);
        messageArea = (EditText) findViewById(R.id.messageArea);
        relativeLayout = findViewById(R.id.layout1);
        // scrollView = (ScrollView)findViewById(R.id.scrollView);
        MessageThread thread = new MessageThread("http://prahladghat.adorecatering.co.in/showall.aspx");
        thread.execute();


    }


    class MessageThread extends AsyncTask {
        String path;

        public MessageThread(String path) {
            this.path = path;
        }


        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL(path);
                URLConnection connection = url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Scanner s = new Scanner(in);
                String result = "";
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    result = result + line;

                }
                return result;
            } catch (Exception ex) {
                return ex;
            }
        }

        @Override
        public void onPostExecute(Object result) {
            try {
                super.onPostExecute(result);
                if (result instanceof Exception) {
                    throw (Exception) result;
                }


                String data = "" + result;
                data = data.trim();
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("messages");
                int n = jsonArray.length();
                messages[] Messages = new messages[n];
                for (int i = 0; i <= n - 1; i++) {
                    JSONObject object = new JSONObject("" + jsonArray.get(i));
                    String from = object.getString("from");
                    String to = object.getString("to");

                    String message = object.getString("message");

                    messages Message = new messages(from, to, message);
                    Messages[i] = Message;

                }
                Messagesadapter adapter = new Messagesadapter(Message.this, Messages);
                lv1.setAdapter(adapter);

            } catch (Exception ex) {
                System.out.println(ex);
                Toast.makeText(Message.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                setTitle("Message not send");
            }
        }
    }

    class SentThread extends AsyncTask {
        String path;
        String From, to, message;

        public SentThread(String From, String to, String message) {
            this.From = From;
            this.to = to;
            this.message = message;

            this.path = Utilities.Message(From, to, message);

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                String result = Utilities.downloadString(path);


                return result;
            } catch (Exception ex) {
                return ex;
            }
        }

        @Override
        public void onPostExecute(Object result) {
            try {
                super.onPostExecute(result);
                if (result instanceof Exception)
                    throw (Exception) result;

                //  String data=Utilities.register(""+txtUserName.getText(),""+txtPassword.getText(),""+txtName.getText(),""+txtPhone.getText());
                String data = "" + result;

                setTitle("Message Sent" + data);

            } catch (Exception ex) {
                System.out.println(ex);
                Toast.makeText(Message.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                setTitle("Registred not Sucessfully");
            }
        }
    }

    //*************************************************************************
    public void Fa(View view) {

        SentThread thread = new SentThread("" + Utilities.username, "" + Utilities.to, "" + messageArea.getText());
        thread.execute();

    }

}
