package com.example.firebaserestaurantrater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// This "RecyclerViewAdapter" have to arguments or instance variables "context","comments"
// They extend "RecyclerView.Adapter" class from the import
class CommentViewAdapter (val context : Context,
                          val comments : List<Comment>) : RecyclerView.Adapter<CommentViewAdapter.CommentViewHolder>()
{
    /**
     * This "view holder" is what connects the actual view elements in the item_comment.xml file with this Adapter class
     */
    inner class CommentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val userNameTextView = itemView.findViewById<TextView>(R.id.usernameTextView)
        val commentTextView = itemView.findViewById<TextView>(R.id.commentTextView)
    }
    // Combining the child xml with parent xml, to make sure that parent xml have "n" numbers of element required.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }
    // Here we are assigning the data to xml to make sure that specific task is achieved, like fetching comments from FireStore
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        with (holder) {
            userNameTextView.text = comment.userName
            commentTextView.text = comment.comment
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}