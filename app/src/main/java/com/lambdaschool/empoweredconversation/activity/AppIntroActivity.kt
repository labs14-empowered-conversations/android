package com.lambdaschool.empoweredconversation.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.github.paolorotolo.appintro.AppIntro
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.IndicatorController
import com.github.paolorotolo.appintro.model.SliderPage
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.fragment.IntroSlide1Fragment
import com.lambdaschool.empoweredconversation.fragment.IntroSlide2Fragment
import com.lambdaschool.empoweredconversation.fragment.IntroSlide3Fragment
import com.lambdaschool.empoweredconversation.fragment.IntroSlide4Fragment

class AppIntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleViewPager()
    }

    override fun onDonePressed() {
        startActivity(Intent(applicationContext,
            MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
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
        setColorDoneText(resources.getColor(R.color.colorDarkTeal))
        setIndicatorColor(resources.getColor(R.color.colorDarkTeal), resources.getColor(R.color.colorDarkGrey))
    }

}
