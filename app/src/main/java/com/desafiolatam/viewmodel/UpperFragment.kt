package com.desafiolatam.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.desafiolatam.viewmodel.databinding.FragmentUpperBinding

class UpperFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentUpperBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpperBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.counterUpperStateFlow.collect {contador ->
                binding.tvContadorUpper.text = contador.toString()
            }
        }

/*        binding.tvFragmentUpper.setOnClickListener {
            viewModel.increaseUpper()

            if (viewModel.checkForWinner()) {
                Toast.makeText(requireContext(), "¡Ganaste!", Toast.LENGTH_SHORT).show()
            }
        }*/

/*        binding.tvFragmentUpper.setOnClickListener {
            viewModel.increaseUpper()

            if (viewModel.checkForWinner() && !viewModel.hasWinnerMessageDisplayed()) {
                Toast.makeText(requireContext(), "¡Ganaste!", Toast.LENGTH_SHORT).show()
                viewModel.setWinnerMessageDisplayed(true)
            }
        }*/

        binding.tvFragmentUpper.setOnClickListener {
            if (!viewModel.hasWinnerDetected()) {
                viewModel.increaseUpper()

                if (viewModel.checkForWinner()) {
                    binding.tvClickUpper.text = "GANADOR!!"
                    Toast.makeText(requireContext(), "¡Ganaste UPPER!", Toast.LENGTH_SHORT).show()
                    viewModel.setWinnerDetected(true)
                }
            }
        }
    }
}