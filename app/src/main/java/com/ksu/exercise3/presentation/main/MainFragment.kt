package com.ksu.exercise3.presentation.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ksu.exercise3.R
import com.ksu.exercise3.domain.NewsDomain
import com.ksu.exercise3.presentation.adapter.NewsRecyclerAdapter
import com.ksu.exercise3.presentation.adapter.NewsRecyclerAdapter.ClickItemListener
import com.ksu.exercise3.utils.navigate
import com.ksu.exercise3.utils.navigateToDetail
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), ClickItemListener {

    private val viewModel: MainViewModel by viewModel()
    private var newsRecyclerAdapter: NewsRecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveDataNews.observe(viewLifecycleOwner, ::checkResponseAndShowState)

        newsRecyclerAdapter = NewsRecyclerAdapter(Glide.with(requireContext()), this)
        recycler.adapter = newsRecyclerAdapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler.layoutManager = LinearLayoutManager(context)
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler.layoutManager = GridLayoutManager(context, 2)
        }
        recycler.addItemDecoration(DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL))
        about.setOnClickListener { navigate(R.id.action_mainFragment_to_aboutFragment) }
    }

    override fun clickOnItem(news: NewsDomain) {
        navigateToDetail(R.id.action_mainFragment_to_detailNewsFragment, news)
    }

    private fun checkResponseAndShowState(list: List<NewsDomain>) {
        newsRecyclerAdapter?.replaceItems(list)
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        newsRecyclerAdapter = null
    }
}



