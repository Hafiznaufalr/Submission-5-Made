package net.hafiznaufalr.submissionfinalmade.ui.widget

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import net.hafiznaufalr.submissionfinalmade.BuildConfig.BASE_IMAGE_URL
import net.hafiznaufalr.submissionfinalmade.R
import net.hafiznaufalr.submissionfinalmade.db.movie.MovieHelper
import net.hafiznaufalr.submissionfinalmade.db.tv.TvHelper
import java.util.ArrayList

internal class RemoteViewFactory(private val mContext: Context) :
    RemoteViewsService.RemoteViewsFactory {

    private val mWidgetItems = ArrayList<Bitmap>()


    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        val movieHelper = MovieHelper.getInstance(mContext)
        movieHelper.open()
        val result = movieHelper.getAllMovie()

        result.forEach {
            mWidgetItems.add(
                Glide.with(mContext).asBitmap().load("${BASE_IMAGE_URL}${it.posterPath}")
                    .submit().get())
        }

        val tvShowHelper = TvHelper.getInstance(mContext)
        tvShowHelper.open()
        val resultTvShow = tvShowHelper.getAllTv()
        resultTvShow.forEach {
            mWidgetItems.add(
                Glide.with(mContext).asBitmap().load("${BASE_IMAGE_URL}${it.posterPath}")
                    .submit().get())
        }
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int {
        return mWidgetItems.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems[position])

        val extras = Bundle()
        extras.putInt(LatestMovieTv.EXTRA_ITEM, position)
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun hasStableIds(): Boolean {
        return false
    }

}