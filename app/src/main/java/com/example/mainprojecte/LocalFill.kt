package com.example.mainprojecte


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_local_fill.view.*

/**
 * A simple [Fragment] subclass.
 */
class LocalFill(val title: String) : Fragment() {
    private lateinit var adapter: ScoresAdapter

    var realm = Realm.getDefaultInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_local_fill, container, false)

        /* val editText: EditText = view.findViewById(R.id.childEditText)
         view.setOnClickListener{
             it.hideKeyBoard()
             editText.clearFocus()
         } */
        getScore(view)

        return view

    }
    private fun configureRecyclerView(scores: MutableList<Score>, view: View) {
        adapter = ScoresAdapter(activity!!, scores)

        view.scorepointlocal.adapter = adapter
        view.scorepointlocal.layoutManager = LinearLayoutManager(activity!!)

    }
    fun View.hideKeyBoard(){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    fun getScore(view: View){
        val scores= realm.where(Score::class.java)
            .findAll()
        configureRecyclerView(scores.toMutableList(), view)
    }
    /* scores.sort
    scores =scores.filter {
    /it.username == UsetnameSingleton.instance.usetname

    }
     */



}
