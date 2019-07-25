package com.lambdaschool.empoweredconversation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lambdaschool.empoweredconversation.PhraseList
import com.lambdaschool.empoweredconversation.R
import kotlinx.android.synthetic.main.fragment_landing.*

class LandingFragment : Fragment() {


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
        Thread(Runnable {
            while (true) {
                activity?.runOnUiThread { phrase_textview.text = PhraseList.getNextPhrase() }
                Thread.sleep(5000)
            }
        }).start()

    }

}
