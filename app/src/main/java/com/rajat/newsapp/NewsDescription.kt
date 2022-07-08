package com.rajat.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NewsDescription : AppCompatActivity() {

    lateinit var source: TextView
    lateinit var title: TextView
    lateinit var description: TextView
    lateinit var url: String
    lateinit var urlToImage: String
    lateinit var publishedAt: TextView
    lateinit var newImg : ImageView
    lateinit var left : View
    lateinit var right : View
    lateinit var read : TextView

    var newsList = ArrayList<NewsData>()

    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_description)

        val intent = intent
        val args = intent.getBundleExtra("BUNDLE")
        newsList = (args!!.getSerializable("ARRAYLIST") as ArrayList<NewsData>?)!!
        position = intent.getIntExtra("pos",0)



        source=findViewById(R.id.source)
        title=findViewById(R.id.title)
        description=findViewById(R.id.description)
        publishedAt=findViewById(R.id.date)
        newImg=findViewById(R.id.newImg)
        left=findViewById(R.id.left)
        right=findViewById(R.id.right)
        read=findViewById(R.id.read)

        left.setOnClickListener {
            if(position==0){
                Toast.makeText(this@NewsDescription,"You Reached End",Toast.LENGTH_SHORT).show()
            }
            else {
                position--
                getData()
            }
        }
        right.setOnClickListener {
            if (position==newsList.size-1){
                Toast.makeText(this@NewsDescription,"You Reached End",Toast.LENGTH_SHORT).show()
            }
            else{
                position++
                getData()
            }

        }
        read.setOnClickListener {
            val intent = Intent(this@NewsDescription,NewsWebview::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }

        getData()
    }

    private fun getData(){

        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val output = SimpleDateFormat("dd/MM/yyyy")
        var d = Date()
        try {
            d = input.parse(newsList[position].publishedAt) as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val formatted: String = output.format(d)

        source.text = newsList[position].source
        title.text = newsList[position].title
        description.text = newsList[position].description
        publishedAt.text = formatted
        url = newsList[position].url
        Log.d("rajjjjj",url)
        urlToImage = newsList[position].urlToImage

        Picasso.with(this@NewsDescription).load(urlToImage).fit().into(newImg)

    }
}