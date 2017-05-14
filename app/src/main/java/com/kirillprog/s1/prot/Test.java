package com.kirillprog.s1.prot;

import android.support.v7.app.AppCompatActivity;

public class Test extends AppCompatActivity {}

      /*private static final String TAG = "Learn&&Programming";
    private int questionNumber = 0;
    private int numberOfCorrectAnswers = 0;

    private List<String> questionsList = new ArrayList<String>();
    private String correctAnswer;
    private Handler handler;
    private final OnDismissListener onDismissListener = new OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            Log.i("onDismiss()", "onDismiss()");
            resetQuiz();
            loadNextQuestion();
        }
    };

    private OnClickListener answerClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            submitAnswer((Button) v);
        }
    };

    private TextView resultBox;
    private TableLayout buttonTableLayout;
    private TextView questionNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        int numberLesson = getIntent().getIntExtra("num", 0);
        int lang = getIntent().getIntExtra("lang", 0);

        if(savedInstanceState != null) {
            questionNumber = savedInstanceState.getInt("questionNumber");
        }

        handler = new Handler();
        initUIReferences();

        resetQuiz();
        loadNextQuestion();
    }

    private void initUIReferences() {
        questionNumberTextView = (TextView) findViewById(R.id.questionNumberTextView);
        buttonTableLayout = (TableLayout) findViewById(R.id.buttonTableLayout);
        resultBox = (TextView) findViewById(R.id.resultTextView);
    }

    private void loadNextQuestion() {
        clearResultBox();
        correctAnswer = deriveCountryName(nextImage);
        Log.i("CORRECT_ANSWER", correctAnswer);

        removeOldAnswerOptionButtons();

        incrementQuestionNumberAndUpdateTitle();
    }

    private void submitAnswer(Button submittedAnswerButton) {
        String guess = submittedAnswerButton.getText().toString();
        removeClickablilityFromAllAnswerOptionButtons();
        submittedAnswerButton.setEnabled(false);
        if (guess.equals(correctAnswer)) {
            numberOfCorrectAnswers++;
            displayResultAsCorrect();
        }else {
            displayResultAsWrong();
        }
        if(questionNumber == 10) {
            alertDialog();
            return;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadNextQuestion();
            }
        }, 1000);
    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Test.this);
        builder.setTitle("Final Score");
        builder.setMessage("Your got " + numberOfCorrectAnswers + " right out of 10.");

        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(onDismissListener);
        dialog.show();
    }

    private void displayResultAsWrong() {
        resultBox.setText("Wrong!");
        resultBox.setTextColor(getResources().getColor(R.color.incorrect_answer));
    }

    private void displayResultAsCorrect() {
        resultBox.setText("Correct!");
        resultBox.setTextColor(getResources().getColor(R.color.correct_answer));
    }

    private void removeOldAnswerOptionButtons() {
        for (int row = 0; row < buttonTableLayout.getChildCount(); ++row)
            ((TableRow) buttonTableLayout.getChildAt(row)).removeAllViews();
    }

    private void removeClickablilityFromAllAnswerOptionButtons() {
        for (int row = 0; row < buttonTableLayout.getChildCount(); ++row)
            ((TableRow) buttonTableLayout.getChildAt(row)).setClickable(false);
    }

    private String pickIncorrectCountryName() {
        String countryName = null;
        while(true) {
            countryName = deriveCountryName(randomFlag());
            if(! correctAnswer.equalsIgnoreCase(countryName)) break;
        }
        return countryName;
    }

    private String deriveCountryName(final String nextImage) {
        return nextImage.substring(nextImage.indexOf("-")+1);
    }

    private void incrementQuestionNumberAndUpdateTitle() {
        questionNumber++;
        questionNumberTextView.setText("Question " + questionNumber + "of 10.");
    }

    private void resetQuiz() {
        Log.i("resetQuiz()", "resetQuiz()");
        numberOfCorrectAnswers = 0;
        questionNumber = 0;
        reloadQuizQuestions();
    }

    private void clearResultBox() {
        resultBox.setText("");
    }

    private void reloadQuizQuestions() {
        final int MAX_QUESTIONS = 10;
        for(int i=0; i< MAX_QUESTIONS;) {
            if(! quizQuestionsList.contains(flagName)) {
                quizQuestionsList.add(flagName);
                i++;
            }
        }
    }
}
/*/


