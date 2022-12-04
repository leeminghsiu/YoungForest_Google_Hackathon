package com.example.young_forest.Fragments

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.annotation.RequiresApi

class oldFlashlight {
    private val mHandler: Handler = Handler()
    private lateinit var cameraM: CameraManager
    private lateinit var powerBtn: ImageButton
    var isFlash = false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        powerBtn = findViewById(R.id.torchbtn )
        cameraM = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        powerBtn.setOnClickListener{buttonClicked(it)}
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashLightOnOrOff(v: View?): Runnable = object : Runnable {
        override fun run() {
            if(!isFlash){
                val cameraListId = cameraM.cameraIdList[0]
                cameraM.setTorchMode(cameraListId, true)
                isFlash = true
                powerBtn.setImageResource(R.drawable.power_on)
            }
            else{
                val cameraListId = cameraM.cameraIdList[0]
                cameraM.setTorchMode(cameraListId, false)
                isFlash = false
                powerBtn.setImageResource(R.drawable.power_off)
            }
        }
    }

    var nums = arrayOf(1, 3, 1, 0)

    // LIGHT TIME PATTERN CONTROL
    fun buttonClicked(view: View?) {
        val runnable: Runnable = object : Runnable {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun run() {
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