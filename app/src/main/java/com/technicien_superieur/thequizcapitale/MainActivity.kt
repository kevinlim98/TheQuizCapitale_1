package com.technicien_superieur.thequizcapitale

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("com.technicien_superieur.thequizcapitale", Context.MODE_PRIVATE)

        val userScore1 = sharedPreferences.getInt("userScore1", -1)

        if (userScore1 > -1) {
            button1.text = "Level 1    Result: $userScore1/4 "
        }

        val userScore2 = sharedPreferences.getInt("userScore2", -1)

        if (userScore2 > -1) {
            button2.text = "Level 2    Result: $userScore2/4 "
        }

        val userScore3 = sharedPreferences.getInt("userScore3", -1)

        if (userScore3 > -1) {
            button3.text = "Level 3    Result: $userScore3/4 "
        }
        val sum = userScore1 + userScore2+ userScore3
        button4.text = "Total Result :  "+sum+"/12"
    }

    fun onClickBtnPlay(view:View) {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }

    fun onClickBtnPlay1(view:View) {
        val intent = Intent(this, QuizActivity2::class.java)
        startActivity(intent)
    }

    fun onClickBtnPlay2(view:View) {
        val intent = Intent(this, QuizActivity3::class.java)
        startActivity(intent)
    }


}
