package com.example.emicalculator.data.util

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.emicalculator.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Dialogs {

    private var loadingDialog: AlertDialog? = null

    fun showConfirmationDialog(
        state: Boolean,
        context: Activity,
        isCancellable: Boolean,
        message:String,
        callBack: ((Boolean) -> Unit)? = null
    ) {
        try {
            if (!context.isFinishing || !context.isDestroyed) {
                context.runOnUiThread {
                    if (state) {
                        if (loadingDialog != null) {
                            if (loadingDialog?.isShowing == true) {
                                loadingDialog?.dismiss()
                            }
                        }
                        val popUp = MaterialAlertDialogBuilder(context, R.style.TransparentDialog)
                        val view = context.layoutInflater.inflate(R.layout.show_confirmation_dialog, null)
                        popUp.setView(view)
                        val noButton: MaterialButton = view.findViewById(R.id.no)
                        val yesButton: MaterialButton = view.findViewById(R.id.yes)
                        val title:TextView = view.findViewById(R.id.title)

                        title.text = message

                        noButton.setOnClickListener {
                            callBack?.invoke(false)
                            loadingDialog?.dismiss()
                        }

                        yesButton.setOnClickListener {
                            callBack?.invoke(true)
                            loadingDialog?.dismiss()
                        }
                        loadingDialog = popUp.create()
                        loadingDialog?.setCancelable(isCancellable)
                        loadingDialog?.setCanceledOnTouchOutside(isCancellable)
                        loadingDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        loadingDialog?.show()
                    } else {
                        if (loadingDialog != null) {
                            if (loadingDialog?.isShowing == true) {
                                loadingDialog?.dismiss()
                            }
                        }

                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}