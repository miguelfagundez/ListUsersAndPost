package com.devproject.miguelfagundez.kubratest.network

import com.devproject.miguelfagundez.kubratest.model.post.PostResponse
import com.devproject.miguelfagundez.kubratest.model.user.UserResponse
import com.devproject.miguelfagundez.kubratest.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/****************************************
 * UserRetrofit class.
 * @author Miguel Fagundez
 * @since 04/17/2020
 * @version 1.0
 **************************************/
class UserRetrofit {

    private val retrofit : Retrofit
    private val service : UserService

    init{
        retrofit = createRetrofit()
        service = createService(retrofit)
    }

    private fun createService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    //Create the Retrofit instance
    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    fun searchNextListOfUsers() : Call<List<UserResponse>> {
        return service.getNextListOfUsers()
    }

    fun getUserPosts(userId : String) : Call<List<PostResponse>>{
        return service.getUserPosts(userId)
    }
}