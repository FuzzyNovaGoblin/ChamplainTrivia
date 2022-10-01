package com.fuzytech.champlaintrivia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.fuzytech.champlaintrivia.R
import com.fuzytech.champlaintrivia.databinding.FragmentImageQuestionBinding
import com.fuzytech.champlaintrivia.databinding.FragmentStringQuestionBinding
import com.fuzytech.champlaintrivia.question.MultipleChoiceQuestion

class ImageQuestionFragment : Fragment() {
    private lateinit var question: MultipleChoiceQuestion<Int>
    private lateinit var nextQuestion: () -> Unit


    private lateinit var buttons: List<ImageView>

    private lateinit var binding: FragmentImageQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageQuestionBinding.inflate(inflater, container, false)
        binding.apply { buttons = listOf(answer1, answer2, answer3, answer4) }
        (0..4).forEach {
            buttons[it].setOnClickListener { nextQuestion() }
            buttons[it].setImageResource(question.answers[it])
        }
        binding.question.text = question.question
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance(nextQ: () -> Unit, question: MultipleChoiceQuestion<Int>) =
            ImageQuestionFragment().apply {
                nextQuestion = nextQ
                this.question = question
            }

    }
}