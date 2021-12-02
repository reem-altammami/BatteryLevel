package com.example.batterylevel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var TAG = MainActivity::class.simpleName
    var batteryLevel: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        batteryLevel = findViewById(R.id.battery_level)
        val batteryStatus = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryBrodcastReceiver, batteryStatus)
    }

    private val batteryBrodcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)

            if (intent?.action == "android.intent.action.BATTERY_CHANGED") {
                Log.d(TAG,"Level $level")
                batteryLevel?.post {
                    batteryLevel?.text = level.toString().plus("").plus("%")
                }
            }
//
//            if(intent?.getIntExtra(BatteryManager.EXTRA_STATUS,-1)== BatteryManager.BATTERY_STATUS_CHARGING){
//                batteryLevel?.text= "Charge"
//            } else {
//                batteryLevel?.text = "Not Charging"
//            }
        }


    }

    override fun onDestroy(){
        super.onDestroy()
        unregisterReceiver(batteryBrodcastReceiver)
    }
}

//override fun onReceive(context: Context?, intent: Intent?) {
//    if (intent?.action == "android.intent.action.BATTERY_CHANGED") {
//        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
//        Log.d("Tag","Level $level")
//        batteryLevel?.post {
//            if (level in 100 downTo 70) {
//                batteryLevel?.text = "Excellent"
//            } else if (level in 69 downTo 30){
//                batteryLevel?.text = "Good"
//
//            } else if (level in 29 downTo 5){
//                batteryLevel?.text = "Low"
//
//            }
//        }
//    }
//}

