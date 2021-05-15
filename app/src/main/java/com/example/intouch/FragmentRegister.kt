package com.example.intouch


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.android.synthetic.main.fragment_register.view.buttonRegister

/**
 * A simple [Fragment] subclass.
 */
class FragmentRegister: Fragment() {
    var realm = Realm.getDefaultInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=  inflater.inflate(R.layout.fragment_register, container, false)
        clicarlogin(view)
        clicarRegister(view)
        return view
    }
    fun MirarUsuari(usernames: String): Usuari? {
        val usuari = realm.where(Usuari::class.java)
            .equalTo("username",usernames)
            .findFirst()
        return usuari


    }
    fun clicarlogin(view: View) {
        view.buttonlogan.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment2_to_loginFragment2)
        }
    }
    fun createUsuari(username:String,password:String) {
        var usuari = Usuari()
        usuari.username = username
        usuari.password = password
        var usuaris =MirarUsuari(usuari.username)
        if (usuaris == null) {
            realm.beginTransaction()
            realm.copyToRealm(usuari)
            realm.commitTransaction()
            val intent = Intent(activity!!, MainActivity::class.java)
            startActivity(intent)
        }
        else {
            Toast.makeText(activity!!, "Usuario ya registrado", Toast.LENGTH_LONG).show()
        }
    }



    fun clicarRegister(view: View) {
        view.buttonRegister.setOnClickListener {
            var usernames = view.findViewById<EditText>(R.id.editText2)
            var contras = view.findViewById<EditText>(R.id.editText)
            createUsuari(usernames.text.toString(),contras.text.toString())





        }
    }







}