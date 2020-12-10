package com.tenorinho.testandorecyclercomgrupos.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.tenorinho.testandorecyclercomgrupos.ui.adapter.ContatoAdapter
import com.tenorinho.testandorecyclercomgrupos.R
import com.tenorinho.testandorecyclercomgrupos.model.Contato
import com.tenorinho.testandorecyclercomgrupos.model.Grupo

class MainActivity : AppCompatActivity() {
    private var lista:RecyclerView? = null
    private var adapter: ContatoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lista = findViewById(R.id.lista)
        adapter = ContatoAdapter(getListaGrupo())
        lista?.adapter = adapter
    }
    fun getListaGrupo():ArrayList<Grupo>{
        val lista = ArrayList<Grupo>()
        lista.add(Grupo("A", arrayListOf(Contato("Alfredo"),Contato("Angelica"),Contato("Avaldo"),Contato("Allan"))))
        lista.add(Grupo("B", arrayListOf(Contato("Bernardo"),Contato("Bruno"),Contato("Barbara"),Contato("Bruna"))))
        lista.add(Grupo("C", arrayListOf(Contato("Camila"),Contato("Carlos"),Contato("Cesar"),Contato("Conceição"))))
        lista.add(Grupo("D", arrayListOf(Contato("Danilo"),Contato("Daniela"),Contato("Diego"),Contato("Denise"),Contato("Daiane"),Contato("Debora"))))
        lista.add(Grupo("E", arrayListOf(Contato("Everton"),Contato("Erika"),Contato("Emerson"),Contato("Elias"))))
        lista.add(Grupo("F", arrayListOf(Contato("Fabio"))))
        lista.add(Grupo("G", ArrayList<Contato>()))
        lista.add(Grupo("H", ArrayList<Contato>()))
        lista.add(Grupo("L", arrayListOf(Contato("Luiz"),Contato("Lucas"),Contato("Ludmila"),Contato("Lais"))))
        return lista
    }
}