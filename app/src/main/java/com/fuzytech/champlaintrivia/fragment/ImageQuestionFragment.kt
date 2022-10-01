package com.fuzytech.champlaintrivia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.fuzytech.champlaintrivia.QuestionCallback
import com.fuzytech.champlaintrivia.R
import com.fuzytech.champlaintrivia.databinding.FragmentImageQuestionBinding
import com.fuzytech.champlaintrivia.databinding.FragmentStringQuestionBinding
import com.fuzytech.champlaintrivia.question.MultipleChoiceQuestion

class ImageQuestionFragment : Fragment() {
    private lateinit var question: MultipleChoiceQuestion<Int>
    private lateinit var nextQuestion: QuestionCallback


    private lateinit var buttons: List<ImageView>

    private lateinit var binding: FragmentImageQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            question = it.getSerializable("question")!! as MultipleChoiceQuestion<Int>
            nextQuestion = it.getSerializable("nextQuestion")!! as QuestionCallback

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageQuestionBinding.inflate(inflater, container, false)
        binding.apply { buttons = listOf(answer1, answer2, answer3, answer4) }
        for (i in 0 until 4)
        {
            buttons[i].setOnClickListener { nextQuestion(question.validate(i)) }
            buttons[i].setImageResource(question.answers[i])
        }
        binding.question.text = question.question
        return binding.root
    }
    
    companion object {
        @JvmStatic
        fun newInstance(nextQ: QuestionCallback, question: MultipleChoiceQuestion<Int>) =
            ImageQuestionFragment().apply {
                StringQuestionFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("question", question)
                        putSerializable("nextQuestion", nextQ)
                    }
                }
            }

    }
}