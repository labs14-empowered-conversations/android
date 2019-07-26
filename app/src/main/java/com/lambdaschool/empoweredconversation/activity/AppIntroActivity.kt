package com.lambdaschool.empoweredconversation.activity

import android.graphics.Color
import android.os.Bundle
import com.github.paolorotolo.appintro.AppIntro
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.fragment.IntroSlide1Fragment

class AppIntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(IntroSlide1Fragment())

        setBarColor(resources.getColor(R.color.color_intro_slide_background_bar))
        setSeparatorColor(resources.getColor(R.color.color_intro_slide_background_bar))
        isProgressButtonEnabled = false
        showSkipButton(false)
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
    }

    override fun onDonePressed() {
        super.onDonePressed()
    }

    override fun onSlideChanged() {
        super.onSlideChanged()
    }
}
