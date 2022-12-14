package com.fuzytech.champlaintrivia.highscore

import android.util.Log
import java.io.File
import kotlin.math.log

object Scoreboard {

    private lateinit var file: File

    fun init(file: File) {
        this.file = file
    }

    fun file(quiz: String) = file.resolve("${quiz}.txt")

    fun scores(quiz: String): MutableMap<String, Int> {
        val map = HashMap<String, Int>()
        if (!file(quiz).exists()) {
            file(quiz).createNewFile()
        }
        file(quiz).readLines().forEach {it.split(" ".toRegex(), 2).also {map[it[1]] = it[0].toInt()}}
        Log.i("check_data", map.entries.toString())
        return map
    }

    fun highScores(quiz: String, limit: Int): List<String> {
        val scores = scores(quiz)
        val players = ArrayList<String>()
        players.addAll(scores.keys)
        players.sortByDescending { scores[it] }
        return players.map { "$it - ${scores[it]}" }.take(limit)
    }

    fun addScore(quiz: String, player: String, score: Int) {
        val scores = scores(quiz)
        scores[player] = score
        val contents = scores.entries.map {(player, score) -> "$score $player"}.joinToString("\n")
        file(quiz).writeText(contents)
    }

}