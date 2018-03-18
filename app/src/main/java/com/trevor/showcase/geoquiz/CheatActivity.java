package com.trevor.showcase.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.trevor.showcase.geoquiz.answerIsTrue";
    private static final String EXTRA_ANSWER_IS_SHOWN = "com.trevor.showcase.geoquiz.answerIsShown";


    private boolean mAnswerIsTrue;

    private TextView mCheatAnswerTextView;
    private Button mCheatButton;

    // These are an here to hide the implementation details but accessible contract
    public static boolean wasAnswerShown(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mCheatAnswerTextView = findViewById(R.id.cheat_answer);

        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue == true) {
                    mCheatAnswerTextView.setText(R.string.true_button);
                }
                else {
                    mCheatAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShown(true);
            }
        });

    }

    private void setAnswerShown(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

}
