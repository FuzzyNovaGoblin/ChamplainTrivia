package com.fuzytech.champlaintrivia.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fuzytech.champlaintrivia.QuestionCallback
import com.fuzytech.champlaintrivia.R
import com.fuzytech.champlaintrivia.databinding.FragmentHomPageBinding
import com.fuzytech.champlaintrivia.databinding.FragmentImageQuestionBinding

class HomPageFragment : Fragment() {

    private lateinit var binding: FragmentHomPageBinding
    private lateinit var nextQuestion: QuestionCallback

    companion object {
        fun newInstance(nextQ: QuestionCallback) = HomPageFragment().apply {
            nextQuestion = nextQ
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomPageBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener {
            Log.i("here", "in click listener")
            nextQuestion(false)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("here", "frag in on create")

        super.onCreate(savedInstanceState)
    }
}