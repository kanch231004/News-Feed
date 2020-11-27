package com.kanchanpal.newsfeed.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kanchanpal.newsfeed.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {

    private val args : NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsDetailBinding.inflate(inflater,container,false)
        binding.ivBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        val newsModel = args.newsItem
        binding.newsDetail = newsModel
        return binding.root
    }
}
