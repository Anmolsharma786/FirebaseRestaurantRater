package com.example.firebaserestaurantrater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.firebaserestaurantrater.RestaurantListViewModel
import com.example.firebaserestaurantrater.databinding.ActivityRecyclerListBinding

class RecyclerListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecyclerListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get data from the view model(RecyclerViewAdapter)
        val viewModel : RestaurantListViewModel by viewModels()
        viewModel.getRestaurants().observe( this, {
                //passing list of restaurants  we got from "RestaurantListViewModel" as arguments using
                // lambda functions
                restaurants ->
            //creating instance of recycler view adapter
            var recyclerViewAdapter = RecyclerViewAdapter(this, restaurants)
            binding.verticalRecyclerView.adapter = recyclerViewAdapter
        })

    }
}