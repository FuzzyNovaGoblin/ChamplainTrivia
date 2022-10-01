package com.fuzytech.champlaintrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fuzytech.champlaintrivia.databinding.ActivityHighScoreBinding
import com.fuzytech.champlaintrivia.highscore.Scoreboard

class HighScoreActivity : AppCompatActivity() {

    private lateinit var quiz: String
    private lateinit var binding: ActivityHighScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        this.quiz = (intent.extras?.getString("quiz") ?: savedInstanceState?.getString("quiz"))!!
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)
        binding = ActivityHighScoreBinding.inflate(layoutInflater)
        binding.highscores.setText(Scoreboard.highScores(quiz, 10).joinToString("\n"))
    }
}