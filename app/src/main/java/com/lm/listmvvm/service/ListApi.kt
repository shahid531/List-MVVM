package com.lm.listmvvm.service

import com.lm.listmvvm.model.ListResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ListApi {

    @GET("api/users?page=2")
    suspend fun getListData(): Response<ListResponseModel>

}