package com.example.learningblibli.core.domain.usecase

import com.example.learningblibli.core.data.repository.AuthRepository
import com.example.learningblibli.core.data.repository.FakeFirebaseUser
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.impl.RegisterByFirebaseUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterByFirebaseUseCaseImplTest{

    @Mock
    lateinit var authRepository: AuthRepository
    @InjectMocks
    lateinit var registerByFirebaseUseCaseImpl: RegisterByFirebaseUseCaseImpl

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)

    }

    @After
    fun tearDown(){
        Mockito.verifyNoMoreInteractions(authRepository)
    }

    @Test
    fun registerByFirebaseUseCaseTest() = runTest{
        val email = "admin@gmail.com"
        val password = "123456"
        val dummyData = FakeFirebaseUser()
        Mockito.`when`(authRepository.signUp(email,password)).thenReturn(Resource.Success(dummyData))
        val result  = registerByFirebaseUseCaseImpl(email,password)
        Assert.assertTrue(result is Resource.Success)
        Assert.assertNotNull(result.data)
        Assert.assertEquals(dummyData.uid,result.data?.uid)
        Mockito.verify(authRepository).signUp(email, password)
    }
}