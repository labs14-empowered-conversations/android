package com.lambdaschool.empoweredconversation

import kotlin.random.Random

class PhraseList {

    companion object {
        private var counter = 0

        private val phrases: ArrayList<String> =
            arrayListOf(
                "Supporting Survivors",
                "The Power of Words",
                "Feeling Connected",
                "Healing Through Relationships",
                "Being Seen and Heard",
                "Growing My Skills in Communication",
                "Listening to Understand",
                "Showing Up When it Matters",
                "Deepening My Ability to Connect",
                "Communicating with Love",
                "Learning How to Respond Better",
                "Responding with Empathy"
            )

        fun getNextPhrase(): String {
            if (counter == 9) counter = 0
            return phrases[counter++]
        }

        fun setStartingIndex() {
            counter = Random.nextInt(0, 8)
        }
    }

}