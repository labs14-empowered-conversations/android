package com.lambdaschool.empoweredconversation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.lambdaschool.empoweredconversation.PhraseList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_landing.*
import kotlinx.coroutines.*
import android.content.Intent
import android.net.Uri
import com.lambdaschool.empoweredconversation.R
import com.lambdaschool.empoweredconversation.openUrlOnClick


class LandingFragment : Fragment() {
    private lateinit var carouselJob: Job
    private lateinit var carouselScope: CoroutineScope

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.toolbar_title?.text = "Home"

        start_now_button.setOnClickListener{
            view.findNavController().navigate(R.id.conversationFragment)
        }

        context?.let { instagram_button.openUrlOnClick("https://www.instagram.com/empoweredconvo/", it) }
        context?.let { twitter_button.openUrlOnClick("https://twitter.com/empoweredconvo/", it) }
        context?.let { facebook_button.openUrlOnClick("https://www.facebook.com/empoweredconvo/", it) }

    }

    override fun onStart() {
        super.onStart()
        updateCarousel()
    }

    private fun updateCarousel() {
        carouselJob = Job()
        carouselScope = CoroutineScope(Dispatchers.IO + carouselJob)
        PhraseList.setStartingIndex()
        carouselScope.launch {
            while (phrase_textview != null) {
                withContext(Dispatchers.Main) {
                    phrase_textview.text = PhraseList.getNextPhrase()
                    YoYo.with(Techniques.SlideInLeft)
                        .duration(1000)
                        .repeat(0)
                        .playOn(phrase_textview)
                }

                Thread.sleep(3000)

                withContext(Dispatchers.Main) {
                    YoYo.with(Techniques.SlideOutRight)
                        .duration(1000)
                        .repeat(0)
                        .playOn(phrase_textview)
                }

                Thread.sleep(1000)
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onStop() {
        super.onStop()
        carouselJob.cancel()
        carouselScope.cancel()
    }
}
