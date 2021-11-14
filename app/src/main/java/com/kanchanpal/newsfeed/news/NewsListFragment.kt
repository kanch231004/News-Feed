package com.kanchanpal.newsfeed.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kanchanpal.newsfeed.api.NetworkState
import com.kanchanpal.newsfeed.commonUtil.ConnectivityUtil
import com.kanchanpal.newsfeed.databinding.FragmentNewsListBinding
import com.kanchanpal.newsfeed.di.Injectable
import com.kanchanpal.newsfeed.di.injectViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*
import javax.inject.Inject

class NewsListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NewsViewModel
    private var isConnected : Boolean = true
    private lateinit var  binding : FragmentNewsListBinding
    val newsViewModel : NewsViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater,container,false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        isConnected = ConnectivityUtil.isConnected(context)
        if (!isConnected)
            Toast.makeText(context?.applicationContext,"No internet connection!",Toast.LENGTH_SHORT).show()

        val adapter = NewsAdapter()
        binding.rvNewsList.adapter = adapter
        subscribeUI(adapter)
    }

    private fun subscribeUI(adapter: NewsAdapter) {
        val data = viewModel.newsList(isConnected)
        data?.networkState?.observe(viewLifecycleOwner, Observer {
            when(it) {
                is NetworkState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                is NetworkState.ERROR -> {
                    progressBar.visibility = View.GONE
                    // Handle fail state
                }
                is NetworkState.LOADED -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
        data?.pagedList?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
