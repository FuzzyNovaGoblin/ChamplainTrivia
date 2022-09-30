package com.fuzytech.champlaintrivia.question

class MultipleChoiceQuestion<T>(override val question: String, val answers: List<T>, val answer: Int) : Question<Int> {
    override fun validate(answer: Int) = answer == this.answer
}