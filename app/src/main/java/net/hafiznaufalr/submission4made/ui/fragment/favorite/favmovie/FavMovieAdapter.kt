package net.hafiznaufalr.submission4made.ui.fragment.favorite.favmovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import net.hafiznaufalr.submission4made.R
import net.hafiznaufalr.submission4made.model.Movie
import net.hafiznaufalr.submission4made.network.RetrofitService

class FavMovieAdapter(private val context: Context?,
                   private val data : List<Movie>,
                   private val onClickListener: (Movie) -> Unit)
    : RecyclerView.Adapter<FavMovieAdapter.MyHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int)
            = MyHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_movie,p0,false))


    override fun getItemCount(): Int {
        return data.size
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val v = holder.itemView

        v.tv_title.text = data[position].title
        Glide.with(context!!).load(RetrofitService.BASE_IMAGE_URL + data[position].posterPath).into(v.iv_poster)
        v.setOnClickListener {
            onClickListener(data[position])
        }

    }

}