package com.createsapp.newsappinmvvm.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.createsapp.newsappinmvvm.NewsActivity
import com.createsapp.newsappinmvvm.NewsViewModel
import com.createsapp.newsappinmvvm.R
import com.createsapp.newsappinmvvm.Resource
import com.createsapp.newsappinmvvm.adapter.NewsAdapter


class BreakingNewsFragment : Fragment() {
    private  var TAG = "BreakingNewsFragment"

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    lateinit var rvBreakingNews: RecyclerView
    lateinit var progressbar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel = (activity as NewsActivity).viewModel

        val root = inflater.inflate(R.layout.fragment_breaking_news, container, false)

        rvBreakingNews = root.findViewById(R.id.rvBreakingNews)
        progressbar = root.findViewById(R.id.paginationProgressBar)



        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        setupRecyclerView()

        return root
    }

    private fun hideProgressBar() {
        progressbar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressbar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}