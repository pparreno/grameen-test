package com.pjpsystems.grameentest.ui.scheduler

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pjpsystems.grameentest.R
import com.pjpsystems.grameentest.data.retrofit.Holiday
import com.pjpsystems.grameentest.databinding.ActivityAppoinmtmentComposerBinding
import com.pjpsystems.grameentest.ui.dashboard.InvitationSelectionActivity
import com.pjpsystems.grameentest.ui.scheduler.viewmodels.AppointmentComposerViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.getInstance
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class AppointmentComposerActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    lateinit var viewBinding: ActivityAppoinmtmentComposerBinding

    lateinit var viewModel: AppointmentComposerViewModel

    private lateinit var dpd: DatePickerDialog
    private lateinit var startTPD: TimePickerDialog
    private lateinit var endTPD: TimePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.select_date)
        viewBinding = ActivityAppoinmtmentComposerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.selectDateButton.setOnClickListener {
            showDatePicker()
        }
        viewBinding.selectDateButton.isEnabled = false
        viewBinding.startTimeButton.setOnClickListener {
            startTPD = TimePickerDialog.newInstance(this@AppointmentComposerActivity,false)
            startTPD.show(supportFragmentManager, "Select Start Time")
        }
        viewBinding.endTimeButton.setOnClickListener {
            endTPD = TimePickerDialog.newInstance(this@AppointmentComposerActivity,false)
            endTPD.show(supportFragmentManager, "Select End Time")
        }


        val country_code = intent.extras?.getString(InvitationSelectionActivity.KEY_COUNTRY_EXTRA)

        viewModel = ViewModelProvider(this).get(AppointmentComposerViewModel::class.java)
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
            if (!listHolidayDates.contains(formattedIteratorDate.toString())) {
                listDaysNonHoliday.add(dayIterator.clone() as Calendar)
            } else {
                Timber.d("Holiday found! %s", formattedIteratorDate.toString())
            }
            dayIterator.add(Calendar.DAY_OF_MONTH, 1)
        }
        val availableDays: Array<Calendar> =
            listDaysNonHoliday.toArray(arrayOfNulls<Calendar>(listDaysNonHoliday.size))
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
        val date =
           "" + dayOfMonth.toString() + "/" + (monthOfYear + 1).toString() + "/" + year
        viewBinding.dateField.setText(date)
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {

        val formattedHour: String = if(hourOfDay < 10) {
            String.format("0%d", hourOfDay)
        } else {
            hourOfDay.toString()
        }

        val formattedMinutes: String = if(minute < 10) {
            String.format("0%d", minute)
        } else {
            minute.toString()
        }

        val time =
            "$formattedHour:$formattedMinutes"
        if(view == startTPD){
            viewBinding.startTimeField.setText(time)
        } else {
            viewBinding.endTimeField.setText(time)
        }
    }
}