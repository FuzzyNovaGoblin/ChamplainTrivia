package com.fuzytech.champlaintrivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fuzytech.champlaintrivia.databinding.ActivityMainBinding
import com.fuzytech.champlaintrivia.highscore.Scoreboard
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
            startQuiz(R.raw.questions, "champlain")
        }
        binding.champlainButton.setOnLongClickListener {
            showScore("burlington")
        }

        binding.burlingtonButton.setOnClickListener {
            startQuiz(R.raw.burlington, "burlington")
        }
        binding.burlingtonButton.setOnLongClickListener {
            showScore("burlington")
        }

        binding.programmingButton.setOnClickListener {
            startQuiz(R.raw.programming, "programming")
        }
        binding.programmingButton.setOnLongClickListener {
            showScore("programming")
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


        Scoreboard.init(dataDir)
    }

    private fun startQuiz(file_id: Int, quiz: String) {
        val intent = Intent(
            this,
            QuizActivity::class.java
        )
        intent.putExtra("file_id", file_id)
        intent.putExtra("quiz", quiz)
        startActivity(intent)
    }

    private fun showScore(quiz: String): Boolean {
        val intent = Intent(
            this,
            HighScoreActivity::class.java
        )
        intent.putExtra("quiz", quiz)
        intent.putExtra("score", -1)
        startActivity(intent)
        return true
    }

}