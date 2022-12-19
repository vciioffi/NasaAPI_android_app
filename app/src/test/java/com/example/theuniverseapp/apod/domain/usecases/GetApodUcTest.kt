package com.example.theuniverseapp.apod.domain.usecases

import com.example.theuniverseapp.apod.data.repository.ApodRepository
import com.example.theuniverseapp.apod.domain.model.ApodModel
import org.junit.Before
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetApodUcTest{

    @RelaxedMockK
    private lateinit var apodRepository: ApodRepository

    lateinit var getApodUc: GetApodUc

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getApodUc = GetApodUc(apodRepository)
    }

    @Test
    fun `when the api doesn't return an apod then get values from database`() = runBlocking{
        //given
        val apodModel:ApodModel = ApodModel("","","","","","")
        coEvery { apodRepository.getApodResult() } returns apodModel
        //when
        getApodUc()
        //then
        assert(apodModel == getApodUc())

    }
}