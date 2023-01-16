package com.alex_cutnet.aliasgame.domain.entities

data class Team(
    val nameTeam: String,
    val time: Int = DEFAULT_TIME,
    val correctAnswersForWin: Int = CORRECT_ANSWERS_FOR_WIN,
) {
    companion object {
        private const val DEFAULT_TIME = 60
        private const val CORRECT_ANSWERS_FOR_WIN = 25
    }
}
