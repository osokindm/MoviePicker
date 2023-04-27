package com.example.moviepicker.pages

import android.view.View
import com.example.moviepicker.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import org.hamcrest.Matcher

object GenresScreen : KScreen<GenresScreen>() {
    override val layoutId: Int
        get() = R.layout.fragment_genres
    override val viewClass: Class<*>?
        get() = null

    val rvGenres = KRecyclerView(
        builder = { withId(R.id.genresRecyclerView) },
        itemTypeBuilder = { itemType(::GenreScreen) }
    )

    class GenreScreen(matcher: Matcher<View>): KRecyclerItem<GenresScreen>(matcher) {
        val genreButton = KButton(matcher) { withId(R.id.genreButton) }
    }
}