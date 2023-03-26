package com.alex_cutnet.aliasgame.scenes.vm

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var timer: CountDownTimer? = null

    private var _questionWord = MutableLiveData<List<String>>()
    val questionWord: LiveData<List<String>>
        get() = _questionWord

    private var _scoreTrueAnswers = MutableLiveData<Int>()
    val scoreTrueAnswers: LiveData<Int>
        get() = _scoreTrueAnswers

    private var _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    fun startGame() {
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            ONE_MINUTE_GAME * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    fun getQuestions(questions: List<String>): LiveData<List<String>> {
        val questionsForOneRound = mutableListOf<String>()

        for (question in questions) {
            if (MAX_SIZE_QUESTIONS_LIST != questionsForOneRound.size) {
                questionsForOneRound.add(question)
            }
        }

        _questionWord.value = questionsForOneRound.toList()
        return _questionWord
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / ONE_MINUTE_GAME
        val leftSeconds = seconds - (minutes * ONE_MINUTE_GAME)

        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    private fun finishGame() {

    }

    companion object {
        private const val MAX_SIZE_QUESTIONS_LIST = 10
        private const val ONE_MINUTE_GAME = 60
        private const val MILLIS_IN_SECONDS = 1000L
    }
}

