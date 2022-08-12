package com.example.pokeapitest.ui.viewmodel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapitest.databinding.ItemPokeBinding
import com.example.pokeapitest.results
import com.squareup.picasso.Picasso

class PokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemPokeBinding.bind(view)

    fun bind(pokeinfo: results) {
        binding.tvName.text = pokeinfo.name
        Picasso.get().load(pokeinfo.urlImg).into(binding.imgPokemon)
    }
}