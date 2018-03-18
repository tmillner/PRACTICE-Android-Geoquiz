package com.trevor.showcase.geoquiz;

/**
 * Created by trevormillner on 12/10/17.
 */

public class Question {

    private int mTextId;
    private boolean mIsAnswerTrue;

    public Question(int textId, boolean isAnswerTrue) {
        mTextId = textId;
        mIsAnswerTrue = isAnswerTrue;
    }

    public int getTextId() {
        return mTextId;
    }

    public void setTextId(int textId) {
        mTextId = textId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }
}
