package com.tenorinho.testandorecyclercomgrupos.ui.activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.RecyclerView
import com.tenorinho.testandorecyclercomgrupos.ui.adapter.ContatoAdapter
import com.tenorinho.testandorecyclercomgrupos.R
import com.tenorinho.testandorecyclercomgrupos.model.Contato
import com.tenorinho.testandorecyclercomgrupos.model.Grupo
import java.security.Permission

class MainActivity : AppCompatActivity() {
    private var lista:RecyclerView? = null
    private var adapter: ContatoAdapter? = null
    private val READ_CONTACTS = 250

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lista = findViewById(R.id.lista)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(Array<String>(1){Manifest.permission.READ_CONTACTS}, READ_CONTACTS)
        }
        else{
            adapter = ContatoAdapter(GetContatosTask().execute(this).get())
            lista?.adapter = adapter
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == READ_CONTACTS){
            if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                //Péssima prática
                finish()
            }
        }
    }
    class GetContatosTask : AsyncTask<Activity, Unit, ArrayList<Grupo>>(){
        override fun doInBackground(vararg p0: Activity): ArrayList<Grupo> {
            val c:Cursor? = p0[0].contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,null)
            c?.moveToFirst()
            val lista_contato = ArrayList<Contato>()
            val grupo_contato = ArrayList<Grupo>()
            if(c != null) {
                do {
                    lista_contato.add(Contato(c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))))
                }
                while (c.moveToNext())
            }
            lista_contato.sortBy { it.nome }
            val listaLetras = ArrayList<Char>()
            lista_contato.forEach {
                var letra:Char = it.nome.replace('Á','A')[0]
                if(!listaLetras.contains(letra)){
                    listaLetras.add(letra)
                }
            }
            listaLetras.forEach {
                val letra:Char = it
                grupo_contato.add(Grupo(it.toString(), lista_contato.filter { it.nome.replace('Á','A')[0].equals(letra) }))
            }
            return grupo_contato
        }
    }
}