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

class QuizActivity3 : AppCompatActivity() {

    var quizs = ArrayList<Quiz>()
    var numberOfGoodAnswers: Int = 0
    var currentQuizIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizs.add(Quiz("Class derived: public base1, public base2 { } is an example of", "A - Polymorphic inheritance", "B - Multilevel inheritance", "C - Multiple inheritance", 3))
        quizs.add(Quiz("Which of the following is not a valid conditional inclusions in preprocessor directives?", "A - #ifdef", "B - #ifundef", "C - #endif", 2))
        quizs.add(Quiz("Which of the following is not a standard exception built in C++?", "A - std::bad_creat", "B - std::bad_alloc", "C - std::bad_cast", 1))
        quizs.add(Quiz("When class B is inherited from class A, what is the order in which the constructors of those classes are called", "A - Class A first Class B next", "B - Class B first Class A next", "C - Class B's only as it is the child class", 1))

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
            sharedPreferences.edit().putInt("userScore3", numberOfGoodAnswers).apply()

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
