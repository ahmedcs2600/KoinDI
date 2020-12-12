package com.app.koindi.ui.home

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.koindi.R
import com.app.koindi.base.AppFragment
import com.app.koindi.databinding.FragmentHomeBinding
import com.app.koindi.ktx.showToast
import com.app.koindi.models.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : AppFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val mHomeViewModel by viewModel<HomeViewModel>()

    private lateinit var mAdapter: HomeAdapter

    override fun initializeComponents() {
        setUpAdapter()
        bindViewModel()
    }


    private fun setUpAdapter() {
        with(binding.recyclerMoviesList) {
            adapter = HomeAdapter { movieItem ->
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieItem))
            }.also {
                mAdapter = it
            }
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        mHomeViewModel.moviesList.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> binding.isLoading = true
                is State.Error -> {
                    binding.isLoading = false
                    showToast(state.message)
                }
                is State.Success -> {
                    binding.isLoading = false
                    state.data?.results?.let { result ->
                        mAdapter.updateList(result)
                    }
                }
            }
        })
    }
}