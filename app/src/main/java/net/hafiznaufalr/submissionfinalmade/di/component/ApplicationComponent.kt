package net.hafiznaufalr.submissionfinalmade.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.hafiznaufalr.submissionfinalmade.App
import net.hafiznaufalr.submissionfinalmade.di.builder.ActivityBuilder
import net.hafiznaufalr.submissionfinalmade.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ActivityBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<App>