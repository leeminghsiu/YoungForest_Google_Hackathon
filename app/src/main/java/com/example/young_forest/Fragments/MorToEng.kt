package com.example.young_forest.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.young_forest.R


class MorToEng : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_mor_to_eng, container, false)
        val input = view.findViewById<TextView>(R.id.morseText)
        val output = view.findViewById<TextView>(R.id.morseTrans)
        var morseText = ""
        var morseTrans = ""

        view.findViewById<Button>(R.id.morseDot).setOnClickListener {
            morseText += "."
            input.setText(morseText)
        }

        view.findViewById<Button>(R.id.morseLine).setOnClickListener {
            morseText += "-"
            input.setText(morseText)
        }

        view.findViewById<Button>(R.id.morseBlank).setOnClickListener {
            morseText += " "
            input.setText(morseText)
        }

        view.findViewById<Button>(R.id.clear1).setOnClickListener {
            morseText = ""
            input.setText("This is your input")
        }

        /***
        view.findViewById<Button>(R.id.translate1).setOnClickListener{
            morseTrans = morTransToEng(morseText)
            output.setText(morseTrans)
        }
        **/

        return view
    }

}