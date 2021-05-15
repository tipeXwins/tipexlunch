
package com.example.intouch

import io.realm.RealmObject

open class Usuari(
    var username: String = "",
    var email: String = "",
    var password: String = ""

) : RealmObject() {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "score" to mapOf(
                "username" to username,
                "password" to password
            )
        )
    }
}
