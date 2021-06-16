package com.example.pointofsale.presentation.listProducts.editDeleteDialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import com.example.pointofsale.R

class EditCategoryDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_category, container, false)
        dialog?.window?.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.layout_corner_rounded_blue,
                null
            )
        )
        view.findViewById<Button>(R.id.btnConfirmAddCategory).setOnClickListener {
            dismiss()
        }

        view.findViewById<Button>(R.id.btnCancelAddCategory).setOnClickListener {
            dialog?.cancel()
        }
        return view
    }
}