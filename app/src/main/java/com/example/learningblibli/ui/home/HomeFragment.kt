package com.example.learningblibli.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningblibli.MyApplication
import com.example.learningblibli.R
import com.example.learningblibli.base.BaseFragment
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.sharedpreferences.AppSharedPreferences
import com.example.learningblibli.databinding.FragmentHomeBinding
import com.example.learningblibli.ui.adapter.MealAdapter
import com.example.learningblibli.ui.detail.DetailFragment
import com.example.learningblibli.ui.login.AuthViewModel
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private val authViewModel: AuthViewModel by activityViewModels {
        factory
    }
    private var mealAdapter: MealAdapter? = null
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: AppSharedPreferences
    private var isActive = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity
        (requireActivity().application as MyApplication).appComponent.inject(this)
        setupHomeViewModel()
        setUpMealAdapter()
        setUpMealRecyclerView()
        getObserveMeals()
        getObserveCurrentUser()
        getObserveTheme()
    }

    private fun getObserveTheme() {
        homeViewModel.getThemeSettings().observe(viewLifecycleOwner){
            if (it) {
                isActive=it
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                isActive=it
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun getObserveCurrentUser() {
//        authViewModel.currentUser.observe(viewLifecycleOwner){
//            if(it==null){
//                val current = findNavController().graph.id
//                val navOptions = NavOptions.Builder()
//                    .setPopUpTo(current,true)
//                    .build()
//                findNavController().navigate(R.id.loginFragment,null,navOptions)
//            }
//        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.main,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

        if(menuItem.itemId==R.id.menu_logout){
            authViewModel.logout()
            return true
        }
        else if(menuItem.itemId==R.id.action_settings){
            homeViewModel.saveThemeSetting(!isActive)
            return true
        }

        return super.onMenuItemSelected(menuItem)
    }


    private fun setupHomeViewModel() {
        homeViewModel =
            ViewModelProvider(this,factory)[HomeViewModel::class.java]
    }

    private fun setUpMealRecyclerView() {
        with(binding.rvMeal){
            mealAdapter?.let {
                this.adapter=mealAdapter
                this.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            }
        }
    }

    private fun getObserveMeals() {
        homeViewModel.getMeals()
        homeViewModel.meals.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    mealAdapter?.run {
                        this.setData(it.data)
                    }
                }
                is Resource.Error -> {

                }
            }
        }

    }


    private fun setUpMealAdapter() {
        if(mealAdapter==null){
            val newMealAdapter = MealAdapter()
            newMealAdapter.onItemClick = {
                val bundle = bundleOf(
                    DetailFragment.MEAL to it
                )
                findNavController().navigate(R.id.detailFragment,bundle)
            }
            mealAdapter=newMealAdapter
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}