package com.afterapps.hephaestus.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afterapps.hephaestus.databinding.FragmentArtImageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtImageFragment : Fragment() {

    private val viewModel: ArtImageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArtImageBinding.inflate(inflater)

        initViews(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments?.let { ArtImageFragmentArgs.fromBundle(it) }
        val artImageUrl = args?.artImageUrl
        artImageUrl?.let { viewModel.onArtImageUrlArgsReady(it) }
    }

    private fun initViews(binding: FragmentArtImageBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }
}