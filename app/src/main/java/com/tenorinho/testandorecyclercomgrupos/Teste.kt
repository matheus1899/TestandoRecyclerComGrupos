package com.tenorinho.testandorecyclercomgrupos

fun main(){
    val lista:List<Grupo> = getListaGrupo()
    val total:Int = getTotalItens(lista)

    for(pos:Int  in 0 until total){
        // Partindo do pressuposto de
        // que 0 é o cabecalho
        if(pos == 0){
            println(lista[0].Nome)
            continue
        }
        val quantidadeDeGrupos:Int = lista.size
        var quantidadeDeItens = 0
        for(grupo:Int in 1..quantidadeDeGrupos){
            var c = 0
            for(n:Int in grupo downTo 1){
                c += lista[n-1].ListaContatos.size
            }
            // Defini se a posicao atual
            // pertence a este grupo
            if(pos >= grupo + c){
                quantidadeDeItens = grupo + c
                continue
            }
            else{
                //Primeiro grupo
                if(quantidadeDeItens == 0){
                    //CABECALHO [0]
                    //ITEM      [1 - GRUPO] = 0
                    //ITEM      [2 - GRUPO] = 1
                    val contato:String = lista[grupo - 1].ListaContatos[pos - grupo]
                    println("  $contato")
                }
                //Outros grupos
                else{
                    //CABECALHO[0] → QUANT = 1
                    // ITEM    [1] → QUANT = 2
                    //CABECALHO[2]  _________▲
                    if(pos == quantidadeDeItens){
                        val nome:String = lista[grupo-1].Nome
                        println(nome)
                    }
                    //Necessário pegar a quantidade
                    //de itens de grupos anteriores
                    else{
                        var quantDeItensAnteriores = 0
                        for(i:Int in 1 until grupo){
                            quantDeItensAnteriores += lista[i-1].ListaContatos.size+1 //+1 PARA CONTAR O CABECALHO
                        }
                        quantDeItensAnteriores++
                        // pos                    = 22
                        // quantDeItensAnteriores = 20
                        // ---------------------------
                        //                           2
                        // ListaContato[2]___________▲
                        val contato:String = lista[grupo-1].ListaContatos[pos - quantDeItensAnteriores]
                        println("  $contato")
                    }
                }
                quantidadeDeItens = 0
                break
            }
        }
    }

    //Trecho que pega apenas o tipo(se é cabecalho ou não)
    // H = Cabecalho
    // I = Item

    /*for(pos:Int in 0 until total){
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
