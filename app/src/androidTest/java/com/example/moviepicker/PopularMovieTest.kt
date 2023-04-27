package com.example.moviepicker

import androidx.test.ext.junit.rules.activityScenarioRule
import com.example.moviepicker.pages.HomeScreen
import com.example.moviepicker.pages.MovieScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class PopularMovieTest : TestCase() {
    private val movieIndex = 0
    private var title = "Звёздные войны: Эпизод 1 — Скрытая угроза"

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkPopularMovieDescription() = run {
        step("Open popular movie description") {
            HomeScreen {
                goToMovieDescription(movieIndex)
            }
        }
        step("Check title in description") {
            MovieScreen {
                movieTitle.isVisible()
                movieTitle.containsText(title)
            }
        }
    }
}