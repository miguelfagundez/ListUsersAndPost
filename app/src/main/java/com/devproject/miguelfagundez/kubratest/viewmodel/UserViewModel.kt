package com.devproject.miguelfagundez.kubratest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devproject.miguelfagundez.kubratest.model.post.PostResponse
import com.devproject.miguelfagundez.kubratest.model.user.UserResponse
import com.devproject.miguelfagundez.kubratest.network.UserRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/****************************************
 * UserViewModel class.
 * the main ViewModel component of MVVM
 * @author Miguel Fagundez
 * @since 04/17/2020
 * @version 1.0
 **************************************/
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val listOfUserLiveData = MutableLiveData<List<UserResponse>>()
    private val listOfPostLiveData = MutableLiveData<List<PostResponse>>()

    private val retrofit = UserRetrofit()

    private val TAG_FAILURE = "TAG_USER_MESSAGE_F"
    private val TAG_SUCCESSFULLY = "TAG_USER_MESSAGE"

    fun getNextListOfUsers(): MutableLiveData<List<UserResponse>> {

        retrofit.searchNextListOfUsers().enqueue(
            object : Callback<List<UserResponse>>{
                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    Log.d(TAG_FAILURE, t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ) {
                    response.body()?.let {results ->
                        listOfUserLiveData.value = results
                    }
                }
            }
        )
        return listOfUserLiveData
    }

    fun getListOfPosts(userId : String) : MutableLiveData<List<PostResponse>>{

        retrofit.getUserPosts(userId).enqueue(
            object : Callback<List<PostResponse>>{
                override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                    Log.d(TAG_FAILURE, t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<List<PostResponse>>,
                    response: Response<List<PostResponse>>
                ) {
                    response.body()?.let {results ->
                        listOfPostLiveData.value = results
                    }
                }

            }
        )
        return listOfPostLiveData
    }
}