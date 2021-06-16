package com.example.pointofsale.presentation.listProducts.allProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.pointofsale.databinding.FragmentAllProductsBinding
import com.example.pointofsale.presentation.listProducts.editDeleteDialogs.DeleteCategoryDialog
import com.example.pointofsale.presentation.listProducts.editDeleteDialogs.EditCategoryDialog
import com.example.pointofsale.presentation.navigation.Navigation
import org.koin.android.ext.android.inject

class AllProductsFragment : Fragment() {

    private lateinit var layout: FragmentAllProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = FragmentAllProductsBinding.inflate(inflater, container, false)
        return layout.root
    }

}