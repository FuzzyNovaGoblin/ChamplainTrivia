package com.fuzytech.champlaintrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fuzytech.champlaintrivia.question.Question
import com.fuzytech.champlaintrivia.question.QuestionParser

private lateinit var questions: List<Question<*>>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questions = QuestionParser.parse(resources.openRawResource(R.raw.questions).bufferedReader().use{it.readText()})
        print(questions)
        setContentView(R.layout.activity_main)
    }
}