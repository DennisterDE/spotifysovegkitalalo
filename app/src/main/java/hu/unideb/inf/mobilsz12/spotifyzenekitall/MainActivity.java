package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textview);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://spotify23.p.rapidapi.com/search/?q=metal3E&type=multi&offset=0&limit=10&numberOfTopResults=5")
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
                    String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(myResponse);
                        }
                    });
                }
            }
        });

    }
}