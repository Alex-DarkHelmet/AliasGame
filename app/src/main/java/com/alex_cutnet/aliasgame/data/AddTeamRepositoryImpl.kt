package com.alex_cutnet.aliasgame.data

import com.alex_cutnet.aliasgame.domain.entity.Team
import com.alex_cutnet.aliasgame.domain.repoistory.AddTeamRepository

object AddTeamRepositoryImpl : AddTeamRepository {

    private var countUndefinedTeam = 0

    private val DEFAULT_NAME_TEAM = "Team $countUndefinedTeam"

    override fun addTeam(inputName: String): Team {
        return if (parseInputName(inputName)) {
            Team(nameTeam = inputName)
        } else  {
            ++countUndefinedTeam
            Team(nameTeam = DEFAULT_NAME_TEAM)
        }
    }

    private fun parseInputName(inputName: String): Boolean {
        val regex = Regex("[A-z]")
        return (inputName.matches(regex))
    }
}
