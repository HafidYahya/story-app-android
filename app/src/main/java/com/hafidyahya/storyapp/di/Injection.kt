package com.hafidyahya.storyapp.di

import android.content.Context
import com.hafidyahya.storyapp.data.StoryRepository
import com.hafidyahya.storyapp.data.api.ApiConfig
import com.hafidyahya.storyapp.data.pref.UserPreference
import com.hafidyahya.storyapp.data.pref.dataStore
import com.hafidyahya.storyapp.database.StoryDatabase


object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val database = StoryDatabase.getInstance(context)
        val apiService = ApiConfig.getApiService(pref)
        return StoryRepository.getInstance(apiService, pref, database)
    }
}