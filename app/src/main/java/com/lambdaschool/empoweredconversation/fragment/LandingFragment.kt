package com.lambdaschool.empoweredconversation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.lambdaschool.empoweredconversation.PhraseList
import com.lambdaschool.empoweredconversation.R
import kotlinx.android.synthetic.main.fragment_landing.*
import kotlinx.coroutines.*

class LandingFragment : Fragment() {
    private val carouselJob = Job()
    private val carouselScope = CoroutineScope(Dispatchers.IO + carouselJob)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateCarousel()
    }

    private fun updateCarousel() {
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
    }
}
