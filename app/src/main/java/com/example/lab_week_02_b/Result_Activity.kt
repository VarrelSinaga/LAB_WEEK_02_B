package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class Result_Activity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
        private const val DEBUG_TAG = "DEBUG_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        if(intent != null){
            val colorCode = intent.getStringExtra(COLOR_KEY)
            val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
            try {
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
            }
            catch (ex: IllegalArgumentException){
                Intent().let{ errorIntent ->
                    errorIntent.putExtra(ERROR_KEY, true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                }
            }
            val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(R.string.color_code_result_message,colorCode?.uppercase())
        }
        Log.d(DEBUG_TAG, "onCreate")
        findViewById<Button>(R.id.back_button).setOnClickListener{
            startActivity(
                Intent(this,
                    MainActivity::class.java)
            )
        }
    }
}