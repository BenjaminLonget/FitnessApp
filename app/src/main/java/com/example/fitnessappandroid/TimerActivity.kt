package com.example.fitnessappandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitnessappandroid.databinding.ActivityMainBinding
import com.example.fitnessappandroid.databinding.ActivityTimerBinding
import kotlin.math.roundToInt

class TimerActivity : AppCompatActivity()
{
    private lateinit var bindingAct: ActivityTimerBinding
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAct = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(bindingAct.root)

        bindingAct.startStopBtn.setOnClickListener()
        {
            startStopTimer()
        }
        bindingAct.resetBtn.setOnClickListener()
        {
            resetTimer()
        }

        serviceIntent = Intent(applicationContext, TimerService::class.java)

        // broadcast receiver
        registerReceiver(updateTime, IntentFilter(TimerService.Timer_Updated))
    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        bindingAct.timeView.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer() {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.Time_extra, time)
        startService(serviceIntent)
        bindingAct.startStopBtn.text = "Stop"
        timerStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        bindingAct.startStopBtn.text = "Start"
        timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(p0: Context, p1: Intent)
        {
            time = intent.getDoubleExtra(TimerService.Time_extra, 0.0)
            bindingAct.timeView.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String
    {
        val resultInt = time.roundToInt()
        val hours = resultInt%86400/3600
        val minutes = resultInt%86400/3600/60
        val seconds = resultInt%86400/3600%60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Int, minutes: Int, seconds: Int): String = String.format("%02d:%02d:%02d", hours, minutes, seconds)
}