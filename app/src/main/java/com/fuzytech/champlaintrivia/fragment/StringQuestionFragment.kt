package com.fuzytech.champlaintrivia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.fuzytech.champlaintrivia.QuestionCallback
import com.fuzytech.champlaintrivia.R
import com.fuzytech.champlaintrivia.databinding.FragmentStringQuestionBinding
import com.fuzytech.champlaintrivia.question.MultipleChoiceQuestion
import com.fuzytech.champlaintrivia.question.OpenResponseQuestion

class StringQuestionFragment : Fragment() {
    private lateinit var question: MultipleChoiceQuestion<String>
    private lateinit var nextQuestion: QuestionCallback


    private lateinit var buttons: List<Button>

    private lateinit var binding: FragmentStringQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            question = it.getSerializable("question")!! as MultipleChoiceQuestion<String>
            nextQuestion = it.getSerializable("nextQuestion")!! as QuestionCallback

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStringQuestionBinding.inflate(inflater, container, false)
        binding.apply { buttons = listOf(answer1, answer2, answer3, answer4) }
        for (i in 0 until 4) {
            buttons[i].text = question.answers[i]
            buttons[i].setOnClickListener { nextQuestion(question.validate(i)) }

        }
        binding.question.text = question.question
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(nextQ: QuestionCallback, question: MultipleChoiceQuestion<String>) =
            StringQuestionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("question", question)
                    putSerializable("nextQuestion", nextQ)
                }
            }
    }
}