package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.data.repository.FakeFirebaseUser
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCurrentUserUseCaseTest{

    @Mock
    private lateinit var authRepository: AuthRepository

    @InjectMocks
    private lateinit var getCurrentUserUseCase: GetCurrentUserUseCase

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)

    }

    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(authRepository)
    }

    @Test
    fun getCurrentUserUseCaseTest(){
        val dummyData =FakeFirebaseUser()
        Mockito.`when`(authRepository.getCurrentUser()).thenReturn(dummyData)
        val data = getCurrentUserUseCase.invoke()
        Assert.assertNotNull(data)
        Assert.assertEquals(dummyData.uid,data?.uid)
        Mockito.verify(authRepository).getCurrentUser()
    }
}