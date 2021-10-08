package com.example.youtubeapp

import android.content.Context
import android.content.res.Configuration
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {

    //Internet Connection Layout
    lateinit var llNoInternet: LinearLayout
    lateinit var tvOops: TextView
    lateinit var ivRefresh: ImageView

    //Main
    lateinit var llMain: LinearLayout
    lateinit var rvMain: RecyclerView
    var videos = arrayListOf(
        YouTubeDetails("Lunch Box Cake", "YK86ZQK7aAw", "lunchcake"),
        YouTubeDetails("Chocolate Mousse Cake", "XZJEHpDnr_s", "chocolate_mousse_cake"),
        YouTubeDetails("Japanese Cheese Cake", "mRI-4boceN0", "japanese_cheesecake"),
        YouTubeDetails("Strawberry Chocolate Cake", "YlBVRmAtnJc", "strawberry_chocolate_cake"),
        YouTubeDetails("Tom and Jerry Cheese Cake ", "Xw3GSefvY2Q", "tom_and_jerry_cheese _cake"),
        YouTubeDetails("Lemon Cake", "ybbUEfrpKuc", "lemon_cake"),
        YouTubeDetails("Green Tea Cream Cake", "yVo4zGeVinA", "green_tea_cream_cake"),
        YouTubeDetails("Soft Roll Cake ", "W3k88_AA-yE","roll_cake")
    )

    //YouTube
    lateinit var ytPlayerView: YouTubePlayerView
    private lateinit var ytPlayer: YouTubePlayer
    private var timeStamp = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Internet Connection Layout
        llNoInternet = findViewById(R.id.llNoInternet)
        tvOops = findViewById(R.id.tvOops)
        ivRefresh = findViewById(R.id.ivRefresh)
        //Main
        llMain = findViewById(R.id.llMain)
        //RecyclerView
        rvMain = findViewById(R.id.rvMain)

        ivRefresh = findViewById(R.id.ivRefresh)
        ivRefresh.setOnClickListener {
            checkInternetConnection()
        }
        //YouTube
        ytPlayerView = findViewById(R.id.ytPlayerView)

        //CheckInternetConnectivity
        checkInternetConnection()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            ytPlayerView.enterFullScreen()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            ytPlayerView.exitFullScreen()
        }
    }

    fun initializeRV() {
        ytPlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                ytPlayer = youTubePlayer
                ytPlayer.cueVideo(videos[0].ID, timeStamp)
                //RecyclerView
                rvMain.adapter = RecyclerViewAdapter(videos, ytPlayer, ytPlayerView)
                rvMain.layoutManager = LinearLayoutManager(applicationContext)

            }
        })


    }

    fun checkInternetConnection(): Boolean {
        //Internet Connection Layout
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting
        //Check internet connection
        if (activeNetwork?.isConnectedOrConnecting == true) {
            //do something with the network
            llMain.visibility = View.VISIBLE
            llNoInternet.visibility = View.GONE
            initializeRV()
            return true
        } else {
            llMain.visibility = View.GONE
            llNoInternet.visibility = View.VISIBLE
            println("Not Connected To The Internet")
            return false
        }
    }

}