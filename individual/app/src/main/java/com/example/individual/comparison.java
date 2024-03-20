package com.example.individual;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class comparison extends MainActivity {

    private TextView textViewNumber1;
    private TextView textViewNumber2;
    private int number1;
    private int number2;

    public void generateRandomNumbers() {
        Random random = new Random();
        number1 = random.nextInt(900) + 100; // Generate a random 3-digit number
        number2 = random.nextInt(900) + 100; // Generate another random 3-digit number
        textViewNumber1.setText(String.valueOf(number1));
        textViewNumber2.setText(String.valueOf(number2));
    }

    public void showResultDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog when OK button is clicked
            }
        });
        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison);

        textViewNumber1 = findViewById(R.id.textViewNumber1);
        textViewNumber2 = findViewById(R.id.textViewNumber2);
        Button buttonBigger = findViewById(R.id.buttonBigger);
        Button buttonLesser = findViewById(R.id.buttonLesser);

        buttonLesser.setText("<");
        generateRandomNumbers();


        buttonBigger.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (number1 > number2) {
                    showResultDialog("correct");
                } else {
                    showResultDialog("wrong!!");
                }
                generateRandomNumbers();
            }
        });

        buttonLesser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (number1 > number2) {
                    showResultDialog("wrong!!");
                } else {
                    showResultDialog("correct");
                }
                generateRandomNumbers();
            }
        });
    }
}