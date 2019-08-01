package com.lambdaschool.empoweredconversation.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.github.paolorotolo.appintro.AppIntro
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.fragment.intro.IntroSlide1Fragment
import com.lambdaschool.empoweredconversation.fragment.intro.IntroSlide2Fragment
import com.lambdaschool.empoweredconversation.fragment.intro.IntroSlide3Fragment
import com.lambdaschool.empoweredconversation.fragment.intro.IntroSlide4Fragment

class AppIntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleViewPager()
    }

    override fun onDonePressed() {
        startActivity(Intent(applicationContext,
            MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        getSharedPreferences("AppIntro", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("AppIntroViewed", true)
            .apply()
        finish()
    }

    private fun handleViewPager() {
        addSlide(IntroSlide1Fragment())
        addSlide(IntroSlide2Fragment())
        addSlide(IntroSlide3Fragment())
        addSlide(IntroSlide4Fragment())

        setBarColor(resources.getColor(R.color.color_intro_slide_background_bar))
        setSeparatorColor(resources.getColor(R.color.color_intro_slide_background_bar))
        showSkipButton(false)

        //setFadeAnimation()
        //setZoomAnimation()
        //setFlowAnimation()
        setDepthAnimation()

        setNextArrowColor(resources.getColor(R.color.colorDarkTeal))
        doneButton.setBackgroundColor(resources.getColor(R.color.color_intro_slide_background_bar))
        setColorDoneText(resources.getColor(R.color.colorDarkTeal))
        setIndicatorColor(resources.getColor(R.color.colorDarkTeal), resources.getColor(R.color.colorDarkGrey))
    }

}
