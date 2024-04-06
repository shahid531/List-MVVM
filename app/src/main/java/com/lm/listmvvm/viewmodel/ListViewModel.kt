package com.lm.listmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lm.listmvvm.error.Resource
import com.lm.listmvvm.model.ListResponseModel
import com.lm.listmvvm.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _listData = MutableLiveData<Resource<ListResponseModel>>()
    val listData get() = _listData
    fun getData() {
        viewModelScope.launch {
            repository.getData()
                .onStart {
                    _listData.value = Resource.Loading()
                }
                .catch {
                    _listData.value = Resource.Error("something went wrong...")
                }
                .collect {
                    _listData.value = Resource.Success(it.body())
                }
        }
    }
}