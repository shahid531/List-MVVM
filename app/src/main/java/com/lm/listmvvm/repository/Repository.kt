package com.lm.listmvvm.repository

import com.lm.listmvvm.model.ListResponseModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getData(): Flow<Response<ListResponseModel>>
}