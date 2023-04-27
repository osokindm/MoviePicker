package com.example.moviepicker

import androidx.test.ext.junit.rules.activityScenarioRule
import com.example.moviepicker.pages.GenresScreen
import com.example.moviepicker.pages.HomeScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class GenresListTest : TestCase() {
    private val genres = listOf(
        "Мультфильмы",
        "Триллеры",
        "Комедии",
        "Ужасы"
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkGenresScreen() = run {
        step("Open genres screen") {
            HomeScreen {
                goToGenresTab()
            }
        }
        step("Check genres count") {
            GenresScreen {
                Assert.assertEquals(genres.size, rvGenres.getSize())
            }
        }
        step("Check genres list") {
            GenresScreen {
                rvGenres {
                    for (i in genres.indices) {
                        childAt<GenresScreen.GenreScreen>(i) {
                            genreButton.isVisible()
                            genreButton.hasText(genres[i])
                        }
                    }
                }
            }
        }
    }
}