package com.example.pointofsale.presentation.listProducts

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.pointofsale.R
import com.example.pointofsale.databinding.ActivityListProductsBinding
import com.example.pointofsale.presentation.listProducts.allProducts.AllProductsFragment
import com.example.pointofsale.presentation.listProducts.womenProducts.WomenProductsFragment
import com.example.pointofsale.presentation.listUsers.ListUserActivity
import com.example.pointofsale.presentation.logOut.LogOutDialogFragment
import com.example.pointofsale.presentation.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.android.inject
import timber.log.Timber
import android.view.ContextThemeWrapper


class ListProductsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var layout: ActivityListProductsBinding
    private val navigation by inject<Navigation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingLayout()
        layout.tlListProduct.setupWithViewPager(layout.vpListProduct)
        setAdapterViewer()
        initMenu()

        layout.btnNewProduct.setOnClickListener {
            navigation.navigateToNextScreen(this)
        }

        layout.ivFilter.setOnClickListener {
            initPopUpMenu()
        }
    }

    private fun initPopUpMenu(){
        val wrapper = ContextThemeWrapper(this, R.style.BasePopupMenu)
        val popupMenu = PopupMenu(wrapper,layout.ivFilter)
        popupMenu.menuInflater.inflate(R.menu.menu_filter_product_list, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {item->
            when(item.itemId){
                R.id.itemAll->{
                    Toast.makeText(this, "All",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.itemSold->{
                    Toast.makeText(this, "Sold",Toast.LENGTH_SHORT).show()
                    true
                }
                else->{
                    Toast.makeText(this, "Available",Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
        popupMenu.show()
    }

    private fun setBindingLayout() {
        Timber.d("ListProductsActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_list_products)
        layout.lifecycleOwner = this
    }

    private fun setAdapterViewer() {
        val fragmentManager = supportFragmentManager
        val adapter = ViewPagerAdapter(fragmentManager)
        adapter.addFragment(AllProductsFragment(), "All")
        adapter.addFragment(WomenProductsFragment(this@ListProductsActivity), "Women")

        layout.vpListProduct.adapter = adapter
    }

    private fun initMenu() {
        val toggle = ActionBarDrawerToggle(
            this,
            layout.drawerLayout,
            layout.listProductsToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        layout.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)
        setSupportActionBar(layout.listProductsToolbar)
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