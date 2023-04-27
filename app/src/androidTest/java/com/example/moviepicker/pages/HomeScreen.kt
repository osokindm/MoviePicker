package com.example.moviepicker.pages

import android.view.View
import com.example.moviepicker.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.tabs.KTabLayout
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher


object HomeScreen : KScreen<HomeScreen>() {
    private val genresTabId = 1

    override val layoutId
        get() = R.layout.fragment_home
    override val viewClass: Class<*>?
        get() = null

    private val tabLayout = KTabLayout { withId(R.id.homeTabLayout) }

    private val rvPopularMovies = KRecyclerView(
        builder = { withId(R.id.popularMoviesRecyclerView) },
        itemTypeBuilder = { itemType(::PopularMovieScreen) }
    )

    class PopularMovieScreen(matcher: Matcher<View>): KRecyclerItem<PopularMovieScreen>(matcher) {
        val movieCard = KView(matcher) { withId(R.id.movieCard) }
        val movieTitle = KTextView(matcher) { withId(R.id.movieTitle) }
    }

    fun goToGenresTab() {
        this.tabLayout.selectTab(genresTabId)
    }

    fun goToMovieDescription(index: Int) {
        rvPopularMovies.childAt<PopularMovieScreen>(index) {
            movieCard.isVisible()
            movieCard.click()
        }
    }

}