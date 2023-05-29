package com.retrofitkotlin1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rick = findViewById<RecyclerView>(R.id.rv_character)

        ApiConfig.getService().getRick().enqueue(object : retrofit2.Callback <ResponseRick>{
            override fun onResponse(call: retrofit2.Call<ResponseRick>,respose :retrofit2.Response  <ResponseRick>){
                if(respose.isSuccessful){
                        val responseRick=respose.body()
                        val dataRick = responseRick?.results
                        val rickAdapter = RickAdapter(dataRick as List<ResultsItem>)
                        rick.apply{
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            setHasFixedSize(true)
                            rickAdapter.notifyDataSetChanged()
                            adapter = rickAdapter
                        }
                 }
            }
            fun onFailture (call:retrofit2.Call<ResponseRick>,t:Throwable){
                Toast.makeText(applicationContext,t.localizedMessage,Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: retrofit2.Call<ResponseRick>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}