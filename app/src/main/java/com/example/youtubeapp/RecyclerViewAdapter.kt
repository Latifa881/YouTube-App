package com.example.youtubeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(val videos: ArrayList<YouTubeDetails>, val youTubePlayer: YouTubePlayer,val context_: Context) :
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
            cvTitle.text = videoTitle

            cvTitle.setOnClickListener {
               //if (MainActivity().checkInternetConnection(context_)) {
                    youTubePlayer.cueVideo(videoID, 0f)
              //  }
            }
        }


    }

    override fun getItemCount() = videos.size

}