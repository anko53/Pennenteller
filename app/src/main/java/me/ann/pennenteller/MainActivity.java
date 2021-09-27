package me.ann.pennenteller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int teller = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tellerText = findViewById(R.id.tellerText);
        Button tellerAdd = findViewById(R.id.tellerAdd);
        Button tellerSubtract = findViewById(R.id.tellerSubtract);

        tellerAdd.setOnClickListener(view -> {
            teller++;
            tellerText.setText(String.valueOf(teller));
        });
        tellerSubtract.setOnClickListener(view -> {
            teller --;
            tellerText.setText(String.valueOf(teller));
        });
    }

}