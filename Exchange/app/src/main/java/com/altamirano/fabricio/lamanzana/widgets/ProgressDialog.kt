package com.altamirano.fabricio.lamanzana.widgets

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.altamirano.fabricio.lamanzana.R

class ProgressDialog(val ctx: Context) {

    private var dialog: AlertDialog

    private val text: TextView

    private val builder: AlertDialog.Builder = AlertDialog.Builder(ctx)

    init {
        builder.setCancelable(false)
        val ll = LayoutInflater.from(this.ctx)
        val view = ll.inflate(R.layout.layout_progress_dialog, null)
        text = view.findViewById(R.id.txt)
        builder.setView(view)
        this.dialog = builder.create()
    }

    fun setMessage(text: String) {
        this.text.text = text
    }

    fun setMessage(idString: Int) {
        this.text.setText(idString)
    }

    fun show() {
        if (!this.dialog.isShowing) {
            this.dialog.show()
        }
    }

    fun dismiss() {
        if (this.dialog.isShowing) {
            this.dialog.dismiss()
        }
    }
}