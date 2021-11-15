package com.example.firebaserestaurantrater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firebaserestaurantrater.databinding.ActivityCommentBinding
import com.google.firebase.firestore.FirebaseFirestore

class CommentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCommentBinding
    private lateinit var viewModel : CommentViewModel
    private lateinit var viewModelFactory : CommentViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intent is going to make sure that we get the ids from previous xml
        binding.restaurantNameTextView.text = intent.getStringExtra("restaurantName")
        val restaurantID = intent.getStringExtra("restaurantID")

        binding.saveCommentButton.setOnClickListener {

            //getting the username from the xml file and the comment
            val userName = binding.usernameEditText.text.toString()
            val commentBody = binding.bodyEditText.text.toString()

            //create the ability to save a comment
            if (userName.isNotEmpty() && commentBody.isNotEmpty())
            {
                Toast.makeText(this, "Both user name and comment are good", Toast.LENGTH_LONG).show()
                // connection with database
                val db = FirebaseFirestore.getInstance().collection("comments")
                // Since this collection could not exists
                // in the FireStore
                // We will generate the ids automatically
                val id = db.document().id

                // This will make sure that restaurantID is not null
                restaurantID?.let{
                    // creating instance of Comment class
                    val newComment = Comment(id,userName,commentBody,restaurantID)
                    // setting the document in the FireStore
                    db.document().set(newComment)
                        .addOnSuccessListener { Toast.makeText(this,"Added to DB", Toast.LENGTH_LONG).show() }
                        .addOnFailureListener { Toast.makeText(this,"Failed to add comment", Toast.LENGTH_LONG).show() }
                }
            }
            else
            {
                Toast.makeText(this, "Both user name and comment are req'd", Toast.LENGTH_LONG).show()
            }
        }

        //this code connects the RecyclerView with the ViewAdapter and List the Comments
        // This code is to read from the FireStore
        restaurantID?.let{

            viewModelFactory = CommentViewModelFactory(restaurantID)
            // we have to initialize the viewModel which connects use to viewModelFactory
            viewModel = ViewModelProvider(this, viewModelFactory).get(CommentViewModel::class.java)
            // in lambda expression this "comments" is live mutable object which we fetches from the viewModel
            viewModel.getComments().observe(this, {comments ->
                var recyclerAdapter = CommentViewAdapter(this, comments)
                binding.commentsRecyclerView.adapter = recyclerAdapter
                //binding.commentsRecyclerView.adapter = recyclerAdapter
            })
        }
    }
}