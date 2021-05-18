package com.pjpsystems.grameentest.ui.configuration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pjpsystems.grameentest.databinding.ActivityLoadScreenBinding
import com.pjpsystems.grameentest.ui.configuration.viewmodels.LoadScreenViewModel

class LoadScreenActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityLoadScreenBinding

    lateinit var viewModel: LoadScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoadScreenBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(LoadScreenViewModel::class.java)
        viewModel.retrieveDefaultValues()
    }
}