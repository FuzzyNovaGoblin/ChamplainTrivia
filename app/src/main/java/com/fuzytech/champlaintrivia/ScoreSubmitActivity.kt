package com.fuzytech.champlaintrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fuzytech.champlaintrivia.databinding.ActivityHighScoreBinding
import com.fuzytech.champlaintrivia.databinding.ActivityScoreSubmitBinding

class ScoreSubmitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreSubmitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreSubmitBinding.inflate(layoutInflater)
        binding.score.setText(intent.extras?.getInt("score")!!)
        setContentView(R.layout.activity_score_submit)
    }
}