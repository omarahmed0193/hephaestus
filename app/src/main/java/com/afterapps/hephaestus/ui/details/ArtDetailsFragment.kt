package com.afterapps.hephaestus.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.afterapps.hephaestus.databinding.FragmentArtDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtDetailsFragment : Fragment() {

    private val viewModel: ArtDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArtDetailsBinding.inflate(inflater)

        initViews(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments?.let { ArtDetailsFragmentArgs.fromBundle(it) }
        val artEntry = args?.artEntry
        artEntry?.let { viewModel.onArtEntryArgsReady(it) }
    }

    private fun initViews(binding: FragmentArtDetailsBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

}