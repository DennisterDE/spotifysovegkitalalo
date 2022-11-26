package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
        tv = findViewById(R.id.textView);
        OkHttpClient client = new OkHttpClient();
        tv.setMovementMethod(new ScrollingMovementMethod());


        Request request = new Request.Builder()
                .url("https://spotify23.p.rapidapi.com/search/?q=metal3E&type=multi&offset=0&limit=10&numberOfTopResults=5")
                .get()
                .addHeader("X-RapidAPI-Key", "2e185465a8mshe8ce882fc29b7aep1fc59djsnd64480e1008a")
                .addHeader("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                .build();


      /*  client.newCall(request).enqueue(new Callback() {
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
*/
        String Lyric = "Underneath the blackest soil\n" +
                "I lay down my warm beating heart\n" +
                "And deep beneath the frozen ground\n" +
                "I bury down my deepest thoughts\n" +
                "\n" +
                "Because the road I've chosen\n" +
                "Is free of fortune and desire\n" +
                "Or the mundane toil and worry\n" +
                "Because the trail I've taken\n" +
                "Leads down into the darkest shore\n" +
                "Where the stars have passed away\n" +
                "\n" +
                "Tonight, the world is burning\n" +
                "Black smoke hides the skies\n" +
                "Dark clouds rise up swirling\n" +
                "Shadows engulf the land\n" +
                "\n" +
                "By the side of forsaken path\n" +
                "I leave behind my old shadow\n" +
                "Into the deep under these shores\n" +
                "I drown my broken memories\n" +
                "\n" +
                "For something to change\n" +
                "Something has to give\n" +
                "For something to grow\n" +
                "Something has to yield\n" +
                "Underneath the blackest soil\n" +
                "I lay down my warm beating heart\n" +
                "And deep beneath the frozen ground\n" +
                "I bury down my deepest thoughts\n" +
                "\n" +
                "Because the road I've chosen\n" +
                "Is free of fortune and desire\n" +
                "Or the mundane toil and worry\n" +
                "Because the trail I've taken\n" +
                "Leads down into the darkest shore\n" +
                "Where the stars have passed away\n" +
                "\n" +
                "Tonight, the world is burning\n" +
                "Black smoke hides the skies\n" +
                "Dark clouds rise up swirling\n" +
                "Shadows engulf the land\n" +
                "\n" +
                "By the side of forsaken path\n" +
                "I leave behind my old shadow\n" +
                "Into the deep under these shores\n" +
                "I drown my broken memories\n" +
                "\n" +
                "For something to change\n" +
                "Something has to give\n" +
                "For something to grow\n" +
                "Something has to yield\n" +
                "Underneath the blackest soil\n" +
                "I lay down my warm beating heart\n" +
                "And deep beneath the frozen ground\n" +
                "I bury down my deepest thoughts\n" +
                "\n" +
                "Because the road I've chosen\n" +
                "Is free of fortune and desire\n" +
                "Or the mundane toil and worry\n" +
                "Because the trail I've taken\n" +
                "Leads down into the darkest shore\n" +
                "Where the stars have passed away\n" +
                "\n" +
                "Tonight, the world is burning\n" +
                "Black smoke hides the skies\n" +
                "Dark clouds rise up swirling\n" +
                "Shadows engulf the land\n" +
                "\n" +
                "By the side of forsaken path\n" +
                "I leave behind my old shadow\n" +
                "Into the deep under these shores\n" +
                "I drown my broken memories\n" +
                "\n" +
                "For something to change\n" +
                "Something has to give\n" +
                "For something to grow\n" +
                "Something has to yield\n" +
                "I kiss the light goodbye";

        tv.setText(Lyric);
    }
}