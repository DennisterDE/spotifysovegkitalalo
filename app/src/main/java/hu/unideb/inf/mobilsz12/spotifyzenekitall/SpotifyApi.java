package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SpotifyApi {

    public String searchresponse;
    public String lyricresponse;
    public static Request searchrequest(String searchtext) {

        Request request = new Request.Builder()
                .url("https://spotify23.p.rapidapi.com/search/?q=" + Start.searchtext + "E&type=tracks&offset=0&limit=10&numberOfTopResults=5")
                .get()
                .addHeader("X-RapidAPI-Key", "2e185465a8mshe8ce882fc29b7aep1fc59djsnd64480e1008a")
                .addHeader("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                .build();

        return request;
    }
    public static Request lyricrequest(String id)
    {
     Request request = new Request.Builder()
                    .url("https://spotify23.p.rapidapi.com/track_lyrics/?id=" + id)
                    .get()
                    .addHeader("X-RapidAPI-Key", "38d087b910mshc8a3253af641960p1dd3b0jsnad311ca0f348")
                    .addHeader("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                    .build();


        return request;
    }

}