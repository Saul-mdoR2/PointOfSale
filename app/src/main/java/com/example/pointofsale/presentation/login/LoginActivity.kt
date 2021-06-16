package com.example.pointofsale.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pointofsale.R
import com.example.pointofsale.databinding.ActivityLoginBinding
import com.example.pointofsale.presentation.navigation.Navigation
import com.example.pointofsale.presentation.resetPassword.ResetPasswordActivity
import org.koin.android.ext.android.inject
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private val navigation by inject<Navigation>()
    private lateinit var layout: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBindingLayout()

        layout.btnLogin.setOnClickListener {
            navigation.navigateToNextScreen(this)
        }

        layout.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }
    }

    private fun setBindingLayout() {
        Timber.d("LoginActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_login)
        layout.lifecycleOwner = this
    }
}
