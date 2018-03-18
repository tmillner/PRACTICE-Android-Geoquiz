package com.trevor.showcase.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static String TAG = "QuizActivity";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private Button mCheatButton;

    private TextView mCurrentQuestion;
    private static final String QUESTION_INDEX = "index";
    private static final String EXTRA_ANSWER_IS_TRUE = "com.trevor.showcase.geoquiz.answerIsTrue";
    private static final int REQUEST_CODE_SHOW_ANSWER = 0;

    private Question[] mQuestionList = new Question[] {
            new Question(R.string.questions_africa, false),
            new Question(R.string.questions_continents, false),
            new Question(R.string.questions_ocean, true)
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    // These are an here to hide the implementation details
    private static Intent newCheatIntent(Context context, boolean isAnswerTrue) {
        Intent cheatIntent = new Intent(context, CheatActivity.class);
        cheatIntent.putExtra(EXTRA_ANSWER_IS_TRUE, isAnswerTrue);
        return cheatIntent;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle savedInstanceState) called");
        setContentView(R.layout.activity_quiz);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(QUESTION_INDEX, 0);
        }

        mCurrentQuestion = findViewById(R.id.question_text_view);
        setQuestion();
        /* // Enable this to allow clicking on text to navigate forward
        mCurrentQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionList.length;
                setQuestion();
            }
        });
        */

        mCheatButton = findViewById(R.id.dont_know_answer_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Go to Cheat Activity
                Intent cheatIntent = newCheatIntent(QuizActivity.this,
                        mQuestionList[mCurrentIndex].isAnswerTrue());
                startActivityForResult(cheatIntent, REQUEST_CODE_SHOW_ANSWER);
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionList.length;
                mIsCheater = false;
                setQuestion();
            }
        });

        mPreviousButton = findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mCurrentIndex == 0) {
                    mCurrentIndex = mQuestionList.length - 1;
                }
                else {
                    mCurrentIndex--;
                }
                setQuestion();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != RESULT_OK) {
            return;
        }
        if(requestCode == REQUEST_CODE_SHOW_ANSWER) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean isTrue = mQuestionList[mCurrentIndex].isAnswerTrue();

        int messageStringId = 0;

        if(mIsCheater) {
            messageStringId = R.string.judgment_toast;
        }
        else {
            if (isTrue == userPressedTrue) {
                messageStringId = R.string.correct_toast;
            }
            else {
                messageStringId = R.string.incorrect_toast;
            }
        }

        // can't simply pass in 'this'
        Toast.makeText(QuizActivity.this, messageStringId, Toast.LENGTH_LONG).show();
    }

    private void setQuestion() {
        int question = mQuestionList[mCurrentIndex].getTextId();
        mCurrentQuestion.setText(question);
    }

    @Override
    protected void onSaveInstanceState(Bundle b) {
        super.onSaveInstanceState(b);
        Log.d(TAG, "onSaveInstanceState() called");
        b.putInt(QUESTION_INDEX, mCurrentIndex);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
