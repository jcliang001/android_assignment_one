package com.csci448.jiancongliang.kotlinquiz
import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cheat.*
import kotlinx.android.synthetic.main.activity_quiz.*

class CheatActivity : AppCompatActivity(){

    companion object {
        private const val LOG_TAG = "448.CheatActivity"
        private const val ANSWER_KEY = "CORRECT_ANSWER_KEY"
        private val EXTRA_VALUE_SHOWN = "VALUE_SHOWN"
        public final fun createIntent(context: Context, isAnswerTrue: Boolean) : Intent{
            val cheatIntent = Intent(context, CheatActivity::class.java )
            cheatIntent.putExtra(ANSWER_KEY,isAnswerTrue )

            return cheatIntent
        }

        fun wasAnswerShown(result: Intent?):Boolean{
            return result!!.getBooleanExtra(EXTRA_VALUE_SHOWN,false)
        }

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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
         val output = (intent.getBooleanExtra(ANSWER_KEY, false)).toString()

        cheat_button.setOnClickListener {
            cheat_answer_location.text = output
            cheat_answer_location.visibility= View.VISIBLE
        }

    }
}