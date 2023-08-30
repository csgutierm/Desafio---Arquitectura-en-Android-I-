package com.desafiolatam.viewmodel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.desafiolatam.viewmodel.databinding.FragmentLowerBinding

class LowerFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentLowerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLowerBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.counterLowerStateFlow.collect {contador ->
                binding.tvContadorLower.text = contador.toString()
            }
        }

        binding.tvFragmentLower.setOnClickListener {
            if (!viewModel.hasWinnerDetected()) {
                viewModel.increaseLower()

                if (viewModel.checkForWinner()) {
                    binding.tvClickLower.text = "GANADOR!!"
                    Toast.makeText(requireContext(), "¡Ganaste LOWER!", Toast.LENGTH_SHORT).show()
                    viewModel.setWinnerDetected(true)
                }
            }
        }
    }
}