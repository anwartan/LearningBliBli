package com.example.learningblibli.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningblibli.R
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.databinding.FragmentHomeBinding
import com.example.learningblibli.ui.ViewModelFactory
import com.example.learningblibli.ui.adapter.MovieAdapter
import com.example.learningblibli.ui.detail.DetailFragment

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

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


        val homeViewModel =
            ViewModelProvider(this, ViewModelFactory())[HomeViewModel::class.java]
        val movieAdapter =MovieAdapter()

        movieAdapter.onItemClick = {
            val bundle = bundleOf(
                DetailFragment.ID_MOVIE to it.id
            )
           findNavController().navigate(R.id.detailFragment,bundle)
        }
        //kenapa pada penggunaan liniear layout parameter context not null tapi bisa terima context null able
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        with(binding.rvMovie){
            layoutManager  = linearLayoutManager
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        //base progress bar now playing

        homeViewModel.movie.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    movieAdapter.setData(it.data)
                }
                is Resource.Error -> {

                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}