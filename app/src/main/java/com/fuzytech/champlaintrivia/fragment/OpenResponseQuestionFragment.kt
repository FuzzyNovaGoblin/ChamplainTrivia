package com.fuzytech.champlaintrivia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.fuzytech.champlaintrivia.QuestionCallback
import com.fuzytech.champlaintrivia.R
import com.fuzytech.champlaintrivia.databinding.FragmentOpenResponseQuestionBinding
import com.fuzytech.champlaintrivia.databinding.FragmentStringQuestionBinding
import com.fuzytech.champlaintrivia.question.OpenResponseQuestion
import com.fuzytech.champlaintrivia.question.Question

class OpenResponseQuestionFragment : Fragment() {
    private lateinit var question: OpenResponseQuestion
    private lateinit var nextQuestion: QuestionCallback

    private lateinit var binding: FragmentOpenResponseQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            question = it.getSerializable("question")!! as OpenResponseQuestion
            nextQuestion = it.getSerializable("nextQuestion")!! as QuestionCallback
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOpenResponseQuestionBinding.inflate(inflater, container, false)
        binding.question.text = question.question
        binding.submit.setOnClickListener { nextQuestion(question.validate(binding.answer.text.toString())) }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(nextQ: QuestionCallback, question: OpenResponseQuestion) =
            OpenResponseQuestionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("question", question)
                    putSerializable("nextQuestion", nextQ)
                }
            }
    }
}