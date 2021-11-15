package com.example.firebaserestaurantrater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridAdapter (val context : Context,
                   val restaurants : List<Restaurant>,
                    val itemListener: RestaurantItemListener)  : RecyclerView.Adapter<GridAdapter.RestaurantViewHolder>(){

    /**
     * This class is used to allow us to access the item_restaurant.xml objects
     * THIS IS CALLED AS VIEWHOLDER
     */
    // you can define classes within the classes
    inner class RestaurantViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        // Holding the name of the restaurants
        val nameTextView = itemView.findViewById<TextView>(R.id.textView)
        // Holding the rating
        val ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_restaurant.xml)
     * with the RecyclerView or activity_recycler_list.xml
     *
     * This method create number of slots required for the task using child's layout to parent
     *
     * This also you can say is instance of RestaurantViewHolder class
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_grid_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    /**
     * This method basically populates the data in the parent xml
     *
     * This method will bind the viewHolder with a specific restaurant object
     */
    override fun onBindViewHolder(viewHolder: RestaurantViewHolder, position: Int) {
        // the slots which were populated in last func

        val restaurant = restaurants[position]

        // This is fancy way
//        with (viewHolder){
//            nameTextView.text = restaurant.name

//            ratingBar.rating = restaurant.rating!!.toFloat()
//        }
        viewHolder.nameTextView.text = restaurant.name
        //rating bar need a float it cannot handles Integer
        viewHolder.ratingBar.rating = restaurant.rating!!.toFloat()
        // passing the "restaurant" object to func of interface once it is clicked to make sure the data flow from one layout to other
        viewHolder.itemView.setOnClickListener{
            itemListener.restaurantSelected(restaurant)
        }
    }


    // This will return the number of restaurants in the database
    override fun getItemCount(): Int {
        return restaurants.size
    }

    interface RestaurantItemListener{
        fun restaurantSelected(restaurant : Restaurant)
    }

}