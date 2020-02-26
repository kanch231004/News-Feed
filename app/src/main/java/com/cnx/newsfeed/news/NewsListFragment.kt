package com.cnx.newsfeed.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cnx.newsfeed.api.Status
import com.cnx.newsfeed.commonUtil.ConnectivityUtil
import com.cnx.newsfeed.databinding.FragmentNewsListBinding
import com.cnx.newsfeed.di.Injectable
import com.cnx.newsfeed.di.injectViewModel
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

        isConnected = ConnectivityUtil.isConnected(context!!)

        if (!isConnected)
            Toast.makeText(context?.applicationContext,"No internet connection!",Toast.LENGTH_SHORT).show()

        val adapter = NewsAdapter()

        binding.rvNewsList.adapter = adapter

        subscribeUI(adapter)
    }

    private fun subscribeUI(adapter: NewsAdapter) {

        val data = viewModel.newsList(isConnected)

        data?.networkState?.observe(viewLifecycleOwner, Observer {

            when(it.status) {

                Status.RUNNING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.FAILED -> {
                    progressBar.visibility = View.GONE
                    // Handle fail state
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                }
            }
        })

        data?.pagedList?.observe(viewLifecycleOwner, Observer {

            adapter.submitList(it)
        })
    }
}
