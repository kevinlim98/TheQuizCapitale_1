package com.technicien_superieur.thequizcapitale

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*

class QuizActivity : AppCompatActivity() {

    var quizs = ArrayList<Quiz>()
    var numberOfGoodAnswers: Int = 0
    var currentQuizIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizs.add(Quiz("Abstract class is__", "A - A class must contain all pure virtual functions", "B - A class must contain at least one pure virtual function", "C - A class may not contain pure virtual function.", 2))
        quizs.add(Quiz("By default the members of the structure are", "A - private", "B - protected", "C - public", 3))
        quizs.add(Quiz("The programs machine instructions are store in __ memory segment.", "A - Data", "B - Stack", "C - Code", 3))
        quizs.add(Quiz("What is a generic class.", "A - Class template", "B - Function template", "C - Inherited class", 1))

        //Pour mélanger les questions
        Collections.shuffle(quizs);

        showQuestion(quizs.get(currentQuizIndex))
    }

    fun showQuestion(quiz: Quiz) {
        txtQuestion.setText(quiz.question)
        answer1.setText(quiz.answer1)
        answer2.setText(quiz.answer2)
        answer3.setText(quiz.answer3)

    }

    fun handleAnswer(answerID: Int) {
        val quiz = quizs.get(currentQuizIndex)

        if (quiz.isCorrect(answerID)) {
            numberOfGoodAnswers++
            Toast.makeText(this, "+1", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "+0", Toast.LENGTH_SHORT).show()
        }


        currentQuizIndex++


        if (currentQuizIndex >= quizs.size) {

            val sharedPreferences = getSharedPreferences("com.technicien_superieur.thequizcapitale", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt("userScore1", numberOfGoodAnswers).apply()

            var alert = AlertDialog.Builder(this)
            alert.setTitle("Congratulations!")
            alert.setMessage("You answered " + numberOfGoodAnswers + " questions correctly!")
            alert.setPositiveButton("OK") { dialogInterface: DialogInterface?, i: Int ->
                finish()
            }
            alert.show()

        } else { // On continue la partie
            showQuestion(quizs.get(currentQuizIndex))
        }

    }

    fun onClickAnwerOne(view: View) {
        handleAnswer(1)
    }

    fun onClickAnwerTwo(view: View) {
        handleAnswer(2)
    }

    fun onClickAnwerThree(view: View) {
        handleAnswer(3)
    }


}
