package com.example.mainprojecte

import io.realm.RealmObject

open class Score(
    var username: String = "",
    var time: String = ""
) : RealmObject() {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "score" to mapOf(
                "username" to username,
                "time" to time
            )
        )
    }
}