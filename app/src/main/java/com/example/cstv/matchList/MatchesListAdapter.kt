package com.example.cstv.matchList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cstv.R
import com.example.cstv.databinding.MatchCardItemBinding
import java.util.zip.Inflater

class MatchesListAdapter(
    private val context: Context,
    val listCards: List<Card>
) : RecyclerView.Adapter<MatchesListAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.match_card_item, parent, false)
        val card = CardViewHolder(layout)

        return card
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(listCards[position])
    }

    override fun getItemCount(): Int {
        return listCards.size
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


        fun bind(card: Card){
            //Fazer as propriedades receberem seus respectivos strings ou URLs
        }


    }
}