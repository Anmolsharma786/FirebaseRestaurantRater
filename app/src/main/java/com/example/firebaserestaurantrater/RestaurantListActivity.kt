package com.example.firebaserestaurantrater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import com.example.firebaserestaurantrater.databinding.ActivityRestaurantListBinding
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRestaurantListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create an instance of our RestaurantListViewModel - this is syntax
        val viewModel : RestaurantListViewModel by viewModels()
        // we will get the rows and observe them which will make it async process
        viewModel.getRestaurants().observe( this, { restaurants ->
            // This will make sure that no view is empty since it is async process
            binding.linearLayout.removeAllViews()
            // looping through the rows we have in table
            for (restaurant in restaurants) {
                //Add restaurant to the LinearList
                val textView = TextView(this)
                textView.text = restaurant.name
                textView.textSize = 20f
                // adding the rows
                binding.linearLayout.addView(textView)
            }
        })
/*
* ALL OF THE COMMENTED CODE IS FROM 5TH WEEK, this code is not async and also not the helpful with user orientations.
* */
//        //connect to the DB
//        val db = FirebaseFirestore.getInstance().collection("restaurants")
//
//        val query = db.get().addOnSuccessListener { documents ->
//
//            //loop over the restaurants
//            for (document in documents)
//            {
//                Log.i("DB_Response", "${document.data}")
//
//                //create a restaurant object from the DB
//                val restaurant = document.toObject(Restaurant::class.java)
//
//                //Add restaurant to the LinearList
//                val textView = TextView(this)
//                textView.text = restaurant.name
//                textView.textSize = 20f
//
//                binding.linearLayout.addView(textView)
//            }

//        }
    }
}