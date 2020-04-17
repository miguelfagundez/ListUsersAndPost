package com.devproject.miguelfagundez.kubratest.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.Menu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.devproject.miguelfagundez.kubratest.R
import com.devproject.miguelfagundez.kubratest.adapter.PostAdapter
import com.devproject.miguelfagundez.kubratest.adapter.UserAdapter
import com.devproject.miguelfagundez.kubratest.model.user.UserResponse
import com.devproject.miguelfagundez.kubratest.utils.Constants
import com.devproject.miguelfagundez.kubratest.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : UserViewModel
    private lateinit var observerSearch: Observer<List<UserResponse>>

    /*******************************************************************
     * RecyclerView adapter
     * *****************************************************************/
    var adapter = UserAdapter(mutableListOf())

    /*******************************************************************
     * Fragment definition and initialization
     * *****************************************************************/
    private val postDetailsFragment = PostFragment()

    /*****************************************************************
     * Broadcast receiver implementation
     * *****************************************************************/
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // the broadcast sent the id and we catch it
            // open movie details in a fragment
            var id = 0
            var name = ""
            intent?.getIntExtra(Constants.KEY_VALUE_FRAGMENT_ID,0)?.let {
                id = it
            }
            intent?.getStringExtra(Constants.KEY_VALUE_FRAGMENT_NAME)?.let {
                name = it
            }

            openUserPosts(id, name)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Registering the broadcast
        registerReceiver(receiver, IntentFilter(Constants.KEY_VALUE_FILTER))

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        observerSearch = Observer {listUser ->
            adapter = UserAdapter(listUser)
            recycler_view.layoutManager =LinearLayoutManager(this)
            recycler_view.adapter = adapter
        }

        viewModel.getNextListOfUsers().observe(this, observerSearch)
    }

    //*************************************************
    // Menu inflater - Action Bar
    //*************************************************
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun openUserPosts(userId: Int, name:String) {
        val bundle = Bundle()
        bundle.putString(Constants.KEY_VALUE_BUNDLE, userId.toString())
        bundle.putString(Constants.KEY_VALUE_BUNDLE_USER_NAME, name)

        postDetailsFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out,
                R.anim.slide_in,
                R.anim.slide_out
            )
            .replace(R.id.fragment_view, postDetailsFragment)
            .addToBackStack(postDetailsFragment.tag)
            .commit()

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
