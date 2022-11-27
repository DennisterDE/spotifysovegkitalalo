package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parse(String src) throws IOException
    {
        return  objectMapper.readTree(src);
    }
}
