package com.suitmedia.suitmediaapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suitmedia.suitmediaapp.data.UserItem
import com.suitmedia.suitmediaapp.data.UserRepository

class ChooseViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _users: MutableLiveData<LiveData<PagingData<UserItem>>> = MutableLiveData()

    val users: LiveData<PagingData<UserItem>>
        get() = _users.value ?: userRepository.getUser().cachedIn(viewModelScope)

    fun fetchUsers() {
        Log.d("ChooseViewModel", "Triggered")
        _users.value = userRepository.getUser().cachedIn(viewModelScope)
    }
}