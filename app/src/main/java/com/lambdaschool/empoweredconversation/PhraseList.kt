package com.lambdaschool.empoweredconversation

import kotlin.random.Random

class PhraseList {

    companion object {
        private var counter = 0

        private val phrases: ArrayList<String> =
            arrayListOf("From which we spring at the edge of forever",
                "No drama Oxford comma",
                "Great turbulent clouds finite",
                "Vastness is bearable only through love",
                "The sky calls to us",
                "The interiors of collapsing stars",
                "Citizens of distant epochs",
                "Star stuff harvesting star light",
                "Unbounded tendrils of gossamer clouds")

        fun getNextPhrase(): String {
            if (counter == 9) counter = 0
            return phrases[counter++]
        }

        fun setStartingIndex() {
            counter = Random.nextInt(0,8)
        }
    }

}