package com.cake.cakeapp.service

import com.cake.cakeapp.modules.cakelist.Cake
import retrofit2.Response
import javax.inject.Inject

class ServiceHelperImpl  @Inject constructor(
    private val cakeService: CakeService
):ServiceHelper {
    override suspend fun getCakes(): Response<List<Cake>> = cakeService.getCakes()
}