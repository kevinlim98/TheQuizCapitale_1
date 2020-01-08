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

class QuizActivity2 : AppCompatActivity() {

    var quizs = ArrayList<Quiz>()
    var numberOfGoodAnswers: Int = 0
    var currentQuizIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizs.add(Quiz("Which type of casting can be used only with pointers and references to objects?", "A - Dynamic_cast", "B - Static_cast", "C - Pointer_Cast", 1))
        quizs.add(Quiz("Under which of the following circumstances, synchronization takes place?", "A - When the file is closed", "B - When the buffer is empty", "C - Explicitly, with manipulators", 3))
        quizs.add(Quiz("How do we check if the file has reached its end?", "A - Use if_file_end()", "B - use end_of_file()", "C - use eof()", 3))
        quizs.add(Quiz("Identify the C++ compiler of Linux", "A - cpp", "B - g++", "C - Borland", 2))

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
            sharedPreferences.edit().putInt("userScore2", numberOfGoodAnswers).apply()

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
