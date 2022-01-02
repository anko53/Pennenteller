package me.ann.pennenteller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static int teller = 0;
    private static final int MINIMUM = 0;

    TextView tellerText;

    int tellerBeforeUndo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tellerText = findViewById(R.id.teller_text);
        Button tellerAdd = findViewById(R.id.teller_add);
        Button tellerSub = findViewById(R.id.teller_sub);
        Button reset = findViewById(R.id.reset);

        setupLocalStorage();

        tellerAdd.setOnClickListener(view -> {
            updateTeller(teller + 1);
        });

        tellerSub.setOnClickListener(view -> {
            if (teller -1 < MINIMUM){
                Snackbar mySnackbar = Snackbar.make(view, "De teller mag niet minder dan " + MINIMUM +" zijn.",  BaseTransientBottomBar.LENGTH_SHORT );
                mySnackbar.show();
                return;
            }
            updateTeller(teller - 1);
        });

        reset.setOnClickListener(view -> {
//            Op 0 zetten van teller, tenzij gekozen wordt voor Undo...
            tellerBeforeUndo = teller;
            updateTeller(0);
            Snackbar mySnackbar = Snackbar.make(view, "Teller teruggezet", Snackbar.LENGTH_LONG);
            mySnackbar.setAction("Undo", view1 -> {
                updateTeller(tellerBeforeUndo);
            });
            mySnackbar.show();
        });


    }

    private void setupLocalStorage() {
        SharedPreferences sharedPreferences = getSharedPreferences("opslag", MODE_PRIVATE);
        int uitOpslag = sharedPreferences.getInt("teller", 0);
        teller = uitOpslag;
        tellerText.setText(String.valueOf(teller));
    }

    private void updateTeller(int nieuweTeller) {
        SharedPreferences sharedPreferences = getSharedPreferences("opslag", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("teller", nieuweTeller);
        editor.apply();
        teller = nieuweTeller;
        tellerText.setText(String.valueOf(teller));

    }

}
