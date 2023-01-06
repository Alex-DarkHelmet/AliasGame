package com.alex_cutnet.aliasgame.domain.repoistory

import com.alex_cutnet.aliasgame.domain.entity.Team

interface AddTeamRepository {

    fun addTeam(inputName: String): Team

    fun changeSumOfCorrectAnswers(oldValue: Int)


}