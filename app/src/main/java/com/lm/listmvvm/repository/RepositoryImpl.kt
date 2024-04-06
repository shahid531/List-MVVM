package com.lm.listmvvm.repository

import com.lm.listmvvm.model.ListResponseModel
import com.lm.listmvvm.service.ListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val listApi: ListApi) : Repository {
    override suspend fun getData(): Flow<Response<ListResponseModel>> {
        return flow {
            emit(listApi.getListData())
        }.flowOn(Dispatchers.IO)
    }
}