package com.example.cstv.matchList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cstv.databinding.FragmentMatchesListBinding

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
        viewModel.listMatches()
        recyclerView = binding.cardsRecycler

        viewModel.matchesList.observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = MatchesListAdapter(requireContext(), it)
        })



    }

}