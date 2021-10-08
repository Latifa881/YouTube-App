package com.example.youtubeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(
    val videos: ArrayList<YouTubeDetails>,
    val youTubePlayer: YouTubePlayer,
    val ytPlayerView: YouTubePlayerView
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = videos[position]

        holder.itemView.apply {
            val videoTitle = data.title
            val videoID = data.ID
            val videoIcone = data.Icon

            cvTitle.text = videoTitle
            var Icon = 0
            when {
                videoIcone == "lunchcake" -> Icon = R.drawable.lunchcake
                videoIcone == "chocolate_mousse_cake" -> Icon = R.drawable.chocolate_mousse_cake
                videoIcone == "japanese_cheesecake" -> Icon = R.drawable.japanese_cheesecake
                videoIcone == "strawberry_chocolate_cake" -> Icon =
                    R.drawable.strawberry_chocolate_cake
                videoIcone == "tom_and_jerry_cheese _cake" -> Icon =
                    R.drawable.tom_and_jerry_cheese_cake
                videoIcone == "lemon_cake" -> Icon = R.drawable.lemon_cake
                videoIcone == "green_tea_cream_cake" -> Icon = R.drawable.green_tea_cream_cake
                videoIcone == "roll_cake" -> Icon = R.drawable.roll_cake
            }

            cvIcon.setImageResource(Icon)

            cvTitle.setOnClickListener {
                ytPlayerView.visibility = View.VISIBLE
                youTubePlayer.cueVideo(videoID, 0f)

            }
        }


    }

    override fun getItemCount() = videos.size

}