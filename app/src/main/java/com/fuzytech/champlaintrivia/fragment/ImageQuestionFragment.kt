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

class ImageQuestionFragment : Fragment() {
    private lateinit var question: String
    private lateinit var answers: List<Int>
    private var answer: Int? = null

    private lateinit var buttons: List<ImageView>

    private lateinit var binding: FragmentImageQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            question = it.getString("question")!!
            answers = it.getIntegerArrayList("answers")!!
            answer = it.getInt("answer")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageQuestionBinding.inflate(inflater, container, false)
        binding.apply { buttons = listOf(answer1, answer2, answer3, answer4) }
        (0..4).forEach { buttons[it].setImageResource(answers[it]) }
        binding.question.setText(question)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(question: String, answers: ArrayList<Int>, answer: Int) =
            StringQuestionFragment().apply {
                arguments = Bundle().apply {
                    putString("question", question)
                    putIntegerArrayList("answers", answers)
                    putInt("answer", answer)
                }
            }
    }
}