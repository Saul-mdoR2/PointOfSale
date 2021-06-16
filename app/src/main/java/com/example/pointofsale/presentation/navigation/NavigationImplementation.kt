package com.example.pointofsale.presentation.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pointofsale.presentation.MainActivity
import com.example.pointofsale.presentation.createNewUser.CreateUserActivity
import com.example.pointofsale.presentation.listProducts.ListProductsActivity
import com.example.pointofsale.presentation.listUsers.ListUserActivity
import com.example.pointofsale.presentation.login.LoginActivity
import com.example.pointofsale.presentation.newProduct.NewProductActivity
import com.example.pointofsale.presentation.splashScreen.SplashScreenActivity
import timber.log.Timber

class NavigationImplementation : Navigation {
    override fun navigateToNextScreen(fromActivity: AppCompatActivity, extras: Bundle?) {
        Timber.d("NavigationImpl_TAG: navigateToNextScreen: from: ${fromActivity::class.java}, extras: $extras")
        val nextScreen = getNextScreen(fromActivity) ?: return
        navigate(fromActivity, nextScreen, extras)
    }

    override fun navigateBack(fromActivity: AppCompatActivity, defaultBehavior: () -> Unit) {
        Timber.d("NavigationImpl_TAG: navigateBack: from: ${fromActivity::class.java}")

        val previousScreen = getPreviousScreen(fromActivity)
        if (previousScreen == null) {
            defaultBehavior()
            return
        }
        navigate(fromActivity, previousScreen)
    }

    private fun <T> navigate(context: AppCompatActivity, screen: Class<T>, extras: Bundle? = null) {
        val intent = Intent(context, screen)
        extras?.let { intent.putExtras(extras) }

        if (screen == MainActivity::class.java) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        context.startActivity(intent)
    }

    private fun getNextScreen(fromActivity: AppCompatActivity): Class<*>? {
        return when (fromActivity::class) {
            SplashScreenActivity::class -> LoginActivity::class.java
            LoginActivity::class -> MainActivity::class.java
            ListUserActivity::class -> CreateUserActivity::class.java
            ListProductsActivity::class -> NewProductActivity::class.java
            else -> null
        }
    }

    private fun getPreviousScreen(fromActivity: AppCompatActivity): Class<*>? {
        return when (fromActivity::class) {
            CreateUserActivity::class -> ListUserActivity::class.java
            else -> null
        }
    }
}