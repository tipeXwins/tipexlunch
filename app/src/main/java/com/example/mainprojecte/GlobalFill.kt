package com.example.mainprojecte


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_global_fill.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody


/**
 * A simple [Fragment] subclass.
 */
class GlobalFill(val title: String) : Fragment() {
    private lateinit var adapter: ScoresAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_global_fill, container, false)
        // val textView: TextView = view.findViewById(R.id.childviewager)
        // textView.text = title


        /* val editText: EditText = view.findViewById(R.id.childEditText)
        view.setOnClickListener{
            it.hideKeyBoard()
            editText.clearFocus()
        } */



        getScores { scores ->
            activity!!.runOnUiThread {
                configureRecyclerView(scores.toMutableList())
            }
        }

        return view

    }

    fun View.hideKeyBoard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun configureRecyclerView(scores: MutableList<Score>) {
        adapter = ScoresAdapter(activity!!, scores)

        scorepoint.adapter = adapter
        scorepoint.layoutManager = LinearLayoutManager(activity!!)

    }

    private fun getScores(onResponse: (List<Score>) -> Unit) {

        val client = OkHttpClient()

        val request = Request
            .Builder()
            .get()
            .url("http://104.248.17.126:4000/api/scores")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                var scores: MutableList<Score> = mutableListOf()

                val responseBody = response.body!!

                val json = Gson().fromJson(responseBody.string(), JsonObject::class.java)

                val jsonScores = json.get("data").asJsonArray

                jsonScores.forEach { jsonScore ->
                    scores.add(
                        Score(
                            (jsonScore as JsonObject).get("username").asString,
                            (jsonScore as JsonObject).get("time").asString
                        )
                    )
                }

                onResponse(scores)

            }

            override fun onFailure(call: Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }


    private fun createScore() {
        val score = Score("Álvaro-app", "00:40")

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

