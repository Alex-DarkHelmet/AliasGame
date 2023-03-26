package com.alex_cutnet.aliasgame.scenes.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AddTeamViewModel(application: Application) : AndroidViewModel(application) {

    private val _nicknamesLiveData = MutableLiveData<List<String>>()
    val nicknamesLiveData: LiveData<List<String>>
        get() = _nicknamesLiveData

    fun readFileFromAssets(fileName: String): List<String> {
        val file = getApplication<Application>()
            .assets
            ?.open(fileName)
            ?.bufferedReader()
            .use {
                it?.readLines()?.shuffled()
            } ?: throw RuntimeException("file in not found")

        return file
    }

    fun nicknames(nickname: List<String>) {
        val localNicknames = listOf(nickname[0], nickname[1])
        _nicknamesLiveData.value = localNicknames
    }
}