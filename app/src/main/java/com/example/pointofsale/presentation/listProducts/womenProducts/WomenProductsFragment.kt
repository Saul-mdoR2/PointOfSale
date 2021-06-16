package com.example.pointofsale.presentation.listProducts.womenProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pointofsale.databinding.FragmentWomenProductsBinding
import com.example.pointofsale.presentation.listProducts.editDeleteDialogs.DeleteCategoryDialog
import com.example.pointofsale.presentation.listProducts.editDeleteDialogs.EditCategoryDialog

class WomenProductsFragment(private val activity: AppCompatActivity) : Fragment() {
    private lateinit var layout: FragmentWomenProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = FragmentWomenProductsBinding.inflate(inflater, container, false)
        layout.ivDeleteCategory.setOnClickListener {
            DeleteCategoryDialog().show(activity.supportFragmentManager, "DialogDeleteCategory")
        }

        layout.ivEditCategory.setOnClickListener {
            EditCategoryDialog().show(activity.supportFragmentManager, "DialogEditCategory")
            // open screen edit category
        }
        return layout.root
    }
}