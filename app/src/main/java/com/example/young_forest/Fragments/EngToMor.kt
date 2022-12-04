package com.example.young_forest.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.young_forest.R


class EngToMor : Fragment() {

    var engToMorHashMap: HashMap<String, String> = hashMapOf(
        "a" to ".-", "b" to "-...", "c" to "-.-.", "d" to "-..",
        "e" to ".", "f" to "..-.", "g" to "--.", "h" to "....",
        "i" to "..", "j" to ".---", "k" to "-.-", "l" to ".-..",
        "m" to "--", "n" to "-.", "o" to "---", "p" to ".--.",
        "q" to "--.-", "r" to ".-.", "s" to "...", "t" to "-",
        "u" to "..-", "v" to "...-", "w" to ".--", "x" to "-..-",
        "y" to "-.--", "z" to "--..", "1" to ".----", "2" to "..---",
        "3" to "...--", "4" to "....-", "5" to ".....", "6" to "-....",
        "7" to "--...", "8" to "---..", "9" to "----.", "0" to "-----"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_eng_to_mor, container, false)

        return view
    }

    /**
     * This fun translate English to Morse Code
     */
    private fun engTransToMor(english: String): String{
        var morse = ""
        var delimiter = ""
        val engList = english.split(delimiter)
        for (letter in engList){
            if (engToMorHashMap.containsKey(letter)){
                morse += engToMorHashMap.get(letter)
            }
        }
        return morse
    }

}