package com.example.learningblibli.ui.home

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
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.databinding.FragmentHomeBinding
import com.example.learningblibli.ui.ViewModelFactory
import com.example.learningblibli.ui.adapter.MealAdapter
import com.example.learningblibli.ui.detail.DetailFragment

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private var mealAdapter: MealAdapter? = null
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

        homeViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(requireContext()))[HomeViewModel::class.java]


        setUpMealAdapter()
        setUpMealRecyclerView()
        getObserveMeals()
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