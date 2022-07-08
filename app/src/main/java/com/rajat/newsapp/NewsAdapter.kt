package com.rajat.newsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter
    (val context: Context, val news: ArrayList<NewsData>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val urlToImage: ImageView = view.findViewById(R.id.newImg)
        val title: TextView = view.findViewById(R.id.title)
        val date : TextView = view.findViewById(R.id.date)
        val relative : RelativeLayout = view.findViewById(R.id.relative)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_row_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsData = news[position]
        holder.title.text = newsData.title
        Picasso.with(context).load(newsData.urlToImage).fit().into(holder.urlToImage)

        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val output = SimpleDateFormat("dd/MM/yyyy")
        var d = Date()
        try {
            d = input.parse(newsData.publishedAt) as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val formatted: String = output.format(d)
        holder.date.text = formatted

        holder.relative.setOnClickListener {
            val intent = Intent(context,NewsDescription::class.java)
            val args = Bundle()
            args.putSerializable("ARRAYLIST", news)
            intent.putExtra("BUNDLE", args)
            intent.putExtra("pos", position)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return news.size
    }
}