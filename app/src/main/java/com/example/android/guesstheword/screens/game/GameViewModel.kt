package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//class GameViewModel : ViewModel(){
//    init {
//        Log.i("GameViewModel","GameViewModel created")
//
//
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//
//        Log.i("GameViewModel","GameViewModel destroyed")
//    }
//
//
//}


class GameViewModel : ViewModel() {

    //the current word
    private val _word = MutableLiveData<String>()




    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    val word: LiveData<String>
    get() = _word

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    //event to trigger the end of game
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
    get()= _eventGameFinish




    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    init {
        _word.value = ""
        _score.value = 0
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
    }
    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }


    }

    /**
     * Callback called when the ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }


    /** Methods for updating the ui **/
    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }





    //method for game completed event
    fun onGameFinish(){
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete(){
        _eventGameFinish.value = true
    }

}