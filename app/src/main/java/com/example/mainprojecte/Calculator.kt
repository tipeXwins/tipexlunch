package com.example.mainprojecte


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_calculator.*
import kotlinx.android.synthetic.main.fragment_calculator.view.*

/**
 * A simple [Fragment] subclass.
 */
class Calculator : Fragment() {
    lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)


        view.bresultat.setOnClickListener {
            viewModel.compute()

        }
        view.bac.setOnClickListener {
            viewModel.ac()
        }
        view.bc.setOnClickListener {
            viewModel.restar()

        }

        val ButtonListener = View.OnClickListener {
            val number = (it as Button).text.toString()
            viewModel.inputNumber(number)

        }
        listOf<Button>(view.findViewById(R.id.b0),
            view.findViewById(R.id.b1),
            view.findViewById(R.id.b2),
            view.findViewById(R.id.b3),
            view.findViewById(R.id.b4),
            view.findViewById(R.id.b5),
            view.findViewById(R.id.b6),
            view.findViewById(R.id.b7),
            view.findViewById(R.id.b8),
            view.findViewById(R.id.b9),
            view.findViewById(R.id.bdiv),
            view.findViewById(R.id.bsum),
            view.findViewById(R.id.bres),
            view.findViewById(R.id.bmult),
            view.findViewById(R.id.bpunto))
            .forEach { it.setOnClickListener(ButtonListener) }

        viewModel.currentInput.observe(activity!! , Observer { newInput ->
            view.textView.text = newInput

        })
        return view
    }
}


