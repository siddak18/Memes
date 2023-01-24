package com.example.memekotlin

import android.app.Notification.Action
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var currimageurl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadmeme()
    }
private fun loadmeme(){
    fini.visibility=View.VISIBLE
    val queue= Volley.newRequestQueue(this)
    var url="https://meme-api.com/gimme"
    val jsonobjectrequest= JsonObjectRequest(
        Request.Method.GET,url,null,
        { response ->
             currimageurl=response.getString("url")
            Glide.with(this).load(currimageurl).into(memeimage)
            fini.visibility=View.GONE
        },
        {
            Toast.makeText(this,"something  wrong ",Toast.LENGTH_LONG).show()
        })
    queue.add(jsonobjectrequest)
}
    fun next(view: View) {
        val inp=editTextTextPersonName.editableText.toString()
        Toast.makeText(this,"here is your $inp type of meme",Toast.LENGTH_LONG).show()
        loadmeme()
    }

    fun sharememe(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"hey check this meme $currimageurl")
        val chooser=Intent.createChooser(intent,"sharing this meme using...")
        startActivity(chooser)
    }
}