package com.createsapp.newsappinmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.createsapp.newsappinmvvm.R
import com.createsapp.newsappinmvvm.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private lateinit var title: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivArticleImage: ImageView
    private lateinit var tvSource: TextView
    private lateinit var tvPublishedAt: TextView
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            title = itemView.findViewById(R.id.tvTitle) as TextView
            tvDescription = itemView.findViewById(R.id.tvDescription)
            ivArticleImage = itemView.findViewById(R.id.ivArticleImage)
            tvSource = itemView.findViewById(R.id.tvSource)
            tvPublishedAt = itemView.findViewById(R.id.tvPublishedAt)
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            title.text = article.description
            tvPublishedAt.text = article.publishedAt
            tvDescription.text = article.description
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


}