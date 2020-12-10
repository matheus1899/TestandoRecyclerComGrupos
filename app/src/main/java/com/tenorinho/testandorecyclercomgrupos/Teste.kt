package com.tenorinho.testandorecyclercomgrupos

fun main(){
    val lista = getListaGrupo()
    val total = getTotalItens(lista)

    for(pos in 0..total-1){
        if(pos == 0){
            println(lista[0].Nome)
            continue
        }
        val numero_de_grupos = lista.size
        var quantidade_itens = 0
        for(grupo in 1..numero_de_grupos){
            var c = 0
            for(n in grupo downTo 1){
                c += lista[n-1].ListaContatos.size
            }
            if(pos >= grupo + c){
                quantidade_itens = grupo + c
                continue
            }
            else{
                //primeiro grupo
                if(quantidade_itens == 0) {
                    val contato = lista[grupo - 1].ListaContatos[pos - grupo]
                    println("  $contato")
                }
                //outros grupos
                else{
                    if(pos == quantidade_itens){
                        var nome = lista[grupo-1].Nome
                        println(nome)
                    }
                    else{
                        var quantidadeGruposAnterior = 0
                        for(i in 1..grupo-1){
                            quantidadeGruposAnterior += lista[i-1].ListaContatos.size+1
                        }
                        quantidadeGruposAnterior++
                        val contato = lista[grupo-1].ListaContatos[pos-quantidadeGruposAnterior]
                        println("  $contato")
                    }
                }
                quantidade_itens = 0
                break
            }
        }
    }
    /*for(pos in 0..total-1){
        if(pos == 0){
            print("\nH\n")
            continue
        }
        val quantidadeDeGrupos:Int = lista.size
        var quantidadeDeItens = 0
        for(i:Int in 1..quantidadeDeGrupos){
            var c = 0
            for(n:Int in i downTo 1){
                c += lista[n-1].ListaContatos.size
            }
            if(pos >= i+c){
                quantidadeDeItens = i+c
                continue
            }
            if(pos == quantidadeDeItens){
                println("\nH")
                break
            }
            else{
                print("I ")
                break
            }
        }
    }*/
}

fun getTotalItens(lista:List<Grupo>):Int{
    var i = 0
    i = lista.size
    for (g:Grupo in lista){
        i += g.ListaContatos.size
    }
    return i
}

fun getListaGrupo():List<Grupo>{
    val lista = ArrayList<Grupo>()
    lista.add(Grupo("A", arrayListOf("Alfredo","Angelica","Avaldo","Allan")))
    lista.add(Grupo("B", arrayListOf("Bernardo","Bruno","Barbara","Bruna")))
    lista.add(Grupo("C", arrayListOf("Camila","Carlos","Cesar","Conceição")))
    lista.add(Grupo("D", arrayListOf("Danilo","Daniela","Diego","Denise","Daiane","Debora","")))
    lista.add(Grupo("E", arrayListOf("Everton","Erika","Emerson","Elias")))
    lista.add(Grupo("F", arrayListOf("Fabio")))
    lista.add(Grupo("G", arrayListOf("Gean")))
    lista.add(Grupo("L", arrayListOf("Luiz","Lucas","Ludmila","Lais")))
    return lista
}
data class Grupo(
    var Nome:String,
    var ListaContatos:List<String>
)
