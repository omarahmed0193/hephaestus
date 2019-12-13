package com.afterapps.hephaestus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.afterapps.hephaestus.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)

        initViews(binding)

        initObservers(binding)

        return binding.root
    }

    private fun initViews(binding: FragmentHomeBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.artEntriesRv.adapter = ArtEntriesAdapter()
    }

    private fun initObservers(binding: FragmentHomeBinding) {

        // Update the adapter when network status change to show/remove the loading item
        viewModel.networkStatus.observe(
            viewLifecycleOwner,
            Observer { (binding.artEntriesRv.adapter as ArtEntriesAdapter).setNetworkState(it) })

        // Update the view model with new page to start fetching data
        viewModel.currentPageNumber.observe(
            viewLifecycleOwner,
            Observer { it?.let { pageNumber -> viewModel.onPageNumberChanged(pageNumber) } })
    }
}