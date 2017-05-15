package com.kirillprog.s1.prot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnClickListener;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Testing extends AppCompatActivity {
    private static final String TAG = "Learn&&Programming";
    private int questionNumber = 0;
    private int trueAnswer = 0;
    private int[][] numberOfCorrectAnswers;
    private String[] currentTestName;
    private String[][] currentTestText;
    private ListView questionListView;
    private TextView resultBox;
    private TextView testText;
    private TextView questionNumberTextView;
    private Button exitButton;
    int numberLesson;
    int lang;
    private String[][][] answers;

    private final OnDismissListener onDismissListener = new OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            Log.i("onDismiss()", "onDismiss()");
            loadNextQuestion();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        numberLesson = getIntent().getIntExtra("num", 0);
        lang = getIntent().getIntExtra("lang", 0);
        setAnswers();
        cppOrPython();
        setUI();
        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                final TextView textView = (TextView) itemClicked;
                Handler handler = new Handler();
                String strText = textView.getText().toString();
                int correctNumber = numberOfCorrectAnswers[numberLesson][questionNumber];
                if(strText.equals(answers[numberLesson][questionNumber][correctNumber])) {
                    trueAnswer++; questionNumber++;
                    textView.setTextColor(getResources().getColor(R.color.correctAnswer));
                    resultBox.setText("Правильно");
                } else {
                    questionNumber++;
                    textView.setTextColor(getResources().getColor(R.color.notCorrectAnswer));
                    resultBox.setText("Неверно");
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(questionNumber <= 9)
                            loadNextQuestion();
                        else alertDialog();
                    }
                }, 1000);

            }
        });
        loadNextQuestion();
    }

    private void setUI(){
        questionNumberTextView = (TextView) findViewById(R.id.questionNumberTextView);
        resultBox = (TextView) findViewById(R.id.resultTextView);
        questionListView = (ListView) findViewById(R.id.testListView);
        exitButton = (Button) findViewById(R.id.toStart);
        testText = (TextView) findViewById(R.id.testText);
    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Testing.this);
        builder.setTitle("Результат");
        builder.setMessage("Вы набрали " + trueAnswer + " очков из " + answers[numberLesson].length);
        loadNextQuestion();

        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(onDismissListener);
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Testing.this.finish();
            }
        }, 3000);


    }


    private void cppOrPython(){
        switch(lang){
            case 0:
                currentTestName = getResources().getStringArray(R.array.cpp_lesson_name);
                currentTestText = new String[][] {
                        getResources().getStringArray(R.array.cpp_test_1_question),
                        getResources().getStringArray(R.array.cpp_test_2_question),
                        getResources().getStringArray(R.array.cpp_test_3_question),
                        getResources().getStringArray(R.array.cpp_test_4_question),
                        getResources().getStringArray(R.array.cpp_test_5_question),
                        getResources().getStringArray(R.array.cpp_test_6_question),
                        getResources().getStringArray(R.array.cpp_test_7_question),
                        getResources().getStringArray(R.array.cpp_test_8_question),
                        getResources().getStringArray(R.array.cpp_test_9_question),
                        getResources().getStringArray(R.array.cpp_test_10_question),
                        getResources().getStringArray(R.array.cpp_test_11_question),
                        getResources().getStringArray(R.array.cpp_test_12_question),
                };
                numberOfCorrectAnswers = new int[][] {
                        getResources().getIntArray(R.array.cpp_test_1_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_2_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_3_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_4_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_5_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_6_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_7_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_8_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_9_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_10_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_11_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_12_correct_answer),
                }; break;
            case 1:
                currentTestName = getResources().getStringArray(R.array.python_lesson_name);
                currentTestText = new String[][] {
                        /*getResources().getStringArray(R.array.cpp_test_1_question), // набор текстов для каждого из тестов
                        getResources().getStringArray(R.array.cpp_test_2_question),
                        getResources().getStringArray(R.array.cpp_test_3_question),
                        getResources().getStringArray(R.array.cpp_test_4_question),
                        getResources().getStringArray(R.array.cpp_test_5_question),
                        getResources().getStringArray(R.array.cpp_test_6_question),
                        getResources().getStringArray(R.array.cpp_test_7_question),
                        getResources().getStringArray(R.array.cpp_test_8_question),
                        getResources().getStringArray(R.array.cpp_test_9_question),
                        getResources().getStringArray(R.array.cpp_test_10_question),
                        getResources().getStringArray(R.array.cpp_test_11_question),
                        getResources().getStringArray(R.array.cpp_test_12_question),*/
                };
                numberOfCorrectAnswers = new int[][] {
                       /* getResources().getIntArray(R.array.cpp_test_1_correct_answer), // набор правильных вариантов ответов(хранятся индексы
                        getResources().getIntArray(R.array.cpp_test_2_correct_answer),   // правмльных ответов)
                        getResources().getIntArray(R.array.cpp_test_3_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_4_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_5_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_6_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_7_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_8_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_9_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_10_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_11_correct_answer),
                        getResources().getIntArray(R.array.cpp_test_12_correct_answer),*/
                }; break;
        }
    }
    private void loadNextQuestion(){
        questionNumberTextView.setText(currentTestName[numberLesson] + '\n' + "Вопрос №" + (questionNumber+1) + " " + answers[numberLesson].length);
        testText.setText(currentTestText[numberLesson][questionNumber]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, answers[numberLesson][questionNumber]);
        questionListView.setAdapter(adapter);
        resultBox.setText("");

    }
    private void setAnswers(){
        if(lang == 0) {
            answers = new String[][][]{
                    {{"Hello World", "Hello", "World", "Это не оператор вывода"},
                            {"Функция ничего не возвращает", "переменная тип int", "Функция возвращает тип int"},
                            {"Зависит от использования", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},
                    {{"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"},
                            {"yes", "no", "may be", "not stated"}},

            };
        } else {answers = new String[][][]{/*
                {{"yes", "no", "may be", "not stated"},         //здесь хранятся варианты ответов для каждого вопроса каждого теста
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}},
                {{"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"},
                        {"yes", "no", "may be", "not stated"}}  */
        };
        }
    }
}
