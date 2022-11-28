package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        OkHttpClient client = new OkHttpClient();
        tv.setMovementMethod(new ScrollingMovementMethod());
        button1 = findViewById(R.id.buttonSong1);
        button2 = findViewById(R.id.buttonSong2);
        button3 = findViewById(R.id.buttonSong3);
        button4 = findViewById(R.id.buttonSong4);
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        Random random = new Random();




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

                List<String> tmptracklist = new ArrayList<>();
                tmptracklist.addAll(tracklist);

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
                                tv.setText(lyrics);

                                for (Button button :buttonList)
                                {
                                 int index= random.nextInt(tmptracklist.size());
                                    button.setText(tmptracklist.get(index));
                                    tmptracklist.remove(index);
                                }
                            }
                        });


                    }
                });

            }


        }));


    }


    public void checkifanswer(View view) {

    }
}