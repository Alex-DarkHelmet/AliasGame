package com.alex_cutnet.aliasgame.domain.entity

data class Team(
    val nameTeam: String,
    var sumOfCorrectAnswers: Int = DEFAULT_SUM_ANSWERS,

) {
    companion object {
        const val DEFAULT_SUM_ANSWERS = 10
    }
}
