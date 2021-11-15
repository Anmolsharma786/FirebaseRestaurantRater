package com.example.firebaserestaurantrater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.firebaserestaurantrater.databinding.ActivityGridRecyclerBinding
// This class is extending the AppCompatActivity and then implementing the GridAdapter.RestaurantItemListener
class GridRecyclerActivity : AppCompatActivity(), GridAdapter.RestaurantItemListener {
    private lateinit var binding: ActivityGridRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get data from the view model(RecyclerViewAdapter)
        // This gets the data from the Firebase and then passes it to the adapter where adapter will put in child xml and then child xml will to parent xml
        val viewModel : RestaurantListViewModel by viewModels()
        viewModel.getRestaurants().observe( this, {
            //passing list of restaurants  we got from "RestaurantListViewModel" as arguments using
            // lambda functions
                restaurants ->
            //creating instance of adapter class
            var gridAdapter = GridAdapter(this, restaurants, this)
            binding.gridRecyclerView.adapter = gridAdapter
        })
    }

    /**
     * When a restaurant is selected, pass the Restaurant information to the comment activity
     */
    override fun restaurantSelected(restaurant: Restaurant) {
        // Here we are going to change the activity
        val intent = Intent(this, CommentActivity::class.java)
        // This how we pass the information from one interface to other(This is very case sensitive stuff so make sure it si right)
        intent.putExtra("restaurantID", restaurant.id)
        intent.putExtra("restaurantName", restaurant.name)
        // Running the activity
        startActivity(intent)
    }
}