package com.example.mainprojecte

class UsernameSingleton{
    var username: String = ""
    var time: Int = 0
    private constructor()
    companion object {

        val instance  = UsernameSingleton()
    }
}