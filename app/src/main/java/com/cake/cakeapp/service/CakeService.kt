package com.cake.cakeapp.service

import com.cake.cakeapp.modules.cakelist.Cake
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CakeService {

    @GET("hart88/79a65d27f52cbb74db7df1d200c4212b/raw/ebf57198c7490e42581508f4f40da88b16d784ba/cakeList")
    suspend fun getCakes(): Response<List<Cake>>
}