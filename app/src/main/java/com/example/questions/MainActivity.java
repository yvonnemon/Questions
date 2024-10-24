package com.example.questions;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int mCurrentIndex = 0;
    private TextView mQuestionTextView;



    ArrayList<Question> mQuestionBank = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] preguntas = getResources().getStringArray(R.array.questions);

        for (int i = 0; i < preguntas.length; i++) {
            this.mQuestionBank.add(new Question(preguntas[i], i % 2 == 0));
        }
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mQuestionTextView = (TextView) findViewById(R.id.textView);
        Button buttonPrev = findViewById(R.id.buttonPrev);
        Button buttonNext = findViewById(R.id.buttonNext);

        Button buttonFalse = findViewById(R.id.buttonfalso);
        Button buttonTrue = findViewById(R.id.buttontrue);
        buttonFalse.setBackgroundColor(Color.parseColor("#2255aa"));
        buttonTrue.setBackgroundColor(Color.parseColor("#2255aa"));

        buttonPrev.setText(R.string.previousButton);
        buttonNext.setText(R.string.nextButton);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFalse.setEnabled(true);
                buttonTrue.setEnabled(true);
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size();
                String question = mQuestionBank.get(mCurrentIndex).getQuestion();
                mQuestionTextView.setText(question);
                buttonFalse.setBackgroundColor(Color.parseColor("#2255aa"));
                buttonTrue.setBackgroundColor(Color.parseColor("#2255aa"));
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFalse.setEnabled(true);
                buttonTrue.setEnabled(true);
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.size();
                String question = mQuestionBank.get(mCurrentIndex).getQuestion();
                mQuestionTextView.setText(question);
                buttonFalse.setBackgroundColor(Color.parseColor("#2255aa"));
                buttonTrue.setBackgroundColor(Color.parseColor("#2255aa"));
            }
        });

        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFalse.setEnabled(false);
                mQuestionBank.get(mCurrentIndex).setPressed(true);
                boolean r = mQuestionBank.get(mCurrentIndex).isAnswer();
                int rojo = Color.parseColor("#FF0000");
                int verde = Color.parseColor("#00CC00");
                if (r) {
                    buttonTrue.setBackgroundColor(rojo);
                } else {
                    buttonTrue.setBackgroundColor(verde);
                }
            }
        });

        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonTrue.setEnabled(false);
                mQuestionBank.get(mCurrentIndex).setPressed(false);
                boolean r = mQuestionBank.get(mCurrentIndex).isAnswer();
                int rojo = Color.parseColor("#FF0000");
                int verde = Color.parseColor("#00CC00");
                if (r) {
                    buttonFalse.setBackgroundColor(rojo);
                } else {
                    buttonFalse.setBackgroundColor(verde);
                }
            }
        });


    }
}