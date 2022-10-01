package com.fuzytech.champlaintrivia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.fuzytech.champlaintrivia.R
import com.fuzytech.champlaintrivia.databinding.FragmentOpenResponseQuestionBinding
import com.fuzytech.champlaintrivia.databinding.FragmentStringQuestionBinding
import com.fuzytech.champlaintrivia.question.OpenResponseQuestion
import com.fuzytech.champlaintrivia.question.Question

class OpenResponseQuestionFragment : Fragment() {
    private lateinit var question: OpenResponseQuestion
    private lateinit var nextQuestion: () -> Unit

    private lateinit var binding: FragmentOpenResponseQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let {
//            question = it.getString("question")!!
//            answer = it.getString("answer")!!
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOpenResponseQuestionBinding.inflate(inflater, container, false)
        binding.question.text = question.question
        binding.submit.setOnClickListener { nextQuestion() }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(nextQ: () -> Unit, question: OpenResponseQuestion) =
            OpenResponseQuestionFragment().apply {
                nextQuestion = nextQ
                this.question = question
            }
    }
}