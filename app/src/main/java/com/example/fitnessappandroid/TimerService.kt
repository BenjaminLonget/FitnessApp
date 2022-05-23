package com.example.fitnessappandroid

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

class TimerService : Service()
{
    // don need to do anything here so return null
    override fun onBind(p0: Intent?): IBinder? = null

    private val timer = Timer()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {
        val time = intent.getDoubleExtra(Time_extra, 0.0)
        // a timer with a delay of 0  and period of 1000 milliseconds (1 sec)
        timer.scheduleAtFixedRate(TimeTask(time), 0, 1000)
        return START_NOT_STICKY
    }

    override fun onDestroy()
    {
        timer.cancel()
        super.onDestroy()
    }

    // our time task
    private inner class TimeTask(private var time: Double) : TimerTask()
    {
        override fun run()
        {
            val intent = Intent(Timer_Updated)
            time++
            intent.putExtra(Time_extra, time)
            sendBroadcast(intent)
        }
    }

    // companion object to store som strings
    companion object
    {
        const val Timer_Updated = "timerUpdated"
        const val Time_extra = "timeExtra"
    }
}
