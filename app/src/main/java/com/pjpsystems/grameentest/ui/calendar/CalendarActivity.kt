package com.pjpsystems.grameentest.ui.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pjpsystems.grameentest.R
import com.pjpsystems.grameentest.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.select_date)
    }
}