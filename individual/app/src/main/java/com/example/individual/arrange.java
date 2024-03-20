package com.example.individual;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class arrange extends AppCompatActivity {
    private final ArrayList<Integer> numbers = new ArrayList<>();
    private final ArrayList<Integer> randomNumbers = new ArrayList<>();
    private final ArrayList<TextView> numberTextViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange);

        // Initialize TextViews
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);

        // Add TextViews to the list
        numberTextViews.add(textView1);
        numberTextViews.add(textView2);
        numberTextViews.add(textView3);
        numberTextViews.add(textView4);
        numberTextViews.add(textView5);

        generateRandomNumbers();

        // Set onClick listeners for buttons

        // Check button
        Button checkButton = findViewById(R.id.check);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOrder();
            }
        });


        Button newSessionButton = findViewById(R.id.newset);
        newSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewSession();
            }
        });

        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                clearTextViews();
            }
        });
    }
    private void clearTextViews() {
        for (TextView textView : numberTextViews) {
            textView.setText(""); // Clear the text in each TextView
        }
        numbers.clear(); // Clear the list of numbers
    }

    private int generateRandomNumber() {
        return new Random().nextInt(1000); // Generate random number between 0 to 99
    }

    private void assignRandomNumberToButton(Button button) {
        int randomNumber = generateRandomNumber();
        randomNumbers.add(randomNumber); // Store the random number
        button.setText(String.valueOf(randomNumber));
    }

    private void processButtonClick(int number) {
        if (numbers.contains(number)) {
            Toast.makeText(this, "You have already clicked on this button!", Toast.LENGTH_SHORT).show();
        } else {
            if (numbers.size() < numberTextViews.size()) {
                numbers.add(number);
                // Update the corresponding TextView with the clicked number
                numberTextViews.get(numbers.size() - 1).setText(String.valueOf(randomNumbers.get(number - 1))); // Get the random number assigned to the clicked button
                //button.setEnabled(false); // Disable the button after it has been clicked
            } else {
                Toast.makeText(this, "You have already clicked on all buttons!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void checkOrder() {
        if (numbers.size() < 5) {
            Toast.makeText(this, "Please enter at least 5 numbers.", Toast.LENGTH_SHORT).show();
            return; // Exit the method if there are less than 5 numbers
        }

        ArrayList<Integer> sortedNumbers = new ArrayList<>();
        for (TextView textView : numberTextViews) {
            sortedNumbers.add(Integer.parseInt(textView.getText().toString()));
        }
        ArrayList<Integer> ascendingSortedNumbers = new ArrayList<>(sortedNumbers);
        Collections.sort(ascendingSortedNumbers);
        boolean ascending = sortedNumbers.equals(ascendingSortedNumbers);

        ArrayList<Integer> descendingSortedNumbers = new ArrayList<>(sortedNumbers);
        descendingSortedNumbers.sort(Collections.reverseOrder());
        boolean descending = sortedNumbers.equals(descendingSortedNumbers);

        if (ascending) {
            Toast.makeText(this, "Numbers are in ascending order!", Toast.LENGTH_SHORT).show();
        } else if (descending) {
            Toast.makeText(this, "Numbers are in descending order!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Numbers are not in order!", Toast.LENGTH_SHORT).show();
        }

    }
    private void startNewSession() {
        // Clear TextViews and any other necessary setup for starting a new session
        generateRandomNumbers();
        // Add any additional setup code here as needed
    }
    public void generateRandomNumbers() {
        Random random = new Random();
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

        // Generate random numbers and assign them to buttons
        assignRandomNumberToButton(button1);
        assignRandomNumberToButton(button2);
        assignRandomNumberToButton(button3);
        assignRandomNumberToButton(button4);
        assignRandomNumberToButton(button5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processButtonClick(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processButtonClick(2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processButtonClick(3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processButtonClick(4);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processButtonClick(5);
            }
        });

    }

}
