package com.example.firebaserestaurantrater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebaserestaurantrater.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //this line of code will bring all the ids from xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Validating the user responses
        binding.button.setOnClickListener{
            if(binding.restaurantEditText.text.toString().isNotEmpty() && binding.spinner.selectedItemPosition > 0){
                //creating the instance of the restaurant
                val restaurant = Restaurant()
                restaurant.name = binding.restaurantEditText.text.toString()
                restaurant.rating =  binding.spinner.selectedItem.toString().toInt()


                //Storing the restaurant in Firebase - Store
                //1. get an ID from Firestore(Firestore only does the storing and retrieving
                val db = FirebaseFirestore.getInstance().collection("restaurants")
                restaurant.id = db.document().id

                //2. store the restaurant as a document - in MySql document can be considered as row
                // !! this is null protection built by kotlin
                db.document(restaurant.id!!).set(restaurant)
                    //adding the listeners to make sure user see something on screen when the row is added to data
                    .addOnSuccessListener {
                        Toast.makeText(this,"Restaurant Added", Toast.LENGTH_LONG).show()
                        binding.restaurantEditText.setText("")
                        binding.spinner.setSelection(0)
                        startActivity(Intent(this, GridRecyclerActivity::class.java))
                    }
                    //listener to make sure that in case of failure user still see messages
                    .addOnFailureListener{
                        Toast.makeText(this,"Restaurant name and rating required", Toast.LENGTH_LONG).show()
                        var message = it.localizedMessage
                        message.let{
                            Log.i("DB Message", message)
                        }
                    }
            }
            else{
                Toast.makeText(this,"Restaurant name and ratings are required", Toast.LENGTH_LONG).show()
            }
        }
    }
}