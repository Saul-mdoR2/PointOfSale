package com.example.pointofsale.presentation.newProduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.pointofsale.R
import com.example.pointofsale.databinding.ActivityNewProductBinding
import com.example.pointofsale.presentation.navigation.Navigation
import kotlinx.android.synthetic.main.activity_new_product.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class NewProductActivity : AppCompatActivity() {
    private lateinit var layout:ActivityNewProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingLayout()
        initToolbar()
        layout.btnSaveNewProduct.setOnClickListener {
            ProductCreatedDialogFragment().show(supportFragmentManager, "DialogProductCreated")
        }

        layout.addFieldProduct.setOnClickListener {
            AddFieldDialog().show(supportFragmentManager, "DialogAddField")
        }

    }


    private fun setBindingLayout() {
        Timber.d("CreateUserActivity_TAG: setBinding: ")
        layout = DataBindingUtil.setContentView(this, R.layout.activity_new_product)
        layout.lifecycleOwner = this
    }

    private fun initToolbar() {
        layout.newProductToolbar.setNavigationOnClickListener {
            finish()
        }
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }
}