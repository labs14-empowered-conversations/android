package com.lambdaschool.empoweredconversation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.lambdaschool.empoweredconversation.App
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.model.Conversation
import com.lambdaschool.empoweredconversation.vm.ConversationViewModel
import com.lambdaschool.empoweredconversation.vm.ConversationViewModelFactory
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_converstation_form.*
import javax.inject.Inject

class ConversationForm : AppCompatActivity() {

    private lateinit var survivorNumber: String
    private lateinit var ffName: String
    private lateinit var ffNumber: String

    @Inject
    lateinit var conversationViewModelFactory: ConversationViewModelFactory

    private lateinit var conversationViewModel: ConversationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_form)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Start a Conversation"

        App.app.conversationComponent?.inject(this)

        conversationViewModel = ViewModelProviders.of(this,
            conversationViewModelFactory).get(ConversationViewModel::class.java)


        val item1 = PrimaryDrawerItem().withIdentifier(1).withName("Home")
        val item2 = PrimaryDrawerItem().withIdentifier(2).withName("Start a conversation")

        val header = AccountHeaderBuilder()
            .withActivity(this)
            .withHeaderBackground(R.drawable.gradient)
            .addProfiles(
                ProfileDrawerItem().withName("Empowered Conversations").withIcon(R.drawable.eclogowhite)
            )
            .withOnlyMainProfileImageVisible(true)
            .withProfileImagesClickable(false)
            .withSelectionListEnabledForSingleProfile(false)
            .build()

        val result = DrawerBuilder()
            .withAccountHeader(header)
            .withActivity(this)
            .withToolbar(toolbar)
            .addDrawerItems(
                item1,
                DividerDrawerItem(),
                item2
            )
            .withSelectedItem(-1)
            .withOnDrawerItemClickListener { view, position, drawerItem ->
                if (drawerItem.identifier == 1L) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else if (drawerItem.identifier == 2L) {
                    val intent = Intent(applicationContext, ConversationForm::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                false
            }
            .build()

        result.setSelection(2, false)

        continue_button.setOnClickListener {
            YoYo.with(Techniques.Shake)
                .duration(200)
                .repeat(0)
                .playOn(users_name_edit_text)

            survivorNumber = survivor_number.text.toString()
            ffName = ff_name.text.toString()
            ffNumber = ff_number.text.toString()

            if (validateFields(survivorNumber, ffName, ffNumber)) {
                conversationViewModel
                    .postConversation(Conversation(survivorNumber, ffName, ffNumber))
                    .observe(this, Observer { convo ->
                        convo?.let {
                            Log.i("Conversation", it.ffName)
                        }
                    })
            }
        }
    }

    fun validateFields(survivorNumber: String, ffName: String,
                       ffNumber: String): Boolean {

        return true
    }
}
