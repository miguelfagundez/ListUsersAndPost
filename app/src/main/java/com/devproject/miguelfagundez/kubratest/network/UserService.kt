package com.devproject.miguelfagundez.kubratest.network

import com.devproject.miguelfagundez.kubratest.model.post.PostResponse
import com.devproject.miguelfagundez.kubratest.model.user.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/****************************************
 * UserService interface.
 * Defining the services for this user app
 * @author Miguel Fagundez
 * @since 04/17/2020
 * @version 1.0
 **************************************/
interface UserService {

    // Search 10 Users
    @GET("/users")
    fun getNextListOfUsers(): Call<List<UserResponse>>

    // Searching user posts
    @GET("/users/{userId}/posts")
    fun getUserPosts(
        @Path("userId") userId : String
    ): Call<List<PostResponse>>
}