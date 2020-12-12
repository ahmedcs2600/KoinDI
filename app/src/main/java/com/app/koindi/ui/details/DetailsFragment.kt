package com.app.koindi.ui.details

import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.app.koindi.R
import com.app.koindi.base.AppFragment
import com.app.koindi.databinding.FragmentDetailsBinding
import com.app.koindi.exceptions.NoBundleArgumentFound
import com.app.koindi.ktx.horizontalLayoutManager
import com.app.koindi.ktx.showToast
import com.app.koindi.models.State
import com.app.koindi.models.responses.MovieModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.IllegalStateException
import kotlin.contracts.ExperimentalContracts


class DetailsFragment : AppFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val arguments: DetailsFragmentArgs by navArgs()

    private val mDetailsViewModel: DetailsViewModel by viewModel { parametersOf(mArgument)}

    private lateinit var mAdapter: ActorsRecyclerAdapter

    private val mArgument : MovieModel
    get() = arguments.item ?: throw NoBundleArgumentFound("Movie item argument is Null")

    @ExperimentalContracts
    override fun initializeComponents() {
        binding.viewModel = mDetailsViewModel
        setUpAdapter()
        bindViewModel()
    }

    private fun setUpAdapter() {
        with(binding.recyclerActors) {
            adapter = ActorsRecyclerAdapter().also { mAdapter = it }
            layoutManager = horizontalLayoutManager()
        }
    }

    private fun bindViewModel() {
        mDetailsViewModel.actorList.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> {
                    binding.isLoading = true
                }
                is State.Error -> {
                    binding.isLoading = false
                    showToast(state.message)
                }
                is State.Success -> {
                    binding.isLoading = false
                    val data = state.data?.actorsList ?: throw IllegalStateException("Actors Data is Null")
                    mAdapter.updateList(data)
                }
            }
        })
    }

}