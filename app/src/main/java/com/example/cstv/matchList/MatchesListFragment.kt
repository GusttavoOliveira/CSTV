package com.example.cstv.matchList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cstv.R
import com.example.cstv.databinding.FragmentMatchesListBinding

class MatchesListFragment : Fragment() {
    private lateinit var binding: FragmentMatchesListBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchesListBinding.inflate(inflater, container, false)
        return binding.root

    }



}