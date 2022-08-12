package com.example.pokeapitest

import com.google.gson.annotations.SerializedName

data class PokeResponse(
    @SerializedName("count") var count: Int,
    @SerializedName("next") var next: String,
    @SerializedName("previuos") var previous: String,
    @SerializedName("results") var listPokemones: List<results>
)

data class results(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String,
    @SerializedName("urlImgDefault") var urlImg: String,
    @SerializedName("stats") var Liststats: List<base_stat>
)

data class base_stat(
    @SerializedName("base_stat") var base_stat:String
)





