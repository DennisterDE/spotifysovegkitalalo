package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;
import java.util.ResourceBundle;

public class  JsonParser {

    public static String randomtrackIdParser(String valasz) {
        Random random = new Random();
        String trackid = null;
        try {

            JSONObject jsonObject = new JSONObject(valasz);
            JSONObject tracksObject = jsonObject.getJSONObject("tracks");
            JSONArray tracklist = tracksObject.getJSONArray("items");
            int randomindex = (int) random.nextInt(tracklist.length());
            JSONObject trackObject = tracklist.getJSONObject(randomindex);
            JSONObject trackdata = trackObject.getJSONObject("data");
            trackid = trackdata.getString("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return trackid;
    }
    public static String trackLyricParser (String valasz)
    {
        StringBuilder sb = new StringBuilder();
        String szoveg=null;
        try {
            JSONObject jsonObject = new JSONObject(valasz);
            JSONObject lyrics =  jsonObject.getJSONObject("lyrics");
            JSONArray lines = lyrics.getJSONArray("lines");

            int linessize = lines.length();
            for (int i=0; i<= lines.length();i++)
            {
                JSONObject line = lines.getJSONObject(i);
                System.out.println(line.getString("words"));
                sb.append(line.getString("words"));
            }

            szoveg = sb.toString();


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return szoveg;
    }

}
