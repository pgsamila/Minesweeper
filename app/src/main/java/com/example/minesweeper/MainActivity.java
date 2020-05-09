package com.example.minesweeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static final String BEGINNER = "BEGINNER";
    private static final String INTERMEDIATE = "INTERMEDIATE";
    private static final String EXPERT = "EXPERT";
    private WebView webview;
    private Spinner gameModeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = (WebView) this.findViewById(R.id.webview);
        gameModeSpinner = this.findViewById(R.id.spinner);
        ArrayAdapter<String> appThemeAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, new String[]{BEGINNER, INTERMEDIATE, EXPERT});
        gameModeSpinner.setAdapter(appThemeAdapter);

        gameModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                if(item.equals(BEGINNER)){
                    webview.loadUrl("file:///android_asset/index_beginner.html");
                }else if(item.equals(INTERMEDIATE)){
                    webview.loadUrl("file:///android_asset/index_intermediate.html");
                }else if(item.equals(EXPERT)){
                    webview.loadUrl("file:///android_asset/index_expert.html");
                }else {
                    Toast.makeText(MainActivity.this,"No Item Selected!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        webview.getSettings().setJavaScriptEnabled(true);

    }

    public void onHowToPlayButtonClickEvent(View view) {
        String howToPlay = "click anywhere on the board to start the game. Then, click another " +
                "square. When you start seeing numbers, recognize that the number denotes how" +
                " many mines are touching that square. If you think that a square might" +
                " definitely haves a mine underneath of it, right click on it to flag the square.";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("How To Play");

        builder.setMessage(howToPlay);

        builder.show();
    }
}
