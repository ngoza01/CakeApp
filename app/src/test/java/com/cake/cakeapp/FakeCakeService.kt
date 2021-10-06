package com.cake.cakeapp

import com.cake.cakeapp.modules.cakelist.Cake
import com.cake.cakeapp.service.ServiceHelper
import retrofit2.Response

class FakeCakeService:ServiceHelper {
    override suspend fun getCakes(): Response<List<Cake>> {
        val cakes =  listOf<Cake>(Cake("Birthday Cake","Cake used for Birthday","https://www.cakes.com/birthday.png"))

        return  Response.success(cakes)

    }
}