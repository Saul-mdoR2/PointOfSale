package com.example.pointofsale.presentation.splashScreen

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.pointofsale.R
import com.example.pointofsale.presentation.navigation.Navigation
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit


@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    private val navigation by inject<Navigation>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View?  {
        CoroutineScope(Dispatchers.IO).launch {
            delay(5, unit = TimeUnit.SECONDS)
            navigation.navigateToNextScreen(this@SplashScreenActivity)
        }
        return super.onCreateView(name, context, attrs)
    }

}