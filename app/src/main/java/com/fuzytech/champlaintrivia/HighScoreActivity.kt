package com.fuzytech.champlaintrivia

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fuzytech.champlaintrivia.databinding.ActivityHighScoreBinding
import com.fuzytech.champlaintrivia.highscore.Scoreboard


class HighScoreActivity : AppCompatActivity() {

    private lateinit var quiz: String
    private lateinit var binding: ActivityHighScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        this.quiz = (intent.extras?.getString("quiz") ?: savedInstanceState?.getString("quiz"))!!

        val score = intent.extras?.getInt("score") ?: -1


        super.onCreate(savedInstanceState)
        binding = ActivityHighScoreBinding.inflate(layoutInflater)
        binding.highscores.setText(Scoreboard.highScores(quiz, 10).joinToString("\n"))
        setContentView(binding.root)

        binding.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz score")
            intent.putExtra(Intent.EXTRA_TEXT, "Check this out! I got a score of $score in the $quiz quiz!")
        }

        if (score != -1) {
            var newName = ""
            Log.i("here", "score not -1")
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val input: EditText = EditText(this);
            input.hint = "your name"
            input.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_TEXT)
            builder.setView(input)
            builder.setPositiveButton(
                "OK"
            ) { _, _ ->
                newName = input.text.toString()
                Scoreboard.addScore(quiz, newName, score)
                binding.highscores.text = Scoreboard.highScores(quiz, 10).joinToString("\n")
                setContentView(binding.root)
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ -> dialog.cancel() }
            builder.show()
        } else {
            binding.share.hide()
        }
    }
}