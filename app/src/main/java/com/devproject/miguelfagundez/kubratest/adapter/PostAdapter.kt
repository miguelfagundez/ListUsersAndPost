package com.devproject.miguelfagundez.kubratest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devproject.miguelfagundez.kubratest.R
import com.devproject.miguelfagundez.kubratest.model.post.PostResponse
import com.devproject.miguelfagundez.kubratest.model.user.UserResponse

class PostAdapter(private var postList: List<PostResponse>, var name:String) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    /*****************************************************************
     * onCreateViewHolder Method
     *  Inflate the view using the parent and the custom item layout
     * Create the view of each element in the recycler view
     * *****************************************************************/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    }

    /*****************************************************************
     * getItemCount Method
     * return the number of elements of the recycler view
     * *****************************************************************/
    override fun getItemCount() = postList.size

    /*****************************************************************
     * onBindViewHolder Method
     *  With the holder created in onCreateViewHolder
     *  include each data into the layout elements
     *  set a listener in order to detect a click
     * Connect the data with the layout elements in the recycler view
     * *****************************************************************/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            userName.text = name
            postTitle.text = postList.get(position).title
            postBody.text = postList.get(position).body
        }
    }

    /*****************************************************************
     * ViewHolder Method
     * Create a inner class with hold our layout elements
     * *****************************************************************/
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val userName = view.findViewById<TextView>(R.id.tvPostUserName)
        val postTitle = view.findViewById<TextView>(R.id.tvPostTitle)
        val postBody = view.findViewById<TextView>(R.id.tvPostBody)
    }

}