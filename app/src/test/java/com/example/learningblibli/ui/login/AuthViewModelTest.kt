package com.example.learningblibli.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.learningblibli.core.data.source.remote.Resource
import com.example.learningblibli.core.domain.usecase.GetCurrentUserUseCase
import com.example.learningblibli.core.domain.usecase.LoginByFirebaseUseCase
import com.example.learningblibli.core.domain.usecase.LogoutByFirebaseUseCase
import com.example.learningblibli.core.domain.usecase.RegisterByFirebaseUseCase
import com.example.learningblibli.utils.DataDummy
import com.example.learningblibli.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    @Mock
    private lateinit var loginByFirebaseUseCase: LoginByFirebaseUseCase

    @Mock
    private lateinit var registerByFirebaseUseCase: RegisterByFirebaseUseCase

    @Mock
    private lateinit var getCurrentUserUseCase: GetCurrentUserUseCase

    @Mock
    private lateinit var logoutByFirebaseUseCase: LogoutByFirebaseUseCase

    @InjectMocks
    private lateinit var authViewModel: AuthViewModel
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private val dispatcher = UnconfinedTestDispatcher()
    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
        Mockito.verifyNoMoreInteractions(getCurrentUserUseCase)
        Mockito.verifyNoMoreInteractions(loginByFirebaseUseCase)
        Mockito.verifyNoMoreInteractions(registerByFirebaseUseCase)
        Mockito.verifyNoMoreInteractions(logoutByFirebaseUseCase)

    }

    @Test
    fun getCurrentUser() {
        val dummy = DataDummy.generateUser()
        Mockito.`when`(getCurrentUserUseCase()).thenReturn(dummy)
        authViewModel.getCurrentUser()
        val actual = authViewModel.currentUser.getOrAwaitValue()
        Assert.assertNotNull(actual)
        Assert.assertEquals(dummy.uid,actual?.uid)
        Mockito.verify(getCurrentUserUseCase).invoke()
    }



    @Test
    fun loginUser() = runTest {
        val email = "admin@gmail.com"
        val password = "123456"
        val dummyData = DataDummy.generateUser()
        Mockito.`when`(getCurrentUserUseCase()).thenReturn(dummyData)
        Mockito.`when`(loginByFirebaseUseCase(email,password)).thenReturn(Resource.Success(dummyData))
        authViewModel.loginUser(email,password)
        val actual = authViewModel.currentUser.getOrAwaitValue()
        Assert.assertNotNull(actual)
        Assert.assertEquals(dummyData.uid,actual?.uid)
        Mockito.verify(loginByFirebaseUseCase).invoke(email,password)
        Mockito.verify(getCurrentUserUseCase).invoke()

    }


    @Test
    fun register() = runTest {
        val email = "admin@gmail.com"
        val password = "123456"
        val dummyData = DataDummy.generateUser()
        Mockito.`when`(registerByFirebaseUseCase(email,password)).thenReturn(Resource.Success(dummyData))
        authViewModel.register(email,password)
        val actual = authViewModel.signupFlow.getOrAwaitValue()
        Assert.assertTrue(actual is Resource.Success)
        Assert.assertEquals(dummyData.uid,actual.data?.uid)
        Mockito.verify(registerByFirebaseUseCase).invoke(email,password)

    }

    @Test
    fun logout()  = runTest(dispatcher){
        Mockito.`when`(getCurrentUserUseCase()).thenReturn(null)
        authViewModel.logout()
        delay(1000)
        val actual = authViewModel.currentUser.getOrAwaitValue()
        Assert.assertNull(actual)
        Mockito.verify(logoutByFirebaseUseCase).invoke()
        Mockito.verify(getCurrentUserUseCase).invoke()

    }
}