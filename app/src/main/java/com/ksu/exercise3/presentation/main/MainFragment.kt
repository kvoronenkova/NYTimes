package com.ksu.exercise3.presentation.main

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ksu.exercise3.DetailNewsFragment
import com.ksu.exercise3.R
import com.ksu.exercise3.adapter.NewsRecyclerAdapter
import com.ksu.exercise3.adapter.NewsRecyclerAdapter.ClickItemListener
import com.ksu.exercise3.dto.NewsDTO
import com.ksu.exercise3.dto.ResponseDTO
import com.ksu.exercise3.network.Controller
import com.ksu.exercise3.utils.navigate
import com.ksu.exercise3.utils.navigateToDetail
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Response
import java.io.IOException

class MainFragment: Fragment(), ClickItemListener {

    private val viewModel: MainViewModel by viewModels()
    private var newsRecyclerAdapter: NewsRecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveDataNews.observe(viewLifecycleOwner,::checkResponseAndShowState)

        newsRecyclerAdapter = NewsRecyclerAdapter(requireContext(), this, Glide.with(this))
        recycler.adapter = newsRecyclerAdapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recycler.layoutManager = LinearLayoutManager(context)
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler.layoutManager = GridLayoutManager(context, 2)
        }
        recycler.addItemDecoration(DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL))
    }

    override fun clickOnItem(news: NewsDTO?) {
        if (news != null) {
            navigateToDetail(R.id.action_mainFragment_to_detailNewsFragment,news)
        }
    }

    private fun checkResponseAndShowState(response: Response<ResponseDTO>) {
        val body = response?.body()
        newsRecyclerAdapter!!.replaceItems(body!!)
        Log.d("Test1", "replaceitems")
        progressBar!!.visibility = View.GONE
    }
}



