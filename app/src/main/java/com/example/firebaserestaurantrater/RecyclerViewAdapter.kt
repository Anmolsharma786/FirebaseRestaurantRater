package com.example.firebaserestaurantrater

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// This "RecyclerViewAdapter" have to arguments or instance variables "context","restaurants"
// They extend "RecyclerView.Adapter" class from the import
class RecyclerViewAdapter (val context : Context,
                           val restaurants : List<Restaurant>)   : RecyclerView.Adapter<RecyclerViewAdapter.RestaurantViewHolder>(){

    /**
     * This class is used to allow us to access the item_restaurant.xml objects
     * THIS IS CALLED AS VIEWHOLDER
     */
    // you can define classes within the classes
    inner class RestaurantViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        // Holding the name of the restaurants
        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        // Holding the rating
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_restaurant.xml)
     * with the RecyclerView or activity_recycler_list.xml
     *
     * This also you can say is instance of RestaurantViewHolder class
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    /**
     * This method will bind the viewHolder with a specific restaurant object
     */
    override fun onBindViewHolder(viewHolder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        // This is fancy way
//        with (viewHolder){
//            nameTextView.text = restaurant.name

//            ratingBar.rating = restaurant.rating!!.toFloat()
//        }
        viewHolder.nameTextView.text = restaurant.name
        //rating bar need a float it cannot handles Integer
        viewHolder.ratingBar.rating = restaurant.rating!!.toFloat()
    }


// This will return the number of restaurants in the database
    override fun getItemCount(): Int {
        return restaurants.size
    }


}