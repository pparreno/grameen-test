package com.pjpsystems.grameentest.ui.configuration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pjpsystems.grameentest.databinding.ActivityLoadScreenBinding

class LoadScreenActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityLoadScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoadScreenBinding.inflate(layoutInflater)
    }
}