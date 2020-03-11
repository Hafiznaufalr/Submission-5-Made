package net.hafiznaufalr.submissionfinalmade.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import net.hafiznaufalr.submissionfinalmade.network.ApiInterface
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie.MovieFragment
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie.MoviePresenter
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie.MovieView
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv.TvFragment
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv.TvPresenter
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv.TvView
import retrofit2.Retrofit
import javax.sql.CommonDataSource

@Module
abstract class AppModule {
    @Module
    companion object{

        @JvmStatic
        @Provides
        fun providesMoviePresenter(view: MovieView): MoviePresenter = MoviePresenter(view)

        @JvmStatic
        @Provides
        fun providesTvPresenter(view: TvView): TvPresenter = TvPresenter(view)

    }
    @Binds
    abstract fun bindHomeView(fragment: MovieFragment): MovieView

    @Binds
    abstract fun bindTvView(fragment: TvFragment): TvView
}