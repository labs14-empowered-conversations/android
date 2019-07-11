package com.lambdaschool.empoweredconversation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_converstation_form.*

class ConversationForm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_form)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Start a Conversation"

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
        }
    }
}
