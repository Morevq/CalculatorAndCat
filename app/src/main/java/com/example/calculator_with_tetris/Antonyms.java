package com.example.calculator_with_tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Antonyms extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antonyms);
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView textView = findViewById(R.id.textView);
        textView.setText("");
        new Thread() {
            @Override
            public void run() {
                try {
                    EditText editText = findViewById(R.id.editTextTextPersonName);
                    URL url = new URL("https://antonymonline.ru/antonyms.json?word=" + editText.getText());
                    URLConnection connection = url.openConnection();
                    Scanner scanner = new Scanner(connection.getInputStream());
                    String responce = scanner.nextLine();
                    Log.d("RESPONSE", responce);
                    JSONObject jsonObject = new JSONObject(responce);
                    JSONArray jsonArray = jsonObject.getJSONArray("antonyms");
                    String textToTextView = "";
                    for(int i=0;i<jsonArray.length();++i){
                        Log.d("ANTONYM " + i, jsonArray.get(i).toString());
                        textToTextView += i + ") " + jsonArray.get(i).toString() + "\n";
                        final int index = i;
                    }
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(textToTextView);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}