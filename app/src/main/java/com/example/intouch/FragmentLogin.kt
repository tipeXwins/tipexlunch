package com.example.intouch


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
class FragmentLogin : Fragment() {
    var realm = Realm.getDefaultInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view= inflater.inflate(R.layout.fragment_login, container, false)

        view.buttonSO.setOnClickListener {
            findNavController().navigate(R.id.Login_to_Register)
        }
        clicarLogin(view)
        clicarregister(view)

        return view


    }

    fun clicarregister(view:View) {
        view.buttonSO.setOnClickListener {
            findNavController().navigate(R.id.Login_to_Register)
        }

    }

    fun clicarLogin(view: View) {

        view.buttonLog.setOnClickListener {
            val mail = view.findViewById<EditText>(R.id.EditTextMail).text.toString()
            val contras = view.findViewById<EditText>(R.id.EditTextPassword)
            val usuari = realm.where(Usuari::class.java)
                .equalTo( "email", mail)
                .findFirst()
            if (usuari == null) {
                Toast.makeText(requireActivity(), "Inserte usuario", Toast.LENGTH_LONG).show()
            }
            else {
                if ((usuari.email == mail) and (usuari.password == contras.text.toString())) {
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(requireActivity(), "Usuari o Contraseña Incorrectos", Toast.LENGTH_LONG)
                        .show()

                }
            }
        }


    }


}