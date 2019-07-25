package com.lambdaschool.empoweredconversation

import kotlin.random.Random

class PhraseList {
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

    fun getPhrase(): String {
        return phrases[Random.nextInt(0,9)]
    }
}