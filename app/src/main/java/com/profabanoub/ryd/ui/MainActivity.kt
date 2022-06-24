package com.profabanoub.ryd.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.profabanoub.ryd.R
import com.profabanoub.ryd.databinding.ActivityMainBinding
import com.profabanoub.ryd.utils.ImageUtils

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetData.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_get_data -> {
                loadData()
            }
        }
    }

    private fun loadData() {

        val videoId = binding.etVideoId.text.toString()

        if (binding.etVideoId.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please enter a valid id.", Toast.LENGTH_SHORT)
                .show()
        } else {
            binding.progressLoading.visibility = View.VISIBLE

            viewModel.getVideoData(videoId)

            val imageLink = "https://img.youtube.com/vi/$videoId/0.jpg"
            ImageUtils.setImageToView(this, imageLink, binding.imgVideoImage)

            Handler(Looper.getMainLooper()).postDelayed({
                val videoData = viewModel.queryStateFlow.value
                showViews()
                binding.tvDateCreated.text = "Date Created: ${videoData.dateCreated}"
                binding.tvDislikes.text = "Dislikes: ${videoData.dislikes}"
                binding.tvLikes.text = "Likes: ${videoData.likes}"
                binding.tvRating.text = "Rating: ${videoData.rating}"
                binding.tvViewCount.text = "View Count: ${videoData.viewCount}"

                binding.progressLoading.visibility = View.GONE
            }, 2000)
        }
    }

    private fun showViews() {
        binding.tvDateCreated.visibility = View.VISIBLE
        binding.tvDislikes.visibility = View.VISIBLE
        binding.tvLikes.visibility = View.VISIBLE
        binding.tvRating.visibility = View.VISIBLE
        binding.tvViewCount.visibility = View.VISIBLE
    }
}