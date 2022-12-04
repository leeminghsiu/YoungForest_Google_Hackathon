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

    var morToEngHashMap: HashMap<String, String> = hashMapOf(
        ".-" to "a", "-..." to "b", "-.-." to "c", "-.." to "d",
        "." to "e", "..-." to "f", "--." to "g", "...." to "h",
        ".." to "i", ".---" to "j", "-.-" to "k", ".-.." to "l",
        "--" to "m", "-." to "n", "---" to "o", ".--." to "p",
        "--.-" to "q", ".-." to "r", "..." to "s", "-" to "t",
        "..-" to "u", "...-" to "v", ".--" to "w", "-..-" to "x",
        "-.--" to "y", "--.." to "z", ".----" to "1", "..---" to "2",
        "...--" to "3", "....-" to "4", "....." to "5", "-...." to "6",
        "--..." to "7", "---.." to "8", "----." to "9", "-----" to "0"
    )

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
            morseTrans = ""
            input.setText("This is your input")
            output.setText("This is the translation")
        }

        view.findViewById<Button>(R.id.translate1).setOnClickListener{
            morseTrans = morTransToEng(morseText)
            output.setText(morseTrans)
        }

        return view
    }

    /**
     * This fun translate Morse Code to English
     */
    private fun morTransToEng(morse: String): String {
        var delimiter = " "
        var transText = ""
        val morseCode = morse.split(delimiter)
        for (code in morseCode){
            if (code != "" || code!= " " || code!= null){
                var toEng = morToEngHashMap.get(code)
                if ( toEng != null){
                    transText += toEng
                }
            }
        }
        return transText
    }

}