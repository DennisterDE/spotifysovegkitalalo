package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        tv = findViewById(R.id.textView);
        OkHttpClient client = new OkHttpClient();
        tv.setMovementMethod(new ScrollingMovementMethod());

        Request searchrequest = SpotifyApi.searchrequest(Start.searchtext);

        client.newCall(searchrequest).enqueue((new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                final String mySearchResponse = response.body().string();

                List<String> songData = JsonParser.randomtrackIdParser(mySearchResponse);
                List<String> tracklist = new ArrayList<>();
                tracklist.add(songData.get(1));
                while (tracklist.size()<4)
                {
                    String newtrackname = JsonParser.otherTrackNameParser(mySearchResponse);
                    if(!tracklist.contains(newtrackname))
                    tracklist.add(newtrackname);
                }



                System.out.println(songData.get(0));

                Request lyricsRequest = SpotifyApi.lyricrequest(songData.get(0));

                client.newCall(lyricsRequest).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String myLyricResponse = response.body().string();
                        String lyrics = JsonParser.trackLyricParser(myLyricResponse);

                        System.out.println(lyrics);
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(tracklist.toString());
                            }
                        });


                    }
                });

            }


        }));


    }
}