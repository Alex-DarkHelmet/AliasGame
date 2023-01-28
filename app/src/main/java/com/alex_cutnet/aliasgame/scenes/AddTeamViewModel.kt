package com.alex_cutnet.aliasgame.scenes

import androidx.lifecycle.ViewModel

class AddTeamViewModel : ViewModel() {

    fun checkNicknamesNotDuplicates(nicknamesList: List<String>): List<String> {
        val localNicknamesList = mutableListOf<String>()

        for (nickname in nicknamesList) {
            if (!localNicknamesList.contains(nickname))
                localNicknamesList.add(nickname)
            else continue
        }
        return localNicknamesList
    }
}