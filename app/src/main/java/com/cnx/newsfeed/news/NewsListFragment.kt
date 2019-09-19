package com.cnx.newsfeed.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cnx.newsfeed.data.Result
import com.cnx.newsfeed.databinding.FragmentNewsListBinding
import com.cnx.newsfeed.di.Injectable
import com.cnx.newsfeed.di.injectViewModel
import javax.inject.Inject

class NewsListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = injectViewModel(viewModelFactory)
        val binding = FragmentNewsListBinding.inflate(inflater,container,false)
        context ?: return binding.root

        val adapter = NewsAdapter()

        binding.rvNewsList.adapter = adapter

        subscribeUI(binding,adapter)

        return binding.root
    }


    private fun subscribeUI(binding: FragmentNewsListBinding, adapter: NewsAdapter) {

        viewModel.newsList.observe(viewLifecycleOwner, Observer {

            when (it.status) {

                Result.Status.SUCCESS -> {

                    it.data?.let { adapter.submitList(it) }
                }

                Result.Status.ERROR -> {

                   Log.d("NewsList"," error message ${it.message}")

                }

                Result.Status.LOADING -> {


                    Log.d("NewsList","loading message")
                }
            }
        })

    }

}