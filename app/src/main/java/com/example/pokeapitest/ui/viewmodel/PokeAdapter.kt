package com.example.pokeapitest.ui.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapitest.R
import com.example.pokeapitest.results

class PokeAdapter(private val pokeinfo: List<results>) : RecyclerView.Adapter<PokeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        return PokeViewHolder(layoutInflater.inflate(R.layout.item_poke, parent, false))
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val item: results = pokeinfo[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = pokeinfo.size
}