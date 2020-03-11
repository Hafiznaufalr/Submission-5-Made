package net.hafiznaufalr.submissionfinalmade

import dagger.android.AndroidInjector
import dagger.android.DaggerActivity
import dagger.android.DaggerApplication
import net.hafiznaufalr.submissionfinalmade.di.component.DaggerApplicationComponent
import javax.inject.Scope

class App: DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.create().apply { inject(this@App) }
    }

}