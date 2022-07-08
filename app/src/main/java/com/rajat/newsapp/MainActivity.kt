package com.rajat.newsapp

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    lateinit var recycler: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: NewsAdapter
    lateinit var technology : TextView
    lateinit var business : TextView
    lateinit var entertainment : TextView
    lateinit var sports : TextView
    lateinit var health : TextView

    var dataNews = ArrayList<NewsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
        layoutManager = LinearLayoutManager(this)
        technology = findViewById(R.id.technology)
        business = findViewById(R.id.business)
        entertainment = findViewById(R.id.entertainment)
        sports = findViewById(R.id.sports)
        health = findViewById(R.id.health)

        technology.setBackgroundResource(R.drawable.tabs)
        technology.setTextColor(Color.parseColor("#ffffff"))

        business.setBackgroundResource(0)
        entertainment.setBackgroundResource(0)
        sports.setBackgroundResource(0)
        health.setBackgroundResource(0)

        business.setTextColor(Color.parseColor("#000000"))
        entertainment.setTextColor(Color.parseColor("#000000"))
        sports.setTextColor(Color.parseColor("#000000"))
        health.setTextColor(Color.parseColor("#000000"))



        getData("technology")

        technology.setOnClickListener {
            dataNews.clear()
            getData("technology")
            adapter.notifyDataSetChanged()

            technology.setBackgroundResource(R.drawable.tabs)
            technology.setTextColor(Color.parseColor("#ffffff"))

            business.setBackgroundResource(0)
            entertainment.setBackgroundResource(0)
            sports.setBackgroundResource(0)
            health.setBackgroundResource(0)

            business.setTextColor(Color.parseColor("#000000"))
            entertainment.setTextColor(Color.parseColor("#000000"))
            sports.setTextColor(Color.parseColor("#000000"))
            health.setTextColor(Color.parseColor("#000000"))
        }
        business.setOnClickListener {
            dataNews.clear()
            getData("business")
            adapter.notifyDataSetChanged()

            business.setBackgroundResource(R.drawable.tabs)
            business.setTextColor(Color.parseColor("#ffffff"))

            technology.setBackgroundResource(0)
            entertainment.setBackgroundResource(0)
            sports.setBackgroundResource(0)
            health.setBackgroundResource(0)

            technology.setTextColor(Color.parseColor("#000000"))
            entertainment.setTextColor(Color.parseColor("#000000"))
            sports.setTextColor(Color.parseColor("#000000"))
            health.setTextColor(Color.parseColor("#000000"))
        }
        entertainment.setOnClickListener {
            dataNews.clear()
            getData("entertainment")
            adapter.notifyDataSetChanged()

            entertainment.setBackgroundResource(R.drawable.tabs)
            entertainment.setTextColor(Color.parseColor("#ffffff"))

            technology.setBackgroundResource(0)
            business.setBackgroundResource(0)
            sports.setBackgroundResource(0)
            health.setBackgroundResource(0)

            technology.setTextColor(Color.parseColor("#000000"))
            business.setTextColor(Color.parseColor("#000000"))
            sports.setTextColor(Color.parseColor("#000000"))
            health.setTextColor(Color.parseColor("#000000"))
        }
        sports.setOnClickListener {
            dataNews.clear()
            getData("sports")
            adapter.notifyDataSetChanged()

            sports.setBackgroundResource(R.drawable.tabs)
            sports.setTextColor(Color.parseColor("#ffffff"))

            technology.setBackgroundResource(0)
            business.setBackgroundResource(0)
            entertainment.setBackgroundResource(0)
            health.setBackgroundResource(0)

            technology.setTextColor(Color.parseColor("#000000"))
            business.setTextColor(Color.parseColor("#000000"))
            entertainment.setTextColor(Color.parseColor("#000000"))
            health.setTextColor(Color.parseColor("#000000"))
        }
        health.setOnClickListener {
            dataNews.clear()
            getData("health")
            adapter.notifyDataSetChanged()

            health.setBackgroundResource(R.drawable.tabs)
            health.setTextColor(Color.parseColor("#ffffff"))

            technology.setBackgroundResource(0)
            business.setBackgroundResource(0)
            entertainment.setBackgroundResource(0)
            sports.setBackgroundResource(0)

            technology.setTextColor(Color.parseColor("#000000"))
            business.setTextColor(Color.parseColor("#000000"))
            entertainment.setTextColor(Color.parseColor("#000000"))
            sports.setTextColor(Color.parseColor("#000000"))
        }

    }

    private fun getData(category : String){
        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=${category}&apiKey=7c002208ab244c488fc89e4a2f4d667d"

        if (ConnectionManager().checkConnectivity(this@MainActivity)) {
            val jsonRequest =
                object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                    try {

                        val status = it.getString("status")

                        if(status.equals("ok")){
                            val newsArr = it.getJSONArray("articles")
                            for (i in 1 until newsArr.length()){
                                val state = newsArr.getJSONObject(i)
                                val details = NewsData(
                                    state.getJSONObject("source").getString("name"),
                                    state.getString("title"),
                                    state.getString("description"),
                                    state.getString("url"),
                                    state.getString("urlToImage"),
                                    state.getString("publishedAt"),
                                )
                                dataNews.add(details)

                            }
                        }
                        else{
                            Toast.makeText(
                                this@MainActivity,
                                "Unable to Fetch Data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        adapter = NewsAdapter(this@MainActivity, dataNews)

                        recycler.adapter = adapter
                        recycler.layoutManager = layoutManager

                    } catch (e: JSONException) {
                        Toast.makeText(
                            this@MainActivity,
                            "Unable to Fetch Data"+e,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(this@MainActivity, "Unable to Fetch Data"+it, Toast.LENGTH_SHORT)
                        .show()
                }) {
                    //@Throws(AuthFailureError::class)
                    /*override fun getParams(): Map<String, String>? {
                        val params: MutableMap<String, String> = HashMap()
                        params["country"] = "in"
                        params["category"] = category
                        params["apiKey"] = "7c002208ab244c488fc89e4a2f4d667d"
                        return params
                    }
                }
            queue.add(jsonRequest)*/
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["User-Agent"] = "Mozilla/5.0"
                        return headers
                    }
                }

            queue.add(jsonRequest)
        } else {
            val dialog = AlertDialog.Builder(this@MainActivity)
            dialog.setTitle("Failed")
            dialog.setMessage("Internet Connection Not Found")
            dialog.setPositiveButton("Settings") { text, listener ->
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                finish()
            }
            dialog.setNegativeButton("Cancel") { text, listener ->
                ActivityCompat.finishAffinity(this@MainActivity)
            }
            dialog.create()
            dialog.show()
        }
    }
}