package com.example.young_forest

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class FlashActivity : AppCompatActivity() {

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

    private val mHandler: Handler = Handler()
    private lateinit var cameraM: CameraManager
    private lateinit var powerBtn: Button


    var isFlash = false
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashlight)
        powerBtn = findViewById(R.id.torch)
        cameraM = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        var input: EditText = findViewById(R.id.input)

        powerBtn.setOnClickListener{buttonClicked(it, morToNums(engTransToMor(input.text.toString())))}
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashLightOnOrOff(v: View?): Runnable = object : Runnable {
        override fun run() {
            if(!isFlash){
                val cameraListId = cameraM.cameraIdList[0]
                cameraM.setTorchMode(cameraListId, true)
                isFlash = true
            }
            else{
                val cameraListId = cameraM.cameraIdList[0]
                cameraM.setTorchMode(cameraListId, false)
                isFlash = false
            }
        }
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
        Log.i("i", "engToMor: " + morse)
        return morse
    }

    private fun morToNums(morse: String): IntArray {
        val nums = IntArray(morse.length)
        var delimiter = ""
        var num: Int = 0
        var mor_list = morse.split(delimiter)
        for (cha in mor_list){
            if(morToNumHashMap.containsKey(cha)){
                nums.set(num, morToNumHashMap.get(cha)!!)
                Log.i("i", "morTONums:" + morToNumHashMap.get(cha).toString())
                num ++
            }
        }
        return nums
    }

    // LIGHT TIME PATTERN CONTROL
    fun buttonClicked(view: View?, nums:IntArray) {
        val runnable: Runnable = object : Runnable {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun run() {
                Log.i("i", "107 final" + nums.toString())
                for(i in 1..3){
                    for (sec in nums) {
                        if (sec == 0) {
                            Thread.sleep(5000)
                            Log.i("i", "word break")
                        }
                        else {
                            flashLightOnOrOff(powerBtn).run()
                            synchronized(this) {
                                Thread.sleep(sec.toLong() * 1000)
                            }
                            Log.i("i", "A.$i plus $sec sec")
                            flashLightOnOrOff(powerBtn).run()
                            synchronized(this) {
                                Thread.sleep(1000)
                            }
                        }
                    }
                }
            }
        }
        val thread = Thread(runnable)
        thread.start()
    }

}




