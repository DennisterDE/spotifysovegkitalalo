package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    List<String> tracklist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        OkHttpClient client = new OkHttpClient();
        tv.setMovementMethod(new ScrollingMovementMethod());

        button1 = findViewById(R.id.buttonSong1);
        button1.setOnClickListener(this);
        button1.setEnabled(false);

        button2 = findViewById(R.id.buttonSong2);
        button2.setOnClickListener(this);
        button2.setEnabled(false);

        button3 = findViewById(R.id.buttonSong3);
        button3.setOnClickListener(this);
        button3.setEnabled(false);

        button4 = findViewById(R.id.buttonSong4);
        button4.setOnClickListener(this);
        button4.setEnabled(false);

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

                tracklist.add(songData.get(1));
                while (tracklist.size() < 4) {
                    String newtrackname = JsonParser.otherTrackNameParser(mySearchResponse);
                    if (!tracklist.contains(newtrackname))
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

                                for (Button button : buttonList) {
                                    int index = random.nextInt(tmptracklist.size());
                                    button.setText(tmptracklist.get(index));
                                    tmptracklist.remove(index);
                                }
                                button1.setEnabled(true);
                                button2.setEnabled(true);
                                button3.setEnabled(true);
                                button4.setEnabled(true);

                            }
                        });

                    }
                });


            }


        }));


    }


    public String getbuttontext(View view) {

        Button b = (Button) view;
        String buttonText = b.getText().toString();
        return buttonText;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSong1:
                setButtonColor(view);
                break;
            case R.id.buttonSong2:
                setButtonColor(view);
                break;
            case R.id.buttonSong3:
                setButtonColor(view);
                break;
            case R.id.buttonSong4:
                setButtonColor(view);
                break;

        }
    }

    public void setButtonColor(View button) {
        if (getbuttontext(button).equals(tracklist.get(0))) {
            int RGB = android.graphics.Color.argb(255, 0, 255, 0);
            button.setBackgroundColor(RGB);
        } else {
            int RGB = android.graphics.Color.argb(255, 255, 0, 0);
            button.setBackgroundColor(RGB);

        }

    }
}
