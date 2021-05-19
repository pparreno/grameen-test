package com.pjpsystems.grameentest.ui.calendar

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pjpsystems.grameentest.R
import com.pjpsystems.grameentest.data.retrofit.Holiday
import com.pjpsystems.grameentest.databinding.ActivityCalendarBinding
import com.pjpsystems.grameentest.ui.calendar.viewmodels.CalendarViewModel
import com.pjpsystems.grameentest.ui.dashboard.InvitationSelectionActivity
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.getInstance
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CalendarActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var viewBinding: ActivityCalendarBinding

    lateinit var viewModel: CalendarViewModel

    private lateinit var dpd: DatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.select_date)
        viewBinding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.selectDateButton.setOnClickListener {
            showDatePicker()
        }
        viewBinding.selectDateButton.isEnabled = false

        val country_code = intent.extras?.getString(InvitationSelectionActivity.KEY_COUNTRY_EXTRA)

        viewModel = ViewModelProvider(this).get(CalendarViewModel::class.java)
        country_code?.let {
            viewModel.getHolidays("2021", it).observe(this, { holidays ->
                Timber.d(holidays.toString())
                configureCalendarView(holidays)
                viewBinding.selectDateButton.isEnabled = true
            })
        }
    }

    private fun showDatePicker() {
        dpd.show(supportFragmentManager, "date time picker")
    }

    @SuppressLint("NewApi", "SimpleDateFormat")
    private fun configureCalendarView(holidays: List<Holiday>) {
        val now: Calendar = getInstance()
        val future: Calendar = getInstance()

        val listHolidayDates = extractDatesFromHolidayList(holidays)

        future.set(Calendar.MONTH, Calendar.DECEMBER)
        future.set(Calendar.DAY_OF_MONTH, 31)
        dpd = DatePickerDialog.newInstance(
            this
        )
        dpd.minDate = now
        dpd.maxDate = future
        dpd.version = DatePickerDialog.Version.VERSION_2

        val dayIterator: Calendar = getInstance()
        val endTime: Calendar = future.clone() as Calendar
        endTime.add(Calendar.DAY_OF_MONTH, 1)

        val format = SimpleDateFormat("yyyy-MM-dd")
        val listDaysNonHoliday = ArrayList<Calendar>()
        while (!dayIterator.time.after(endTime.time)) {
            val formattedIteratorDate = format.format(dayIterator.time)
            Timber.d("Looping: %s", formattedIteratorDate.toString())
            if(!listHolidayDates.contains(formattedIteratorDate.toString())){
                listDaysNonHoliday.add(dayIterator.clone() as Calendar)
            } else {
                Timber.d("Holiday found! %s", formattedIteratorDate.toString())
            }
            dayIterator.add(Calendar.DAY_OF_MONTH, 1)
        }
        val availableDays: Array<Calendar> = listDaysNonHoliday.toArray(arrayOfNulls<Calendar>(listDaysNonHoliday.size))
        dpd.selectableDays = availableDays
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun extractDatesFromHolidayList(holidays: List<Holiday>): List<String> {
        val holidaysList: ArrayList<String> = ArrayList()
        for (holiday: Holiday in holidays) {
            holidaysList.add(holiday.date)
        }
        return holidaysList
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        TODO("Not yet implemented")
    }
}