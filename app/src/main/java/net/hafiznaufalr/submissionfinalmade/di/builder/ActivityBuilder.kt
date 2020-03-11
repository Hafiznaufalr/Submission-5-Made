package net.hafiznaufalr.submissionfinalmade.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.hafiznaufalr.submissionfinalmade.di.module.AppModule
import net.hafiznaufalr.submissionfinalmade.di.scope.Presentation
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.movie.MovieFragment
import net.hafiznaufalr.submissionfinalmade.ui.fragment.catalogue.tv.TvFragment

@Module
abstract class ActivityBuilder {
    @Presentation
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract  fun contributeMovieFragment(): MovieFragment

    @Presentation
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun contributeTvFragment(): TvFragment
}