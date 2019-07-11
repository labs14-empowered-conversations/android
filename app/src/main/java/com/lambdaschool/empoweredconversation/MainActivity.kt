package com.lambdaschool.empoweredconversation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Home"

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

        result.setSelection(1, false)

        val users = ArrayList<User>()
        for (i in 0 until 500){
            users.add(User("$i", "$i"))
        }

        users_list.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = UsersListAdapter(users)
        }

    }
}
