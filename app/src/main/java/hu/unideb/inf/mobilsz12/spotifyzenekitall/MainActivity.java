package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv;



    public String urlbuilder (String search)
    {
        return "https://spotify23.p.rapidapi.com/search/?q="+search+"&type=tracks&offset=0&limit=2&numberOfTopResults=5";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        OkHttpClient client = new OkHttpClient();
        tv.setMovementMethod(new ScrollingMovementMethod());




        Request request = new Request.Builder()
                .url("https://spotify23.p.rapidapi.com/search/?q="+Start.searchtext+"E&type=tracks&offset=0&limit=2&numberOfTopResults=5")
                .get()
                .addHeader("X-RapidAPI-Key", "2e185465a8mshe8ce882fc29b7aep1fc59djsnd64480e1008a")
                .addHeader("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful())
                {

                    String valasz = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(valasz);

                        System.out.println(jsonObject.getString("query"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    //JsonNode node = JsonParser.parse(response.body().string());

                    //String nodevalasz = node.fieldNames().toString();
                    //String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           // tv.setText(myResponse);
                        }
                    });
                }
            }
        });


    }
}