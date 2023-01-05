package com.example.learningblibli.feature_search.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningblibli.core.adapter.MealVerticalAdapter
import com.example.learningblibli.core.base.BaseFragment
import com.example.learningblibli.feature_search.R
import com.example.learningblibli.feature_search.databinding.FragmentSearchBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var mealVerticalAdapter: MealVerticalAdapter? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchViewModel()
        setupMealVerticalAdapter()
        setupMealVerticalRecyclerView()
        getObserverSearchMeal()
        getObserverLoading()
        getSharedFlowError()
    }

    private fun getSharedFlowError() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            searchViewModel.error.collectLatest {
                showToast(it)
            }
        }
    }

    private fun getObserverLoading() {
        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.loading.collectLatest {
                if (it) showLoadingDialog()
                else hideLoadingDialog()
            }
        }
    }

    private fun setupSearchViewModel() {
        searchViewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateMenu(menu, menuInflater)
        menuInflater.inflate(R.menu.search_menu, menu)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.search) {
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
        searchViewModel.meals.observe(viewLifecycleOwner) {
            mealVerticalAdapter?.setData(it)
        }
    }

    private fun setupMealVerticalRecyclerView() {
        with(binding.rvMeal) {
            mealVerticalAdapter?.let {
                adapter = mealVerticalAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    private fun setupMealVerticalAdapter() {
        val newMealVerticalAdapter =
            MealVerticalAdapter()
        newMealVerticalAdapter.onItemClick = {
            val request = NavDeepLinkRequest.Builder
                .fromUri("https://www.learningblibli.com/detail_fragment/${it.idMeal}".toUri())
                .build()
            findNavController().navigate(request)

        }
        mealVerticalAdapter = newMealVerticalAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}