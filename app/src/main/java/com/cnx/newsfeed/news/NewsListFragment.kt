package com.cnx.newsfeed.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cnx.newsfeed.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentNewsListBinding.inflate(inflater,container,false)
        context ?: return binding.root

        return binding.root
    }

}