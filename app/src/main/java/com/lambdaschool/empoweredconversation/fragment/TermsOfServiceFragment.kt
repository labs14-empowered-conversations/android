package com.lambdaschool.empoweredconversation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lambdaschool.empoweredconversation.R
import kotlinx.android.synthetic.main.fragment_terms_of.*

class TermsOfServiceFragment : DialogFragment() {
    private lateinit var listener: TosFragmentDialogListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_terms_of, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        try {
            listener = targetFragment as TosFragmentDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement EditNameDialogListener")
        }
        accept_button.isEnabled = false
        tos_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            accept_button.isEnabled = isChecked
        }
        accept_button.setOnClickListener {
            listener.onFinishTosDialog(true)
            this.dismiss()
        }
    }

    interface TosFragmentDialogListener {
        fun onFinishTosDialog(accepted: Boolean)
    }
}
