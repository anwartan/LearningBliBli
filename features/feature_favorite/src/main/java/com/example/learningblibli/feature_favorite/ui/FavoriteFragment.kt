package com.example.learningblibli.feature_favorite.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningblibli.core.adapter.MealVerticalAdapter
import com.example.learningblibli.core.base.BaseFragment
import com.example.learningblibli.feature_favorite.databinding.FragmentFavoriteBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavoriteFragment : BaseFragment() {

    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!
    private lateinit var favoriteViewModel: FavoriteViewModel
    private var mealVerticalAdapter: MealVerticalAdapter? = null
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavoriteViewModel()
        setupMealVerticalAdapter()
        setupMealVerticalRecyclerView()
        getObserverSearchMeal()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun setupFavoriteViewModel() {
        favoriteViewModel = ViewModelProvider(this,
           factory
        )[FavoriteViewModel::class.java]
    }
    private fun setupMealVerticalAdapter() {
        val newMealVerticalAdapter = MealVerticalAdapter()
        newMealVerticalAdapter.onItemClick={
            val request = NavDeepLinkRequest.Builder
                .fromUri("https://www.learningblibli.com/detail_fragment/${it.idMeal}".toUri())
                .build()
            findNavController().navigate(request)
        }
        mealVerticalAdapter = newMealVerticalAdapter
    }
    private fun getObserverSearchMeal() {
        favoriteViewModel.getMeals()
        favoriteViewModel.meals.observe(viewLifecycleOwner){
            mealVerticalAdapter?.setData(it)
        }
    }

    private fun setupMealVerticalRecyclerView() {
        with(binding.rvMeal){
            mealVerticalAdapter?.let {
                adapter = mealVerticalAdapter
                layoutManager = LinearLayoutManager(context,
                   LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null


    }

}