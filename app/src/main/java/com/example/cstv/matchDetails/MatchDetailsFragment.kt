package com.example.cstv.matchDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.cstv.R
import com.example.cstv.databinding.FragmentMatchDetailsBinding
import com.example.cstv.entities.ApiState
import com.example.cstv.service.repository.RepositoryTeams

class MatchDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMatchDetailsBinding
    private lateinit var viewModel: MatchDetailsViewModel
    private var imageUrlTeam1: String? = null
    private var imageUrlTeam2: String? = null
    private var nameTeam1: String? = null
    private var nameTeam2: String? = null
    private lateinit var date: String
    private lateinit var leagueSerie: String
    private lateinit var teamNames: String

    companion object {
        const val IMAGETEAM1 = "team_image_1"
        const val IMAGETEAM2 = "team_image_2"
        const val NAMETEAM1 = "team_name_1"
        const val NAMETEAM2 = "team_name_2"
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

        teamNames = "$nameTeam1,$nameTeam2"

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

        viewModel = ViewModelProvider(
            this,
            MatchDetailsViewlModelFactory(RepositoryTeams())
        ).get(MatchDetailsViewModel::class.java)
        viewModel.listTeams(teamNames)

        onBackArrowClicked()
        onBindHeader()
        onBindingFlipper()


    }

    private fun onBindingFlipper() {
        viewModel.apiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiState.Loading ->
                    binding.run {
                        viewFlipper.displayedChild = 1
                        progressBar.isVisible = true
                        containerDetails.isVisible = false
                    }

                is ApiState.Succes ->
                    binding.run {
                        viewFlipper.displayedChild = 1
                        progressBar.isVisible = false
                        containerDetails.isVisible = true
                        onBindPlayers()
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

    private fun onBindHeader() {
        if (!imageUrlTeam1.isNullOrBlank() && imageUrlTeam1 != "null") {
            binding.imageTeam1.load(imageUrlTeam1)
        } else {
            binding.imageTeam1.load(R.drawable.without_photo) {
                transformations(CircleCropTransformation())
            }
        }

        if (!imageUrlTeam2.isNullOrBlank() && imageUrlTeam2 != "null") {
            binding.imageTeam2.load(imageUrlTeam2)
        } else {
            binding.imageTeam2.load(R.drawable.without_photo) {
                transformations(CircleCropTransformation())
            }
        }

        if (nameTeam1 != null) {
            binding.nameTeam1.text = nameTeam1
        } else {
            binding.nameTeam1.text = context?.getString(R.string.desconhecido)
        }

        if (nameTeam2 != null) {
            binding.nameTeam2.text = nameTeam2
        } else {
            binding.nameTeam2.text = context?.getString(R.string.desconhecido)
        }

        binding.headerLeague.text = leagueSerie
        binding.dateText.text = date
    }

    private fun onBindPlayers() {

        viewModel.team1.observe(viewLifecycleOwner, Observer {

            if (it.players.size >= 1) {
                binding.photo1.load(it.players[0]?.playerImage ?: R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName1.text =
                    "${it.players[0]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[0]?.last_name ?: ""}"

                binding.nickname1.text =
                    "${it.players[0]?.nickName ?: context?.getString(R.string.desconhecido)}"
            } else {
                binding.photo1.load(R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName1.text = context?.getString(R.string.desconhecido)

                binding.nickname1.text = context?.getString(R.string.desconhecido)
            }

            if (it.players.size >= 2) {
                binding.photo2.load(it.players[1]?.playerImage ?: R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName2.text =
                    "${it.players[1]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[1]?.last_name ?: ""}"

                binding.nickname2.text =
                    "${it.players[1]?.nickName ?: context?.getString(R.string.desconhecido)}"
            } else {
                binding.photo2.load(R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName2.text = context?.getString(R.string.desconhecido)

                binding.nickname2.text = context?.getString(R.string.desconhecido)
            }
            if (it.players.size >= 3) {
                binding.photo3.load(it.players[2]?.playerImage ?: R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName3.text =
                    "${it.players[2]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[2]?.last_name ?: ""}"

                binding.nickname3.text =
                    "${it.players[2]?.nickName ?: context?.getString(R.string.desconhecido)}"
            } else {
                binding.photo3.load(R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName3.text = context?.getString(R.string.desconhecido)

                binding.nickname3.text = context?.getString(R.string.desconhecido)
            }

            if (it.players.size >= 4) {
                binding.photo4.load(it.players[3]?.playerImage ?: R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName4.text =
                    "${it.players[3]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[3]?.last_name ?: ""}"

                binding.nickname4.text =
                    "${it.players[3]?.nickName ?: context?.getString(R.string.desconhecido)}"
            } else {
                binding.photo4.load(R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName4.text = context?.getString(R.string.desconhecido)

                binding.nickname4.text = context?.getString(R.string.desconhecido)
            }

            if (it.players.size >= 5) {
                binding.photo5.load(it.players[4]?.playerImage ?: R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName5.text =
                    "${it.players[4]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[4]?.last_name ?: ""}"

                binding.nickname5.text =
                    "${it.players[4]?.nickName ?: context?.getString(R.string.desconhecido)}"
            } else {
                binding.photo5.load(R.drawable.without_photo) {
                    transformations(RoundedCornersTransformation(8F))
                }

                binding.playerName5.text = context?.getString(R.string.desconhecido)

                binding.nickname5.text = context?.getString(R.string.desconhecido)
            }


        })

        viewModel.team2.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it.players.size >= 1) {
                    binding.photo6.load(it.players[0]?.playerImage ?: R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName6.text =
                        "${it.players[0]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[0]?.last_name ?: ""}"

                    binding.nickname6.text =
                        "${it.players[0]?.nickName ?: context?.getString(R.string.desconhecido)}"
                } else {
                    binding.photo6.load(R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName6.text = context?.getString(R.string.desconhecido)

                    binding.nickname6.text = context?.getString(R.string.desconhecido)
                }

                if (it.players.size >= 2) {
                    binding.photo7.load(it.players[1]?.playerImage ?: R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName7.text =
                        "${it.players[1]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[1]?.last_name ?: ""}"

                    binding.nickname7.text =
                        "${it.players[1]?.nickName ?: context?.getString(R.string.desconhecido)}"
                } else {
                    binding.photo7.load(R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName7.text = context?.getString(R.string.desconhecido)

                    binding.nickname7.text = context?.getString(R.string.desconhecido)
                }
                if (it.players.size >= 3) {
                    binding.photo8.load(it.players[2]?.playerImage ?: R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName8.text =
                        "${it.players[2]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[2]?.last_name ?: ""}"

                    binding.nickname8.text =
                        "${it.players[2]?.nickName ?: context?.getString(R.string.desconhecido)}"
                } else {
                    binding.photo8.load(R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName8.text = context?.getString(R.string.desconhecido)

                    binding.nickname8.text = context?.getString(R.string.desconhecido)
                }

                if (it.players.size >= 4) {
                    binding.photo9.load(it.players[3]?.playerImage ?: R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName9.text =
                        "${it.players[3]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[3]?.last_name ?: ""}"

                    binding.nickname9.text =
                        "${it.players[3]?.nickName ?: context?.getString(R.string.desconhecido)}"
                } else {
                    binding.photo9.load(R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName9.text = context?.getString(R.string.desconhecido)

                    binding.nickname9.text = context?.getString(R.string.desconhecido)
                }

                if (it.players.size >= 5) {
                    binding.photo10.load(it.players[4]?.playerImage ?: R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName10.text =
                        "${it.players[4]?.first_name ?: context?.getString(R.string.desconhecido)} ${it.players[4]?.last_name ?: ""}"

                    binding.nickname10.text =
                        "${it.players[4]?.nickName ?: context?.getString(R.string.desconhecido)}"
                } else {
                    binding.photo10.load(R.drawable.without_photo) {
                        transformations(RoundedCornersTransformation(8F))
                    }

                    binding.playerName10.text = context?.getString(R.string.desconhecido)

                    binding.nickname10.text = context?.getString(R.string.desconhecido)
                }
            } else {
                binding.photo6.load(R.drawable.without_photo)
                binding.photo7.load(R.drawable.without_photo)
                binding.photo8.load(R.drawable.without_photo)
                binding.photo9.load(R.drawable.without_photo)
                binding.photo10.load(R.drawable.without_photo)

                binding.playerName6.text = context?.getString(R.string.desconhecido)
                binding.playerName7.text = context?.getString(R.string.desconhecido)
                binding.playerName8.text = context?.getString(R.string.desconhecido)
                binding.playerName9.text = context?.getString(R.string.desconhecido)
                binding.playerName10.text = context?.getString(R.string.desconhecido)

                binding.nickname6.text = context?.getString(R.string.desconhecido)
                binding.nickname7.text = context?.getString(R.string.desconhecido)
                binding.nickname8.text = context?.getString(R.string.desconhecido)
                binding.nickname9.text = context?.getString(R.string.desconhecido)
                binding.nickname10.text = context?.getString(R.string.desconhecido)

            }
        })
    }

    private fun onBackArrowClicked() {
        binding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}