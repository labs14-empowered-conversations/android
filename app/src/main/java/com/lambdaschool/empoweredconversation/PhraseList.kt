package com.lambdaschool.empoweredconversation

import kotlin.random.Random

class PhraseList {

    companion object {
        private var counter = 0

        private val phrases: ArrayList<String> =
            arrayListOf("Grab coffee or a drink",
                "No drama Oxford comma",
                "My height and shoulders",
                "Life is short",
                "Road trips pickles road trips",
                "I have a crush on passionate about",
                "Netflix Murakami",
                "Having a few beers",
                "Ethical nonmonogamy")

        fun getNextPhrase(): String {
            if (counter == 9) counter = 0
            return phrases[counter++]
        }

        fun setStartingIndex() {
            counter = Random.nextInt(0,8)
        }
    }

}