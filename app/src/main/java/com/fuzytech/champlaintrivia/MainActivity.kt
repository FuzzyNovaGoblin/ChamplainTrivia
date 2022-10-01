package com.fuzytech.champlaintrivia

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fuzytech.champlaintrivia.databinding.ActivityMainBinding
import com.fuzytech.champlaintrivia.fragment.HomPageFragment
import com.fuzytech.champlaintrivia.fragment.ImageQuestionFragment
import com.fuzytech.champlaintrivia.fragment.OpenResponseQuestionFragment
import com.fuzytech.champlaintrivia.fragment.StringQuestionFragment
import com.fuzytech.champlaintrivia.question.MultipleChoiceQuestion
import com.fuzytech.champlaintrivia.question.OpenResponseQuestion
import com.fuzytech.champlaintrivia.question.Question
import com.fuzytech.champlaintrivia.question.QuestionParser
import java.lang.IllegalArgumentException

private lateinit var questions: List<Question<*>>
private var questionIndex: Int = 0
private var onHome = true
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("here", "in on create")

        binding = ActivityMainBinding.inflate(layoutInflater)

        val inerthing = resources.openRawResource(R.raw.questions)
        questions = QuestionParser.parse(inerthing.bufferedReader().use { it.readText() })
        inerthing.close()

        Log.i("here", "questions:")
        for (q in questions) {
            Log.i("here", q.toString())
        }
        setContentView(R.layout.activity_main)

        Log.i("here", "before")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, HomPageFragment.newInstance { nextQuestion(it) }).addToBackStack("home page").commit()
        Log.i("here", "after")
    }


    fun nextQuestion(isCorrect: Boolean) {
        onHome = false

        if (isCorrect){
            Toast.makeText(applicationContext, "is correct", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, "you stupid idiotz", Toast.LENGTH_SHORT).show()
        }

        if (questionIndex > questions.size) {
            TODO()
        }

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

        questionIndex += 1
    }

    override fun onBackPressed() {
        if (onHome) super.onBackPressed() else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentLayout, HomPageFragment.newInstance {
                    nextQuestion(it)
                }).commit()
        }
    }
}