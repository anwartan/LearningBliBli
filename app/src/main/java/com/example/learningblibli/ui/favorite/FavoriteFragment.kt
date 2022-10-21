package com.example.learningblibli.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningblibli.R
import com.example.learningblibli.base.BaseFragment
import com.example.learningblibli.databinding.FragmentFavoriteBinding
import com.example.learningblibli.ui.ViewModelFactory
import com.example.learningblibli.ui.adapter.MealVerticalAdapter
import com.example.learningblibli.ui.detail.DetailFragment

class FavoriteFragment :BaseFragment() {

    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!
    private lateinit var favoriteViewModel: FavoriteViewModel
    private var mealVerticalAdapter:MealVerticalAdapter? = null
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

    private fun setupFavoriteViewModel() {
        favoriteViewModel = ViewModelProvider(this,
            ViewModelFactory.getInstance(requireContext())
        )[FavoriteViewModel::class.java]
    }
    private fun setupMealVerticalAdapter() {
        val newMealVerticalAdapter = MealVerticalAdapter()
        newMealVerticalAdapter.onItemClick={
            val bundle = bundleOf(
                DetailFragment.MEAL to it
            )
            findNavController().navigate(R.id.detailFragment,bundle)

        }
        mealVerticalAdapter = newMealVerticalAdapter
    }
    private fun getObserverSearchMeal() {
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