package com.alex_cutnet.aliasgame.scenes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(): ViewModel() {
    //question in game
    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    //count true answers
    private var _scoreTrueAnswers = MutableLiveData<Int>()
    val scoreTrueAnswers: LiveData<Int>
        get() = _scoreTrueAnswers
}