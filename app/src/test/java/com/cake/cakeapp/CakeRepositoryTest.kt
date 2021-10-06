package com.cake.cakeapp

import com.cake.cakeapp.modules.cakelist.Cake
import com.cake.cakeapp.repository.CakeRepository
import com.cake.cakeapp.repository.CakeRepositoryImpl
import com.cake.cakeapp.service.ServiceHelper
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CakeRepositoryTest {

    private  lateinit var repository: CakeRepository
    private  lateinit var fakeCakeService: ServiceHelper
    @Before
    fun setup() {
        fakeCakeService = FakeCakeService()
        repository = CakeRepositoryImpl(fakeCakeService)
    }

    @Test
    fun getCakes_success() = runBlocking{
        val expected =  listOf<Cake>(Cake("Birthday Cake","Cake used for Birthday","https://www.cakes.com/birthday.png"))
        val output =  repository.getCakes()
        val actualResult =  output.body()

        TestCase.assertEquals(expected, actualResult)

        TestCase.assertEquals(1, actualResult?.count())
    }
}