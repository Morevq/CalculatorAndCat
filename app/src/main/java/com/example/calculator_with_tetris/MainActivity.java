package com.example.calculator_with_tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.GetAnsw);
        button.setOnClickListener(this);
    }

    //функция для ввода сс полученого числа
    public int SsYou()
    {
        TextView textView = findViewById(R.id.errors);
        try
        {
            EditText editText = findViewById(R.id.SSYouNum);
            int ssfirst = Integer.parseInt(editText.getText().toString());
            if((ssfirst > 36) || (ssfirst < 2)) throw new Exception();
            return ssfirst;
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    //функция для ввода сс, в которую нужно перевести
    public int SsAns()
    {
        TextView textView = findViewById(R.id.errors);
        try
        {
            EditText editText = findViewById(R.id.SSYouAnsw);
            int ssfirst = Integer.parseInt(editText.getText().toString());
            if((ssfirst > 36) || (ssfirst < 2)) throw new Exception();
            return ssfirst;
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    //функция преобразования char в int
    public int convert(char c)
    {
        return c - 48;
    }

    public void SS()
    {
        int num = 0;
        int cow = 0;
        char[] ssbi = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
        String[] ssbi1 = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36" };
        int ssfirst = SsYou();
        if(ssfirst != 0) {
            String n = "0";
            EditText editText = findViewById(R.id.Num);
            TextView textView = findViewById(R.id.errors);
            TextView textView2 = findViewById(R.id.errors2);
            try {
                n = (editText.getText()).toString();
                int isrealnum = 0;
                //если убрать этот цикл, тол символы, которых не может быть в данной сс будут игнорировавться
                for (int i = 0; i < editText.length(); ++i) {
                    isrealnum = 0;
                    for (int j = 0; j < ssfirst; ++j) {
                        if(n.charAt(i) == ssbi[j]){
                            isrealnum = 1;
                        }
                    }
                    if(isrealnum != 1){
                        break;
                    }
                }
                if(isrealnum == 1) {
                    n = n.toLowerCase();
                    cow += n.length();
                    int cou;
                    char er;
                    int bun = 0;
                    for (int i = 0; i < cow; ++i) {
                        if (n.charAt(i) == '0' || n.charAt(i) == '1' || n.charAt(i) == '2' || n.charAt(i) == '3' || n.charAt(i) == '4' || n.charAt(i) == '5' || n.charAt(i) == '7' || n.charAt(i) == '8' || n.charAt(i) == '9') {
                            num += convert(n.charAt(i));
                            num *= ssfirst;
                            bun += 1;
                        } else {
                            cou = 0;
                            er = n.charAt(i);
                            for (int k = 0; k < ssfirst; ++k) {
                                if (er == ssbi[k]) {
                                    num += k;
                                    num *= ssfirst;
                                    bun += 1;
                                }
                            }
                        }
                    }
                    if (n.charAt(0) == '-') {
                        cow -= 1;
                    }
                    num /= ssfirst;
                    if (cow != bun) {
                        EditText editTextAnsw = findViewById(R.id.Answ);
                        editTextAnsw.setText("Error");
                    }
                }
                else{
                    EditText editTextAnsw = findViewById(R.id.Answ);
                    editTextAnsw.setText("Error");
                }
            } catch (Exception e) {
                EditText editTextAnsw = findViewById(R.id.Answ);
                editTextAnsw.setText("Error");
            }
            int sssecond = SsAns();
            if(sssecond != 0) {
                String numf = "";
                int cow2 = 0;
                while (num != 0) {
                    if (num % sssecond < 10) {
                        numf += num % sssecond;
                    } else {
                        numf += ssbi[num % sssecond];
                    }
                    num /= sssecond;
                    cow2 += 1;
                }
                String numft = "";
                if (n.charAt(0) == '-') {
                    numft = "-";
                }
                for (int i = 0; i < cow2; ++i) {
                    numft += numf.charAt(cow2 - i - 1);
                }
                if (n == "0") {
                    numft = "0";
                }
                numft = numf.toUpperCase();
                String nn = "";
                for (int i = numf.length() - 1; i >= 0; --i) {
                    nn += numf.charAt(i);
                }
                EditText editTextAnsw = findViewById(R.id.Answ);
                editTextAnsw.setText(nn);
            }
            else{
                EditText editTextAnsw = findViewById(R.id.Answ);
                editTextAnsw.setText("Error");
            }
        }
        else{
            EditText editTextAnsw = findViewById(R.id.Answ);
            editTextAnsw.setText("Error");
        }
    }

    @Override
    public void onClick(View v) {
        SS();
    }

}