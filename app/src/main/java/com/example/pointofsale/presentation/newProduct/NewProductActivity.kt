package com.example.pointofsale.presentation.newProduct

import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.pointofsale.R
import com.example.pointofsale.databinding.ActivityNewProductBinding
import timber.log.Timber

class NewProductActivity : AppCompatActivity() {
    private var errors = 0
    private lateinit var layout: ActivityNewProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingLayout()
        initToolbar()
        layout.btnSaveNewProduct.setOnClickListener {
            errors = 0
            validateData()
            if (errors == 0)
                ProductCreatedDialogFragment().show(supportFragmentManager, "ProductCreated")
            else
                ErrorDialogFragment().show(supportFragmentManager, "ErrorCreateProduct")
        }

        layout.addFieldProduct.setOnClickListener {
            AddFieldDialogFragment().show(supportFragmentManager, "DialogAddField")
        }
    }

    private fun showError(view: TextView, layoutBackground: LinearLayout) {
        view.setTextColor(applicationContext.getColor(R.color.red))
        layoutBackground.background = ContextCompat.getDrawable(this, R.drawable.groupbox_red)
        errors += 1
    }

    private fun validateData() {
        if (TextUtils.isEmpty(layout.etProductName.text)) showError(
            layout.tvTitleNameProduct,
            layout.layoutGroupboxName
        )
        if (TextUtils.isEmpty(layout.etProductModel.text)) showError(
            layout.tvTitleModel,
            layout.layoutGroupboxModel
        )
        if (TextUtils.isEmpty(layout.etProductBrand.text)) showError(
            layout.tvTitleBrand,
            layout.layoutGroupboxBrand
        )
        if (TextUtils.isEmpty(layout.etProductCost.text)) showError(
            layout.tvTitleCost,
            layout.layoutGroupboxCost
        )
        if (TextUtils.isEmpty(layout.etProductPrice.text)) showError(
            layout.tvTitlePrice,
            layout.layoutGroupboxPrice
        )
        if (TextUtils.isEmpty(layout.etProductDescription.text)) showError(
            layout.tvTitleDescription,
            layout.layoutGroupboxDescription
        )
        if (TextUtils.isEmpty(layout.etProductReorder.text)) showError(
            layout.tvTitleReorder,
            layout.layoutGroupboxReorder
        )
        if (TextUtils.isEmpty(layout.etProductStock.text)) showError(
            layout.tvTitleStock,
            layout.layoutGroupboxStock
        )
    }

    private fun setBindingLayout() {
        Timber.d("CreateProductActivity_TAG: setBinding: ")
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