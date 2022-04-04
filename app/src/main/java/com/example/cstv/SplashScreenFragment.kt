package com.example.cstv

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cstv.databinding.FragmentSplashScreenBinding
import java.util.*

class SplashScreenFragment: Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            navigationAction()
        }, 2000)
    }

    private fun navigationAction(){
        val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToMatchesListFragment()
        findNavController().navigate(action)
    }
}