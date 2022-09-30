package com.fuzytech.champlaintrivia.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.fuzytech.champlaintrivia.R
import com.fuzytech.champlaintrivia.databinding.FragmentStringQuestionBinding

class StringQuestionFragment : Fragment() {
    private lateinit var question: String
    private lateinit var answers: List<String>
    private var answer: Int? = null

    private lateinit var buttons: List<Button>

    private lateinit var binding: FragmentStringQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getString("question")!!
            answers = it.getStringArrayList("answers")!!
            answer = it.getInt("answer")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStringQuestionBinding.inflate(inflater, container, false)
        binding.apply { buttons = listOf(answer1, answer2, answer3, answer4) }
        (0..4).forEach { buttons[it].setText(answers[it]) }
        binding.question.setText(question)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(question: String, answers: ArrayList<String>, answer: Int) =
            StringQuestionFragment().apply {
                arguments = Bundle().apply {
                    putString("question", question)
                    putStringArrayList("answers", answers)
                    putInt("answer", answer)
                }
            }
    }
}