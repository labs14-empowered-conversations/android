package com.lambdaschool.empoweredconversation.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.lambdaschool.empoweredconversation.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @Throws(Exception::class)
    fun clickContinueButton_submitsForm() {
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout))
    }
}