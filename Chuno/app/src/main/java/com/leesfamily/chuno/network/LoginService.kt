package com.leesfamily.chuno.network

import com.leesfamily.chuno.network.data.DataForm
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @POST("kakao/mobile/login")
    fun login(@Body token: String): Call<DataForm>

    @Headers("Content-Type:text/plain")
    @POST("kakao/tokenConfirm")
    fun tokenConfirm(@Body token: RequestBody): Call<Int>


    fun getUserData(@Header("Authorization") auth: String): Call<DataForm>

//
//    @FormUrlEncoded
//    @GET("/posts")
//    fun getData(@Query("userId") id: String): Call<List<User>>
//
//    @FormUrlEncoded
//    @POST("/posts")
//    fun postData(@FieldMap param: HashMap<String, Object>): Call<User>
}