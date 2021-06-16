package com.example.pointofsale.presentation.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

interface Navigation {
    fun navigateToNextScreen(fromActivity: AppCompatActivity, extras: Bundle? = null)
    fun navigateBack(fromActivity: AppCompatActivity, defaultBehavior: () -> Unit)
}