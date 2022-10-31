package com.example.learningblibli.ui.setting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.learningblibli.core.data.sharedPreferences.AppSharedPreferences
import com.example.learningblibli.core.utils.Constants
import com.example.learningblibli.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class SettingViewModelTest {
    @Mock
    private lateinit var appSharedPreferences: AppSharedPreferences

    @InjectMocks
    private lateinit var settingViewModel: SettingViewModel
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = UnconfinedTestDispatcher()
    @Before
    fun before(){
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun after(){
        Mockito.verifyNoMoreInteractions(appSharedPreferences)
        Dispatchers.resetMain()

    }

    @Test
    fun `when Get Theme Setting Should Return True`(){
        val expected = MutableLiveData(true)
        Mockito.`when`(appSharedPreferences.getBooleanAsLiveData(Constants.DARK_MODE)).thenReturn(expected)
        val actual = settingViewModel.getThemeSettings().getOrAwaitValue()
        Assert.assertEquals(true,actual)
        Mockito.verify(appSharedPreferences).getBooleanAsLiveData(Constants.DARK_MODE)
    }

    @Test
    fun `when Get Theme Setting Should Return False`(){
        val expected = MutableLiveData(false)
        Mockito.`when`(appSharedPreferences.getBooleanAsLiveData(Constants.DARK_MODE)).thenReturn(expected)
        val actual = settingViewModel.getThemeSettings().getOrAwaitValue()
        Assert.assertEquals(false,actual)
        Mockito.verify(appSharedPreferences).getBooleanAsLiveData(Constants.DARK_MODE)
    }

    @Test
    fun `Save Theme Setting with Parameter True`() = runTest{
        settingViewModel.saveThemeSetting(true)
        Mockito.verify(appSharedPreferences).putBoolean(Constants.DARK_MODE,true)
    }

    @Test
    fun `Save Theme Setting with Parameter False`() = runTest{
        settingViewModel.saveThemeSetting(false)
        Mockito.verify(appSharedPreferences).putBoolean(Constants.DARK_MODE,false)
    }
}