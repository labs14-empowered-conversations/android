package com.lambdaschool.empoweredconversation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.vm.LandingViewModel
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var landingViewModel: LandingViewModel
    private val videoId = "S0Q4gqBUs7c"
    private var currentTime = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Home"

        landingViewModel = ViewModelProviders.of(this).get(LandingViewModel::class.java)

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

        lifecycle.addObserver(youtube_player_view)
        youtube_player_view.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                currentTime = second
            }
        })
    }

    override fun onResume() {
        super.onResume()
        youtube_player_view.getYouTubePlayerWhenReady(object: YouTubePlayerCallback{
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, currentTime)
            }
        })
    }
}
