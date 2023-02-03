package com.alex_cutnet.aliasgame.scenes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddTeamViewModel : ViewModel() {

    private var _nicknamesForTeams = MutableLiveData<List<String>>()
    val nicknamesForTeams: LiveData<List<String>>
        get() = _nicknamesForTeams


    fun checkNicknamesNotDuplicates(nicknamesList: List<String>): List<String> {
        val localNicknamesList = mutableListOf<String>()

        for (nickname in nicknamesList) {
            if (localNicknamesList.size != MAX_SUM_TEAMS) {
                if (!localNicknamesList.contains(nickname)) {
                    localNicknamesList.add(nickname)
                } else continue
            } else break // break if list already full
        }

        return localNicknamesList
    }

    companion object {
        private const val MAX_SUM_TEAMS = 5
    }
}