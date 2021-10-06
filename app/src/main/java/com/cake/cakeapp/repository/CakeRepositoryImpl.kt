package com.cake.cakeapp.repository

import com.cake.cakeapp.modules.cakelist.Cake
import com.cake.cakeapp.service.ServiceHelper
import retrofit2.Response
import javax.inject.Inject

class CakeRepositoryImpl @Inject constructor(
    private val serviceHelper: ServiceHelper
):CakeRepository {
    override suspend fun getCakes(): Response<List<Cake>> = serviceHelper.getCakes()
}