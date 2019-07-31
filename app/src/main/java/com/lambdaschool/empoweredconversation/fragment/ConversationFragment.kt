package com.lambdaschool.empoweredconversation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.empoweredconversation.App
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.model.Conversation
import com.lambdaschool.empoweredconversation.vm.ConversationViewModel
import com.lambdaschool.empoweredconversation.vm.ConversationViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_conversation.*
import javax.inject.Inject

class ConversationFragment : Fragment(), TermsOfServiceFragment.TosFragmentDialogListener {
    override fun onFinishTosDialog(accepted: Boolean) {
        if (accepted) {
            continue_button.text = "Send message"
        }
    }

    private lateinit var survivorNumber: String
    private lateinit var ffName: String
    private lateinit var ffNumber: String

    @Inject
    lateinit var conversationViewModelFactory: ConversationViewModelFactory

    private lateinit var conversationViewModel: ConversationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_conversation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.toolbar_title?.text = "Start a conversation"

        App.app.conversationComponent.inject(this)

        conversationViewModel = ViewModelProviders.of(
            this,
            conversationViewModelFactory
        ).get(ConversationViewModel::class.java)


        continue_button.setOnClickListener {
            if (continue_button.text.toString() == "Start") {
                fragmentManager?.let { fragmentManager ->
                    val tosFrag = TermsOfServiceFragment()
                    tosFrag.setTargetFragment(this, 0)
                    tosFrag.show(fragmentManager, "tos_fragment")
                }
            } else {
                survivorNumber = survivor_number.text.toString()
                ffName = ff_name.text.toString()
                ffNumber = ff_number.text.toString()

                if (validateFields()) {
                    conversationViewModel
                        .postConversation(Conversation(survivorNumber, ffName, ffNumber))
                        .observe(this, Observer { convo ->
                            convo?.let {
                                Log.i("Conversation", it.ffname)
                            }
                        })
                }
            }

        }

    }

}

fun validateFields(): Boolean {

    return true
}
