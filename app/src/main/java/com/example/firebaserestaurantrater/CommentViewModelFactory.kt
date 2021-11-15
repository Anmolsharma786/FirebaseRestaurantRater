package com.example.firebaserestaurantrater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
// PROF is also not sure how this works. So just code this way to make sure it works
class CommentViewModelFactory(private val restaurantID : String) : ViewModelProvider.Factory {
    // This is the method we need to implement "create" func from "ViewModelProvider.Factory"
    // which i believe does make sure right model is used but not explained fully.
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java))
            return CommentViewModel(restaurantID) as T
        else
            throw IllegalArgumentException("Unknown View Model")
    }
}