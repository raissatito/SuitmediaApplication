package com.suitmedia.suitmediaapp.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import java.util.concurrent.Executors

class UserRepository(private val apiService: ApiService) {
    companion object {

        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val apiService = ApiConfig.getApiService()
                    instance = UserRepository(apiService)
                }
                return instance as UserRepository
            }

        }
    }

    fun getUser(): LiveData<PagingData<UserItem>> {
        Log.v("UserRepository", "getUser() triggered")
        return Pager(
            config = PagingConfig(
                pageSize = 9,
                initialLoadSize = 9
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}