package com.pjpsystems.grameentest.ui.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.apache.commons.lang3.time.DateUtils
import com.pjpsystems.grameentest.R
import com.pjpsystems.grameentest.databinding.ActivityCalendarBinding
import com.pjpsystems.grameentest.ui.calendar.viewmodels.CalendarViewModel
import com.pjpsystems.grameentest.ui.dashboard.InvitationSelectionActivity
import timber.log.Timber
import java.util.*

class CalendarActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityCalendarBinding

    lateinit var viewModel: CalendarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.select_date)
        viewBinding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        configureCalendarView()
        viewBinding.selectButton.setOnClickListener {

        }
        viewBinding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // set the calendar date as calendar view selected date
            val calendar = Calendar.getInstance()
            calendar.set(year,month,dayOfMonth)

            // set this date as calendar view selected date
            view.date = calendar.timeInMillis
        }

        val country_code = intent.extras?.getString(InvitationSelectionActivity.KEY_COUNTRY_EXTRA)

        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        country_code?.let {
            viewModel.getHolidays("2021", it).observe(this, { holidays ->
                Timber.d(holidays.toString())
            })
        }
    }

    private fun configureCalendarView() {
        val calendarView = viewBinding.calendarView
        val currentDate = Date()
        //set minimum date
        calendarView.minDate = currentDate.time
        //set maximum date (2 months ahead)
        val maxDate: Date = DateUtils.addMonths(currentDate, 2)
        calendarView.maxDate = maxDate.time
    }
}