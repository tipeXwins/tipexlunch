package com.example.mainprojecte


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 */
class MemoryFragment : Fragment() {
    var realm = Realm.getDefaultInstance()
    lateinit var viewModel: ViewModelMemory

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<Int>
    var contador = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_memory2, container, false)

        viewModel = ViewModelProvider(this).get(ViewModelMemory::class.java)

        initButtons(view)
        hideAllCards()
        barajar()


        buttons.withIndex().forEach { indexed ->
            indexed.value.setOnClickListener{
                viewModel.mirarmatch(indexed.index)
            }
        }


        viewModel.firstSelected.observe(activity!!, Observer {
            if (it != null) {
                buttons[it!!].setImageResource(cards[it!!])
            }
        })

        viewModel.secondSelected.observe(activity!!, Observer {
            if (it != null) {
                buttons[it!!].setImageResource(cards[it!!])
                pareja()
                if (contador == 8) win(view)
            }
        })
        // Inflate the layout for this fragment
        return view
    }

    private fun initButtons(view: View) {
        buttons = listOf(
            view.findViewById(R.id.card1),
            view.findViewById(R.id.card2),
            view.findViewById(R.id.card3),
            view.findViewById(R.id.card4),
            view.findViewById(R.id.card5),
            view.findViewById(R.id.card6),
            view.findViewById(R.id.card7),
            view.findViewById(R.id.card8),
            view.findViewById(R.id.card9),
            view.findViewById(R.id.card10),
            view.findViewById(R.id.card11),
            view.findViewById(R.id.card12),
            view.findViewById(R.id.card13),
            view.findViewById(R.id.card14),
            view.findViewById(R.id.card15),
            view.findViewById(R.id.card16)
        )
    }

    private fun hideAllCards() {
        buttons.forEach {
            it.setImageResource(R.drawable.lorcardback)
        }
    }

    fun pareja() {
        if ((cards[viewModel.secondSelected.value!!] == cards[viewModel.firstSelected.value!!]) && (viewModel.secondSelected.value!! != viewModel.firstSelected.value!!)) {
            buttons[viewModel.firstSelected.value!!].isEnabled = false
            buttons[viewModel.secondSelected.value!!].isEnabled = false
            viewModel.firstSelected.value = null
            viewModel.secondSelected.value = null
            ++contador
        } else {

            Handler().postDelayed({
                buttons[viewModel.secondSelected.value!!].setImageResource(R.drawable.lorcardback)
                buttons[viewModel.firstSelected.value!!].setImageResource(R.drawable.lorcardback)
                viewModel.firstSelected.value = null
                viewModel.secondSelected.value = null
            }, 400)


        }
    }

    fun barajar() {
        cards = listOf(
            R.drawable.uno,
            R.drawable.uno,
            R.drawable.dos,
            R.drawable.dos,
            R.drawable.tres,
            R.drawable.tres,
            R.drawable.cuatro,
            R.drawable.cuatro,
            R.drawable.cinco,
            R.drawable.cinco,
            R.drawable.seis,
            R.drawable.seis,
            R.drawable.siete,
            R.drawable.siete,
            R.drawable.ocho,
            R.drawable.ocho

        ).shuffled()

    }

    fun win(view: View) {
        var time: String = viewModel.seconds.value.toString()
        createScore(time)
        createScoreApi(time)
        Snackbar
            .make(
                activity!!.findViewById(android.R.id.content),
                "Mensaje Snackbar",
                Snackbar.LENGTH_SHORT
            )
            .setAction("Barajar") {
                buttons.forEach{
                    it.isEnabled = true}
                    hideAllCards()
                    barajar()
                    contador=0


            }
            .show()

    }
    fun createScore(time: String) {
        var score = Score()
        score.username = UsernameSingleton.instance.username
        score.time= time

        realm.beginTransaction()

        realm.copyToRealm(score)

        realm.commitTransaction()
    }
     fun createScoreApi(time:String) {
        val score = Score(UsernameSingleton.instance.username,time )

        val client = OkHttpClient()

        val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
        val body = JSONObject(score.toMap()).toString()

        val request = Request
            .Builder()
            .post(body.toRequestBody(MEDIA_TYPE_JSON))
            .url("http://104.248.17.126:4000/api/scores")
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                // Todo ha ido bien :)
            }

            override fun onFailure(call: Call, e: IOException) {
                // Algo ha petado :'(
            }

        })
    }


}