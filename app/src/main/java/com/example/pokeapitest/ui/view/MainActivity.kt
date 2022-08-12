package com.example.pokeapitest.ui.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapitest.*
import com.example.pokeapitest.core.RetrofitPoke.getRetrofit
import com.example.pokeapitest.data.network.PokeApiClient
import com.example.pokeapitest.databinding.ActivityMainBinding
import com.example.pokeapitest.ui.viewmodel.PokeAdapter
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var numChange: Int = 0

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PokeAdapter
    private val namePokemones = mutableListOf<results>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //validar conexion internet
        get20Pokemones("" + numChange)
        initRecyclerView()
        btnNavMenu()

    }

    private fun btnNavMenu() {
        binding.bottomNavigation.setOnItemSelectedListener(
            object : NavigationBarView.OnItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when (item.itemId) {
                        R.id.NavBtnNext -> {
                            numChange += 20
                            get20Pokemones("" + numChange)
                            return true
                        }
                        R.id.NavBtnPrevius -> {
                            if (numChange == 0) {
                                get20Pokemones("0")
                            } else {
                                numChange -= 20
                                get20Pokemones("" + numChange)
                            }
                            return true
                        }
                    }
                    return false
                }
            })
    }


    private fun initRecyclerView() {
        adapter = PokeAdapter(namePokemones)
        binding.rvPokemones.layoutManager = LinearLayoutManager(this)
        binding.rvPokemones.adapter = adapter
    }


    private fun get20Pokemones(query: String = "" + numChange) {
        CoroutineScope(Dispatchers.IO).launch {

            val call: Response<PokeResponse> = getRetrofit().create(PokeApiClient::class.java)
                .getNamePokemonesBy20("pokemon/?offset=$query&limit=20")
            val pokemones: PokeResponse? = call.body()

            val result: List<results> = pokemones?.listPokemones ?: emptyList()


            val control = numChange + 1

            for (i in 0..19) {
                result[i].urlImg =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${control + i}.png"
                //println(result[i].urlImg + " " + i + " control" + control + " numchange" + numChange)
            }


            runOnUiThread {
                if (call.isSuccessful) {
                    namePokemones.clear()
                    namePokemones.addAll(result)
                    adapter.notifyDataSetChanged()
                    //nice
                } else {
                    Toast.makeText(this@MainActivity, "error", Toast.LENGTH_LONG).show()
                }
            }
        }
        //validar conexion internet
    }

}

//prueba develop