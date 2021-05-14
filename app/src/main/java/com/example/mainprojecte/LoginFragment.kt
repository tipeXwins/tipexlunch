package com.example.mainprojecte


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.findNavController
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    var realm = Realm.getDefaultInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view= inflater.inflate(R.layout.fragment_login, container, false)
        var text = view.findViewById<EditText>(R.id.editText2)
        view.buttonregister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment2)
        }
        clicarLogin(view)
       clicarregister(view)

        return view


    }

    fun clicarregister(view:View) {
        view.buttonregister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment2)
        }

    }

    fun clicarLogin(view: View) {

        view.buttonLogin.setOnClickListener {
            val usernames = view.findViewById<EditText>(R.id.editText2).text.toString()
            val contras = view.findViewById<EditText>(R.id.editText)
            val usuari = realm.where(Usuari::class.java)
                .equalTo( "username", usernames)
                .findFirst()
            if (usuari == null) {
                Toast.makeText(activity!!, "Inserte usuario", Toast.LENGTH_LONG).show()
            }
            else {
                if ((usuari.username == usernames) and (usuari.password == contras.text.toString())) {
                    val intent = Intent(activity!!, MainActivity::class.java)
                    startActivity(intent)
                   UsernameSingleton.instance.username = usuari.username
                } else {
                    Toast.makeText(activity!!, "Usuari o Contraseña Incorrectos", Toast.LENGTH_LONG)
                        .show()

                }
            }
        }


    }


}
