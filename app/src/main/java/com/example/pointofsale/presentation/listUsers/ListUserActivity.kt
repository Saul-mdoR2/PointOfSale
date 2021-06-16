package com.example.pointofsale.presentation.listUsers

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.pointofsale.R
import com.example.pointofsale.databinding.ActivityListUserBinding
import com.example.pointofsale.presentation.listProducts.ListProductsActivity
import com.example.pointofsale.presentation.logOut.LogOutDialogFragment
import com.example.pointofsale.presentation.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.android.inject
import timber.log.Timber

class ListUserActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val navigation by inject<Navigation>()
    private lateinit var layout: ActivityListUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingLayout()
        layout.btnNewUser.setOnClickListener {
            navigation.navigateToNextScreen(this)
        }
        setSupportActionBar(layout.listUsersToolbar)
        initMenu()
    }

    private fun setBindingLayout() {
        Timber.d("ListUsersActivity: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_list_user)
        layout.lifecycleOwner = this
    }

    private fun initMenu() {
        val toggle = ActionBarDrawerToggle(
            this,
            layout.drawerLayout,
            layout.listUsersToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        layout.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)
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
}