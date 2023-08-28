package com.kanchanpal.newsfeed.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kanchanpal.newsfeed.api.NewsListModel
import com.kanchanpal.newsfeed.databinding.RvNewsListItemsBinding

/**
 * Adapter for the [RecyclerView] in [NewsFragment].
 */
class NewsAdapter : PagedListAdapter<NewsListModel, NewsAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.apply {
            bind(createOnClickListener(newsItem), newsItem)
            itemView.tag = newsItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvNewsListItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(newItem: NewsListModel?): View.OnClickListener {
        return View.OnClickListener {
            val direction = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(
                newItem ?: NewsListModel()
            )
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: RvNewsListItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: NewsListModel?) {
            binding.apply {
                clNews.setOnClickListener(listener)
                textView.text = item?.title
               // tvChannel.text = item?.source?.name
                tvDate.text = item?.publishedAt?.substring(0, 10)
                Glide.with(itemView).load(item?.urlToImage).into(binding.ivNews)
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<NewsListModel>() {

    override fun areItemsTheSame(oldItem: NewsListModel, newItem: NewsListModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: NewsListModel, newItem: NewsListModel): Boolean {
        return oldItem == newItem
    }
}
