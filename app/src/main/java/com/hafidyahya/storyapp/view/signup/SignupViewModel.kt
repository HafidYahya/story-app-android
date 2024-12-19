package com.hafidyahya.storyapp.view.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.hafidyahya.storyapp.data.StoryRepository
import com.hafidyahya.storyapp.data.api.response.ErrorResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignupViewModel(private val repository: StoryRepository) : ViewModel() {
    private val _registrationStatus = MutableLiveData<Result<String?>>()
    val registrationStatus: LiveData<Result<String?>>
        get() = _registrationStatus

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                _registrationStatus.value = Result.Loading
                val response = repository.register(name, email, password)
                val message = response.message
                _registrationStatus.value = Result.Success(message)
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message ?: e.localizedMessage
                _registrationStatus.value = Result.Error(errorMessage)
            }
        }
    }
}

