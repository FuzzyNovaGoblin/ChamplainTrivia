package com.fuzytech.champlaintrivia.question

interface Question<A> {

    public val question: String

    public fun validate(answer: A): Boolean
}