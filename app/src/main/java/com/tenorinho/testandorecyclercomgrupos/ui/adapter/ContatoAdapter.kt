package com.tenorinho.testandorecyclercomgrupos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tenorinho.testandorecyclercomgrupos.R
import com.tenorinho.testandorecyclercomgrupos.model.Contato
import com.tenorinho.testandorecyclercomgrupos.model.Grupo

class ContatoAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private val CABECALHO = 1
    private val ITEM = 2
    private var lista:ArrayList<Grupo>

    constructor(lista:ArrayList<Grupo>):super(){
        for(i:Int in lista.size.minus(1) downTo 0){
            if(lista[i].listaContatos == null || lista[i].listaContatos.size == 0){
                lista.removeAt(i)
            }
        }
        this.lista = lista
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == ITEM){
            return ContatoHolder( LayoutInflater.from(parent.context).inflate( R.layout.item_contato, parent, false))
        }
        else{
            return CabecalhoHolder( LayoutInflater.from(parent.context).inflate( R.layout.item_cabecalho, parent, false))
        }
    }
    override fun getItemCount(): Int {
        return getNumeroTotalDeItens()
    }
    override fun getItemViewType(pos: Int):Int{
        if(pos == 0){
            return CABECALHO
        }
        var tipo:Int = ITEM
        val quantidadeDeGrupos:Int = lista.size
        var quantidadeDeItens = 0
        for(i:Int in 1..quantidadeDeGrupos){
            var c = 0
            for(n:Int in i downTo 1){
                c += lista[n-1].listaContatos.size
            }
            if(pos >= i+c){
                quantidadeDeItens = i+c
                continue
            }
            if(pos == quantidadeDeItens){
                tipo = CABECALHO
                break
            }
            else{
                break
            }
        }
        return tipo
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ContatoHolder){
            val h: ContatoHolder = holder as ContatoHolder
            val obj:Any = getObjectByPosition(position)
            if(obj is Contato){
                h.txtNomeContato.text = obj.nome
                if(!obj.numero.isNullOrBlank()){
                    h.txtNumeroContato.visibility = View.VISIBLE
                    h.txtNumeroContato.text = obj.numero
                }
            }
        }
        if(holder is CabecalhoHolder){
            val h: CabecalhoHolder = holder as CabecalhoHolder
            val obj:Any = getObjectByPosition(position)
            if(obj is Grupo){
                h.txtNomeGrupo.text = obj.nome
            }
        }
    }
    private fun getNumeroTotalDeItens():Int{
        var quantidade:Int = 0
        quantidade = lista.size
        for(i:Int in 0..lista.size-1){
            quantidade += lista[i].listaContatos.size
        }
        return quantidade
    }
    private fun getObjectByPosition(position:Int):Any{
        if(position == 0){
            return lista[0]
        }
        var objParaRetorno = Any()
        val numeroDeGrupos:Int = lista.size
        var quantidadeItens = 0
        for(grupo:Int in 1..numeroDeGrupos){
            var c = 0
            for(n:Int in grupo downTo 1){
                c += lista[n-1].listaContatos.size
            }
            if(position >= grupo + c){
                quantidadeItens = grupo + c
                continue
            }
            else{
                //primeiro grupo
                if(quantidadeItens == 0) {
                    objParaRetorno = lista[grupo - 1].listaContatos[position - grupo]
                    break
                }
                //outros grupos
                else{
                    if(position == quantidadeItens){
                        objParaRetorno = lista[grupo-1]
                        break
                    }
                    else{
                        var quantidadeGruposAnterior = 0
                        for(i:Int in 1..grupo-1){
                            quantidadeGruposAnterior += lista[i-1].listaContatos.size+1
                        }
                        quantidadeGruposAnterior++
                        objParaRetorno = lista[grupo-1].listaContatos[position - quantidadeGruposAnterior]
                    }
                }
                quantidadeItens = 0
                break
            }
        }
        return objParaRetorno
    }
    class CabecalhoHolder:RecyclerView.ViewHolder{
        val txtNomeGrupo:TextView
        constructor(view: View):super(view){
            txtNomeGrupo = view.findViewById(R.id.item_cabecalho_nome)
        }
    }
    class ContatoHolder:RecyclerView.ViewHolder{
        val txtNomeContato:TextView
        val txtNumeroContato:TextView
        constructor(view: View):super(view){
            txtNomeContato = view.findViewById(R.id.item_contato_nome)
            txtNumeroContato = view.findViewById(R.id.item_contato_numero)
        }
    }
}