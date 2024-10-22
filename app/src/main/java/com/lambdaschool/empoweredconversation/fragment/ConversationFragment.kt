package com.lambdaschool.empoweredconversation.fragment

import android.graphics.Paint.UNDERLINE_TEXT_FLAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.lambdaschool.empoweredconversation.*
import com.lambdaschool.empoweredconversation.model.Conversation
import com.lambdaschool.empoweredconversation.vm.ConversationViewModel
import com.lambdaschool.empoweredconversation.vm.ConversationViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_conversation.*
import javax.inject.Inject

class ConversationFragment : Fragment(), TermsOfServiceFragment.TosFragmentDialogListener,
    LoginFragmentDialog.LoginFragmentDialogListener {
    private val tosFragRequestCode = 0
    private val loginFragRequestCode = 1
    private var appKey = ""
    private lateinit var school: String

    override fun onFinishLoginDialog(loggedIn: Boolean, key: String) {
        if (!loggedIn)
            view?.findNavController()?.navigate(R.id.landingFragment)
        else
            appKey = key
    }

    override fun onFinishTosDialog(accepted: Boolean) {
        if (accepted) {
            continue_button.text = "Send message"
            continue_button.isEnabled = true
        }
    }

    private lateinit var survivorNumber: String
    private lateinit var ffName: String
    private lateinit var ffNumber: String

    @Inject
    lateinit var conversationViewModelFactory: ConversationViewModelFactory

    private lateinit var conversationViewModel: ConversationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conversation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.toolbar_title?.text = "Start a conversation"

        App.app.conversationComponent.inject(this)

        video_url_textview.paintFlags = video_url_textview.paintFlags or UNDERLINE_TEXT_FLAG

        context?.let { context ->
            video_url_textview.openUrlOnClick(
                "https://www.youtube.com/watch?v=bBrgb-uLtlE",
                context
            )
        }

        fragmentManager?.let { fragmentManager ->
            val loginFrag = LoginFragmentDialog()
            loginFrag.setTargetFragment(this, loginFragRequestCode)
            loginFrag.show(fragmentManager, "login_fragment")
        }

        conversationViewModel = ViewModelProviders.of(
            this,
            conversationViewModelFactory
        ).get(ConversationViewModel::class.java)

        ff_name.removeError(ff_name_layout)
        ff_number.removeError(ff_number_layout)
        survivor_number.removeError(survivor_number_layout)

        continue_button.setOnClickListener {
            continue_button.isEnabled = false
            survivorNumber = survivor_number.text.toString()
            ffName = ff_name.text.toString()
            ffNumber = ff_number.text.toString()

            if (validateFields()) {
                if (continue_button.text.toString() == "Start") {
                    fragmentManager?.let { fragmentManager ->
                        val tosFrag = TermsOfServiceFragment()
                        tosFrag.setTargetFragment(this, tosFragRequestCode)
                        tosFrag.show(fragmentManager, "tos_fragment")
                    }
                } else {
                    val keys = BuildConfig.loginPw.split(", ")
                    when (appKey) {
                        keys[0] -> school = "michigan"
                        keys[1] -> school = "northwestern"
                    }

                    conversationViewModel
                        .postConversation(Conversation(0, survivorNumber, ffName, ffNumber, school))
                        .observe(this, Observer { convo ->
                            convo?.let {
                                Log.i("Conversation", it.ffname)
                                Toast.makeText(
                                    context,
                                    "Your message was successfully delivered!",
                                    Toast.LENGTH_LONG
                                ).show()
                                ff_name.setText("")
                                ff_number.setText("")
                                survivor_number.setText("")
                                continue_button.isEnabled = true
                            }
                            if (convo == null) {
                                Toast.makeText(
                                    context,
                                    "Your message could not be delivered",
                                    Toast.LENGTH_LONG
                                ).show()
                                continue_button.isEnabled = true
                            }
                            ff_name.clearFocus()
                            ff_number.clearFocus()
                            survivor_number.clearFocus()
                            continue_button.text = "Start"
                        })
                }
            }
        }

    }

    private fun validateFields(): Boolean {
        continue_button.isEnabled = true
        if (TextUtils.isEmpty(ffName)) {
            ff_name_layout.error = "Please provide a name"
            return false
        }
        if (TextUtils.isEmpty(ffNumber)) {
            ff_number_layout.error = "Please provide a number"
            return false
        } else {
            if (ffNumber.replace("[-+.^:,]".toRegex(), "").length != 10) {
                ff_number_layout.error = "Please provide a valid number"
                return false
            }
        }
        if (TextUtils.isEmpty(survivorNumber)) {
            survivor_number_layout.error = "Please provide a number"
            return false
        } else {
            if (survivorNumber.replace("[-+.^:,]".toRegex(), "").length != 10) {
                survivor_number_layout.error = "Please provide a valid number"
                return false
            }
        }
        return true
    }

}



