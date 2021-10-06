package com.cake.cakeapp.repository

import com.cake.cakeapp.modules.cakelist.Cake
import retrofit2.Response

interface CakeRepository {
    suspend fun getCakes(): Response<List<Cake>>
}