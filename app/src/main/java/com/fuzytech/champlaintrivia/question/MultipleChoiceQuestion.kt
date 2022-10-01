package com.fuzytech.champlaintrivia.question

class MultipleChoiceQuestion<T>(override val question: String, val answers: List<T>, val answer: Int, private val jsontype: String) : Question<Int> {
    override fun validate(answer: Int) = answer == this.answer


    override fun toString(): String {
        return question
    }

    override fun jsontype(): String {
        return jsontype
    }
}