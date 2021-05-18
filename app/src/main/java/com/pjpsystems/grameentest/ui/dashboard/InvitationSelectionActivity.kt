package com.pjpsystems.grameentest.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pjpsystems.grameentest.databinding.ActivityInvitationSelectionBinding
import com.pjpsystems.grameentest.ui.calendar.CalendarActivity
import com.pjpsystems.grameentest.ui.dashboard.adapter.UsersAdapter
import com.pjpsystems.grameentest.ui.dashboard.viewmodels.InvitationSelectionViewModel

class InvitationSelectionActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewBinding: ActivityInvitationSelectionBinding

    lateinit var viewModel: InvitationSelectionViewModel

    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInvitationSelectionBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setViews()

        viewModel = ViewModelProvider(this).get(InvitationSelectionViewModel::class.java)
        viewModel.oRoomUsers.observe(this, {
            adapter = UsersAdapter(it, this@InvitationSelectionActivity)
            viewBinding.recyclerview.adapter = adapter
        })

        viewModel.retrieveUsers()
    }

    private fun setViews() {
        viewBinding.recyclerview.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        navigateToCalendarActivity()
    }

    private fun navigateToCalendarActivity() {
        val intent = Intent(this, CalendarActivity::class.java)
        startActivity(intent)
    }
}