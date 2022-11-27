package hu.unideb.inf.mobilsz12.spotifyzenekitall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class start_layout extends AppCompatActivity {


    public EditText getSearchtext() {
        return searchtext;
    }

    Button searchbutton;
    public static EditText searchtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        searchtext = (EditText) findViewById(R.id.searchinput);
        searchbutton = (Button) findViewById(R.id.searchbutton);

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });{

        }


    }


    public void openActivity2()
    {
        Intent intent = new Intent(this,MainActivity.class)
    }
}