package net.hafiznaufalr.submissionfinalmade.ui.widget

import android.content.Intent
import android.widget.RemoteViewsService

class WidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return RemoteViewFactory(this.applicationContext)
    }
}