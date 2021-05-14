package com.example.mainprojecte

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.schedule

class ViewModelMemory: ViewModel() {

    var seconds: MutableLiveData<Int> = MutableLiveData()
    var firstSelected: MutableLiveData<Int?> = MutableLiveData()
    var secondSelected: MutableLiveData<Int?> = MutableLiveData()
     var timer: TimerTask

    init {


        seconds.value = 0
        timer = Timer().schedule(0, period = 1000) {
            seconds.postValue(seconds.value!! + 1)

        }
    }

    override fun onCleared() {
        super.onCleared()


    }

    fun mirarmatch(index: Int) {

        if (firstSelected.value == null) {
            firstSelected.value = index
        } else if ((secondSelected.value == null) and (index != firstSelected.value)) {
            secondSelected.value = index

        }


    }

}