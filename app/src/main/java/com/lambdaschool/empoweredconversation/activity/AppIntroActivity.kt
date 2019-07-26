package com.lambdaschool.empoweredconversation.activity

import android.graphics.Color
import android.os.Bundle
import com.github.paolorotolo.appintro.AppIntro
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntroFragment
import com.github.paolorotolo.appintro.model.SliderPage
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.fragment.IntroSlide1Fragment
import com.lambdaschool.empoweredconversation.fragment.IntroSlide2Fragment
import com.lambdaschool.empoweredconversation.fragment.IntroSlide3Fragment

class AppIntroActivity : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(IntroSlide1Fragment())
        addSlide(IntroSlide2Fragment())
        addSlide(IntroSlide3Fragment())

        setBarColor(resources.getColor(R.color.color_intro_slide_background_bar))
        setSeparatorColor(resources.getColor(R.color.color_intro_slide_background_bar))
        showSkipButton(false)
    }

    override fun onDonePressed() {
        super.onDonePressed()
    }

}
