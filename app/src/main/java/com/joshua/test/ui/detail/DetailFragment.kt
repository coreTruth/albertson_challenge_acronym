package com.joshua.test.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.joshua.test.R
import com.joshua.test.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(FragmentDetailBinding.bind(view)) {
            lifecycleOwner = viewLifecycleOwner
            longForm = args.longForm
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            recyclerviewVariations.adapter = VariationAdapter().apply {
                submitList(args.longForm.variations)
            }
        }
    }
}

