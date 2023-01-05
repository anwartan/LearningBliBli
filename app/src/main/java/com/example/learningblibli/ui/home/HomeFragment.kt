package com.example.learningblibli.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningblibli.R
import com.example.learningblibli.core.base.BaseFragment
import com.example.learningblibli.databinding.FragmentHomeBinding
import com.example.learningblibli.feature_detail.ui.DetailFragment
import com.example.learningblibli.lib_model.model.Meal
import com.example.learningblibli.ui.adapter.MealAdapter
import com.example.learningblibli.ui.login.AuthViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private val authViewModel: AuthViewModel by activityViewModels {
        factory
    }
    private lateinit var mealAdapter: MealAdapter
    private lateinit var recommendationMealAdapter: MealAdapter
    private lateinit var newMealAdapter: MealAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHomeViewModel()
        setUpMealAdapter()
        setUpMealRecyclerView()
        getObserveMeals()
        getSharedFlowError()
    }

    private fun getSharedFlowError() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            homeViewModel.error.collectLatest {
                showToast(it)
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.main, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_logout -> {
                authViewModel.logout()
                true
            }
            R.id.action_settings -> {
                findNavController().navigate(R.id.settingFragment)
                true
            }
            else -> super.onMenuItemSelected(menuItem)
        }
    }

    private fun setupHomeViewModel() {
        homeViewModel =
            ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun setUpMealRecyclerView() {
        with(binding.rvMeal) {
            adapter = mealAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        with(binding.rvRecommendationMeals) {
            adapter = recommendationMealAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        with(binding.rvNewMeals) {
            adapter = newMealAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun getObserveMeals() {
        homeViewModel.getMeals()
        homeViewModel.getRecommendationMeals()
        homeViewModel.getNewMeals()
        homeViewModel.meals.observe(viewLifecycleOwner) {
            mealAdapter.setData(it)
        }

        homeViewModel.recommendationMeal.observe(viewLifecycleOwner) {
            recommendationMealAdapter.setData(it)
        }
        homeViewModel.newMeals.observe(viewLifecycleOwner) {
            newMealAdapter.setData(it)
        }

    }

    private fun setUpMealAdapter() {

        val onItemClick: (Meal) -> Unit = {

            val request = NavDeepLinkRequest.Builder
                .fromUri("https://www.learningblibli.com/detail_fragment/${it.idMeal}".toUri())
                .build()
            findNavController().navigate(request)
        }
        mealAdapter = MealAdapter()
        mealAdapter.onItemClick = onItemClick

        recommendationMealAdapter = MealAdapter()
        recommendationMealAdapter.onItemClick = onItemClick

        newMealAdapter = MealAdapter()
        newMealAdapter.onItemClick = onItemClick

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}