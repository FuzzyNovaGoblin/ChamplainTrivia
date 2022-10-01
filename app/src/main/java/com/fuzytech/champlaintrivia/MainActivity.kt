package com.fuzytech.champlaintrivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fuzytech.champlaintrivia.databinding.ActivityMainBinding
import com.fuzytech.champlaintrivia.question.Question


private lateinit var questions: List<Question<*>>
private var questionIndex: Int = 0
private var onHome = true
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        Log.i("here", "in on create")
//
        Log.i("here", "in on create")

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.champlainButton.setOnClickListener {
            Log.i("here", "in champlainButton click")
            startQuiz(R.raw.questions)
        }

        binding.burlingtonButton.setOnClickListener {
            Log.i("here", "in burlingtonButton click")
            startQuiz(R.raw.burlington)
        }

        binding.programmingButton.setOnClickListener {
            Log.i("here", "in programmingButton click")
            startQuiz(R.raw.programming)
        }
//        val inerthing = resources.openRawResource(R.raw.questions)
//        questions = QuestionParser.parse(inerthing.bufferedReader().use { it.readText() })
//        inerthing.close()
//
//        Log.i("here", "questions:")
//        for (q in questions) {
//            Log.i("here", q.toString())
//        }
        setContentView(binding.root)


//
//        Log.i("here", "before")
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentLayout, HomPageFragment.newInstance { nextQuestion(it) }).addToBackStack("home page").commit()
//        Log.i("here", "after")


    }

    fun startQuiz(file_id: Int) {
        val intent = Intent(
            this@MainActivity,
            QuizActivity::class.java
        )
        intent.putExtra("file_id", file_id)
        startActivity(intent)
    }

}