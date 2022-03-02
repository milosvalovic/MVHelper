package sk.valovic.mvhelperexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import sk.valovic.mvhelper.MVHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("dpToPx",MVHelper.dpToPx(20).toString())
        Log.d("dpToPx",MVHelper.pxToDp(20).toString())
        Log.d("getDisplayHeightInDp",MVHelper.getDisplayHeightInDp(this).toString())
        Log.d("getDisplayHeightInPixels",MVHelper.getDisplayHeightInPixels(this).toString())
        Log.d("currentDateTime",MVHelper.currentDateTime() ?: "FAIL")
    }
}