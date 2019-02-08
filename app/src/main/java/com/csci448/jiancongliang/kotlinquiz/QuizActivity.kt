package com.csci448.jiancongliang.kotlinquiz
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private final var isCheater = 0

    private fun setCurrentScoreText() {
        score_text_view.text = QuizMaster.currentScore.toString()
    }
    private fun updateQuestion() {
        setCurrentScoreText()
        question_text_view.text =
                resources.getString(QuizMaster.getCurrentQuestionTextId())
    }
    private fun checkAnswer(suppliedAnswer: Boolean) {

        if( QuizMaster.isAnswerCorrect(suppliedAnswer) ) {
            if (isCheater == 0) {
                Toast.makeText(
                    baseContext,
                    R.string.correct_toast,
                    Toast.LENGTH_SHORT
                ).show()
                setCurrentScoreText()
            }
            else{
                Toast.makeText(
                    baseContext,
                    R.string.cheat_toast,
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(baseContext,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT).show()
        }


    }

    private fun moveToQuestion(direction: Int) {
        if( direction > 0 ) {
            QuizMaster.moveToNextQuestion()
        } else if( direction < 0 ) {
            QuizMaster.moveToPreviousQuestion()
        }
        updateQuestion()
    }

    companion object {
        private const val LOG_TAG = "448.QuizActivity"
        private const val CURRENT_SCORE_KEY = "CURRENT_SCORE_KEY"
        private const val CURRENT_QUESTION_KEY = "CURRENT_QUESTION_KEY"
        private const val REQUEST_CODE_CHEAT = 0
        private const val CURRENT_ISCHEATER = "ISCHEATER"
        private const val EXTRA = "STATUS_OK"


    }
    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume() called")
    }
    override fun onPause() {
        Log.d(LOG_TAG, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(LOG_TAG, "onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy() called")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG,"onSaveInstanceState() called")

        outState?.putInt ( CURRENT_SCORE_KEY, QuizMaster.currentScore )
        outState?.putInt ( CURRENT_QUESTION_KEY, QuizMaster.currentQuestionIndex )
        outState?.putInt ( CURRENT_ISCHEATER, isCheater)
    }
    //    launchCheat() method needs to do two steps: (1) create the explicit intent (2) start the
//    activity.
    private fun launchCheat(){
        isCheater = 1
        val intent = CheatActivity.createIntent( baseContext,
                                                 QuizMaster.getCurrentAnswer())
        startActivityForResult(intent, REQUEST_CODE_CHEAT)
    }

    fun getCurrentAnswer() = QuizMaster.getCurrentQuestion().isAnswerTrue



    //    The function should do three things:
//    1. Create a blank generic Intent
//    2. Add an extra with a true value
//    3. Send the result intent with status ok
    private fun setCheated(isAnswerShown : Boolean){
        val newIntent = Intent()
        newIntent.putExtra(EXTRA, isAnswerShown)
        setResult(Activity.RESULT_OK, newIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data : Intent?) {
        if (resultCode != Activity.RESULT_OK){
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if (data == null){
                return
            }
        }

        isCheater = if(CheatActivity.wasAnswerShown(data)) 1 else 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz)

        if ( savedInstanceState != null){
            Log.d(LOG_TAG, "savedInstanceState is not null")

            val scoreHistory = savedInstanceState.getInt( CURRENT_SCORE_KEY)
            val priorQuestion = savedInstanceState.getInt( CURRENT_QUESTION_KEY)
            isCheater = savedInstanceState.getInt(CURRENT_ISCHEATER)
            QuizMaster.currentScore = scoreHistory
            QuizMaster.currentQuestionIndex = priorQuestion
        }
        select_cheat_button.setOnClickListener{launchCheat()}
        true_button.setOnClickListener { checkAnswer(true) }
        false_button.setOnClickListener { checkAnswer(false) }
        next_button.setOnClickListener { moveToQuestion(1) }
        previous_button.setOnClickListener { moveToQuestion(-1) }
        updateQuestion()
        isCheater = 0

    }


}