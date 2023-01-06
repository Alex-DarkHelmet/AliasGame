package com.alex_cutnet.aliasgame.domain.useCase

import com.alex_cutnet.aliasgame.domain.repoistory.AddTeamRepository

class ChangeSumOfCorrectAnswersUseCase(
    private val repository: AddTeamRepository
) {
    operator fun invoke(oldValue: Int) {
        repository.changeSumOfCorrectAnswers(oldValue)
    }
}