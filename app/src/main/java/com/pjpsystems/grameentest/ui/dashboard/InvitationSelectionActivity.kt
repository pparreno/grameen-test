package com.pjpsystems.grameentest.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pjpsystems.grameentest.databinding.ActivityInvitationSelectionBinding

class InvitationSelectionActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityInvitationSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityInvitationSelectionBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}