package com.joshua.test

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {
    @get:Rule
    var activityTestRule = activityScenarioRule<MainActivity>()

    @Test
    fun check_if_there_is_error_when_user_submit_without_entering_query() {
        val enterAcronym = "Enter acronym"
        onView(withId(R.id.edit_acronym)).perform(clearText())
        onView(withId(R.id.button_submit)).perform(click())
        onView(withId(R.id.input_layout_acronym)).check(matches(inputLayoutHasError(enterAcronym)))
    }

    @Test
    fun check_user_type_and_displayed_progressbar() {
        val valid = "IBN"
        onView(withId(R.id.edit_acronym)).perform(typeText(valid))
        onView(withId(R.id.button_submit)).perform(click())
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    private fun inputLayoutHasError(error: String): org.hamcrest.TypeSafeMatcher<View> {
        val d = object : org.hamcrest.TypeSafeMatcher<View>() {
            override fun matchesSafely(item: View?): Boolean {
                return (item as? TextInputLayout)?.error?.equals(error) ?: false
            }

            override fun describeTo(description: Description?) {

            }
        }
        return d
    }
}