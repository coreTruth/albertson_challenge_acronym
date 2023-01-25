package com.joshua.test.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joshua.test.R
import com.joshua.test.databinding.FragmentMainBinding
import com.joshua.test.ui.common.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val mainVm by viewModels<MainViewModel>()
    lateinit var binding: FragmentMainBinding

    @Inject lateinit var adapter: LongFormAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        initUi()
        initObserver()
    }

    private fun initUi() = with(binding) {
        lifecycleOwner = viewLifecycleOwner
        vm = mainVm
        recyclerviewLongForms.adapter = adapter
        adapter.setOnItemClickListener {
            val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            findNavController().navigate(direction)
        }
        buttonSubmit.setOnClickListener {
            it.hideKeyboard()
            mainVm.getAcronyms()
        }
    }

    private fun initObserver() {
        mainVm.uiState.observe(viewLifecycleOwner, ::renderUI)
        mainVm.validationError.observe(viewLifecycleOwner) {
            binding.inputLayoutAcronym.error = it
        }
    }

    private fun renderUI(uiState: MainViewModel.MainUiState) = with(binding) {
        when (uiState) {
            is MainViewModel.MainUiState.Loading -> {
                buttonSubmit.isEnabled = false
                progressBar.isVisible = true
                textMessage.isVisible = false
            }
            is MainViewModel.MainUiState.Error -> {
                buttonSubmit.isEnabled = true
                progressBar.isVisible = false
                textMessage.isVisible = true
                textMessage.text = uiState.msg
            }
            is MainViewModel.MainUiState.Success -> {
                buttonSubmit.isEnabled = true
                progressBar.isVisible = false
                textMessage.isVisible = false
                if (uiState.longFormList.isNotEmpty()) {
                    textMessage.isVisible = false
                } else {
                    textMessage.isVisible = true
                    textMessage.setText(R.string.no_acronyms_long_form_found)
                }
                adapter.submitList(uiState.longFormList)
            }
        }
    }
}
