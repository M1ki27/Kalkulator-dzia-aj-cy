
package com.example.kalkulator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView ekran;
    double pierwsza = 0;
    String znak = "";
    boolean nowe = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ekran = findViewById(R.id.ekran);

        int[] cyfry = {
                R.id.zero, R.id.jeden, R.id.dwa, R.id.trzy,
                R.id.cztery, R.id.piec, R.id.szesc,
                R.id.siedem, R.id.osiem, R.id.dziewiec
        };

        for (int id : cyfry) {
            Button b = findViewById(id);

            b.setOnClickListener(v -> {
                if (nowe) {
                    ekran.setText("");
                    nowe = false;
                }

                ekran.append(b.getText().toString());
            });
        }

        findViewById(R.id.przecinek).setOnClickListener(v -> {
            if (nowe) {
                ekran.setText("0");
                nowe = false;
            }

            if (!ekran.getText().toString().contains(",")) {
                ekran.append(",");
            }
        });

        findViewById(R.id.dodaj).setOnClickListener(v -> dzialanie("+"));
        findViewById(R.id.odejmij).setOnClickListener(v -> dzialanie("-"));
        findViewById(R.id.pomnoz).setOnClickListener(v -> dzialanie("*"));
        findViewById(R.id.podziel).setOnClickListener(v -> dzialanie("/"));

        findViewById(R.id.wynik).setOnClickListener(v -> {
            double druga = Double.parseDouble(
                    ekran.getText().toString().replace(",", ".")
            );

            double wynik = druga;

            if (znak.equals("+")) wynik = pierwsza + druga;
            if (znak.equals("-")) wynik = pierwsza - druga;
            if (znak.equals("*")) wynik = pierwsza * druga;

            if (znak.equals("/")) {
                if (druga == 0) {
                    ekran.setText("Blad");
                    nowe = true;
                    znak = "";
                    return;
                }
                wynik = pierwsza / druga;
            }

            ekran.setText(String.valueOf(wynik).replace(".", ","));
            znak = "";
            nowe = true;
        });

        findViewById(R.id.czysc).setOnClickListener(v -> {
            ekran.setText("0");
            pierwsza = 0;
            znak = "";
            nowe = true;
        });
    }

    void dzialanie(String nowyZnak) {
        pierwsza = Double.parseDouble(
                ekran.getText().toString().replace(",", ".")
        );

        znak = nowyZnak;
        nowe = true;
    }
}
