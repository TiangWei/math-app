package com.example.individual;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    public int caseValue;
    public void showResultDialog(String correct) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Result");
        builder.setMessage("You're correct");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog when OK button is clicked
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void instruct(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("How to Play");
        builder.setMessage(message); // Set the message to the provided content

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog when OK button is clicked
                switch (caseValue) {
                    case 1:
                        startActivity(new Intent(MainActivity.this, comparison.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, arrange.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, composing.class));
                        break;
                    default:
                        // Handle the default case if necessary
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Button2 = findViewById(R.id.button2);
        Button Button3 = findViewById(R.id.button3);
        Button compareButton = findViewById(R.id.buttonC);

        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseValue=1;
                instruct(MainActivity.this, "choose the correct symbol of comparison.");
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseValue=2;
                instruct(MainActivity.this, "Arrange the numbers, it will inform it is in what order.");
            }
        });
        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caseValue=3;
                instruct(MainActivity.this, "Select Two numbers which will add up to the number above(red = selected).");
            }
        });
    }
}