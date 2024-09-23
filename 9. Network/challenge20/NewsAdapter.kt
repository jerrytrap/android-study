package com.sample.doitandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.doitandroid.NewsAdapter.NewsViewHolder

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {
    private val news = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)

        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int = news.size

    fun addAll(newsList: List<News>) {
        news.clear()
        news.addAll(newsList)
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val newsImage: ImageView = itemView.findViewById(R.id.imageView_newsImage)
        private val newsTitle: TextView = itemView.findViewById(R.id.textView_newsTitle)
        private val newsDescription: TextView = itemView.findViewById(R.id.textView_newsDescription)

        fun bind(news: News) {
            newsTitle.text = news.title
            newsDescription.text = news.description

            Glide.with(itemView)
                .load(news.imageUrl)
                .override(500)
                .into(newsImage)
        }
    }
}