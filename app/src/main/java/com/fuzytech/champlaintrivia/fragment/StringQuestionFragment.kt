package com.fuzytech.champlaintrivia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.fuzytech.champlaintrivia.R
import com.fuzytech.champlaintrivia.databinding.FragmentStringQuestionBinding
import com.fuzytech.champlaintrivia.question.MultipleChoiceQuestion

class StringQuestionFragment : Fragment() {
    private lateinit var question: MultipleChoiceQuestion<String>
    private lateinit var nextQuestion: () -> Unit


    private lateinit var buttons: List<Button>

    private lateinit var binding: FragmentStringQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStringQuestionBinding.inflate(inflater, container, false)
        binding.apply { buttons = listOf(answer1, answer2, answer3, answer4) }
        (0 until 4).forEach {
            buttons[it].text = question.answers[it]
            buttons[it].setOnClickListener { nextQuestion() }
        }
        binding.question.text = question.question
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(nextQ: () -> Unit, question: MultipleChoiceQuestion<String>) =
            StringQuestionFragment().apply {
                nextQuestion = nextQ
                this.question = question
            }
    }
}