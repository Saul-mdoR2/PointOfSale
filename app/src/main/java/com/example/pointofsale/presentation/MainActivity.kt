package com.example.pointofsale.presentation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.pointofsale.R
import com.example.pointofsale.databinding.ActivityMainBinding
import com.example.pointofsale.presentation.listProducts.ListProductsActivity
import com.example.pointofsale.presentation.listUsers.ListUserActivity
import com.example.pointofsale.presentation.logOut.LogOutDialogFragment
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var layout: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingLayout()
        setSupportActionBar(layout.mainToolbar)
        initMenu()
    }

    private fun initMenu() {
        val toggle = ActionBarDrawerToggle(
            this,
            layout.drawerLayout,
            layout.mainToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        layout.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        layout.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (layout.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            layout.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_settings -> {
                startActivity(Intent(this, ListUserActivity::class.java))
                layout.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_products -> {
                startActivity(Intent(this, ListProductsActivity::class.java))
                layout.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_logout -> {
                LogOutDialogFragment().show(supportFragmentManager, "DialogLogOut")
                layout.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_dashboard -> {
                Toast.makeText(this, "Dashboard", Toast.LENGTH_LONG).show()
                layout.drawerLayout.closeDrawer(GravityCompat.START)
                return true
            }
        }
        layout.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setBindingLayout() {
        Timber.d("MainActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layout.lifecycleOwner = this
    }

}