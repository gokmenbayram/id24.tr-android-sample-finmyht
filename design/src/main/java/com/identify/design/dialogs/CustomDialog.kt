package com.identify.design.dialogs

/**
 * Created by gokmenbayram on 16.10.2025.
 */

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.identify.design.databinding.DialogCustomBinding

class CustomDialog(
    private val context: Context
) {
    private var dialog: AlertDialog? = null

    fun show(
        title: String,
        message: String,
        positiveText: String = "Tamam",
        negativeText: String = "İptal",
        cancelable: Boolean = true,
        onConfirm: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        val binding = DialogCustomBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(context)
            .setView(binding.root)
            .setCancelable(cancelable)

        dialog = builder.create()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        binding.tvTitle.text = title
        binding.tvMessage.text = message
        binding.btnOk.text = positiveText
        binding.btnCancel.text = negativeText

        binding.btnOk.setOnClickListener {
            onConfirm?.invoke()
            dialog?.dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}
