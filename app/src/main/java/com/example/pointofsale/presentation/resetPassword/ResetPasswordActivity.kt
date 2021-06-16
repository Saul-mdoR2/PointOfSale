package com.example.pointofsale.presentation.resetPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.pointofsale.R
import com.example.pointofsale.databinding.ActivityResetPasswordBinding
import timber.log.Timber

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var layout:ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingLayout()
        initToolbar()
        showHidePassword()
        layout.btnResetPassword.setOnClickListener {
            ResetPasswordDialogFragment().show(supportFragmentManager, "DialogPasswordReset")
        }
        layout.btnCancelResetPassword?.setOnClickListener {
            finish()
        }
    }

    private fun setBindingLayout() {
        Timber.d("RessetPasswordActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_reset_password)
        layout.lifecycleOwner = this
    }

    private fun initToolbar() {
        layout.resetPasswordToolbar.setNavigationOnClickListener {
            finish()
        }
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showHidePassword() {
        layout.ivShowHideNewPassword.tag = "SHOW"
        layout.ivShowHideNewPassword.setOnClickListener {
            when (layout.ivShowHideNewPassword.tag) {
                "SHOW" -> {
                    layout.etNewPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    layout.ivShowHideNewPassword.tag = "HIDE"
                }
                "HIDE" -> {
                    layout.etNewPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                    layout.ivShowHideNewPassword.tag = "SHOW"
                }
            }
        }

        layout.ivShowHideConfirmNewPassword.tag = "SHOW"
        layout.ivShowHideConfirmNewPassword.setOnClickListener {
            when (layout.ivShowHideConfirmNewPassword.tag) {
                "SHOW" -> {
                    layout.etConfirmNewPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    layout.ivShowHideConfirmNewPassword.tag = "HIDE"
                }
                "HIDE" -> {
                    layout.etConfirmNewPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                    layout.ivShowHideConfirmNewPassword.tag = "SHOW"
                }
            }
        }
    }
}