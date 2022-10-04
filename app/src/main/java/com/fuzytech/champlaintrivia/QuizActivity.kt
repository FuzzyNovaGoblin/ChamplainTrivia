package com.fuzytech.champlaintrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.fuzytech.champlaintrivia.databinding.ActivityMainBinding
import com.fuzytech.champlaintrivia.fragment.ImageQuestionFragment
import com.fuzytech.champlaintrivia.fragment.OpenResponseQuestionFragment
import com.fuzytech.champlaintrivia.fragment.StringQuestionFragment
import com.fuzytech.champlaintrivia.question.MultipleChoiceQuestion
import com.fuzytech.champlaintrivia.question.OpenResponseQuestion
import com.fuzytech.champlaintrivia.question.Question
import com.fuzytech.champlaintrivia.question.QuestionParser
import java.lang.IllegalArgumentException

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var questions: List<Question<*>>
    private var questionIndex: Int = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val fileId = intent.extras!!.getInt("file_id");

        val inerthing = resources.openRawResource(fileId)
        questions = QuestionParser.parse(inerthing.bufferedReader().use { it.readText() })
        inerthing.close()


        binding = ActivityMainBinding.inflate(layoutInflater)

        nextQuestion(false)

    }


    private fun nextQuestion(isCorrect: Boolean) {

        if (isCorrect){
            score += 1
            Toast.makeText(applicationContext, "is correct, score: $score", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "you stupid idiot", Toast.LENGTH_SHORT).show()
        }

        if (questionIndex >= questions.size) {
            Log.i("here", "in question index too big")
            return
        }
        Log.i("here", "above when")

        when (questions[questionIndex].jsontype()) {
            // rust types are great java types suck if you care enough you can fix this, or even just half fix it with an enum
            "string" -> supportFragmentManager.beginTransaction().replace(
                R.id.fragmentLayout, StringQuestionFragment.newInstance(
                    { nextQuestion(it) },
                    questions[questionIndex] as MultipleChoiceQuestion<String>
                )
            ).commit()
            "openresponse" -> supportFragmentManager.beginTransaction().replace(
                R.id.fragmentLayout, OpenResponseQuestionFragment.newInstance(
                    { nextQuestion(it) },
                    questions[questionIndex] as OpenResponseQuestion
                )
            ).commit()
            "image" -> supportFragmentManager.beginTransaction().replace(
                R.id.fragmentLayout, ImageQuestionFragment.newInstance(
                    { nextQuestion(it) },
                    questions[questionIndex] as MultipleChoiceQuestion<Int>
                )
            ).commit()
            else -> throw IllegalArgumentException("stupid java types")
        }
        Log.i("here", "below when")

        questionIndex += 1
    }

}