package com.cake.cakeapp.service

import com.cake.cakeapp.modules.cakelist.Cake
import retrofit2.Response

interface ServiceHelper {
    suspend fun getCakes(): Response<List<Cake>>
}