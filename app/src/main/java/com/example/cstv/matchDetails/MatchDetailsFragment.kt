package com.example.cstv.matchDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cstv.R
import com.example.cstv.databinding.FragmentMatchDetailsBinding

class MatchDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMatchDetailsBinding
    private var imageUrlTeam1: String? = null
    private var imageUrlTeam2: String? = null
    private var nameTeam1: String? = null
    private var nameTeam2: String? = null
    private lateinit var date: String
    private lateinit var leagueSerie: String

    companion object{
        const val IMAGETEAM1 = "team_image_1"
        const val IMAGETEAM2 = "team_image_2"
        const val NAMETEAM1 = "name_image_1"
        const val NAMETEAM2 = "name_team_2"
        const val DATE = "game_date"
        const val LEAGUE = "league_serie"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            imageUrlTeam1 = it?.getString(IMAGETEAM1).toString()
            imageUrlTeam2 = it?.getString(IMAGETEAM2).toString()
            nameTeam1 = it?.getString(NAMETEAM1).toString()
            nameTeam2 = it?.getString(NAMETEAM2).toString()
            date = it?.getString(DATE).toString()
            leagueSerie = it?.getString(LEAGUE).toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBindView()
    }

    fun onBindView(){

    }

}