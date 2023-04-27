package com.example.moviepicker.pages

import com.example.moviepicker.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object MovieScreen : KScreen<MovieScreen>() {
    override val layoutId: Int
        get() = R.layout.fragment_description
    override val viewClass: Class<*>?
        get() = null

    val movieTitle = KTextView { withId(R.id.movieName) }
}