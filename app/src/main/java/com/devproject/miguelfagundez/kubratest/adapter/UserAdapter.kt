package com.devproject.miguelfagundez.kubratest.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devproject.miguelfagundez.kubratest.R
import com.devproject.miguelfagundez.kubratest.model.user.UserResponse
import com.devproject.miguelfagundez.kubratest.utils.Constants

/****************************************
 * UserAdapter class.
 * Adapter class for our recycler view
 * @author Miguel Fagundez
 * @since 04/17/2020
 * @version 1.0
 **************************************/
class UserAdapter(private var userList: List<UserResponse>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    /*****************************************************************
     * onCreateViewHolder Method
     *  Inflate the view using the parent and the custom item layout
     * Create the view of each element in the recycler view
     * *****************************************************************/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
    }

    /*****************************************************************
     * getItemCount Method
     * return the number of elements of the recycler view
     * *****************************************************************/
    override fun getItemCount() = userList.size

    /*****************************************************************
     * onBindViewHolder Method
     *  With the holder created in onCreateViewHolder
     *  include each data into the layout elements
     *  set a listener in order to detect a click
     * Connect the data with the layout elements in the recycler view
     * *****************************************************************/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            userName.text = userList.get(position).name
            userAddress.text = "${userList.get(position).address.street}, ${userList.get(position).address.city} " +
                    "${userList.get(position).address.zipcode}"

            // Sending a broadcast receiver for searching user posts
            itemView.setOnClickListener {
                val intent = Intent(Constants.KEY_VALUE_FILTER)
                intent.putExtra(Constants.KEY_VALUE_FRAGMENT_ID, userList.get(position).id)
                intent.putExtra(Constants.KEY_VALUE_FRAGMENT_NAME, userList.get(position).name)
                itemView.context.sendBroadcast(intent)
            }
        }

    }

    /*****************************************************************
     * ViewHolder Method
     * Create a inner class with hold our layout elements
     * *****************************************************************/
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val userName = view.findViewById<TextView>(R.id.tvUserName)
        val userAddress = view.findViewById<TextView>(R.id.tvUserAddress)
    }
}