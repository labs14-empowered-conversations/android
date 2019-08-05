package com.lambdaschool.empoweredconversation.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lambdaschool.empoweredconversation.removeError
import kotlinx.android.synthetic.main.fragment_login_fragment_dialog.*
import androidx.navigation.findNavController
import com.lambdaschool.empoweredconversation.R


class LoginFragmentDialog : DialogFragment() {
    private lateinit var listener: LoginFragmentDialogListener
    private var loggedIn = false

    private val pwList: ArrayList<String> = arrayListOf("northwestern", "stanford", "michigan")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        try {
            listener = targetFragment as LoginFragmentDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement EditNameDialogListener")
        }
        password_edit_text.removeError(password_layout)
        enter_password_button.setOnClickListener {
            if (pwList.contains(password_edit_text.text.toString())){
                loggedIn = true
                dismiss()
            }
            else {
                password_layout.error = "Enter correct key"
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        listener.onFinishLoginDialog(loggedIn)
        super.onDismiss(dialog)
    }

    interface LoginFragmentDialogListener {
        fun onFinishLoginDialog(loggedIn: Boolean)
    }
}
