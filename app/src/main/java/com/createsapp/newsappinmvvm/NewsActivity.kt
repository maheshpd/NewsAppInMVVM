package com.createsapp.newsappinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.createsapp.newsappinmvvm.db.ArticelDatabase
import com.createsapp.newsappinmvvm.repository.NewsRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var newsNavHostFragment: Fragment

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment

        val navController = navHostFragment.navController


        bottomNavigationView.setupWithNavController(navController)

        val repository = NewsRepository(ArticelDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(repository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java )
    }
}