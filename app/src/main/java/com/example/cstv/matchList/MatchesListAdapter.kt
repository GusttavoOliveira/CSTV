package com.example.cstv.matchList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cstv.R
import com.example.cstv.databinding.MatchCardItemBinding
import com.example.cstv.entities.MatchItem
import java.util.zip.Inflater

class MatchesListAdapter(
    private val context: Context,
    val listMatches: ArrayList<MatchItem>
) : RecyclerView.Adapter<MatchesListAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.match_card_item, parent, false)
        val card = CardViewHolder(layout)

        return card
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(listMatches[position])
    }

    override fun getItemCount(): Int {
        return listMatches.size
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = MatchCardItemBinding.bind(itemView)
        private val imageTeam1 = binding.imageTeam1
        private val imageTeam2 = binding.imageTeam2
        private val nameTeam1 = binding.nameTeam1
        private val nameTeam2 = binding.nameTeam2
        private val leagueName = binding.leagueName
        private val leagueLogo = binding.leagueLogo
        private val date = binding.date


        fun bind(match: MatchItem) {

            if (match.opponents[0].imageUrl != null) {
                imageTeam1.load(match.opponents[0].imageUrl)
            } else {
                imageTeam1.load(R.drawable.withoutphoto) {
                    transformations(CircleCropTransformation())
                }
            }

            if (match.opponents[1].imageUrl != null) {
                imageTeam2.load(match.opponents[1].imageUrl)
            } else {
                imageTeam2.load(R.drawable.withoutphoto) {
                    transformations(CircleCropTransformation())
                }
            }

            if (match.league.imageUrl != null) {
                leagueLogo.load(match.league.imageUrl)
            } else {
                leagueLogo.load(R.drawable.withoutphoto) {
                    transformations(CircleCropTransformation())
                }
            }

            nameTeam1.text = match.opponents[0].teamName
            nameTeam2.text = match.opponents[1].teamName
            leagueName.text = ("${match.league.leagueName} ${match.serie.serieName}")
            date.text = match.begin_at
        }
    }
}