package com.example.learningblibli.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.learningblibli.data.source.remote.Resource
import com.example.learningblibli.data.source.remote.network.ApiConfig
import com.example.learningblibli.databinding.FragmentDetailBinding
import com.example.learningblibli.domain.model.Movie
import com.example.learningblibli.ui.ViewModelFactory

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idMovie = arguments?.getInt(ID_MOVIE)

        val detailViewModel =
            ViewModelProvider(this, ViewModelFactory())[DetailViewModel::class.java]

        idMovie?.let {
            detailViewModel.setIdMovie(it)
        }
        detailViewModel.detailMovie.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error->{
                    Toast.makeText(context,it.message?:"ERROR",Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading->{
                }
                is Resource.Success->{
                    showDetailMovie(it.data)
                }
            }
        }

    }

    private fun showDetailMovie(data: Movie?) {
        data?.let {
            Glide.with(this)
                .load(ApiConfig.BASE_IMAGE_URL + it.posterPath)
                .into(binding.ivPoster)
            binding.tvDescription.text =it.overview
            binding.toolbarDetail.title =it.title

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    companion object{
        const val ID_MOVIE = "ID_MOVIE"
    }
}