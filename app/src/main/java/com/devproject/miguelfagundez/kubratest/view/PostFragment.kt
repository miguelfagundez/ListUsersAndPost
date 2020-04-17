package com.devproject.miguelfagundez.kubratest.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.devproject.miguelfagundez.kubratest.R
import com.devproject.miguelfagundez.kubratest.adapter.PostAdapter
import com.devproject.miguelfagundez.kubratest.adapter.UserAdapter
import com.devproject.miguelfagundez.kubratest.model.post.PostResponse
import com.devproject.miguelfagundez.kubratest.utils.Constants
import com.devproject.miguelfagundez.kubratest.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_post.*
import kotlinx.android.synthetic.main.post_item.*

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {

    private lateinit var viewModel : UserViewModel
    private lateinit var observer : Observer<List<PostResponse>>

    var userName = "User name Unknown"
    /*******************************************************************
     * RecyclerView adapter
     * *****************************************************************/
    var adapter = PostAdapter(mutableListOf(), userName)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        userName = "User name Unknown"

        arguments?.getString(Constants.KEY_VALUE_BUNDLE_USER_NAME)?.let {name ->
            userName = name
        }

        observer = Observer { listPost ->
            adapter = PostAdapter(listPost, userName)
            recycler_view_post.layoutManager = LinearLayoutManager(context)
            recycler_view_post.adapter = adapter

        }

        // If it gets arguments,
        // There is some changes, then getUserPost method is called
        arguments?.getString(Constants.KEY_VALUE_BUNDLE)?.let {userId ->
            viewModel.getListOfPosts(userId).observe(this, observer)
        }


    }


}
