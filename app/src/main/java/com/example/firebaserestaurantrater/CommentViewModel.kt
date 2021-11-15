package com.example.firebaserestaurantrater

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
// This class was used in the "CommentViewModelFactory" and the "restaurantID"
// This class extends "ViewModel" class
// This class will save the save data inside it for different events and then in database based on the ID
class CommentViewModel(restaurantID : String) : ViewModel() {
    // This is changeable list of Comment object
    private val comments = MutableLiveData<List<Comment>>()
    // This will be automatically calledd when this class is created
    init {
        //query the DB to get all comments for a specific restaurant by passing in the restaurantID
        val db = FirebaseFirestore.getInstance().collection("comments")
            .whereEqualTo("restaurantID", restaurantID)
            .addSnapshotListener { documents, exception ->
                if (exception != null) {
                    Log.w("DB_Response", "Listen Failed")
                    return@addSnapshotListener
                }

                //loop over the documents and convert them into Comment objects, then add them to a list
                // ?.let will make sure that it is not null, called as null safety
                documents?.let {
                    val commentList = ArrayList<Comment>()
                    for (document in documents) {
                        // converting the document to Comment Object
                        val comment = document.toObject(Comment::class.java)
                        // adding the converted Object to Arraylist
                        commentList.add(comment)
                    }
                    // Assigning the Mutable object the arraylist which live in database
                    comments.value = commentList
                }
            }
    }
    // This is how you access the live object and then you can populate the data in Activities
    fun getComments(): LiveData<List<Comment>> {
        return comments
    }
}