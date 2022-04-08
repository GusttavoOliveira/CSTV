package com.example.cstv.matchList

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cstv.databinding.FragmentMatchesListBinding
import com.example.cstv.entities.ApiState

class MatchesListFragment : Fragment() {
    private lateinit var binding: FragmentMatchesListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MatchesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MatchesListViewModel::class.java)
        onBindingFlipper()


    }

    private fun onCardClicked(
        imageTeam1: String?,
        imageTeam2: String?,
        nameTeam1: String?,
        nameTeam2: String?,
        date: String,
        leagueSerie: String
    ) {

        val action = MatchesListFragmentDirections.actionMatchesListFragmentToMatchDetailsFragment(
            nameTeam1,
            nameTeam2,
            date,
            leagueSerie,
            imageTeam1,
            imageTeam2
        )

        findNavController().navigate(action)

    }

    private fun setupAdapter() {

        recyclerView = binding.cardsRecycler

        viewModel.matchesList.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = MatchesListAdapter(requireContext(), it, ::onCardClicked)
        })
    }

    private fun onBindingFlipper() {
        viewModel.apiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiState.Loading ->
                    binding.run {
                        viewFlipper.displayedChild = 1
                        progressBar.isVisible = true
                        cardsRecycler.isVisible = false
                    }

                is ApiState.Succes ->
                    binding.run {
                        viewFlipper.displayedChild = 1
                        progressBar.isVisible = false
                        cardsRecycler.isVisible = true
                        setupAdapter()
                    }

                is ApiState.Failed ->
                    binding.run {
                        viewFlipper.displayedChild = 1
                        progressBar.isVisible = true
                        Toast.makeText(requireContext(), "Falha na requisição", Toast.LENGTH_SHORT)
                            .show()
                    }

            }
        })
    }

}