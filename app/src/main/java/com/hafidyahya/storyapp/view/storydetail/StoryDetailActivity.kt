package com.hafidyahya.storyapp.view.storydetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hafidyahya.storyapp.R
import com.hafidyahya.storyapp.data.api.response.ListStoryItem
import com.hafidyahya.storyapp.databinding.ActivityStoryDetailBinding


class StoryDetailActivity : AppCompatActivity() {
    private val viewModel: StoryDetailViewModel by viewModels()
    private lateinit var binding: ActivityStoryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val story = intent.getParcelableExtra<ListStoryItem>("story")
        if (story != null) {
            viewModel.setStoryDetail(story)
        }
        binding.tvDetailName.text = story?.name
        binding.tvDetailDescription.text = story?.description
        Glide.with(this).load(story?.photoUrl).centerCrop().placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder).into(binding.ivDetailPhoto)
    }
}