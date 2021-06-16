package com.example.pointofsale.presentation.createNewUser

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pointofsale.R
import com.example.pointofsale.databinding.ActivityCreateUserBinding
import com.example.pointofsale.presentation.navigation.Navigation
import org.koin.android.ext.android.inject
import timber.log.Timber

class CreateUserActivity : AppCompatActivity() {

    private val navigation by inject<Navigation>()
    private lateinit var layout: ActivityCreateUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBindingLayout()
        layout.btnSaveNewUser.setOnClickListener {
            UserCreatedDialogFragment().show(supportFragmentManager, "DialogUserCreated")
        }
        layout.btnCancelCreateUser?.setOnClickListener {
            finish()
        }
        showHidePassword()
        initToolbar()
    }

    private fun setBindingLayout() {
        Timber.d("CreateUserActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_create_user)
        layout.lifecycleOwner = this
    }

    private fun initToolbar() {
        layout.newUserToolbar.setNavigationOnClickListener {
            finish()
        }
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        Timber.d("CreateUserActivity_TAG: onBackPressed: ")
        navigation.navigateBack(this) {
            super.onBackPressed()
        }
    }

    private fun showHidePassword() {
        layout.ivShowHidePassword.tag = "SHOW"
        layout.ivShowHideConfirmPassword.tag = "SHOW"

        layout.ivShowHidePassword.setOnClickListener {
            when (layout.ivShowHidePassword.tag) {
                "HIDE" -> {
                    layout.etPasswordUser.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                    layout.ivShowHidePassword.tag = "SHOW"
                }
                "SHOW" -> {
                    layout.etPasswordUser.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    layout.ivShowHidePassword.tag = "HIDE"
                }
            }
        }

        layout.ivShowHideConfirmPassword.setOnClickListener {
            when (layout.ivShowHideConfirmPassword.tag) {
                "HIDE" -> {
                    layout.etConfirmPasswordUser.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                    layout.ivShowHideConfirmPassword.tag = "SHOW"
                }
                "SHOW" -> {
                    layout.etConfirmPasswordUser.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    layout.ivShowHideConfirmPassword.tag = "HIDE"
                }
            }
        }
    }
}