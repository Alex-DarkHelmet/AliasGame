package com.alex_cutnet.aliasgame.domain.useCase

import com.alex_cutnet.aliasgame.domain.entity.Team
import com.alex_cutnet.aliasgame.domain.repoistory.AddTeamRepository

class AddTeamUseCase(
    private val repository: AddTeamRepository
) {
    operator fun invoke(inputName: String): Team {
        return repository.addTeam(inputName)
    }
}