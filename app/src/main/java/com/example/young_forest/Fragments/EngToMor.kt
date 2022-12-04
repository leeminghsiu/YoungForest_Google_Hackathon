package com.example.young_forest.Fragments

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import com.example.young_forest.R
import com.example.young_forest.FlashActivity

import android.os.Build

import android.os.Handler
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi


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

    var morToNumHashMap: HashMap<String, Int> = hashMapOf(
        "." to 1, "-" to 3, " " to 0
    )

    lateinit var dataPasser: OnDataPasser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPasser
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_eng_to_mor, container, false)
        val input = view.findViewById<EditText>(R.id.engToMorText)
        val output = view.findViewById<TextView>(R.id.engToMorTrans)
        var morse = ""

        view.findViewById<Button>(R.id.translate2).setOnClickListener {
            var ori_eng = input.text.toString()
            morse = engTransToMor(ori_eng)
            output.setText(morse)
        }

        view.findViewById<Button>(R.id.clear2).setOnClickListener {
            morse = ""
            input.setText("")
            output.setText(morse)
        }

        view.findViewById<Button>(R.id.flashlight).setOnClickListener {
            var nums = morToNums(morse)
            dataPasser.onDataPasser(nums)
        }

        return view
    }

    private fun morToNums(morse: String): IntArray {
        val nums = IntArray(morse.length)
        var delimiter = ""
        var num: Int = 0
        for (char in morse.split(delimiter)){
            nums.set(num, morToNumHashMap.get(char)!!)
            num ++
        }
        return nums
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
                morse += (engToMorHashMap.get(letter) + " ")
            }
        }
        return morse
    }

}