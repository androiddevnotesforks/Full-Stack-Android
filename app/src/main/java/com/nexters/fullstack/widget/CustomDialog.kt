package com.nexters.fullstack.widget

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.UiThread
import com.nexters.fullstack.R

class CustomDialog(private val builder: Builder) : Dialog(builder.context, builder.themeLayoutId) {

    init {
        builder.dialog = initCustomDialog(builder)
    }


    @UiThread
    private fun initCustomDialog(builder: Builder): Dialog {
        val dialog = Dialog(builder.context, builder.themeLayoutId)

        dialog.setContentView(R.layout.dialog_interface_layout)

        val tvContinue = findViewById<TextView>(R.id.tv_continue)
        val tvExit = findViewById<TextView>(R.id.tv_exit)

        tvContinue?.setOnClickListener(builder.setOnContinueListener)
        tvExit?.setOnClickListener(builder.setOnCancelListener)
        return dialog
    }

    @UiThread
    override fun show() {
        builder.dialog?.show()
    }

    @UiThread
    override fun dismiss() {
        builder.dialog?.dismiss()
    }


    class Builder(internal val context: Context, internal val themeLayoutId: Int) {
        internal var dialog: Dialog? = null
        internal var setOnContinueListener: SetOnContinueListener? = null
        internal var setOnCancelListener: SetOnCancelListener? = null


        @UiThread
        fun build() = CustomDialog(this)

        fun setOnContinueListener(listener: SetOnContinueListener): Builder {
            this.setOnContinueListener = listener
            return this
        }

        fun setOnCancelListener(listener: SetOnCancelListener): Builder {
            this.setOnCancelListener = listener
            return this
        }
    }
}

typealias SetOnContinueListener = (View) -> Unit
typealias SetOnCancelListener = (View) -> Unit