package com.pjpsystems.grameentest.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pjpsystems.grameentest.data.app_model.AppUser
import com.pjpsystems.grameentest.data.room.User
import com.pjpsystems.grameentest.databinding.ActivityInvitationSelectionBinding
import com.pjpsystems.grameentest.ui.scheduler.AppointmentComposerActivity
import com.pjpsystems.grameentest.ui.dashboard.adapter.UsersAdapter
import com.pjpsystems.grameentest.ui.dashboard.viewmodels.InvitationSelectionViewModel
import timber.log.Timber
import java.util.*


class InvitationSelectionActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val KEY_COUNTRY_EXTRA = "InvitationSelectionActivity.KEY_COUNTRY_EXTRA"
    }

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
        v?.let {
            val position = viewBinding.recyclerview.getChildLayoutPosition(it)
            adapter.selectedIndex = position
            adapter.notifyItemChanged(position)
            Timber.d("Selected Index: %d", position)
        }

        navigateToCalendarActivity(adapter.getItem(adapter.selectedIndex).toAppUser())

    }

    private fun navigateToCalendarActivity(user: AppUser) {
        val intent = Intent(this, AppointmentComposerActivity::class.java)
        intent.putExtra(KEY_COUNTRY_EXTRA, user)
        startActivity(intent)
    }
}