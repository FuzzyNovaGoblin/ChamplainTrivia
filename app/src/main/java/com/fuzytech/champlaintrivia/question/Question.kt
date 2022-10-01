package com.fuzytech.champlaintrivia.question


interface Question<A>: java.io.Serializable {

     public val question: String

     public fun validate(answer: A): Boolean

     fun jsontype(): String
}