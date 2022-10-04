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
            startQuiz(R.raw.questions)
        }
        binding.champlainButton.setOnLongClickListener {
            showScore(R.raw.questions)
        }

        binding.burlingtonButton.setOnClickListener {
            startQuiz(R.raw.burlington)
        }
        binding.burlingtonButton.setOnLongClickListener {
            showScore(R.raw.burlington)
        }

        binding.programmingButton.setOnClickListener {
            startQuiz(R.raw.programming)
        }
        binding.programmingButton.setOnLongClickListener {
            showScore(R.raw.programming)
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

    private fun startQuiz(file_id: Int) {
        val intent = Intent(
            this,
            QuizActivity::class.java
        )
        intent.putExtra("file_id", file_id)
        startActivity(intent)
    }

    private fun showScore(file_id: Int): Boolean {
        val intent = Intent(
            this,
            HighScoreActivity::class.java
        )
        intent.putExtra("quiz", file_id.toString())
        intent.putExtra("score", -1)
        startActivity(intent)
        return true
    }

}