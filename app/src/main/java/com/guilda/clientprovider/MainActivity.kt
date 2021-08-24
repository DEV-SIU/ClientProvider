package com.guilda.clientprovider

import android.annotation.SuppressLint
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var  notesRecycler:RecyclerView
    lateinit var noteRefreshButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRecycler = findViewById(R.id.client_list)
        noteRefreshButton = findViewById(R.id.client_btn_refresh)
        getContentProvider()
        noteRefreshButton.setOnClickListener { getContentProvider() }
    }

    @SuppressLint("Recycle")
    private fun getContentProvider(){
        try {
            val url = "content://com.guilda.appcontentprovider.provider/notas"
            val data = Uri.parse(url)
            val cursor: Cursor? =
                contentResolver.query(data,null,null,null,"Titulo")
            notesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }

        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}