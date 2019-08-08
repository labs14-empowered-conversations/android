package com.lambdaschool.empoweredconversation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.net.URI

fun TextInputEditText.removeError(layout: TextInputLayout) {
    val textInputEditText = this
    textInputEditText.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            layout.error = ""
        }
    })
}

fun View.openUrlOnClick(url: String, context: Context) {
    this.setOnClickListener {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(context, intent, null)
    }
}