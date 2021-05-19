package com.pjpsystems.grameentest.ui.configuration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pjpsystems.grameentest.databinding.ActivityLoadScreenBinding
import com.pjpsystems.grameentest.ui.configuration.viewmodels.LoadScreenViewModel
import com.pjpsystems.grameentest.ui.dashboard.InvitationSelectionActivity

class LoadScreenActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityLoadScreenBinding

    lateinit var viewModel: LoadScreenViewModel

    private var completedUsersOps = false
    private var completedCountriesOps = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        viewBinding = ActivityLoadScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this).get(LoadScreenViewModel::class.java)
        viewModel.retrieveDefaultValues()
        viewModel.flagStoredDefaultCountries.observe(this, {
            completedCountriesOps = it
            evaluateForActivityNavigation()
        })

        viewModel.flagStoredDefaultUsers.observe(this, {
            completedUsersOps = it
            evaluateForActivityNavigation()
        })
    }

    private fun evaluateForActivityNavigation() {
        if(completedCountriesOps && completedUsersOps){
            val intent = Intent(this, InvitationSelectionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}