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

class OpenResponseQuestionFragment : Fragment() {
    private lateinit var question: String
    private lateinit var answer: String

    private lateinit var binding: FragmentOpenResponseQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            question = it.getString("question")!!
            answer = it.getString("answer")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOpenResponseQuestionBinding.inflate(inflater, container, false)
        binding.question.setText(question)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(question: String, answer: String) =
            StringQuestionFragment().apply {
                arguments = Bundle().apply {
                    putString("question", question)
                    putString("answer", answer)
                }
            }
    }
}