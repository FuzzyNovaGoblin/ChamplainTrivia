package com.fuzytech.champlaintrivia.question

import org.json.JSONArray
import org.json.JSONObject
import java.lang.IllegalArgumentException

object QuestionParser {

    private inline fun <reified T> toList(array: JSONArray): ArrayList<T> {
        val list = ArrayList<T>()
        for (i in 0 until array.length()) {
            list.add(array[i] as T)
        }
        return list
    }

    fun parse(input: String): List<Question<*>> {
        val list = JSONArray(input)
        val out = mutableListOf<Question<*>>()
        for (i in 0 until list.length()) {
            val entry = list[i] as JSONObject
            val type = entry["type"] as String
            val question = entry["question"] as String
            out.add(when (type) {
                "string" -> MultipleChoiceQuestion(question, toList<String>(entry.getJSONArray("answers")), entry["answer"] as Int, type)
                "openresponse" -> OpenResponseQuestion(question, entry["answer"] as String)
                "image" -> MultipleChoiceQuestion(question, toList<Int>(entry.getJSONArray("answers")), entry["answer"] as Int, type)
                else -> throw IllegalArgumentException("Invalid question type")
            })
        }
        return out
    }

}