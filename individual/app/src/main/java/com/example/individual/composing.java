package com.example.individual;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class composing extends MainActivity {
    private final Button[] buttons = new Button[5];
    private TextView textView4;
    private int randomNumber;
    //checkButton = findViewById(R.id.button5);
    private List<Integer> numbersToAdd; // Using List instead of ArrayList
    private boolean isAnswerChecked = false; // Flag to track if the answer has been checked


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composing);
        // Initialize views
        textView4 = findViewById(R.id.textView4);
        buttons[0] = findViewById(R.id.button8);
        buttons[1] = findViewById(R.id.button7);
        buttons[2] = findViewById(R.id.button6);
        buttons[3] = findViewById(R.id.button);
        // Initialize the "Check" button
        generateRandomNumber();
        setButtonClickListeners();
        setCheckButtonClickListener();
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(900) + 100; // Generates a random integer between 100 and 999
        textView4.setText(String.valueOf(randomNumber));
        numbersToAdd = new ArrayList<>(); // Initialize the numbersToAdd list
        generateNumbersToAdd();
    }

    private void generateNumbersToAdd() {
        Random random = new Random();
        int firstNumber = random.nextInt(randomNumber - 1) + 1; // Generate a random number between 1 and (randomNumber - 1)
        int secondNumber = randomNumber - firstNumber; // Calculate the second number that adds up to randomNumber
        numbersToAdd.add(firstNumber);
        numbersToAdd.add(secondNumber);

        int trapNumber1 = random.nextInt(randomNumber - 1) + 1; // Generate two random trap numbers
        int trapNumber2 = random.nextInt(randomNumber - 1) + 1;
        while (trapNumber1 + trapNumber2 == randomNumber || numbersToAdd.contains(trapNumber1) || numbersToAdd.contains(trapNumber2)) {
            trapNumber1 = random.nextInt(randomNumber - 1) + 1; // Regenerate trap numbers if they add up to randomNumber or are equal to the correct numbers
            trapNumber2 = random.nextInt(randomNumber - 1) + 1;
        }
        numbersToAdd.add(trapNumber1);
        numbersToAdd.add(trapNumber2);

        Collections.shuffle(numbersToAdd); // Shuffle the numbers
        setButtonNumbers();
    }

    private void setButtonNumbers() {
        for (int i = 0; i < 4; i++) {
            buttons[i].setText(String.valueOf(numbersToAdd.get(i)));
        }
    }

    private void setButtonClickListeners() {
        for (int i = 0; i < 4; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toggle the selected state of the button
                    buttons[index].setSelected(!buttons[index].isSelected());
                    if (buttons[index].isSelected()) {
                        // Change background color to indicate selection
                        buttons[index].setBackgroundResource(R.drawable.selected); // Change color to whatever you prefer
                    } else {
                        // Change back to default background color
                        buttons[index].setBackgroundResource(R.drawable.button); // Change color to default (transparent) or any other default color
                    }
                }
            });
        }
    }


    private void setCheckButtonClickListener() {

        Button checkButton = findViewById(R.id.button5);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerChecked) { // Check if the answer hasn't been checked yet
                    checkAnswer();
                }
                else {
                    showResultDialog("You have already checked the answer. Please generate a new random number.");
                }
            }
        });
    }

    private void checkAnswer() {
        int firstSelectedNumber = -1;
        int secondSelectedNumber = -1;
        // Find the selected button numbers
        for (int i = 0; i < 4; i++) {
            if (buttons[i].isSelected()) {
                if (firstSelectedNumber == -1) {
                    firstSelectedNumber = Integer.parseInt(buttons[i].getText().toString());
                } else {
                    secondSelectedNumber = Integer.parseInt(buttons[i].getText().toString());
                    break; // Exit the loop if both numbers are found
                }
            }
        }

        if (firstSelectedNumber != -1 && secondSelectedNumber != -1) {
            int complementNumber = randomNumber - firstSelectedNumber;
            boolean isCorrect = complementNumber == secondSelectedNumber;

            if (isCorrect) {
                showResultDialog("Correct!");
                isAnswerChecked = true; // Set the flag to true to indicate that the answer has been checked
            }
            else {
                showResultDialog("Wrong!");
                isAnswerChecked = true; // Set the flag to true to indicate that the answer has been checked
            }
        } else {
            showResultDialog("Please select two buttons.");
        }
    }

    public void showResultDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (isAnswerChecked) {
                    generateRandomNumber(); // Generate a new random number after checking the answer
                    isAnswerChecked = false; // Reset the flag
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
