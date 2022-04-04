package com.example.cstv.matchList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cstv.R
import com.example.cstv.databinding.MatchCardItemBinding
import com.example.cstv.entities.MatchItem
import com.example.cstv.util.FormattedDate

class MatchesListAdapter(
    private val context: Context,
    val listMatches: List<MatchItem>,
    val onCardClicked: (imageTeam1: String?,
                    imageTeam2: String?,
                    nameTeam1: String?,
                    nameTeam2: String?,
                    date:String,
                    leagueSerie: String) -> Unit
) : RecyclerView.Adapter<MatchesListAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {

        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.match_card_item, parent, false)
        val card = CardViewHolder(layout
        ) { imageTeam1, imageTeam2, nameTeam1, nameTeam2, date, leagueSerie ->
            onCardClicked(
                imageTeam1,
                imageTeam2,
                nameTeam1,
                nameTeam2,
                date,
                leagueSerie
            )
        }

        return card
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(listMatches[position])
    }

    override fun getItemCount(): Int {
        return listMatches.size
    }

    inner class CardViewHolder(
        itemView: View,
        onCardClicked: (imageTeam1: String?,
                        imageTeam2: String?,
                        nameTeam1: String?,
                        nameTeam2: String?,
                        date:String,
                        leagueSerie: String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = MatchCardItemBinding.bind(itemView)
        private var imageUrlTeam1: String? = null
        private var imageUrlTeam2: String? = null
        private var stringNameTeam1: String? = null
        private var stringNameTeam2: String? = null
        private lateinit var nameDate: String
        private lateinit var leagueSerie: String
        private val imageTeam1 = binding.imageTeam1
        private val imageTeam2 = binding.imageTeam2
        private val nameTeam1 = binding.nameTeam1
        private val nameTeam2 = binding.nameTeam2
        private val leagueName = binding.leagueName
        private val leagueLogo = binding.leagueLogo
        private val dateContainer = binding.containerDate
        private val date = binding.date
        private val cardContainer = binding.containerCard

        init {
            cardContainer.setOnClickListener {
                onCardClicked(imageUrlTeam1, imageUrlTeam2, stringNameTeam1, stringNameTeam2, nameDate, leagueSerie)
            }
        }


        fun bind(match: MatchItem) {

            nameDate = FormattedDate(match.begin_at).dateInformation()
            leagueSerie = "${match.league.leagueName} ${match.serie.serieName}"

            if (match.opponents.isNotEmpty()) {

                if (match.opponents[0]?.opponent?.imageUrl != null) {
                    imageTeam1.load(match.opponents[0]?.opponent?.imageUrl)
                    imageUrlTeam1 = match.opponents[0]?.opponent?.imageUrl
                } else {
                    imageTeam1.load(R.drawable.without_photo) {
                        transformations(CircleCropTransformation())
                    }
                }
                Log.d("MatchesListAdapter", "bind: ${match.opponents.size}")
                if(match.opponents.size == 2) {
                    if (match.opponents[1]?.opponent?.imageUrl != null) {
                        imageTeam2.load(match.opponents[1]?.opponent?.imageUrl)
                        imageUrlTeam2 = match.opponents[1]?.opponent?.imageUrl
                    }
                } else {
                    imageTeam2.load(R.drawable.without_photo) {
                        transformations(CircleCropTransformation())
                    }
                    imageUrlTeam2 = null
                }

                if(match.opponents[0]?.opponent?.teamName != null) {
                    nameTeam1.text = match.opponents[0]?.opponent?.teamName
                    stringNameTeam1 = match.opponents[0]?.opponent?.teamName
                }else{
                    nameTeam1.text = context.getString(R.string.desconhecido)
                }

                if(match.opponents.size == 2) {
                    if (match.opponents[1]?.opponent?.teamName != null) {
                        nameTeam2.text = match.opponents[1]?.opponent?.teamName
                        stringNameTeam2 = match.opponents[1]?.opponent?.teamName
                    }
                }else{
                    nameTeam1.text = context.getString(R.string.desconhecido)
                    stringNameTeam2 = context.getString(R.string.desconhecido)
                }

            } else {

                imageUrlTeam1 = null
                imageUrlTeam2 = null
                stringNameTeam1 = null
                stringNameTeam2 = null

                imageTeam1.load(R.drawable.without_photo) {
                    transformations(CircleCropTransformation())
                }

                imageTeam2.load(R.drawable.without_photo) {
                    transformations(CircleCropTransformation())
                }

                nameTeam1.text = context.getString(R.string.desconhecido)
                nameTeam2.text = context.getString(R.string.desconhecido)
            }

            if (match.league.imageUrl != null) {
                leagueLogo.load(match.league.imageUrl)
            } else {
                leagueLogo.load(R.drawable.without_photo) {
                    transformations(CircleCropTransformation())
                }
            }


            leagueName.text = leagueSerie
            date.text = nameDate
            if (date.text == "AGORA") {
                dateContainer.setBackgroundResource(R.drawable.date_background_live)
            } else {
                dateContainer.setBackgroundResource(R.drawable.date_background)
            }

        }
    }
}