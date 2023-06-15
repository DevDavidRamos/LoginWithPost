package com.example.login.presentacion.home

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.login.databinding.DialogAddPostBinding


class AddPostDialog(

): DialogFragment() {

    private lateinit var binding : DialogAddPostBinding
    private var onSubmitClickListener: ((String) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding =  DialogAddPostBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.btnPublicar .setOnClickListener {
            onSubmitClickListener?.invoke(binding.txtPost.text.toString())
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun setOnAddPostClickListener(action: (String) -> Unit) {
        onSubmitClickListener = action
    }

}