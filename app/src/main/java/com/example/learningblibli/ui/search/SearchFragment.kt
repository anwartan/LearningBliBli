package com.example.learningblibli.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningblibli.MyApplication
import com.example.learningblibli.R
import com.example.learningblibli.base.BaseFragment
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.databinding.FragmentSearchBinding
import com.example.learningblibli.ui.adapter.MealVerticalAdapter
import com.example.learningblibli.ui.detail.DetailFragment
import javax.inject.Inject

class SearchFragment : BaseFragment() {


    private lateinit var searchViewModel: SearchViewModel
    private var _binding:FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var mealVerticalAdapter:MealVerticalAdapter? = null
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as MyApplication).appComponent.inject(this)

        setupSearchViewModel()
        setupMealVerticalAdapter()
        setupMealVerticalRecyclerView()
        getObserverSearchMeal()
    }

    private fun setupSearchViewModel() {
        searchViewModel = ViewModelProvider(this,factory)[SearchViewModel::class.java]
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.search_menu, menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId==R.id.search){
            (menuItem.actionView as SearchView).apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            searchViewModel.searchMeals(query)
                        }
                        return true
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }
        }
        return super.onMenuItemSelected(menuItem)
    }

    private fun getObserverSearchMeal() {
        searchViewModel.meals.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success->{
                    mealVerticalAdapter?.setData(it.data)
                }
            }
        }
    }

    private fun setupMealVerticalRecyclerView() {
        with(binding.rvMeal){
            mealVerticalAdapter?.let {
                adapter = mealVerticalAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}